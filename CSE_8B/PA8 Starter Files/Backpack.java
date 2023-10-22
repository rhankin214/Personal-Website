///////////////////////////////////////////////////////////////////////////////
// Main Class File:    Assignment8.java
// File:               Backpack.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;

/**
 * This is Backpack class that you can add items which are berries and
 * pokeballs. You can display your backpack with display() method
 *
 * Bugs: None known
 * @author : your name
 *
 */

public class Backpack {

    // ArrayList for items (berries, pokeballs)
    private ArrayList<Item> myItems;

    /**
     * Creates a resizable-array ArrayLists of Items
     * 
     */
    public Backpack() {
        myItems = new ArrayList<>();
    }

    /**
     * Adds the item to myItems
     * 
     */
    public void add(Item item) {
        myItems.add(item);
    }

    /**
     * Displays the elements in the myItems ArrayList
     * 
     */
    public void display() {
        System.out.println("Items in backpack:");
        System.out.println();
        if(myItems.size() == 0)
        {
            System.out.println("No items to display");
        }
        else {
            for(int i = 0; i < myItems.size(); i++)
            {
                System.out.println(myItems.get(i).toString());
            }
        }
    }
}
