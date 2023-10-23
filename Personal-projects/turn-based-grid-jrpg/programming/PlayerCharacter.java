import java.util.Scanner;
import java.util.ArrayList;

public class PlayerCharacter extends GameCharacter{
    Scanner read = new Scanner(System.in);
    boolean knockedOut = false;
    

    @Override
    public void act(BattleGrid grid){

        if(!knockedOut){
            System.out.println(name + "'s turn");
            System.out.println("1) Attack   1 tp");
            System.out.println("2) Skill");
            System.out.println("3) Item");
            System.out.println("4) Bank");
            String next = read.nextLine();
            if(next.equals("1"))
                attack(grid);
        }
    }

    public void knockout(BattleGrid grid)
    {
        knockedOut = true;
        
    }

    private ArrayList<GameCharacter> findTargets(BattleGrid grid)
    {
        ArrayList<GameCharacter> listOfTargets = new ArrayList<GameCharacter>();
        
        for(int i = 0; i < grid.getHeight(); i++)
        {
            for(int j = 0; j < grid.getWidth(); j++)
                if(grid.getGrid()[i][j] != null)
                    if(grid.getGrid()[i][j].isEnemy())
                        listOfTargets.add(grid.getGrid()[i][j]); 
        }

        return listOfTargets;
    }

    private GameCharacter makeSelection(ArrayList<GameCharacter> listOfTargets)
    {
        for(int i = 0; i < listOfTargets.size(); i++)
            System.out.println((i+1) + ") " + listOfTargets.get(i).getName());
        
        int selection = read.nextInt();
        GameCharacter toReturn = listOfTargets.get(selection - 1);
        return toReturn;
    }
    private int calcDamage(GameCharacter selection)
    {
        return speed;
    }
    
    private void attack(BattleGrid grid) {
        System.out.println("Select Target");
        ArrayList<GameCharacter> listOfTargets = findTargets(grid);

        GameCharacter selection = makeSelection(listOfTargets);
        
        grid.dealDamage(calcDamage(selection), selection);

        Tp -= 1;
        if(Tp > 0)
            act(grid);
        else Tp = 3;
        /* Damage dealing process
         * Subtract the health from target(s)
         * Check if health is 0
         * remove target from grid AND turn queue.
         * queue.remove seems like it will work as intended. Will need to test.
         */
        
    }

    @Override
    public boolean isEnemy()
    {
        return false;
    }
}