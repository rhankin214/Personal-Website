import java.util.ArrayList;
import java.util.Random;
public class EnemyCharacter extends GameCharacter {
    
    int move;

    public EnemyCharacter(String name, int speed, int move, int x, int y, int Tp, char symbol)
    {
        this.name = name;
        this.speed = speed;
        this.move = move;
        this.x = x;
        this.y = y;
        this.Tp = Tp;
        this.symbol = symbol;

    }

    @Override
    public boolean isEnemy()
    {
        return true;
    }

    @Override
    public void act(BattleGrid grid)
    {
        /*
         * acting behavior for basic melee enemy
         * it's ok for them to just move and attack. They should always attack if possible if there's no other actions they can do.
         * Maybe they always go for kills but will prioritize damage if no kills are available.
         * This could work well for that character I want to have as an hp tank.
         * 1) see if there's an enemy in range
         * 2) select the one that would take the most damage
         * 
         */
        while(Tp > 0)
        {
            attack(grid);
        }
        
    }

    private int max(int a, int b)
    {
        if (a >= b)
            return a;
        else 
            return b;
    }
    private int abs(int a)
    {
        if(a < 0)
            return -a;
        else
            return a;
    }
    private int min(int a, int b)
    {
        if(a <= b)
            return a;
        else
            return b;
    }   

    //I'd like to do this recursively if possible.
    //how do?
    /* area looks something like this
     *     _
     *   _ _ _
     * _ _ _ _ _
     *   _ _ _
     *     _
     */
    private ArrayList<GameCharacter> getMeleeTargets(BattleGrid grid)
    {
        int attackRange = move+1;
        /*
         * -attackRange <= i <= attackRange
         * -(attackRange - i) <= j <= attackRange - i
         * with stuff in place to stop out of bounds referencing.
         */
        ArrayList<GameCharacter> toReturn = new ArrayList<GameCharacter>();
        for(int i = max(0, x - attackRange); i <= min(grid.getHeight() -1, x + attackRange); i++)
        {
            for(int j = max(0, y-(attackRange - i)); 
            j <= min(grid.getWidth() -1, y + attackRange - abs(i)); j++)
            {
                if(!grid.getGrid()[i][j].isEnemy())
                    toReturn.add(grid.getGrid()[i][j]);
            }
        }

        return toReturn;
    }
    private GameCharacter selectTarget(ArrayList<GameCharacter> listOfTargets)
    {
        int maxDamage = 0;
        int maxDamageIndex = 0;
        int rand;
        Random rn = new Random();
        boolean selected = false;
        for(int i = 0; i < listOfTargets.size(); i++)
        {
            if(calcDamage(listOfTargets.get(i)) >= listOfTargets.get(i).getHealth())
            {
                return listOfTargets.get(i);
            }
            if(calcDamage(listOfTargets.get(i)) >= maxDamage)
            {
                rand = rn.nextInt(1);
                if(rand == 0 || !selected)
                    maxDamageIndex = i;
            }
        }
        return listOfTargets.get(maxDamageIndex);   
    }
    
    private int calcDamage(GameCharacter selection)
    {
        return speed;
    }
    public void move(){

    }

    public void attack(BattleGrid grid){
        ArrayList<GameCharacter> listOfTargets = getMeleeTargets(grid);
        GameCharacter target = selectTarget(listOfTargets);
        grid.dealDamage(calcDamage(target), target);
    }

    private void calcDamage(){

    }

    private void findTarget()
    {

    }
}
