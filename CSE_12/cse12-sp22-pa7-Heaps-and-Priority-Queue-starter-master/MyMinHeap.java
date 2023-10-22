/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * File that contains implementation for a min heap class. Uses an ArrayList
 * and zero based indexing. Follows the framework of MinHeapInterface.java
 */

// Your import statements
import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation of a min heap. Each element has two child elements, and each
 * parent is smaller than its children. Allows inserted items percolate to the
 * proper spot in the heap, and only the first element may be removed.
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{
	public ArrayList<E> data;

	/**
	 * Default constructor. Initializes data as an empty ArrayList
	 */
	public MyMinHeap()
	{
		data = new ArrayList<E>();
	}

	/**
	 * makes a min heap based on the elements in the collection
	 * then percolates each element down so that it fits the minHeap
	 * rule
	 * @param collection the collection to copy elements from.
	 */
	public MyMinHeap(Collection<? extends E> collection)
	{
		if(collection == null || collection.contains(null))
		{
			throw new NullPointerException();
		}
		data = new ArrayList<E>(collection);
		for(int i = size() - 1; i >= 0; i--)
		{
			percolateDown(i);
		}
	}
	
	public int size()
	{
		return data.size();
	}
	/**
	 * inserts the specified element in the last node in the heap, then
	 * percolates it up to the appropriate index
	 */
	public void insert(E element)
	{
		if(element == null)
		{
			throw new NullPointerException();
		}
		data.add(element);
		percolateUp(size() - 1);
	}
	/**
	 * swaps two elements in the ArrayList
	 */
	protected void swap(int from, int to)
	{
		E temp = data.get(from);
		data.set(from, data.get(to));
		data.set(to, temp);
	}
	/**
	 * returns the index of the element's parent
	 * @param index index of the element to get the parent of
	 */
	protected int getParentIdx(int index)
	{
		return (index - 1)/2;
	}
	/**
	 * gets the index of left child of the node at
	 * the desired index
	 * @param index
	 * @return
	 */
	protected int getLeftChildIdx(int index)
	{
		return (index * 2) + 1;
	}
	/**
	 * gets the index of the right child of the node at
	 * the desired index
	 * @param index
	 * @return
	 */
	protected int getRightChildIdx(int index)
	{
		return (index * 2) + 2;
	}
	/**
	 * gets the index of the smallest child
	 * @param index the index of the node to compare the children of.
	 * @return the index of the smallest child node
	 * left if it's the only child, or if both children are equal.
	 * -1 if there are no children.
	 */
	protected int getMinChildIdx(int index)
	{
		if(getRightChildIdx(index) >= size())
		{
			//if right and left child don't exist,
			//return -1
			if(getLeftChildIdx(index) >= size())
				return -1;
			//if only left child exists, return it.
			else	
				return getLeftChildIdx(index);
		}

		//if right is less than left, return it
		//otherwise, return left
		else if(data.get(getRightChildIdx(index)).
		compareTo(data.get(getLeftChildIdx(index))) < 0)
		{
			return getRightChildIdx(index);
		}
		else
			return getLeftChildIdx(index);
	}
	/**
	 * Swaps the element with ones below it until the min heap law
	 * holds true
	 * @param index the index of the element to percolate.
	 */
	protected void percolateDown(int index)
	{
		/* stops if the node is a leaf or if the smallest child is >= the parent
		 * storing the minChildIndex here in case swapping causes it to change 
		 * later
		 */
		int minChildIdx = getMinChildIdx(index);
		if(minChildIdx == -1
			|| data.get(minChildIdx).compareTo(data.get(index)) >= 0)
		{
			return;
		}
		else
		{
			swap(index, minChildIdx);
			percolateDown(minChildIdx);
		}
	}
	/**
	 * Swaps the element with ones above it until the min heap law
	 * holds true
	 * @param index
	 */
	protected void percolateUp(int index)
	{
		if(index == 0 ||
			data.get(getParentIdx(index)).compareTo(data.get(index)) <= 0)
		{
			return;
		}
		else
		{
			swap(index, getParentIdx(index));
			percolateUp(getParentIdx(index));
		}
	}
	/**
	 * Swaps the element with the last element in the heap
	 * removes and returns the former and percolates down the
	 * latter
	 * @param index
	 * @return
	 */
	protected E deleteIndex(int index)
	{	
		E toReturn = data.get(index);
		swap(index, size() - 1);
		data.remove(size() - 1);
		percolateDown(index);
		return toReturn;
	}
	/**
	 * Return the smallest element in the heap. This is alway the root
	 * element assuming we don't call mid-insert or something like that.
	 * @param index
	 * @return
	 */
	public E getMin()
	{
		if(size() == 0)
		{
			return null;
		}
		return data.get(0);
	}
	/**
	 * removes the root node of the heap
	 * @return the data of the removed node
	 */
	public E remove()
	{
		if(size() == 0)
		{
			return null;
		}
		E toReturn = deleteIndex(0);
		return toReturn;
	}
	/**
	 * Delete all elements in the heap
	 */
	public void clear()
	{
		while(size() != 0)
		{
			deleteIndex(size() - 1);
		}
	}
}