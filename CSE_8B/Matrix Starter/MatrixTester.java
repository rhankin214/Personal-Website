///////////////////////////////////////////////////////////////////////////////
// Title:              Matrix
// Files:              MatrixTester.java, Matrix.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.IOException;

/**
 * A class to test the Matrix class.
 *
 * Bugs: None known
 *
 * @author Rocky Hankin
 */
public class MatrixTester {

    /**
     * Method that checks if two matrices are identical or not.
     *
     * Preconditions: matrix1 and matrix2 each contain an integer 2D array.
     *
     * @param matrix1 First matrix that will be compared with matrix2
     * @param matrix2 Second matrix that will be compared with matrix1
     *
     * @return A boolean value, true if the two matrices are identical, false
     *  otherwise.
     *
     * DO NOT MODIFY METHOD THIS METHOD.
     */
    private static boolean matrixMatch(int[][] matrix1, int[][] matrix2)
    {
        if ((matrix1.length != matrix2.length) || (matrix1[0].length != matrix2[0].length))
            return false;
        for(int i = 0; i < matrix1.length; i++) {
            for(int j = 0; j < matrix1[i].length; j++) {
                if(!(matrix1[i][j] == matrix2[i][j])) {
                    System.out.println(matrix1[i][j] + "!=" + matrix2[i][j]);
                    return false;
                    
                }
            }
        }
        return true;
    }

    /**
     * Execution point of testing code for the Matrix class.
     *
     * DO NOT MODIFY METHOD DECLARATION.
     */
    public static void main(String[] args) throws IOException {
        int[][] input1 =    {{1, 5, 23, 4},
                            {6, 2, 85, 5},
                            {3, 76, 13, 6}};
        Matrix matrix1 = new Matrix(input1);
        int[][] transpose1 = {  {1, 6, 3},
                                {5, 2, 76},
                                {23, 85, 13},
                                {4, 5, 6}};

        String file1 = "input1";
        Matrix matrix2 = new Matrix(file1);
        int[][] input2 = {{1, 7, 3, 6},
                            {2, 4, 2, 8},
                            {5, 3, 4, 9}};
        int[][] transpose2 = { {1, 2, 5},
                                {7, 4, 3},
                                {3, 2, 4},
                                {6, 8, 9}};

        String file2 = "input3";
        Matrix matrix3 = new Matrix(file2);
        int[][] transpose3 = {{2, 3, 2, 3, 7}, 
                          {7, 2, 1, 4, 8}, 
                          {8, 4, 3, 1, 9}};
        // Testing for simple assignment within constructor.
        // We do NOT want a shallow copy - we want a deep copy.
        // As such, if both matrices have the same reference, then this test fails.
        if (matrix1.getMyMatrix() == input1) {
            System.out.println("Test Failed! Shallow copy detected.");
        }
        if(!matrixMatch(matrix1.getMyMatrix(), input1))
            System.out.println("Test failed, matrix1 != input1");
        if(!matrixMatch(matrix2.getMyMatrix(), input2))
            System.out.println("Test failed, matrix2 != input2");

        // Testing `findTranspose()` for `matrix1`.
        // The transpose should be same as transpose1, if not, this test fails.
        if (!(matrixMatch(matrix1.findTranspose(), transpose1))) {
            System.out.println("Test Failed! `matrix1.findTranspose()` returns incorrect o/p.");
        }

        // Testing `findTranspose()` for `matrix2`.
        // The transpose should be same as transpose2, if not, this test fails.
        if (!(matrixMatch(matrix2.findTranspose(), transpose2))) {
            System.out.println("Test Failed! `matrix2.findTranspose()` returns incorrect o/p.");
        }
        if(!matrixMatch(matrix3.findTranspose(), transpose3))
        {
            System.out.println("Test Failed! `matrix3.findTranspose()` returns incorrect o/p.");
        }
        // You should write more tests to ensure constructor functionality (and deep copy),
        // and to test `findTranspose()`.
        //int[][] selfDestruct = matrix3.findTransposeWrong();
        
        Matrix matrix4 = new Matrix(new int[][]{{1, 2, 3},
                                                {2, 1, 4},
                                                {3, 4, 1}});
        int[][] transpose4 = matrix4.findTransposeWrong();
        int[][] expectedTranspose = matrix4.findTranspose();
        if(!matrixMatch(transpose4, expectedTranspose))
        {
            System.out.println("Test Failed! matrix4.findTranspose() returns incorrect");
        }
    }
}