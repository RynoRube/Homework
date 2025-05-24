import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * 
 * 
 * 
 */
public class FormatChecker {
    // instance variables
    private int statedRows; // how many rows the file says should be there
    private int givenRows; // how many rows the file actually had
    private int statedColumns; // how many columns the file says should be there
    private int givenColumns; // how many columns the file actually had
    private double[][] matrixFromFile; // the actual data from the file, needs to hold doubles and since ints are
                                       // smaller than doubles this can hold both
    private boolean firstLineFailure; // determine if the file is even worth reading
    private boolean lineFailure; // determine if a line has incorrect data types

    public FormatChecker(String[] args) throws FileNotFoundException {
        if (args.length == 1 && args[0] != null) {
            String filename = args[0];

            Scanner fileScanner = new Scanner(new File(filename));

            int lineCounter = 0;

            while (fileScanner.hasNext() && firstLineFailure != true && lineFailure != true) {

                String line = fileScanner.nextLine();

                // Scanner lineScanner = new Scanner(line);

                // lineScanner.useDelimiter(" ");

                // loop through entries in the line to determine how many elements there are
                // int elementCounter = 0;
                // while(lineScanner.hasNext())
                // {
                // elementCounter += 1;

                // }

                String[] elements = line.split("\\s+"); // This is another option instead of the delimiter

                int elementCounter = elements.length;

                // variable to count the number of rows
                if (lineCounter == 0) {
                    // if(loop result != 2) {say it's invalid and stop the while loop}
                    if (elementCounter != 2) {
                        System.out.println(
                                "Invalid file. Too many arguments on the first line. Cannot tell how many rows and columns are anticipated.");
                        firstLineFailure = true;

                    }
                    // else {store values in integer variables and do more tests}
                    else {
                        try {
                            statedRows = Integer.parseInt(elements[0]);
                            statedColumns = Integer.parseInt(elements[1]);
                            matrixFromFile = new double[statedRows][statedColumns];
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid file. The values on the first line must both be integers.");
                            firstLineFailure = true;
                        }
                        if (statedRows <= 0 || statedColumns <= 0) {
                            System.out.println("Invalid file. The values on the first line must both be positive.");
                            firstLineFailure = true;
                        }

                    }
                }

                else {
                    for (int i = 0; i < elementCounter; i++) {
                        String currentElement = elements[i];
                        try {
                            double matrixElement = Double.parseDouble(currentElement);
                            matrixFromFile[lineCounter - 1][i] = matrixElement;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid file. Not all data is the correct type.");
                            lineFailure = true;
                        }
                    }
                }

                lineCounter++;

                // lineScanner.close();
            }

            fileScanner.close();

            // look to see if the number of rows and columns are correct
            // determine if file fails or not
            if (firstLineFailure != true && lineFailure != true) {
                givenColumns = matrixFromFile[0].length;
                givenRows = matrixFromFile.length;

                if (statedColumns == givenColumns && statedRows == givenRows && lineFailure != true
                        && firstLineFailure != true) {
                    System.out.println("Valid file!");
                    for (int i = 0; i < matrixFromFile.length; i++) {
                        for (int j = 0; j < matrixFromFile[0].length; j++) {
                            System.out.print(matrixFromFile[i][j] + " ");
                        }
                        System.out.println("");
                    }
                } else {
                    if (statedColumns == givenColumns && statedRows < givenRows) {
                        System.out.println("Invalid file. Too many rows.");
                    } else if (statedColumns == givenColumns && statedRows > givenRows) {
                        System.out.println("Invalid file. Not enough rows.");
                    } else if (statedColumns < givenColumns && statedRows == givenRows) {
                        System.out.println("Invalid file. Too many columns.");
                    } else if (statedColumns > givenColumns && statedRows == givenRows) {
                        System.out.println("Invalid file. Not enough columns.");
                    } else if (statedColumns < givenColumns && statedRows < givenRows) {
                        System.out.println("Invalid file. Too many columns and too many rows.");
                    } else if (statedColumns < givenColumns && statedRows > givenRows) {
                        System.out.println("Invalid file. Too many columns and not enough rows.");
                    } else if (statedColumns > givenColumns && statedRows < givenRows) {
                        System.out.println("Invalid file. Not enough columns and too many rows.");
                    } else if (statedColumns > givenColumns && statedRows > givenRows) {
                        System.out.println("Invalid file. Not enough columns and not enough rows.");
                    }
                }
            }

        }

        else {
            throw new FileNotFoundException();
        }

    }

    public static void main(String[] args) {
        try {
            FormatChecker f1 = new FormatChecker(args);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file input.");
        }
    }
}
