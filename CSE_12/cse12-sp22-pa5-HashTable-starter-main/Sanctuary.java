/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources: Hashmap documentation
 * https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
 * 
 * File for managing an animal santuary. Tracks the number of each
 * kind of animal, and allows for adding or removing them via
 * rescue and release method.
 */
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class that implements a hashmap to keep track of animal numbers.
 * Notes that max number of species and max total animals.
 * Allows the current number to be altered as long as they don't
 * exceed those.
 */
public class Sanctuary {
    
    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Default constructor that creates an empty hashmap
     * and sets the maxSpecies and maxAnimals variables.
     * Doesn't allow either value to be negative.
     * @param maxAnimals
     * @param maxSpecies
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if(maxAnimals < 0 || maxSpecies < 0)
        {
            throw new IllegalArgumentException();
        }
        sanctuary = new HashMap<String, Integer>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * Returns the number of the given animal in the sanctuary
     * Different from HashMap.get() because it returns 0 for
     * nonexistent animals instead of null.
     * Used in several other methods instead of .get().
     * @param species the species to look for
     * @return the number of that species.
     */
    public int getNum(String species) {
        if(species == null)
        {
            throw new IllegalArgumentException();
        }
        Integer toReturn = sanctuary.get(species);
        if(toReturn == null)
        {
            return 0;
        }
        return toReturn;
    }
    
    /**
     * Iterates through the list of species and returns the sum total
     * of animals for each one.
     * Used in the rescue method to prevent going over the maximum.
     * @return the total number of animals
     */
    public int getTotalAnimals() {
        int toReturn = 0;
        Iterator<String> keysIterator = sanctuary.keySet().iterator();
        while(keysIterator.hasNext())
        {
            toReturn += getNum(keysIterator.next());
        }
        return toReturn;
    }

    /**
     * @return the number of species in the santuary
     */
    public int getTotalSpecies() {
        return sanctuary.keySet().size();
    }
    /**
     * Adds the given number of the given species to the sanctuary
     * If the species already exists, the current amount is incremented
     * (assuming enough space)^
     * @param species the species of animals to add
     * @param num the number
     * @return the number that couldn't be added due to space
     */
    public int rescue(String species, int num) {
        if(num <= 0 || species == null)
        {
            throw new IllegalArgumentException();
        }
        int currentNum = getNum(species);
        int numToAdd = 0;
        //Checks if it would increase species count and if that would be too many.
        //if so does nothing and returns num
        if(currentNum == 0 && maxSpecies - getTotalSpecies() <= 0)
        {
            return num;
        }
        //checks if it would exceed the max. If so, add the difference between the max
        //and the total.
        else if(getTotalAnimals() + num > maxAnimals)
        {
            numToAdd = maxAnimals - getTotalAnimals();
        }
        //if neither are true, just add all of them
        else 
        {
            numToAdd = num;
        }
        sanctuary.put(species, numToAdd + currentNum);
        //num left behind = num attempted to add - num added
        return num - numToAdd;
    }
    /**
     * released the specified amount of that animal.
     * If that animal is all gone, removes that animal from the
     * hashmap.
     * @param species the species to add
     * @param num their number
     */
    public void release(String species, int num) {
        if(species == null || num <= 0 || num > getNum(species))
        {
            throw new IllegalArgumentException();
        }
        
        if(getNum(species) - num <= 0)
        {
            //this code also runs if the species isn't on the map
            //since getNum() returns 0 if the species doesn't show up
            //remove does nothing if the key doesn't exist so that's ok
            sanctuary.remove(species);
        }
        else
        {
            sanctuary.put(species, getNum(species) - num);
        }
    }
}
