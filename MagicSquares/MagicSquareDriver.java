/**
 * This is the driver class where Magic Squares can be created and or verified.
 * 
 * @author Ryn Rubert
 * CS 221 Summer 2025
 */

public class MagicSquareDriver {
   
    
    /*The first class you need to create is the MagicSquareDriver class. 
    * This is the driver class a user runs to create a MagicSquare object and get output from its operations. 
    * It should include your main method in which command-line arguments are read and validated and a MagicSquare is read from file or created.
    * To operate correctly, MagicSquareDriver should run from the command line as follows:
    
   * java MagicSquareDriver <-check | -create> <filename> < |size>
    
    * where either a -check or -create flag and a filename is required. 
    * The size should only be included when the create flag is used and must be an odd positive integer.
    * The -check flag indicates that your program will determine whether the file called filename contains a magic square and will print out the appropriate message.
    
    * For instance, to determine whether a file called myMatrix.txt contains a valid magic square, you would use the following command-line syntax:
    
    * java MagicSquareDriver -check myMatrix.txt
    
    * and, assuming it contains the 4x4 magic square shown above, the following message should print out on your console:
    
    * The matrix:
    *    16 3 2 13
    *    5 10 11 8
    *    9 6 7 12
    *    4 15 14 1
    * is a magic square.
    
    * If the matrix in the file is not a magic square, your program should print out the same output, except it should state that the matrix "is not a magic square."
    * With the -create flag, your program will create a magic square with the dimensions size x size and will write that square to the file called filename. 
    * Also print the matrix to the console in the same format as shown for the check flag.
    
    * For instance, to create a 5 x 5 magic square that is written to the file called myMatrix.txt, you would use the following command-line syntax:
    
    * java MagicSquareDriver -create myMatrix.txt 5
    
    * and, since the size was odd, your program would write the magic square to the given file in the same file format as shown in sample input files. 
    * Your program should also print the matrix and the result of confirming that it is a magic square to the console.
    * If the size was not odd, no file should be written and a usage message should be printed to the console specifying the requirement that the size be odd.
    * Be sure to validate the command-line arguments. If the arguments are in the wrong order or there are not the expected number of arguments
    */
    public static void main(String[] args) {
       if(args.length == 2)
       {
        MagicSquare checkSquare = new MagicSquare(args[1]);
        checkSquare.toString();
       }
       else if(args.length == 3)
       {
        if(Integer.parseInt(args[2]) % 2 == 0)
        {
            System.out.println("Invalid size. Must be an odd number.");
        }
        else{
        MagicSquare createSquare = new MagicSquare(args[1], Integer.parseInt(args[2]));
        createSquare.toString();}
       }
       else {
        System.out.println("Invalid arguments.");
       }

    }



}
