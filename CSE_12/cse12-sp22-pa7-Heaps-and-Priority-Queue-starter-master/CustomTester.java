/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources used: none
 * 
 * Tester for the MyMinHeap class with tests not found in public tester.
 * Doesn't test for the other classes Ticket and MyPriorityQueue
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * file containing tests for the MyMinHeap Class not found in public
 * tester. Mostly exceptions and specific cases mentioned in the writeup,
 * like when the parent and child are equal.
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
	
	public ArrayList<Integer> intList(Integer[] ints)
	{
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		for(int i = 0; i < ints.length; i++)
			toReturn.add(ints[i]);
		return toReturn;
	}
	MyMinHeap<Integer> fiveItemHeap;
	
	/**
	 * Test the constructor when passing in null values
	 */
	@Test
	public void testMyMinHeapConstructor() {
		try
		{
			MyMinHeap nullHeap = new MyMinHeap(null);
			fail();
		}
		catch(NullPointerException e) {}
		
		try
		{
			ArrayList<String> nullList = new ArrayList<String>();
			nullList.add(null);
			MyMinHeap nullHeap2 = new MyMinHeap(nullList);
			fail();
		}
		catch(NullPointerException e) {}
	}

	/**
	 * Test the getMinChildIdx method when called on a leaf node
	 */
	@Test
	public void testGetMinChildIdx() {
	 
		MyMinHeap<Integer> threeItemHeap = new MyMinHeap<Integer>();
		threeItemHeap.data = intList(new Integer[] {5, 3, 4});

		assertEquals("Leaf nodes should return -1", -1,
			threeItemHeap.getMinChildIdx(2));
	}

	/**
	 * Test the percolateUp method when a child is equal to a parent
	 */
	@Test
	public void testPercolateUp() {
		fiveItemHeap = new MyMinHeap<Integer>();
		fiveItemHeap.data = intList(new Integer[] {3, 5, 7, 5, 5});
		Integer[] expected = {3, 5, 7, 5, 5};
		fiveItemHeap.percolateUp(3);
		for (int i = 0; i < 5; i++) {
			assertEquals(
					"Heap structure should be unchanged",
					expected[i],
					fiveItemHeap.data.get(i));
		}
	}

	/**
	 * Test the percolateDown method when child equals parent
	 */
	@Test
	public void testPercolateDown() {
		MyMinHeap<Integer> fiveItemHeap = new MyMinHeap<Integer>();
		fiveItemHeap.data = intList(new Integer[] {3, 5, 7, 5, 5});

		fiveItemHeap.percolateDown(1);
		Integer[] expected = {3, 5, 7, 5, 5};
		for (int i = 0; i < 5; i++) {
			assertEquals(
					"Heap structure should be unchanged",
					expected[i],
					fiveItemHeap.data.get(i));
		}
	}

	/**
	 * Test the deleteIndex method when deleting a leaf node
	 */
	@Test
	public void testDeleteIndex() {
		fiveItemHeap = new MyMinHeap<Integer>(
			intList(new Integer[] {3, 5, 7, 5, 5}));
		
		fiveItemHeap.deleteIndex(3);
		Integer[] expected = {3, 5, 7, 5};
		for (int i = 0; i < 4; i++) {
			assertEquals(
					"Heap structure should match expected",
					expected[i],
					fiveItemHeap.data.get(i));
		}
	}

	/**
	 * Test the deleteIndex method when deleting a middle node
	 */
	@Test
	public void testDeleteIndex2() {
		MyMinHeap<Integer> fiveItemHeap = new MyMinHeap<Integer>();
		fiveItemHeap.data = intList(new Integer[] {3, 5, 7, 5, 6});
		
		fiveItemHeap.deleteIndex(1);
		Integer[] expected = {3, 5, 7, 6};
		for (int i = 0; i < 4; i++) {
			assertEquals(
					"Heap structure should match expected",
					expected[i],
					fiveItemHeap.data.get(i));
		}
	}

	/**
	 * Test the insert method when trying to insert null
	 */
	@Test
	public void testInsert(){
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add("H");
		stringList.add("G");
		stringList.add("C");
		MyMinHeap<String> stringHeap = new MyMinHeap<String>(stringList);
		try{
			stringHeap.insert(null);
			fail();
		} catch (NullPointerException e) {}
	}

	/**
	 * Test the insert method when trying to insert a child that equals it's
	 * parent
	 */
	@Test
	public void testInsert2(){
		fiveItemHeap = new MyMinHeap<Integer>(
			intList(new Integer[] {8, 5, 7, 5, 6}));
		fiveItemHeap.insert(7);
	}

   
	/**
	 * Test remove when using it on an empty heap
	 */
	@Test
	public void testRemove(){
		MyMinHeap<Integer> emptyHeap = new MyMinHeap<Integer>();
		assertEquals("removing from empty heap should give null", null, 
			emptyHeap.remove());
	}

  
	/**
	 * Test getMin when heap is empty
	 */
	@Test
	public void testGetMin(){
		MyMinHeap<Integer> emptyHeap = new MyMinHeap<Integer>();
		assertEquals("Min of empty heap should be null", null, 
			emptyHeap.getMin());
	}
}