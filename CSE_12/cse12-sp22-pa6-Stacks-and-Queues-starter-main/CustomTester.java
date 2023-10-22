/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources used: Used Public tester for initDeque method
 * 2-4 sentence file description here
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    // ----------------MyDeque class----------------
    /**
     * Test the constructor when initial capacity is less than 1
     */
    @Test
    public void testMyDequeConstructor() {
        try
        {
            MyDeque illegalDeque = new MyDeque(-1);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }

    /**
     * Test the expandCapacity method when capacity starts at 0
     */
    @Test
    public void testMyDequeExpandCapacity() {
        MyDeque capacityDeque = new MyDeque(0);
        capacityDeque.expandCapacity();
        assertEquals("Capacity should have set to 10", 10, 
            capacityDeque.data.length);
    }

    /**
     * Test the addFirst method when the element added is null
     */
    @Test
    public void testAddFirst() {
        MyDeque nullDeque = new MyDeque(10);
        try
        {
            nullDeque.addFirst(null);
            fail();
        }
        catch (NullPointerException e) {}
    }

    /**
     * Tests addFirst method when it would cause wrapping to occur
     */
    public void testAddFirstWrap() {
        MyDeque<Integer> wrapDeque = new MyDeque<Integer>(6);
        Integer[] wrapEntries = {3, 2, 1, null, null, null};
        Integer[] expected = {3, 2, 1, null, null, 4};
        MyInitDeque(wrapDeque, wrapEntries, 3, 0, 2);        

        wrapDeque.addFirst(4);
        assertEquals("Front should be index 5", wrapDeque.front, 5);
        assertEquals("Size should be incremented", wrapDeque.size, 4);
        checkStructure(wrapEntries, expected);
    }
    /**
     * The initDeque method from the public tester
     * Gave it a different name just incase that trips up the autograder
     * or something.
     */
    static void MyInitDeque(MyDeque<Integer> deque, Object[] data, int size, 
            int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }
    /**
     * Test the addLast method when it would cause wrapping to occur
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> wrapDeque = new MyDeque<Integer>(6);
        Integer[] wrapEntries = {null, null, null, 1, 2, 3};
        Integer[] expected = {4, null, null, 1, 2, 3};
        MyInitDeque(wrapDeque, wrapEntries, 3, 3, 5);

        wrapDeque.addLast(4);
        assertEquals("Rear should be index 0", wrapDeque.rear, 0);
        assertEquals("Size should be incremented", wrapDeque.size, 4);
        checkStructure((Integer[]) wrapDeque.data, expected);
    }

    /**
     * Test the removeFirst method when the next in line is wrapped to the
     * other end of the array
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> wrapDeque = new MyDeque<Integer>(6);
        Integer[] wrapEntries = {2, 3, null, null, null, 1};
        Integer[] expected = {2, 3, null, null, null, null};
        MyInitDeque(wrapDeque, wrapEntries, 3, 5, 1);

        wrapDeque.removeFirst();
        assertEquals("Front should be index 0", wrapDeque.front, 0);
        assertEquals("Size should be decremented", wrapDeque.size, 2);
        checkStructure((Integer[]) wrapDeque.data, expected);
    }
    /**
     * Helper method that fails the test if the the two arrays don't match
     * Used to make sure structure is maintained after add, remove, etc.
     */
    public void checkStructure(Integer[] data, Integer[] expected)
    {
        assertEquals("Size doesn't match", data.length, expected.length);
        for(int i = 0; i < data.length; i++)
        {
            assertEquals("Entry " + i + " doesn't match", 
                data[i], expected[i]);
        }
    }
    /**
     * Test the removeLast method when new last would be wrapped to other siede
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> wrapDeque = new MyDeque<Integer>(6);
        Integer[] wrapEntries = {3, null, null, null, 1, 2};
        Integer[] expected = {null, null, null, null, 1, 2};
        MyInitDeque(wrapDeque, wrapEntries, 3, 4, 0);

        wrapDeque.removeLast();
        assertEquals("Rear should be index 5", wrapDeque.rear, 5);
        assertEquals("Size should be decremented", wrapDeque.size, 2);
        checkStructure((Integer[]) wrapDeque.data, expected);
    }

    /**
     * Test the peekFirst method when dequeue is empty
     */
    @Test
    public void testPeekFirst(){
        MyDeque<Integer> emptyQueue = new MyDeque<Integer>(6);
        assertEquals("Peeking an empty array should return null", null,
            emptyQueue.peekFirst());
    }

    /**
     * Test the peekLast method when deque is empty
     */
    @Test
    public void testPeekLast(){
        MyDeque<Integer> emptyQueue = new MyDeque<Integer>(6);
        assertEquals("Peeking an empty array should return null", null,
            emptyQueue.peekLast());
    }

    // ----------------MyStack class----------------
    /**
     * Test MyStack when attempting to peek an empty stack
     */
    @Test
    public void testMyStack(){
        MyStack<Integer> emptyStack = new MyStack<Integer>(5);
        assertEquals("Peeking an empty stack should return null", null, 
            emptyStack.peek());
    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when peeking an empty queue
     */
    @Test
    public void testMyQueue(){
        MyQueue<Integer> emptyQueue = new MyQueue<Integer>(5);
        assertEquals("Peeking an empty queue should return null", null,
            emptyQueue.peek());
    }
}
