import java.util.ArrayList;
public class GameCharacter {
    public String name;
    public int speed;
    public int health;
    public int x;
    public int y;
    public int Tp;
    public int defaultTp;
    public char symbol;

    public void act()
    {

    }

    public boolean isEnemy()
    {
        return true;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int newHealth)
    {
        health = newHealth;
    }

    public void act(BattleGrid grid){}

    public void knockout(){}

    public GameCharacter(){
        name = "a";
        speed = 1;
        health = 10;
        x = 0;
        y = 0;
        Tp = 1;
        defaultTp = 1;
        symbol = 'e';

    }
    
    public GameCharacter(String name, int speed, int x, int y, int Tp, char symbol){
        this.name = name;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.Tp = Tp;
        this.defaultTp = Tp;
        this.symbol = symbol;

    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String getName()
    {
        return name;
    }

    public int getSpeed()
    {
        return speed;
    }

    public char getSymbol()
    {
        return symbol;
    }
}