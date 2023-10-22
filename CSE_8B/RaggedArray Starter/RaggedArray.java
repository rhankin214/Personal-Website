///////////////////////////////////////////////////////////////////////////////
// Main Class File:    RaggedArrayTester.java
// File:               RaggedArray.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileInputStream;

/**
 * A class that maintains a 2D array. The 2D array could be ragged (i.e. not
 * neccesarily a rectangular array).
 *
 * Bugs: Something is wrong with `findMaxWrong()`...
 *
 * @author (YOUR NAME)
 */
public class RaggedArray {
    // DO NOT CHANGE THE CODE FOR THE PRIVATE MEMBER VARIABLE.
    private int[][] myArray;

    /**
     * Constructor to initialize the RaggedArray via an integer array.
     * DO NOT MODIFY THE CONSTRUCTOR DECLARATION.
     *
     * @param arrayIn the array to DEEP copy into myArray
     */
    public RaggedArray(int[][] arrayIn) {
        myArray = new int[arrayIn.length][];
        for(int i = 0; i < arrayIn.length; i++)
        {
            myArray[i] = new int[arrayIn[i].length];
        }
        for(int r = 0; r < arrayIn.length; r++)
        {
            for(int c = 0; c < arrayIn[r].length; c++)
            {
                myArray[r][c] = arrayIn[r][c];
            }
        }
    }

    /**
     * Constructor to initialize the RaggedArray via an input file.
     * 
     * NOTE: If initializing RaggedArray with an input file, then
     *  myArray is guaranteed to be a rectangular 2D array.
     * 
     * DO NOT MODIFY THE CONSTRUCTOR DECLARATION.
     *
     * @param inputPath the file to read and create for myArray
     */
    public RaggedArray(String inputPath) throws IOException {
        FileInputStream fileReader = new FileInputStream(inputPath);
        Scanner scnr = new Scanner(fileReader);
        myArray = new int[scnr.nextInt()][scnr.nextInt()];
        for(int r = 0; r < myArray.length; r++)
        {
            for(int c = 0; c < myArray.length; c++)
            {
                myArray[r][c] = scnr.nextInt();
            }
        }
        fileReader.close();
        scnr.close();
    }

    /**
     * Getter method for returning `myArray`.
     * 
     * DO NOT MODIFY THIS METHOD.
     *
     * @return this.myArray
     */
    public int[][] getMyArray() {
        return this.myArray;
    }
    
    /**
     * Finds the maximum element in myArray, but there is an error...
     * Can you find the error?
     * 
     * DO NOT MODIFY THIS METHOD.
     *
     * @return an integer that represents the maximum element from `myArray`
     */
    public int findMaxWrong() {
        int max = 0;
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray.length; j++) {
                if (myArray[i][j] > max) {
                    max = myArray[i][j];
                }
            }
        }
        return max;
    }

    /**
     * Finds the maximum element in myArray, but the error should be fixed.
     * Use `findMaxWrong()` as template code, and fix the error.
     * 
     * DO NOT MODIFY METHOD DECLARATION.
     *
     * @return an integer that represents the maximum element from `myArray`
     */
    public int findMax() {
        int max = 0;
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                if (myArray[i][j] > max) {
                    max = myArray[i][j];
                }
            }
        }
        return max;
    }
}