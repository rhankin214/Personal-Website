///////////////////////////////////////////////////////////////////////////////
// Main Class File:    EasterEggTester.java
// File:               EasterEgg.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;

/**
 * A class that helps strategize 
 * for the EasterEgg race.
 *
 * Bugs: Something is weird about `recursiveSum`...
 *
 * @author Rocky Hankin
 */
public class EasterEgg {
	
    /**
     * Generates an array of scores based 
     * on the elements in the eggs ArrayList
     * 
     * @param eggs - an ArrayList of Strings that denote the type of egg
     */
	public int[] calculateScores(ArrayList<String> eggs) throws Exception {

        int [] scores = new int[eggs.size()];
        for(int i = 0; i < eggs.size(); i++)
        {
            if(eggs.get(i).equals("Chocolate"))
            {
                scores[i] = 10 * (i + 1);
            }
            else if(eggs.get(i).equals("Golden"))
            {
                scores[i] = 20 * (i + 1);
            }
            else
            {
                throw new Exception("Invalid Easter Egg");
            }
        }
        return scores;

	}
    
    /**
     * Recursively calculates the highest score possible
     * 
     * @param scores[] - integer array of all the scores of each egg
     * @param idx - index of first or index of last element of scores
     *  (depending on your implementation.)
     */
    public int maxScore(int[] scores, int idx) {
        int max = 0;
        if(idx >= scores.length)
        {
            return 0;
        }
        if(scores.length - idx <= 2)
        {
            for(int j = idx; j < scores.length; j++)
            {
                if(scores[j] > max)
                {
                    max = scores[j];
                }   
            }
            return max;
        }
        for(int i = idx; i < scores.length-2; i++)
        {
            if(scores[i] + maxScore(scores, i + 2) > max)
            {
                max = scores[i] + maxScore(scores, i + 2);
            }
        }
        return max;
    }	

    /**
     * Recursively calculates the sum of numbers from 1 to the 
     * parameter `num`, but there is an error...
     * 
     * @param num - number up to which the sum is calculated 
     */
    public int recursiveSum(int num){
        if(num == 1)
        {
            return 1;
         }
		// TODO: Fix this method to resolve the infinite loop!
        return num + recursiveSum(num - 1);
    }	
}
