///////////////////////////////////////////////////////////////////////////////
// Main Class File:    Assignment8.java
// File:               Pokeball.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This is the pokeball class that represents the balls you use to catch the
 * Pokemon
 *
 * Bugs: None known
 *
 * @author Rocky Hankin
 */
public class Pokeball extends Item{

    private int performance;
    
    /**
     * Default contructor that sets name to "item" and performance to 0
     */
    public Pokeball()
    {
        super();
        performance = 0;
    }
    /**
     * A simple contstructor that assigns parameters to member variables
     * @param pokeballName the name of the new pokeball
     * @param performance the performance
     */
    public Pokeball(String pokeballName, int performance)
    {
        name = pokeballName;
        this.performance = performance;
    }
    /**
     * A getter method that returns the performance
     */
    public int getPerformance()
    {
        return performance;
    }
    /**
     * An override for the toString method that returns a string in the
     * following format:
     * <this.name>
     * performance: <this.performance>
     */
    @Override
    public String toString()
    {
        return name + "\nperformance: " + performance + "\n";
    }
}