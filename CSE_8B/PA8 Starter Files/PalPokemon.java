///////////////////////////////////////////////////////////////////////////////
// Main Class File:    Assignment8.java
// File:               PalPokemon.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * The class for pokemon that have been caught by the player
 *
 * Bugs: None Known
 *
 * @author Rocky Hankin
 */
public class PalPokemon extends Pokemon {
    private String pokeballName;
    /**
     * A default constructor that calls the parent's default and sets
     * pokeball name to undefined.
     */
    public PalPokemon()
    {
        super();
        pokeballName = "undefined";
    }
    /**
     * A constructor that simply assigns parameters to member variables
     * @param pokemonName the pokemon's name
     * @param pokemonSound the sound it makes
     * @param pokemonType its type
     * @param pokeballName the name of the pokeball that caught it
     */
    public PalPokemon(String pokemonName, String pokemonSound, String pokemonType, 
            String pokeballName)
    {
        super(pokemonName, pokemonSound, pokemonType);
        this.pokeballName = pokeballName;
        
    }
    /**
     * @return the pokeball name
     */
    public String getPokeballName()
    {
        return pokeballName;
    }
    /**
     * The text that's printed when the pokemon comes out
     */
    public void comesOutFromBall()
    {
        System.out.println(name + " in " + pokeballName + ", " + type + 
        " type pokemon");
        speak();
    }
    @Override
    public String toString()
    {
        return name + ", PalPokemon" + 
        "\npokeballName: " + pokeballName + "\ntype: " + type + "\n";
    }
}
