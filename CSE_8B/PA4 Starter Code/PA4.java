///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              PA4.java
// Files:              input1, input2, MazePoint.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//
//////////////////////////////////////////////////////////////////////////////

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;



public class PA4 {
    
    /**
    * Takes a maze from an input file and returns a matching array
    *
    * @param fileToRead the file the maze is derived from
    * @return a 2d array of MazePoints that are either walls or empty
    */
    public MazePoint[][] readMaze(String fileToRead) throws IOException
    {
        FileInputStream mazeFile = new FileInputStream(fileToRead);
        Scanner scan = new Scanner(mazeFile);
        //takes the first two ints of the file as the dimensions
        MazePoint[][] maze = new MazePoint[scan.nextInt()][scan.nextInt()];
        String nextInput;
        for(int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[i].length; j++)
            {
                nextInput = scan.next(); //takes the next input
                if(nextInput.equals(" ")) //skips the input if it's a space
                {
                    nextInput = scan.next();
                }
                if(nextInput.equals("X")) //makes the MazePoint a wall or blank
                                          // as appropriate
                {
                    maze[i][j] = new MazePoint(true);
                }
                else if(nextInput.equals("-"))
                {
                    maze[i][j] = new MazePoint(false);
                }
            }
        }
        scan.close();
        mazeFile.close();
        return maze;
    }

    
    public void escapeFromMaze(MazePoint [][] maze){
        if(maze == null)
        {
            System.out.println("Error: null maze");
            
        }
        else if(maze.length == 0 || maze[0].length == 0)
        {
            System.out.println("Error: less than one row/column");
        }
        else
        {
            int i = 0;
            int j = 0;
            while (true)
            {
                if(j < maze[i].length - 1)
                {
                    if(maze[i][j + 1].isEmpty())
                    {
                        maze[i][j].setToPath();
                        j += 1;
                    }
                } 
                if(i < maze.length - 1)
                {
                    if(maze[i + 1][j].isEmpty())
                    {
                        maze[i][j].setToPath();
                        i += 1;
                    }
                }
                if(j == maze[i].length - 1 && i == maze.length - 1)
                {
                    maze[i][j].setToPath();
                    break;
                }
            }
        }
    }

    /**
    * Print the maze as a 2D grid.  You should call this method from
    * testRead and testEscape, as well as from main.
    *
    * Precondition: The maze is not null.
    * Postcondition: The maze has been printed and is unmodified.
    *
    * @param maze The maze to be printed.
    */
    private void printMaze(MazePoint[][] maze)
    {
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[i].length; j++)
            {
                maze[i][j].printSymbol();
            }
            System.out.println();
        }
    }

    /**
    * Compare two maze arrays.  Return true if they have .  You should call
    * this method from testRead and testEscape.
    *
    * Precondition: The mazes are not null and have the same size.
    * Postcondition: The mazes are not modified.
    *
    * @param maze1 the first maze to compare.
    * @param maze2 the second maze to compare.
    * @return true if the mazes contain all the same symbols and false otherwise
    */
    private boolean mazeMatch(MazePoint[][] maze1, MazePoint[][] maze2)
    {
        //checks for length difference first to prevent out of bounds error.
        //assumes mazes are rectangular
        if(maze1.length != maze2.length || maze1[0].length != maze2[0].length)
        {
            return false;
        }
        for(int i = 0; i < maze1.length; i++)
        {
            for(int j = 0; j < maze1[i].length; j++)
            {
                if(!maze1[i][j].symbolMatch(maze2[i][j]))
                {
                    return false;
                }
            }
        }
        return true;
    }

    
    public boolean testRead(String fileToRead, MazePoint[][] expected) throws IOException
    {
        MazePoint[][] inputMaze = this.readMaze(fileToRead);
        System.out.println("Input maze:");
        printMaze(inputMaze);
        System.out.println("expected maze:");
        printMaze(expected);
        
        if(!mazeMatch(inputMaze, expected))
        {
            System.out.println("ERROR: the mazes do not match");
            return false;
        }
        return true;
    }

    
    public boolean testEscape(MazePoint[][] maze,
                              MazePoint[][] expectedSolution)
    {
        if(maze == null)
        {
            return false;
        }
        escapeFromMaze(maze);
        if(!mazeMatch(maze, expectedSolution))
        {
            System.out.println("Error: maze does not match expected");
            System.out.println("Expected:");
            printMaze(expectedSolution);
            System.out.println("Solution found:");
            printMaze(maze);
        }
        return mazeMatch(maze, expectedSolution);
    }

    /**
    * Run unit tests.  You will add to this method.
    * Prints a message indicating whether all tests passed or failed.
    * The method will abort on the first failed test.
    * @return true if all unit tests pass.  false if at least one test fails.
    */
    public boolean unitTests() throws IOException {
        // Manually create the expected maze to match file input1
        MazePoint[][] expected = new MazePoint[3][3];
        expected[0][0] = new MazePoint(false);
        expected[0][1] = new MazePoint(false);
        expected[0][2] = new MazePoint(true);
        expected[1][0] = new MazePoint(true);
        expected[1][1] = new MazePoint(false);
        expected[1][2] = new MazePoint(false);
        expected[2][0] = new MazePoint(true);
        expected[2][1] = new MazePoint(true);
        expected[2][2] = new MazePoint(false);

        if (!this.testRead("input1", expected)) {
            System.out.println("Read test 1 failed.");
            return false;
        }
        else {
          System.out.println("Read test 1 passed!");
        }
        
        MazePoint[][] expected2 = new MazePoint[4][8];
        expected2[0][0] = new MazePoint(false);
        expected2[0][1] = new MazePoint(true);
        expected2[0][2] = new MazePoint(true);
        expected2[0][3] = new MazePoint(false);
        expected2[0][4] = new MazePoint(true);
        expected2[0][5] = new MazePoint(true);
        expected2[0][6] = new MazePoint(true);
        expected2[0][7] = new MazePoint(true);

        expected2[1][0] = new MazePoint(false);
        expected2[1][1] = new MazePoint(false);
        expected2[1][2] = new MazePoint(false);
        expected2[1][3] = new MazePoint(false);
        expected2[1][4] = new MazePoint(true);
        expected2[1][5] = new MazePoint(false);
        expected2[1][6] = new MazePoint(true);
        expected2[1][7] = new MazePoint(false);

        expected2[2][0] = new MazePoint(true);
        expected2[2][1] = new MazePoint(true);
        expected2[2][2] = new MazePoint(true);
        expected2[2][3] = new MazePoint(false);
        expected2[2][4] = new MazePoint(true);
        expected2[2][5] = new MazePoint(true);
        expected2[2][6] = new MazePoint(true);
        expected2[2][7] = new MazePoint(false);

        expected2[3][0] = new MazePoint(false);
        expected2[3][1] = new MazePoint(true);
        expected2[3][2] = new MazePoint(false);
        expected2[3][3] = new MazePoint(false);
        expected2[3][4] = new MazePoint(false);
        expected2[3][5] = new MazePoint(false);
        expected2[3][6] = new MazePoint(false);
        expected2[3][7] = new MazePoint(false);
        if (!this.testRead("input2", expected2)) {
            System.out.println("Read test 2 failed.");
            return false;
        }
        else {
          System.out.println("Read test 2 passed!");
        }

        MazePoint [][] expected3 = new MazePoint[2][3];
        expected3 [0][0] = new MazePoint(false);
        expected3 [0][1] = new MazePoint(true);
        expected3 [0][2] = new MazePoint(false);
        expected3 [1][0] = new MazePoint(false);
        expected3 [1][1] = new MazePoint(false);
        expected3 [1][2] = new MazePoint(false);

        if(!this.testRead("input3", expected3))
        {
            System.out.println("Read rest 3 failed.");
            return false;
        }
        else {
            System.out.println("Read test 3 passed!");
        }

        
        // At this point readMaze is working, so we can use it.
        MazePoint[][] maze1 = this.readMaze("input1");

        // Modify the expected maze from above to have the path
        expected[0][0].setToPath();
        expected[0][1].setToPath();
        expected[1][1].setToPath();
        expected[1][2].setToPath();
        expected[2][2].setToPath();

        if (!this.testEscape(maze1, expected)) {
            System.out.println("Escape test 1 failed.");
            return false;
        }
        else {
          System.out.println("Escape test 1 passed!");
        }
        MazePoint[][] maze2 = this.readMaze("input3");
        expected3[0][0].setToPath();
        expected3[1][0].setToPath();
        expected3[1][1].setToPath();
        expected3[1][2].setToPath();
        if(!this.testEscape(maze2, expected3)) {
            System.out.println("Escape test 2 failed.");
            return false;
        }
        else {
            System.out.println("Escape test 2 passed!");
        }
        MazePoint[][] nullMaze = null;
        if(!this.testEscape(nullMaze, expected))
        {
            System.out.println("Null test passed!");
        }
        escapeFromMaze(nullMaze);
        return true;
    }


    
    public static void main(String[] args) throws IOException
    {
        PA4 solver = new PA4();
        Scanner userScnr = new Scanner(System.in);
        // Perform unitTest first
        if(solver.unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("Failed test.\n");
            return;
        }
        String userInput = "";
        MazePoint [][] maze;
        while(!userInput.equals("end"))
        {
            System.out.println("Enter which maze you'd like to solve");
            userInput = userScnr.nextLine();
            maze = solver.readMaze(userInput);
            solver.escapeFromMaze(maze);
            solver.printMaze(maze);
        }
        userScnr.close();
        

    }
}
