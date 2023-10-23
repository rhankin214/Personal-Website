import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;


public class BattleGrid{
    
    private final static int GRID_WIDTH = 5;
    private final static int GRID_HEIGHT = 5;

    private int enemyCount;
    private int playerCount;
    private GameCharacter[][] battleGrid;
    private Queue<GameCharacter> TurnQueue;

    public GameCharacter[][] getGrid()
    {   
        return battleGrid;
    }

    public void dealDamage(int damage, GameCharacter target)
    {
        target.setHealth(Integer.max(target.getHealth() - damage, 0));

        System.out.println(target.getName() + " was felled!");

        if(target.getHealth() <= 0 && !target.isEnemy())
        {
            removePlayer(target);
        }
        else if(target.getHealth() <= 0 && target.isEnemy())
        {
            removeEnemy(target);
        }
    }

    public void removePlayer(GameCharacter character)
    {
        TurnQueue.remove(character);
        character.knockout();
        playerCount--;
        TurnQueue.offer(character);
    }

    public void removeEnemy(GameCharacter character)
    {
        TurnQueue.remove(character);
        
        for(int i = 0; i < GRID_HEIGHT; i++)
            for(int j = 0; j < GRID_WIDTH; j++)
            {
                if(battleGrid[i][j] == character)
                    battleGrid[i][j] = null;
            }
        enemyCount -= 1;
    }

    private void gameOver(){
        System.out.println("Game over!");
    }

    private void victory(){
        System.out.println("Victory!");
    }

    public int getHeight()
    {
        return GRID_HEIGHT;
    }
    public int getWidth()
    {
        return GRID_WIDTH;
    }

    public BattleGrid(ArrayList<GameCharacter> listOfPlayers, ArrayList<GameCharacter> listOfEnemies) {
        battleGrid = new GameCharacter[GRID_HEIGHT][GRID_WIDTH];
        PlaceUnits(listOfPlayers, listOfEnemies);
        TurnQueue = EnterBySpeed(listOfPlayers, listOfEnemies);
        playerCount = listOfPlayers.size();
        enemyCount = listOfEnemies.size();
    }

    public void fight()
    {
        GameCharacter currentFighter;
        while(enemyCount > 0 && playerCount > 0)
        {
            currentFighter = TurnQueue.poll();
            currentFighter.act(this);
            TurnQueue.offer(currentFighter);
            printGrid();
            printTurnOrder();
        }
        if(enemyCount <= 0)
        {
            victory();
        }
        else if(playerCount <= 0)
        {
            gameOver();
        }
    }

    public void printTurnOrder()
    {
        GameCharacter [] queueArray = new GameCharacter[TurnQueue.size() + 1];
        
        TurnQueue.toArray(queueArray);
        System.out.print("\nTurn order: " + queueArray[0].getName());
        for(int i = 1; i < TurnQueue.size(); i++)
        {
            System.out.print(", " + queueArray[i].getName());
        }
    }

    public void printGrid()
    {
        System.out.println();
        for(int i = 0; i < GRID_HEIGHT; i++)
        {
            for(int j = 0; j < GRID_WIDTH; j++)
                if(battleGrid[i][j] == null)
                    System.out.print("0 ");
                else
                    System.out.print(battleGrid[i][j].symbol + " ");
            System.out.println();
        }
    }

    public void PlaceUnits(ArrayList<GameCharacter> listOfPlayers, ArrayList<GameCharacter> listOfEnemies)
    {
        //we will make it so that no 2 units sharing a space is guaranteed.

        for(int i = 0; i < GRID_HEIGHT; i++)
            for(int j = 0; j < GRID_WIDTH; j++)
            {
                battleGrid[i][j] = null;
            }
        for(int i = 0; i < listOfEnemies.size(); i++)
        {
            battleGrid[listOfEnemies.get(i).getX()][listOfEnemies.get(i).getY()] = listOfEnemies.get(i); 
        }

        for(int i = 0; i < listOfPlayers.size(); i++)
        {
            battleGrid[listOfPlayers.get(i).getX()][listOfPlayers.get(i).getY()] = listOfPlayers.get(i); 
        }
    }

    private Queue<GameCharacter> EnterBySpeed(ArrayList<GameCharacter> listOfPlayers,
        ArrayList<GameCharacter> listOfEnemies)
    {
        Queue<GameCharacter> toReturn = new LinkedList<GameCharacter>();
        
        //create a combined list of GameCharacters
        ArrayList<GameCharacter> listOfGameCharacters = new ArrayList<GameCharacter>();
        for(int i = 0; i < listOfEnemies.size(); i++)
            listOfGameCharacters.add(listOfEnemies.get(i));

        for(int j = 0; j < listOfPlayers.size(); j++)
            listOfGameCharacters.add(listOfPlayers.get(j));    
        
        SortBySpeed(listOfGameCharacters);

        for(int k = 0; k < listOfGameCharacters.size(); k++)
            toReturn.offer(listOfGameCharacters.get(k));

        return toReturn;
    }

    private void SortBySpeed(ArrayList<GameCharacter> listOfGameCharacters)
    {
        int max = 0;
        int maxIndex;
        GameCharacter temp;
        for(int i = 0; i < listOfGameCharacters.size() - 1; i++)
        {
            max = listOfGameCharacters.get(i).getSpeed();
            maxIndex = i;
            for(int j = i + 1; j < listOfGameCharacters.size(); j++)
            {
                if(listOfGameCharacters.get(j).getSpeed() > max)
                {
                    max = listOfGameCharacters.get(j).getSpeed();
                    maxIndex = j;
                }
            }
            if(maxIndex != i)
            {
                temp = listOfGameCharacters.get(i);
                listOfGameCharacters.set(i, listOfGameCharacters.get(maxIndex));
                listOfGameCharacters.set(maxIndex, temp);
            }
        }
    }

    
}