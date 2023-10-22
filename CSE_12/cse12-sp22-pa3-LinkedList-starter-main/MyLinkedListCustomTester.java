import static org.junit.Assert.*;
import org.junit.*;
/**
 * File to test MyLinkedList implementation. Tests edge cases not covered in the public tester.
 */

 /**
  * Main class of the tester. Not much to add.
  */
public class MyLinkedListCustomTester {

    private MyLinkedList<String> emptyStringList;
    private MyLinkedList<Integer> fourIntList;
    private int [] intArr = new int []{0, 1, 2, 3};
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() {
        emptyStringList = new MyLinkedList<String>();
        fourIntList = new MyLinkedList<Integer>();
        
        MyLinkedList<Integer>.Node node0 =  
            this.fourIntList.new Node(this.intArr[0]);
        MyLinkedList<Integer>.Node node1 =  
            this.fourIntList.new Node(this.intArr[1]);
        MyLinkedList<Integer>.Node node2 =  
            this.fourIntList.new Node(this.intArr[2]);
        MyLinkedList<Integer>.Node node3 =  
            this.fourIntList.new Node(this.intArr[3]);

        this.fourIntList.head.next = node0;
        node0.prev = this.fourIntList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = fourIntList.tail;
        this.fourIntList.tail.prev = node3;
        this.fourIntList.size = 4;
    }

    /**
     * Test the add method when adding a null node
     */
    @Test
    public void testAdd() {
        try
        {
            fourIntList.add(null);
            fail();
        }
        catch(NullPointerException e)
        {
            //success
        }
    }

    /**
     * Test the add with index method when index is negative
     */
    @Test
    public void testAddWithIndexTestOne() {
        try
        {
            fourIntList.add(-1, 0);
            fail();
        }
        catch(IndexOutOfBoundsException e)
        {
            //success
        }
    }

    /**
     * Test the add with index method when adding at the head
     */	
    @Test
    public void testAddWithIndexTestTwo() {
        int i = fourIntList.head.getNext().getElement(); // get current head
        int j = fourIntList.tail.getPrev().getElement(); // get current tail
        fourIntList.add(0, 12);
        
        assertEquals("Head should point to new node", 
            fourIntList.head.next.data, (Integer)12); //check head pointer
        assertEquals("current second should be previous head", 
            fourIntList.head.getNext().getNext().getElement(), (Integer)i); //check that new second node is the same as the old head
        assertEquals("tail should match previous tail",
            fourIntList.tail.getPrev().getElement(), (Integer)j); //check that tail has been properly updated
        assertEquals("Size should be properly updated", fourIntList.size(), 5); //
        
    }

    /**
     * Test the get method when index is out of bounds
     */
    @Test
    public void testGet() {
        try
        {
            fourIntList.get(-1); //check that negative index will fail
            fail();
        }
        catch(IndexOutOfBoundsException e)
        {

        }
        try
        {
            fourIntList.get(4); //check that index = size will fail
            fail();
        }
        catch(IndexOutOfBoundsException e)
        {

        }
    }

    /**
     * Test the getNth method when asked to get out of bounds
     */
    @Test
    public void testGetNth() {
        try
        {
            fourIntList.getNth(-1); //check that negative index will fail
            fail();
        }
        catch(IndexOutOfBoundsException e)
        {

        }
        try
        {
            fourIntList.getNth(4); //check that index = size will fail
            fail();
        }
        catch(IndexOutOfBoundsException e)
        {

        }
    }

    /**
     * Test the set method when attempting to set null or out of bounds
     */
    @Test
    public void testSet() {
        try
        {
            fourIntList.set(0, null);
            fail();
        }
        catch(NullPointerException e) {}
        try
        {
            fourIntList.set(-1, 5);
            fail();
        }
        catch(IndexOutOfBoundsException e) {}
        try
        {
            fourIntList.set(4, 5);
            fail();
        }
        catch(IndexOutOfBoundsException e) {}
    }

    /**
     * Test the remove method when attempting to remove out of bounds
     */
    @Test
    public void testRemoveTestOne() {
        try
        {
            fourIntList.remove(-1);
            fail();
        }
        catch(IndexOutOfBoundsException e) {}
        try
        {
            fourIntList.remove(4);
            fail();
        }
        catch(IndexOutOfBoundsException e) {}
    }
    
    /**
     * Test the remove method when removing the last node
     */
    @Test
    public void testRemoveTestTwo() {
        int i = fourIntList.tail.prev.prev.data; //get current second to last node's data
        fourIntList.remove(3);
        assertEquals("Old second to last node should be new tail", i, (int) fourIntList.tail.prev.data);
        assertEquals("Size should be decremented properly.", 3, fourIntList.size());
    }


    /**
     * Test the clear method when list is empty
     */
    @Test
    public void testClear() {
        emptyStringList.clear();
        assertTrue("List should still be empty", emptyStringList.isEmpty());
        assertEquals("List should still have size 0", emptyStringList.size(), 0);
    }

    /**
     * Test the size method when list is not empty
     */
    @Test
    public void testSize() {
        assertEquals("Four element list should have size 4", 4, fourIntList.size());
    }
}