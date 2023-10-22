
///////////////////////////////////////////////////////////////////////////////
// Main Class File:    BarChartRacer.java
// File:               Bar.java
// Quarter:            CSE 8B Winter 2022
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Implementation to create a single Bar in a BarChart
 *
 * Bugs: None known
 *
 * @author Rocky Hankin
 */

public class Bar implements Comparable<Bar> {
    private String name;
    private int value;
    private String category;
    
    /**
     * A basic construct that just assigns the parameters to member variables
     * @param name the String to assign to name
     * @param value the int to assign to value
     * @param category the String to assign to categroy
     */
    public Bar(String name, int value, String category) {
        this.name = name;
        this.value = value;
        this.category = category;
        
    }
    
    /**
     * A helper method that returns the name
     * @return the name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * A helper method that returns the name
     * @return the value of the object
     */
    public int getValue() {
        return value;
    }

    /**
     * A helper method that returns the name
     * @return the category of the object
     */
    public String getCategory() {
        return category;
    }

    /**
     * Compares the value of this bar to another bar
     * @param that the bar this one will be compared to
     * @return 1 if this bar's value is greater, -1 if lower, 0 if equal
     */
    public int compareTo(Bar that) {
        if (this.value > that.getValue())
        {
            return 1;
        }
        else if(this.value < that.getValue())
        {
            return -1;
        }
        else {
            return 0;
        }
    }
}
