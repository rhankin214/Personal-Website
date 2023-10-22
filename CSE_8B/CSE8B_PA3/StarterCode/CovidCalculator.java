///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PA3Tester.java
// File:               CovidCalculator.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Instructor:         Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////


/**
 * A class that calculates statistics on an array
 * of DataPoints
 * 
 * Bugs: None known
 *
 * @author Rocky Hankin
 */
class CovidCalculator {
    private DataPoint[] points;  // The data points to use in the calculations

    /**
    * Constructs a covid calculator from an array of DataPoints
    */
    public CovidCalculator(DataPoint[] input)
    {
        DataPoint [] newArray = input;
        this.points = new DataPoint [input.length];
        // it makes a new array for points and copies the entries from input
        // into points.
        for(int i = 0; i < input.length; i++)
        {
            this.points[i] = newArray[i];
        }
    }

    /**
    * Adds together the number of cases from each entry in points 
    * with the given date and divides by the number of entries.
    *
    * @param date the date in question
    * @return the average number of cases
    */
    
    public double findAverageCases(String date)
    {
        int sum = 0;
        int numEntries = 0;
        double avg;
        //runs through each entry in points
        for (int i = 0; i< this.points.length; i++)
        {
            //checks if the entry has the right date. Adds it if so.
            //increments the number of entries it has added
            if(this.points[i].getDate().equals(date))
            {
                sum += this.points[i].getTotalCases();
                numEntries++;
            }
        }
        if(numEntries == 0) //prevents divide by 0
        {
            return 0;
        }
        else {
        avg = sum/numEntries; //takes the average
        }
        return avg;
    }

    /**
    * Finds which race had the most infections on a given
    * day and state
    * @param date the date in question
    * @param state the state in question
    * @return the average number of cases
    */
    public String findRaceWithHighestCases(String date, String state)
    {
        
        int currentHighest = 0;
        String currentHighestRace = "";
        int numOfRaces = 8;
        for (int i = 0; i < this.points.length; i++) //runs through every DataPoint
        {
            //identifies the data point for the given date and state
            if(this.points[i].getDate().equals(date) 
                && this.points[i].getState().equals(state) ) 
            {
               for(int j = 0; j < numOfRaces; j++) 
               {
                   //Looks at every #of cases for each race and keeps track of the
                   //highest
                   if(this.points[i].getCasesByRace(j) > currentHighest)
                   {
                       currentHighest = this.points[i].getCasesByRace(j);
                       //every time a new highest is found the corresponding
                       //race is set as the new return string
                       currentHighestRace = this.points[i].getRaceName(j);
                   }
               }
               //at this point the data for that date/state has been found so there's
               //no reason to continue the for loop
               break;
            }
            
        }
        return currentHighestRace;
    }

    /**
    * Counts the number of DataPoints for a given state
    * in points
    *
    * @param state the state in question
    * @return the number of DataPoints for that state
    */
    public int myStat (String state)
    {
        int counter = 0;
        for(int i = 0; i < this.points.length; i++)
        {
            if (this.points[i].getState().equals(state))
            {
                counter++;
            }
        }
        return counter;
    }

}
