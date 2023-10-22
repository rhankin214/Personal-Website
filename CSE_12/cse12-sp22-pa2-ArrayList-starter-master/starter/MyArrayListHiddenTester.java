/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * File containing tests implemented by me. Mainly tests edge cases and exceptions.
 */

 //IMPORTANT: Do not change the headers!

import static org.junit.Assert.*;

import org.junit.*;


/**
 * Class containing test methods for MyArrayList.java focusing on edge cases such as null inputs
 * and out of bounds.
 */
public class MyArrayListHiddenTester {

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test */
	static final int DEFAULT_CAPACITY = 5;
	static final int MY_CAPACITY = 3;

	Object[] arr = new Object[10];
	Integer[] arrInts = { 1, 2, 3 };

	private MyArrayList listWithNull, listWithInt;
	@Before
	public void setUp() throws Exception {
		listWithNull = new MyArrayList(arr);
		listWithInt = new MyArrayList<Integer>(arrInts);
		
	}

	/**
	 * Aims to test the constructor when the input argument
	 * is not valid
	 */
	@Test
	public void testConstructorInvalidArg(){
		//test for if capacity arg constructor will throw exception with negative param
		int i = 0;
		try
		{
			MyArrayList invalidList = new MyArrayList(-1);
		}
		catch(IllegalArgumentException e)
		{
			i = 1;
		}
		assertEquals("Constructor should throw exception for capacity < 0.", i, 1);
	}

	/**
	 * Aims to test the constructor when the input argument
	 * is null
	 */
	@Test
	public void testConstructorNullArg(){
		MyArrayList nullArrayList = new MyArrayList(arr);

		assertEquals("Consructor should set capacity to 5 for input null", nullArrayList.getCapacity(), 
			DEFAULT_CAPACITY);
		assertEquals("Constructor should set size to 0 for input null", nullArrayList.size, 0);
	}

	/**
	 * Aims to test the append method when an element is added to a full list
	 * Check reflection on size and capacity
	 */
	@Test
	public void testAppendAtCapacity(){
		listWithInt.append(4);
		for(int i = 0; i < 4; i++)
		{
			assertEquals("Append does not result in the correct values", listWithInt.get(i), i+1);
		}
		assertEquals("Append does not correctly increment size", listWithInt.size, 4);
		assertEquals("Append does not correctly increase capacity", listWithInt.getCapacity(), 6);
	}

	/**
	 * Aims to test the prepend method when a null element is added
	 * Checks reflection on size and capacity
	 * Checks whether null was added successfully
	 */
	@Test
	public void testPrependNull(){
		listWithInt.prepend(null);
		assertEquals("Prepend does not correctly increment size for null test", listWithInt.size, 4);
		assertEquals("Prepend does not correctly increase size for null test", 
			listWithInt.getCapacity(), 6);
		assertEquals("Prepend does not correctly add null", listWithInt.get(0), null);
	}
	
	/**
	 * Aims to test the insert method when input index is out of bounds
	 */
	@Test
	public void testInsertOutOfBound(){
		int i = 0;
		int j = 0;
		try
		{
			listWithInt.insert(-1, 3);
		}
		catch(IndexOutOfBoundsException e)
		{
			i = -1;
		}
		try
		{
			listWithInt.insert(3, 3);
		}
		catch(IndexOutOfBoundsException e)
		{
			j = -1;
		}
		assertEquals("Insert does not correctly throw out of bounds for input below bound", i, -1);
		assertEquals("Insert does not correctly throw out of bounds for input above bound", j, -1);
	}

	/**
	 * Insert multiple (eg. 1000) elements sequentially beyond capacity -
	 * Check reflection on size and capacity
	 * Hint: for loop could come in handy
	 */
	@Test
	public void testInsertMultiple(){
		for(int i = 0; i < 1000; i++)
		{
			listWithInt.insert(0, i);
		}
		assertEquals("Does not correctly increase capacity after many insertions.", 1536,
		 listWithInt.getCapacity());
		assertEquals("Does not corectly increment size after many insertions.", listWithInt.size, 1003);
	}

	/**
	 * Aims to test the get method when input index is out of bound
	 */
	@Test
	public void testGetOutOfBound(){
		int i = 0;
		int j = 0;
		try
		{
			listWithInt.get(-1);
		}
		catch(IndexOutOfBoundsException e)
		{
			i = -1;
		}
		try
		{
			listWithInt.get(3);
		}
		catch(IndexOutOfBoundsException e)
		{
			j = -1;
		}
		assertEquals("Get does not correctly throw out of bounds for input below bound", i, -1);
		assertEquals("Get does not correctly throw out of bounds for input above bound", j, -1);
	}

	/**
	 * Aims to test the set method when input index is out of bound
	 */
	@Test
	public void testSetOutOfBound(){
		int i = 0;
		int j = 0;
		try
		{
			listWithInt.set(-1, 3);
		}
		catch(IndexOutOfBoundsException e)
		{
			i = -1;
		}
		try
		{
			listWithInt.set(3, 3);
		}
		catch(IndexOutOfBoundsException e)
		{
			j = -1;
		}
		assertEquals("Set does not correctly throw out of bounds for input below bound", i, -1);
		assertEquals("Set does not correctly throw out of bounds for input above bound", j, -1);
	}


	/**
	 * Aims to test the remove method when input index is out of bound
	 */
	@Test
	public void testRemoveOutOfBound(){
		int i = 0;
		int j = 0;
		try
		{
			listWithInt.remove(-1);
		}
		catch(IndexOutOfBoundsException e)
		{
			i = -1;
		}
		try
		{
			listWithInt.remove(3);
		}
		catch(IndexOutOfBoundsException e)
		{
			j = -1;
		}
		assertEquals("Insert does not correctly throw out of bounds for input below bound", i, -1);
		assertEquals("Insert does not correctly throw out of bounds for input above bound", j, -1);
	
	}

	/**
	 * Aims to test the expandCapacity method when 
	 * requiredCapacity is strictly less than the current capacity
	 */
	@Test
	public void testExpandCapacitySmaller(){
	   int i = 0;
	   try
	   {
		   listWithInt.expandCapacity(2);
	   }
	   catch(IllegalArgumentException e)
	   {
		   i = -1;
	   }
	   assertEquals("Does not correct throw exception for expanding to a lower capacity", i, -1);
	}

	/**
	 * Aims to test the expandCapacity method when 
	 * requiredCapacity is greater than double(2x) the current capacity
	 */
	@Test
	public void testExpandCapacityExplode(){
		listWithInt.expandCapacity(7);
		assertEquals("Does not correctly expand capacity when expanding to more than double current", 7,
			listWithInt.getCapacity());
	}
	

}
