///////////////////////////////////////////////////////////////////////////////
// Main Class File:    Assignment8.java
// File:               WildPokemon.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This is wild Pokemon class. It extends pokemon and has member variables
 * to do with the catching process
 *
 * Bugs: None known
 *
 * @author Rocky Hankin
 */
public class WildPokemon extends Pokemon{
    private int patience;
    private int speed;
    private int timesEscapedFromBall;
    /**
     * default constructor. Calls parent class's default and sets member variables
     */
    public WildPokemon()
    {
        super();
        patience = 100;
        speed = 0;
        timesEscapedFromBall = 0;
    }
    /**
     * simple constructor that assigns parameters to member variables
     * @param pokemonName the pokemon's name
     * @param pokemonSound its sound
     * @param pokemonType its type
     * @param patienceIn its patience
     * @param speedIn its speed
     */
    public WildPokemon (String pokemonName, String pokemonSound, String pokemonType, 
        int patienceIn, int speedIn)
    {
        super(pokemonName, pokemonSound, pokemonType);
        patience = patienceIn;
        speed = speedIn;
        timesEscapedFromBall = 0;
    }
    /**
     * @return patience
     */
    public int getPatience()
    {
        return patience;
    }
    /**
     * @return speed
     */
    public int getSpeed()
    {
        return speed;
    }
    /**
     * @return times escaped from ball
     */
    public int getTimesEscapedFromBall()
    {
        return timesEscapedFromBall;
    }
    public void setPatience(int newSpeed)
    {
        patience = newSpeed;
    }
    public void setSpeed(int newPatience)
    {
        speed = newPatience;
    }
    public void incrementTimeEscapedFromBall()
    {
        timesEscapedFromBall += 1;
    }
    /**
     * prints the text for when the pokemon is encountered
     */
    public void appear()
    {
        System.out.println("You encounterd a wild " + name + "!");
        speak();
    }
    /**
     * returns true if the pokemon has met the criteria to disappear and
     * prints text to indicate that
     * returns false otherwise
     * @return true if patience is 0 or less or it has escaped a ball 3+ times
     */
    public boolean disappear()
    {
        if(patience <= 0 || timesEscapedFromBall > 3)
        {
            System.out.println(name + " disappears...");
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * returns a string in the format
     * <name>
     * type: <type>
     * patience: <patience>
     * speed: <speed>
     * timesEscapedFromBall: <timesEscapedFromBall>
     */
    @Override
    public String toString()
    {
        return name + ", WildPokemon" +"\ntype: " + type + "\npatience: " + patience
        +"\nspeed: " + speed + "\ntimeEscapedFromBall: " + timesEscapedFromBall + "\n";
    }
}
