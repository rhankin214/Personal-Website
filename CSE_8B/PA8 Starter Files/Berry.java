///////////////////////////////////////////////////////////////////////////////
// Main Class File:    Assignment8.java
// File:               Berry.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * This is the Berry class that represents berries we use to make pokemon
 * easier to catch
 *
 * Bugs: None known
 *
 * @author Rocky Hankin
 */
public class Berry extends Item{
    
    int patienceIncrement;
    int speedDecrement;
    /**
     * a default constructor that uses item's default contstructor and
     * sets member variables to 0
     */
    public Berry()
    {
        super();
        this.patienceIncrement = 0;
        this.speedDecrement = 0;
    }
    /**
     * A constructor that just assings parameters to member variables
     * @param berryName the name of the berry
     * @param patienceIncrement its patience increment
     * @param speedDecrement its speed decrement
     */
    public Berry(String berryName, int patienceIncrement, int speedDecrement)
    {
        name = berryName;
        this.patienceIncrement = patienceIncrement;
        this.speedDecrement = speedDecrement;
    }
    /**
     * a method that just returns the patience increment
     */
    public int getPatienceIncrement()
    {
        return patienceIncrement;
    }
    /**
     * a method that returns the speed decrement
     * @return
     */
    public int getSpeedDecrement()
    {
        return speedDecrement;
    }
    /**
     * an override for the toString method that returns a string of the format:
     * <this.name>
     * patienceIncrement: <this.patienceIncrement>
     * speedDecrement: <this.speedDecrement>
     */
    @Override
    public String toString()
    {
        return name + "\n" + "patienceIncrement: " + patienceIncrement
            + "\n" + "speedDecrement: " + speedDecrement + "\n";
    }
}
