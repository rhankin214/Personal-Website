///////////////////////////////////////////////////////////////////////////////
// Main Class File:    CovidConditionTester.java
// File:               CovidCondition.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A class to test the CovidCondition class.
 *
 * Bugs: None known
 *
 * @author Rocky Hankin
 */
public class CovidConditionTester {
    /**
     * Execution point of testing code for the CovidCondition class.
     * DO NOT MODIFY METHOD DECLARATION.
     */
    public static void main(String[] args) {
        CovidCondition cali = new CovidCondition("California", 76);
        CovidCondition oregon = new CovidCondition("Oregon", 23);

        // Checking `cali.getDetails()` is what I expect.
        // Expecting to see "California currently has a daily average of 76 thousand cases".
        // So if the String is anything else, then the test has failed.
        if (!cali.getDetails().equals("California currently has a daily average of 76 thousand cases")) {
            System.out.println("Test 1 Failed!");
            System.out.println(cali.getDetails());
            return; // `return` early so we do not run any subsequent tests. 
        }
        //state1.getDetails()
        //state2.checkTier()
        // Checking `oregon.checkTier()` is "Orange".
        // If it is not "Orange", then the test has failed.
        if (!oregon.checkTier().equals("Orange")) {
            System.out.println("Test 2 Failed!");
            return;
        }
        oregon.updateAvgCases(110);
        if (!oregon.getDetails().equals("Oregon currently has a daily average of 110 thousand cases"))
        {
            System.out.println("test 3 failed");
            return;
        }
        if(!oregon.checkTier().equals("Purple")) {
            System.out.println("test 4 failed");
            return;
        }

        // You should write more tests to ensure proper functionality of your code.
    }
}
