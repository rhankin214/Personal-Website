///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PA3Tester.java
// File:               DataPoint.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Instructor:         Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////


/**
 * The class of a DataPoint object, which tracks the number of COVID
 * cases across different races in a single state
 * Bugs: none known
 *
 * @author Rocky Hankin
 */
public class DataPoint {
    // The number and races represented in this data point.  DO NOT CHANGE.
    public int numRaces = 8;
    private String[] races = {"White", "Black", "LatinX", "Asian", "AIAN",
                              "NHPI", "Multiracial", "Other"};
    
    private String date;
    private String state;
    private int totalCases;
    private int [] casesByRace;
    /**
     * A constructor that simply assigns the parameters to each member variable
     * 
     * @param dateIn the date of the cases
     * @param stateIn the state the cases happened in
     * @param totalCasesIn the total number of Cases
     * @param casesByRaceIn the amound of cases per race
     */
    
    public DataPoint(String dateIn, String stateIn, int totalCasesIn,
                     int[] casesByRaceIn)
    {
        this.date = dateIn;
        this.state = stateIn;
        this.totalCases = totalCasesIn;
        this.casesByRace = casesByRaceIn;
     }
    /**
     * returns the date variable of the DataPoint
     */
    
    public String getDate() {
        
        return this.date;
    }
    /**
    * returns the state variable of the DataPoint
    */
    
    public String getState() {
        return this.state;
    }
    /**
    * returns the number of cases for the race at the given index
    * @param raceIndex is the index of the race in question
    */
    public int getCasesByRace(int raceIndex) {
        
        return this.casesByRace[raceIndex];
    }
    /**
    * returns the total number of cases of this data point
    */

    public int getTotalCases() {
        
        return this.totalCases;
    }

    /**
    * Return the race name associated with the given index in this data point.
    * Preconditions: index is between 0 (inclusive) and numRaces (8) (exclusive)
    *
    * DO NOT CHANGE.
    *
    * @param index The index of the race.
    * @return The name of the race (e.g. "White" or "LatinX")
    */
    public String getRaceName(int index)
    {
        return this.races[index];
    }
}
