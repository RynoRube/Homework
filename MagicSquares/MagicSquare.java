import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

/**
 * This class creates custom Magic Square objects in java.
 * Magic squares are nxn matrices represented by 2D int arrays.
 * Each entry in the array must be unique and all numbers 1-n^2 must be included in the array.
 * All rows, columns, and diagonals must add to the same value.
 * 
 * @author Ryn Rubert
 * CS 221 Summer 2025
 */

 public class MagicSquare implements MagicSquareInterface{
    // instance variables
    private int[][] matrix;
    private boolean isMagicSquare;


    // Constructors
    /* Two constructors are required.
    * 
    * The first constructor takes a filename, only,
    * and attempts to read that file. If the file
    * cannot be opened or is not in the correct
    * format, a FileNotFoundException should be thrown.
    *   public MagicSquare(String filename) throws FileNotFoundException
    * This constructor is required to call a private
    * utility method
    *   private int[][] readMatrix(String filename) throws FileNotFoundException
    * to open and read the file into a 2D int array.
     */

     /**
      * Constructor to create a magic square from information found in a file
      * @param String filename to be able to read the file
      * @exception FileNotFoundException if there's an error accessing the file
      */
   public MagicSquare(String filename) throws FileNotFoundException
   {
    System.out.println(filename);
   // try {
    this.matrix = readMatrix(filename);
    this.isMagicSquare = isMagicSquare();
   // }
    // catch (FileNotFoundException bummer) {
    //     System.out.println("File not found");
    // }
    }
 
  
    /* The second constructor takes a filename and
    * an int for the dimension N of a new NxN magic
    * square. A generated matrix should be written
    * in the required format to a file with the given
     * name.
    *   public MagicSquare(String filename, int dimension) throws IOException
    * This constructor is required to call a private
    * utility method
    *   private void writeMatrix(int[][] matrix, String filename) throws IOException
    * to write the matrix to the file.
    */
    /**
     * This method creates a magic square and writes it to a file using the following information:
     * @param filename String name of the file
     * @param dimension int dimension of a new matrix to create
     * @exception IOException
     */
    public MagicSquare(String filename, int dimension) throws IOException
    {
        try{
        
        this.matrix = new int[dimension][dimension];

        int row = dimension - 1;
        int col = dimension/2;

        int oldRow;
        int oldCol;

        for(int i = 1; i < dimension*dimension; i++) 
        {
            matrix[row][col] = 1;
            oldRow = row;
            oldCol = col;
            row++;
            col++;
            if(row == dimension)
            {row = 0;}
            if(col == dimension)
            {col = 0;}
            if(matrix[row][col] != 0)
            {
                row = oldRow;
                col = oldCol;
                row--;
            }
        }

        writeMatrix(matrix, filename);
        this.isMagicSquare = isMagicSquare();
    }
    catch(IOException bummer)
    {
        System.out.println("Error making Magic Square.");
    }
       
    }

    // Methods
    /**
     * This method reads a file and converts the information in the file into a matrix represented by a 2D int array
     * @param filename String name of file to access
     * @exception FileNotFoundException
     * @return int[][] with a matrix read from a file
     */
    private int[][] readMatrix(String filename) throws FileNotFoundException
    {
       
        Scanner scannyScans = new Scanner(new File(filename));

        int dimension = scannyScans.nextInt();

        int[][] matrixFromFile = new int[dimension][dimension];

      for(int i = 0; i < dimension; i++) 
      {
        for(int j = 0; j < dimension; j++)
        {
            matrixFromFile[i][j] = scannyScans.nextInt();
        }
      }
        scannyScans.close();
       return matrixFromFile;
    }

    /**
     * A method used to write a matrix represented by a 2D int array to a file
     * @param matrix 2D int array representation of an nxn matrix
     * @param filename String of the name of the file
     * @exception IOException 
     */
    private void writeMatrix(int[][] matrix, String filename) throws IOException
    {
        File newFile = new File(filename);
        PrintWriter lilWriter = new PrintWriter(new FileWriter(newFile));

        lilWriter.println(matrix.length);

        for(int i = 0; i < matrix.length; i++)
        {
            for(int j= 0; j < matrix.length; j++)
            {
                lilWriter.print(matrix[i][j] + " ");
            }
        }


        lilWriter.close();


    }

    /* Methods required for a class that validates
    * or creates magic squares in files with format:
    *   dimensionN
    *   v1 v2 ... vn
    *   ...
    *   vn1 vn2 ... vnn
    * e.g.
    *   3
    *   4 9 2
    *   3 5 7
    *   8 1 6
    */

    /*
	 * Evaluate the matrix (whether read from file or
	 * generated) and return a boolean verdict of
	 * whether the matrix is a magic square.
	 * 
	 * @return true if matrix is a magic square, else false
	 */
    @Override
    public boolean isMagicSquare() 
    {   getMatrix();
        if(matrix.length == matrix[0].length) {
            int[] elementTest = new int[(matrix.length)^2];
            for(int i = 1; i < (matrix.length)*(matrix.length); i++) 
            {
                elementTest[i] = i;
            }
            int[] orderedMatrix = new int[(matrix.length)^2];
            
         for(int value = 1; value <= (matrix.length)*(matrix.length); value++)
         {
           int foundValueCount = 0;
            
           for(int i = 0; i < matrix.length; i++)
           {
            for(int j = 0; j < matrix.length; j++)
            {
                if(matrix[i][j] == value) {
                    foundValueCount += 1;
                }
            }
           }
           if(foundValueCount != 1) 
           {isMagicSquare = false;}
           else if(foundValueCount == 1)
           {orderedMatrix[value - 1] = value;}
         }



        if(Arrays.equals(elementTest, orderedMatrix)) 
        {
            int[] sums = new int[((matrix.length)*2)+2];
            for(int i = 0; i < matrix.length; i++)
            {
                for(int j = 0; j < matrix.length; j++) 
                {
                    int rowSum = 0;
                    rowSum += matrix[i][j];
                    sums[i] = rowSum;
                }
            }
            for(int i = 0; i < matrix.length; i++)
            {
                for(int j = 0; j < matrix.length; j++)
                {
                    int columnSum = 0;
                    columnSum += matrix[j][i];
                    sums[i + matrix.length] = columnSum;
                }
            }
            int diagonalSum1 = 0;
            for(int i = 0; i < matrix.length; i++)
            {
                for(int j = 0; j < matrix.length; j++)
                {
                    if(i == j) {
                        diagonalSum1 += matrix[i][j];
                    }
                }
            }
            sums[((matrix.length)*2) + 1] = diagonalSum1;

            int diagonalSum2 = 0;
            for(int i = 0; i < matrix.length; i++) 
            {
                for(int j = matrix.length; j >= 0; j--)
                {
                    diagonalSum2 += matrix[i][j];
                    i++;
                }
            }
            sums[((matrix.length)*2) + 2] = diagonalSum2;

          int equalSumsCounter = 0;
          for(int i = 1; i < sums.length; i++)
          {
            if(sums[i] == sums[i-1])
            {
                equalSumsCounter += 1;
            }
          }
          if(equalSumsCounter == sums.length - 1)
            {
            isMagicSquare = true;
          }
          else {isMagicSquare = false;}
        }
        else{isMagicSquare = false;}

        }
        else {isMagicSquare = false;}
        
        return isMagicSquare;
    }



    /*
	 * Return a copy of the matrix (whether read from file 
	 * or generated) as a 2D array of ints. Be sure this
	 * method does not compromise encapsulation.
	 * 
	 * @return 2D array of ints that may or may not be a valid magic square
	 */
    @Override
    public int[][] getMatrix() 
    {
        int[][] matrixCopy = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) 
        {
            for( int j = 0; j < matrix.length; j++)
            {
                matrixCopy[i][j] = matrix[i][j];
            }
        }

        return matrixCopy;
    }


	/*
	 * Return a string formatted as in these examples:
	 *   The matrix
	 *     4 9 2
	 *     3 5 7
	 *     8 1 6
	 *   is a magic square.
	 *  or
	 *    The matrix
	 *      5 3 7
	 *      9 4 2
	 *      1 6 5
	 *    is not a magic square.
	 *    
	 * @return formatted string showing the matrix and whether it is a valid magic square
	 */
    @Override
	public String toString() 
    {
        String result = "";

        result += "The matrix:";
        result += "\n";

        for(int i = 0; i < matrix.length; i ++)
        {
            for(int j = 0; j < matrix.length; j++)
            {
                result += matrix[i][j] + " ";
            }
            result += "\n";
        }

        if(isMagicSquare()) 
        {
            result += "is a magic square.";
        }
        else {result += "is not a magic square.";}

        return result;
    }


 }