
/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources used: none
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * Tests written by me as per the requirements of the assignment.
 * Sets values manually to avoid problems like in the public tester.
 * Tests modeled after those in the public tester.
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * Contains tests for edge cases and other scenarios not covered in the public tester. listLen3 is a
 * list with 3 items and listLen0 is an empty list.
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {

	private MyLinkedList listLen3, listLen0;
    private MyLinkedList.MyListIterator listLen3Iter, listLen0Iter;
	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
	listLen3 = new MyLinkedList();
	listLen3.add("Christine");
	listLen3.add("Philip");
	listLen3.add("Stephen");
	listLen3Iter = listLen3.new MyListIterator();

	listLen0 = new MyLinkedList();
	listLen0Iter = listLen0.new MyListIterator();
	}

	/**
	 * test the hasNext method when next node is the tail node
	 */
	@Test
	public void testHasNext() {
		//manually setting iterator to the last node
		listLen3Iter.idx = 2;
		listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
		listLen3Iter.right = listLen3.head.getNext().getNext().getNext().getNext();
		listLen3Iter.canRemoveOrSet = true;
		listLen3Iter.forward = true;

		assertFalse("hasNext should not count tail node", listLen3Iter.hasNext());
	}

	/**
	 * check that NoSuchElement exception works properly
	 */
	@Test
	public void testNext() {
		try
		{
			listLen0Iter.next();
			fail();
		}
		catch(NoSuchElementException e)
		{
			//success
		}
	}

	/**
	 * test the hasPrevious method when previous is the head node
	 */
	@Test
	public void testHasPrevious() {
		listLen3Iter.idx = 0;
		listLen3Iter.left = listLen3.head;
		listLen3Iter.right = listLen3.head.getNext();
		listLen3Iter.canRemoveOrSet = true;
		listLen3Iter.forward = true;
		assertFalse("hasPrevious should not count the head node", listLen3Iter.hasPrevious());
	}

	/**
	 * test that previous() changes forward to false
	 */
	@Test
	public void testPrevious() {
		//manually setting iterator to the last node
		listLen3Iter.idx = 3;
		listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
		listLen3Iter.right = listLen3.head.getNext().getNext().getNext().getNext();
		listLen3Iter.canRemoveOrSet = true;
		listLen3Iter.forward = true;
		listLen3Iter.previous();
		assertFalse("the previous method should set forward to false", listLen3Iter.forward);
	}

	/**
	 * test nextIndex() after a next and a previous
	 */
	@Test
	public void testNextIndex() {
		//manually setting iterator to the first node
		listLen3Iter.idx = 0;
		listLen3Iter.left = listLen3.head.getNext();
		listLen3Iter.right = listLen3.head.getNext().getNext();
		listLen3Iter.canRemoveOrSet = true;
		listLen3Iter.forward = true;
		listLen3Iter.next();
		listLen3Iter.previous();
		assertEquals("Next index should work properly after multiple moves", listLen3Iter.nextIndex(), 0);
	}

	/**
	 * test previousIndex after attempting an illegal previous
	 */
	@Test
	public void testPreviousIndex() {
		
		try
		{
			listLen0Iter.previous();
		}
		catch(NoSuchElementException e){

		}
		
		assertEquals("Index should not change after illegal previous", listLen3Iter.previousIndex(), -1);
	}

	/**
	 * test the set method when attempting to set to null
	 */
	@Test
	public void testSet() {
		//manually setting iterator to the last node
		listLen3Iter.idx = 2;
		listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
		listLen3Iter.right = listLen3.head.getNext().getNext().getNext().getNext();
		listLen3Iter.canRemoveOrSet = true;
		listLen3Iter.forward = true;
		try{
			listLen3Iter.set(null);
			fail();
		}
		catch (NullPointerException e)
		{
			//success
		}
	}

	/**
	 * test remove if add was called
	 */
	@Test
	public void testRemoveTestOne() {
		//manually set list to the end
		listLen3Iter.idx = 2;
		listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
		listLen3Iter.right = listLen3.head.getNext().getNext().getNext().getNext();
		listLen3Iter.canRemoveOrSet = true;
		listLen3Iter.forward = true;
		listLen3Iter.add("Stringo");
		
		try
		{
			listLen3Iter.remove();
			fail();
		}
		catch(IllegalStateException e)
		{
			//success
		}
	}

	/**
	 * test remove if neither next or previous have been called
	 */
	@Test
	public void testRemoveTestTwo() {
		try
		{
			listLen3Iter.remove();
			fail();
		}
		catch(IllegalStateException e)
		{
			//success
		}
	}

	/**
	 * test add() when attempting to add null
	 */
	@Test
	public void testAdd() {
		//manually setting iterator to the last node
		listLen3Iter.idx = 2;
		listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
		listLen3Iter.right = listLen3.head.getNext().getNext().getNext().getNext();
		listLen3Iter.canRemoveOrSet = true;
		listLen3Iter.forward = true;
		try{
			listLen3Iter.add(null);
			fail();
		}
		catch(NullPointerException e)
		{
			//success
		}
	}

}