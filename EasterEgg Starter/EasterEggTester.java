///////////////////////////////////////////////////////////////////////////////
// Title:              EasterEggTester.java
// Files:              EasterEgg.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;


/**
 * A class to test the EasterEgg class.
 *
 * Bugs: None known
 *
 * @author Rocky Hankin
 */
class EasterEggTester {

    /**
     * Execution point of testing code for the RaggedArray class.
     * 
     * DO NOT MODIFY METHOD DECLARATION.
     */
	public static void main (String[] args) {
	
        // Example 1
        EasterEgg obj1 = new EasterEgg();

        ArrayList<String> eggs = new ArrayList<String>();

        eggs.add("Chocolate");
        eggs.add("Chocolate");
        eggs.add("Golden");
        eggs.add("Chocolate");
        eggs.add("Golden");
        eggs.add("Chocolate");
        eggs.add("Chocolate");

        // Checking if the returned scores array is as expected.
        int[] scores = new int[eggs.size()];
        try {
            scores = obj1.calculateScores(eggs);
            if(!Arrays.equals(new int[]{10,20,60,40,100,60,70}, scores)) {
    
                System.out.println("TEST 1 FAILED!");         
                System.out.println("Current scores array - ");
    
                for (int i: scores){
                    System.out.print(i + " ");
                }            
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Checking if the calculated maximum score is as expected.
        int total = obj1.maxScore(scores, 0);
        if (total != 240) {
            System.out.println("\nTEST 2 FAILED!");        
            System.out.println("Current max score - " + total);
        };           
        int [] scores2 = new int[]{10, 20, 60, 40, 50, 120};
        int total2 = obj1.maxScore(scores2, 0);
        if(total2 != 190)
        {
            System.out.println("BUMMER DUDE");
            System.out.println("Got " + total2);
        }
        // You should write more tests to ensure complete functionality!
        System.out.println(obj1.recursiveSum(5));
	}
}
