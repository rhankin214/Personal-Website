///////////////////////////////////////////////////////////////////////////////
// Main Class File:    Assignment8.java
// File:               RecursionWarmup.java
// Quarter:            CSE 8B Winter 2022
//
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.ArrayList;
public class RecursionWarmup {
    public static String binaryString(int n)
    {
        if(n == 0)
        {
            return "0";
        }
        if(n == 1)
        {
            return "1";
        }
        else{
            return binaryString(n/2) + n%2;    
        }
    }
    public static boolean isSubSetSum(ArrayList<Integer> set, int targetNumber)
    {
        if(set.size() == 0 || targetNumber < 0)
        {
            return false;
        }
        else if(targetNumber == 0)
        {
            return true;
        }
        
        ArrayList<Integer> tempArrayList = new ArrayList<>();
        for (int i = 0; i < set.size(); i++)
        {
            tempArrayList.add(set.get(i));
            
        }
        for(int i = 0; i < set.size(); i++)
        {
            tempArrayList.remove(i);
            if(isSubSetSum(tempArrayList, targetNumber - set.get(i)))
            {
                return true;
            }
            tempArrayList.add(i, set.get(i));
        }
        return false;
    }
    public static void main(String [] args)
    {
        System.out.println(binaryString(17));
        System.out.println(binaryString(32));
        System.out.println(binaryString(10));
        System.out.println(binaryString(65));

        ArrayList<Integer> theInts = new ArrayList<>();
        theInts.add(1);
        theInts.add(23);
        theInts.add(17);
        theInts.add(2);
        theInts.add(80);
        if(!isSubSetSum(theInts, 20)
        ||!isSubSetSum(theInts, 97)
        ||!isSubSetSum(theInts, 40)
        ||isSubSetSum(theInts, 4))
        {
            System.out.println("method failed");
        }
    }
}
