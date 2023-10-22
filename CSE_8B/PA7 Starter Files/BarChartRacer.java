///////////////////////////////////////////////////////////////////////////////
// Main Class File:    BarChartRacer.java
// File:               BarChartRacer.java
// Quarter:            CSE 8B Winter 2022
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Driver to create a video/multiple BarCharts successively and display
 * them to the user.
 * Run command: java BarChartRacer <input_file> <num entries per chart>
 * For example: java BarChartRacer cities-usa.txt 10
 *
 * Bugs: None known
 *
 * @author Your name
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.io.*;

public class BarChartRacer {

    // a tracker for the year/date of the current chunk
    private static String caption;

    /**
     * constructs an array of bars based on input data
     * @param in the scanner for the relevant input data
     * @return an array of the new bars
     */
    private static ArrayList<Bar> getBars(Scanner in) {
        ArrayList<Bar> returnArray = new ArrayList<Bar>();
        int numChunks = in.nextInt();
        in.nextLine();
        String [] currentLineArray; 
        Bar nextBar;
        for(int i = 0; i < numChunks; i++)
        {
            currentLineArray = in.nextLine().split(",");
            caption = currentLineArray[0];
            nextBar = new Bar(currentLineArray[1] + ", " + currentLineArray[2], 
                    Integer.parseInt(currentLineArray[3]), currentLineArray[4]);
            returnArray.add(nextBar);
        }
        return returnArray;
    }

    /**
     * 
     * @param chart
     * @param bars
     * @param numEntries
     */
    private static void createChart(BarChart chart, ArrayList<Bar> bars, int numEntries) {
        if(bars.size() < numEntries)
        {
            System.out.println("Error: There's not that many data points");
            return;
        }
        
        for (int i = 1; i <= numEntries; i++)
        {
            chart.add(bars.get(bars.size()-i).getName(), 
                    bars.get(bars.size()-i).getValue(), 100);
        }
        chart.setCaption(caption);
    }

    /**
     * Prompts the user to enter a filename and runs a bar chart racer based
     * on the data in the file
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Enter the file to use: ");
        FileInputStream userInputFile = new FileInputStream(userInput.next());
        
        System.out.println("You'd like to focus on the top _ entries: ");
        int numEntries = userInput.nextInt();
        Scanner userFileScanner = new Scanner(userInputFile);

        BarChart chart = new BarChart(userFileScanner.nextLine(),userFileScanner.nextLine(),
                            userFileScanner.nextLine());

        // do not modify these two lines
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();

        ArrayList<Bar> bars;
        
        // For each chunk of data, create a BarChart object and draw it
        while(userFileScanner.hasNext())
        {
            bars = getBars(userFileScanner);
            Collections.sort(bars);
            createChart(chart, bars, numEntries);
            
            
            // do not modify these lines
            // clear and redraw chart (you need to do this for every graph you create)
            chart.draw(); // here chart is your BarChart object
            StdDraw.show();
            StdDraw.pause(1);
            chart.reset(); // here chart is your BarChart object
        }
        userInput.close();
    }
}
