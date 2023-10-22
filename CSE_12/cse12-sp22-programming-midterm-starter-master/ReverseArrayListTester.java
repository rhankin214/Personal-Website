/**
 * Tests to check the implementation of reverseRegion method in MyArrayList.java
*/

//other import statements

import org.junit.*;
import static org.junit.Assert.*;

//IMPORTANT: DO NOT MODIFY THE TEST HEADERS!
/**
 * This class contains various test cases to test the reverseRegion method
 */
public class ReverseArrayListTester {

    Integer [] intArr = {1, 2, 3, 4, 5};
    String [] stringArr = {"Hello", "Goodbye", "Well met", "Hola", "Adieu"};

    MyArrayList<Integer> intList;
    MyArrayList<String> stringList;
    /**
     * Run before every test
     */
    @Before
    public void setUp(){
        intList = new MyArrayList<Integer>(intArr);
        stringList = new MyArrayList<String>(stringArr);
    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        try
        {
            intList.reverseRegion(-1, 4);
            fail();
        }
        catch (IndexOutOfBoundsException e) {}
        try
        {
            intList.reverseRegion(0, 5);
            fail();
        }
        catch(IndexOutOfBoundsException e) {}
    }

    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        Integer [] expectedArray = {1, 2, 3, 4, 5};
        intList.reverseRegion(3, 2);
        for(int i = 0; i < intList.size(); i++)
        {
            assertEquals("entry at index " + i + " doesn't match", expectedArray[i], intList.get(i));
        }
    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds and
     * in the middle of the list 
     * (i.e. fromIndex > 0 and toIndex < size-1), 
     * and fromIndex is less than toIndex.
    */
    @Test
    public void testReverseIndexWithinBounds(){
        Integer [] expectedArray = {5, 4, 3, 2, 1};
        intList.reverseRegion(0, 4);
        for(int i = 0; i < intList.size(); i++)
        {
            assertEquals("entry at index " + i + " doesn't match", expectedArray[i], intList.get(i));
        }
    }

    /**
     * Custom test
     * Tests the reverse region on a string array to make sure implementation works for different
     * variable types
    */
    @Test
    public void testReverseCustom(){
        stringList.reverseRegion(0, 4);
        String [] expectedArray = {"Adieu", "Hola", "Well met", "Goodbye", "Hello"};
        for(int i = 0; i < stringList.size(); i++)
        {
            assertEquals("Entries at index " + i + " do not match", stringList.get(i), expectedArray[i]);
        }
    }


}
