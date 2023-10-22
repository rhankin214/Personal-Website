import org.junit.*;
import static org.junit.Assert.*;
public class ReverseLinkedListTester {
    MyLinkedList<Integer> linkedList;
    /**
     * Run before every test
     */
    @Before
    public void setUp(){
        Integer [] data = {1, 2, 3, 4, 5};
        linkedList = new MyLinkedList<Integer>(data);
        
    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        //TODO: Add your test case
    }

    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        //TODO: Add your test case

    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds and
     * in the middle of the list 
     * (i.e. fromIndex > 0 and toIndex < size-1), 
     * and fromIndex is less than toIndex.
    */
    //tests when toIndex and fromIndex are adjacent
    @Test
    public void testReverseIndexWithinBounds(){
        linkedList.reverseRegion(0, 4);
        int[] toCompare = {5, 4, 3, 2, 1};
        
        for(int i = 0; i < 5; i++)
        {
            assertEquals("nodes at index " + i + " are not equal", toCompare[i], 
                linkedList.get(i));
        }
    }

    /**
     * Custom test
    */
    @Test
    public void testReverseCustom(){
        //TODO: Add your test case

    }

}
