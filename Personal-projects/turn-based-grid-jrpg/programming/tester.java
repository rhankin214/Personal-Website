import java.util.ArrayList;

public class tester {

    /*
     * Things to code
     * enemy movement/behavior
     * healing
     * game over
     * individual character kits
     */

    public static void main (String [] args)
    {
        PlayerCharacter P1 = new PlayerCharacter();
        GameCharacter P2 = new GameCharacter("P2", 2, 0, 1, 1, '2');
        GameCharacter E = new GameCharacter("P3", 3, 2, 2, 1, 'e');
        ArrayList<GameCharacter> listOfPlayers = new ArrayList<GameCharacter>();
        ArrayList<GameCharacter> listOfEnemies = new ArrayList<GameCharacter>();
        
        
        
        listOfPlayers.add(P1);
        listOfPlayers.add(P2);
        listOfEnemies.add(E);

        BattleGrid testGrid = new BattleGrid(listOfPlayers, listOfEnemies);
        testGrid.printGrid();
        testGrid.printTurnOrder();
        testGrid.fight();
    }
}
