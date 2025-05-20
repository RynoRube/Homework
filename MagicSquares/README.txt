***************
* Magic Squares
* CS 221
* 05/15/2025
* Ryn Rubert
****************


OVERVIEW:
This project creates "magic squares" which are nxn matrices that have unique elements in each index of the matrix and all of the columns, rows, and diagonals sum to the same value. 


INCLUDED FILES:

* MagicSquares.java - source file 
* MagicSquareDriver.java - source file
* MagicSquareInterface.java - source file 
* MagicSquaresTest.java - source file 
* README - this file 


BUILDING AND RUNNING:
From the directory containing all of the files in this project, compile the test class with the command:
$ javac MagicSquaresTest.java 

Run the compiled tester with the command:
$ java MagicSquaresTest

The console will report which tests in the program and which tests failed. 


PROGRAM DESIGN:
MagicSquare implements MagicSquareInterface so that it can inherit all of the methods from the interface. 
This allows MagicSquare to both read and write matrices to files.
MagicSquare also tests to see whether a matrix is a magic square or not. 

MagicSquareDriver is where the magic square objects are able to be created and checked. 


TESTING:
The MagicSquaresTester file is set up to test several instances of nxn matrices to determine whether they are magic squares or not. 

The tester is testing both a valid and an invalid matrix for each of the following dimensions: 3x3, 4x4, 5x5, 6x6



DISCUSSION:

I struggled a little to figure out how to read the information from the file. 
I thought I had to use a delimiter and parse through the information based on when there was a space in the document. 
As I tried to use this approach, I was using a lot of while loops that kept making things out of scope. 
I could use the dimension value to use nested while loops to complete this, it would just take a while to think through where everything would go. 
I started to draw out the problem to visualize what the code would be doing. 
As I did this, I realized that we already had a dimension variable which would tell me exactly how many lines there are in the file. 
This meant that I didn't need to use a while loop, because I already knew exactly how many times my scanner needed to go through the file. 
It needed to go through the amount of times the dimension told it to go through. 
That meant that I could use nested for loops instead of a while loop. 
Then I wondered if I even needed the delimiter, so I looked up whether the line.nextInt() method requires being on the same line to work or if it automatically goes to the next line.
I found out that this method automatically goes to the next line, so I could just have my for loops continue to look for the next integer and that should be enough to populate a 2D int array from a file.


I'm so confused in this project. I think my logic is good, but then I run the tester and it says that everything fails. 
I've spent some time trying to find resources to get help for future projects and have spent time figuring out a better study schedule to be successful moving forward. 

