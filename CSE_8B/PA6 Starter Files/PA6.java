import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              PA6.java
// Files:              RedditDataPoint.java, Reddit_Data.csv
// Quarter:            CSE 8B, Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//
///////////////////////////////////////////////////////////////////////////////
/*
 * Class that deals with creating a RedditDataPoint array from a file
 * and printing information about the data
 */
public class PA6 {
    // PROVIDED CONSTANT
    private static final String DELIMITER = ","; // CSV file delimiter
    private static final int NAME_INDEX = 0; // A
    private static final int TEXT_INDEX = 114; // DK
    private static final int LEX_LIWC_I_INDEX = 19; // T
    private static final int LEX_LIWC_WE_INDEX = 20; // U
    private static final int LEX_LIWC_SHEHE_INDEX = 22; // W

    /*
     * creates and returns an array of RedditDataPoints based on information in a file.
     */
    public static ArrayList<RedditDataPoint> readData(String fileName, ArrayList<String> categories)
            throws IOException {
        // Create new ArrayList to store the data from the file
        ArrayList<RedditDataPoint> data = new ArrayList<RedditDataPoint>();
        ArrayList<Object> obList = new ArrayList<Object>();
        obList.add("Hi");
        obList.add(15);
        FileInputStream fileReader = new FileInputStream(fileName);
        Scanner scnr = new Scanner(fileReader);
        String name = "";
        String text = "";
        double lex_liwc_i = 0.0;
        double lex_liwc_shehe = 0.0;
        double lex_liwc_we = 0.0;
        String currentLine = "";
        String [] currentLineArray;
        scnr.nextLine();
        while(scnr.hasNext())
        {
            currentLine = scnr.nextLine();
            
            currentLineArray = currentLine.split(DELIMITER);
            name = currentLineArray[NAME_INDEX];
            if(!categories.contains(name))
            {
                categories.add(name);
            }                   
            text = currentLineArray[TEXT_INDEX];
            lex_liwc_i = Double.parseDouble(currentLineArray[LEX_LIWC_I_INDEX]);
            lex_liwc_we = Double.parseDouble(currentLineArray[LEX_LIWC_WE_INDEX]);
            lex_liwc_shehe = Double.parseDouble(currentLineArray[LEX_LIWC_SHEHE_INDEX]);
            
            data.add(new RedditDataPoint(name, text, lex_liwc_i, lex_liwc_we, lex_liwc_shehe));
        }
        scnr.close();
        return data;
    }

    /*
     * prints the number of posts of each category in the data array
     * @param data is the array of reddit data points
     * @param categories is the list of categories in data
     */
    public static void printTotalPosts(ArrayList<RedditDataPoint> data, ArrayList<String> categories) {
        int numInCategory = 0;
        for(int i = 0; i < categories.size(); i++)
        {
            for(int j = 0; j < data.size(); j++)
            {
                if(data.get(j).getName().equals(categories.get(i)))
                {
                    numInCategory++;
                }
            }
            System.out.println("r/" + categories.get(i) + " total posts: " + numInCategory);
            numInCategory = 0;
        }
        System.out.println();
    }

    /*
     * prints the number of posts that contains "I" on each subreddit
     * @param data is the array of reddit data points
     * @param categories is the list of categories in data
     */
    public static void printTotalLexLiwcI(ArrayList<RedditDataPoint> data, ArrayList<String> categories) {
        int numWithI = 0;
        for(int i = 0; i < categories.size(); i++)
        {
            for(int j = 0; j < data.size(); j++)
            {
                if(data.get(j).getName().equals(categories.get(i))
                    && data.get(j).getLexLiwcI() > 0)
                {
                    numWithI++;
                }
            }
            System.out.println("r/" + categories.get(i) + " total lex_liwc_i: " + numWithI);
            numWithI = 0;
        }
        System.out.println();
    }

    /*
     * prints the number of posts that contain "we" on each subreddit.
     * @param data is the array of reddit data points
     * @param categories is the list of categories in data
     */
    public static void printTotalLexLiwcWe(ArrayList<RedditDataPoint> data, ArrayList<String> categories) {
        int numWithWe = 0;
        for(int i = 0; i < categories.size(); i++)
        {
            for(int j = 0; j < data.size(); j++)
            {
                if(data.get(j).getName().equals(categories.get(i))
                    && data.get(j).getLexLiwcWe() > 0)
                {
                    numWithWe++;
                }
            }
            System.out.println("r/" + categories.get(i) + " total lex_liwc_we: " + numWithWe);
            numWithWe = 0;
        }
        System.out.println();
    }
    

    /*
     * Prints the number of posts with "he/she" in them for each subreddit
     * @param data is the array of reddit data points
     * @param categories is the list of categories in data
     */
    public static void printTotalLexLiwcShehe(ArrayList<RedditDataPoint> data, ArrayList<String> categories) {
        int numWithSheHe = 0;
        for(int i = 0; i < categories.size(); i++)
        {
            for(int j = 0; j < data.size(); j++)
            {
                if(data.get(j).getName().equals(categories.get(i))
                    && data.get(j).getLexLiwcShehe() > 0)
                {
                    numWithSheHe++;
                }
            }
            System.out.println("r/" + categories.get(i) + " total lex_liwc_shehe: " + numWithSheHe);
            numWithSheHe = 0;
        }
        System.out.println();
    }

    /*
     * Starpoint OPTIONAL
     */
    public static void printStarPointAverage(ArrayList<RedditDataPoint> data, ArrayList<String> categories) {

    }

    /*
     * TODO: Add header
     */
    public static void main(String args[]) throws IOException {
        ArrayList<String> categories = new ArrayList<String>();
        ArrayList<RedditDataPoint> RedditData = readData("Reddit_Data.csv", categories);
        printTotalPosts(RedditData, categories);
        printTotalLexLiwcI(RedditData, categories);
        printTotalLexLiwcWe(RedditData, categories);
        printTotalLexLiwcShehe(RedditData, categories);
    }

}