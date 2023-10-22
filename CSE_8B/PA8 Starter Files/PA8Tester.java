///////////////////////////////////////////////////////////////////////////////
// Main Class File:    Assignment8.java
// File:               Item.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Tests written to check if other classes work as intended
 *
 * Bugs: None known
 *
 */
public class PA8Tester {
    public static void main (String[] args) {
        System.out.println("\n-------------------------------------------\n");

        /* PART 1 */
        Pokeball ultrBall = new Pokeball("Ultraball", 10);
        Pokeball grtBall = new Pokeball("Greatball", 5);
        System.out.println(ultrBall.getName() +" "+ ultrBall.getPerformance());
        System.out.println(grtBall.getName() +" "+ grtBall.getPerformance());



        System.out.println("\n-------------------------------------------\n");
        
        /* PART 2 */
        
        Berry RazzBerry = new Berry("Razz Berry", 10, 10);
        Berry AspearBerry = new Berry("Aspear Berry", 15, 5);
        System.out.println(RazzBerry.getName() + " "
        + RazzBerry.getPatienceIncrement() + " "
        + RazzBerry.getSpeedDecrement());
        
        System.out.println(AspearBerry.getName() + " " 
        + AspearBerry.getPatienceIncrement() + " " 
        + AspearBerry.getSpeedDecrement());




        System.out.println("\n-------------------------------------------\n");

        /* PART 3 */
        
        // TODO: Create 2 PalPokemon objects with different names, 
        //       sounds, types, and pokeball names. Then make a function 
        //       call to comesOutFromBall() for each objects.
        PalPokemon Chesnaught = new PalPokemon("Chesnaught", "Brughaugh", 
        "grass/fighting", "pokeball");
        PalPokemon Kricketune = new PalPokemon("Kriketune", "dededeWOOOOP",
        "bug", "net ball");
        System.out.println(Chesnaught.getName() + " " +
        Chesnaught.getType() + " " +
        Chesnaught.getSound() + " " +
        Chesnaught.getPokeballName());

        System.out.println(Kricketune.getName() + " " +
        Kricketune.getType() + " " +
        Kricketune.getSound() + " " +
        Kricketune.getPokeballName());

        Chesnaught.comesOutFromBall();
        Kricketune.comesOutFromBall();
        
        

        System.out.println("\n-------------------------------------------\n");

        /* PART 4 */
        
        // TODO: Create 2 WildPokemon object with different names, sounds, 
        //       types, patience, and speed (They should be different 
        //       pokemons from part3 when testing palPokemons). Then print 
        //       the name, sound, type, patience, speed, and 
        //       timesEscapedFromBall of each WildPokemon on a separate 
        //       line using getter methods. Also make a function call to 
        //       appear() and disappear() for both of the objects
        WildPokemon Girantina = new WildPokemon("Girantina", "Wuuaaaghhh",
        "dragon/ghost", 0, 90);
        WildPokemon Regirock = new WildPokemon("Regirock", "UNUNUN", "rock",
        60, 20);
        System.out.println(Girantina.getName() + " " +
        Girantina.getType() + " " +
        Girantina.getSound() + " " +
        Girantina.getPatience() + " " +
        Girantina.getSpeed() + " ");
        Girantina.appear();
        Girantina.disappear();

        System.out.println(Regirock.getName() + " " +
        Regirock.getType() + " " +
        Regirock.getSound() + " " +
        Regirock.getPatience() + " " +
        Regirock.getSpeed() + " ");
        Regirock.appear();
        Regirock.disappear();


        System.out.println("\n-------------------------------------------\n");

        /* PART 5 */
        
        
        Backpack theBackpack = new Backpack();
        theBackpack.display();
        
        theBackpack.add(ultrBall);
        theBackpack.add(grtBall);
        theBackpack.display();
        // TODO3: Add 2 berries that were created in part 2 
        //       to the backpack and display the backpack
        theBackpack.add(RazzBerry);
        theBackpack.add(AspearBerry);
        theBackpack.display();

        // TODO4: Create an display the empty pokedex
        Pokedex thePokedex = new Pokedex();
        thePokedex.display();

        // TODO5: Add 2 pal pokemons that were created in part 3
        //        to the pokedex and display the backpack
        thePokedex.add(Chesnaught);
        thePokedex.add(Kricketune);
        thePokedex.display();
        // TODO6: Add 2 wild pokemons that were created in part 4
        //        to the pokedex and display the backpack
        thePokedex.add(Girantina);
        thePokedex.add(Regirock);
        thePokedex.display();

    }
}
