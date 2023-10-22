/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources used: none
 * 
 * Implementation of a priority queue using a min heap as a backbone.
 * Used by autograder.java.
 */

import java.util.Collection;

/**
 * Implements a priority queue. Elements are inserted in order of their
 * priority, from lowest to highest. The lowest priority elements are removed
 * first.
 */
public class MyPriorityQueue<E extends Comparable<E>>
{
	MyMinHeap<E> heap;
	
	/**
	 * Constructor that creates an empty priority queue
	 */
	public MyPriorityQueue(){
		heap = new MyMinHeap<>();
	}

	/**
	 * Constructor that creates a priority queue from a collection
	 * @param collection The collection used to intialize priority queue
	 */
	public MyPriorityQueue(Collection<? extends E> collection){
		heap = new MyMinHeap<>(collection);
	}

	/**
	 * Adds an element to the priority queue
	 * @param element the element to be added
	 */
	public void push(E element){
		heap.insert(element);
	}

	/**
	 * Removes the element with the highest priority from the priority queue 
	 * @return the element with the highest priority
	 */
	public E pop(){
		return heap.remove();
	}

	/**
	 * Sees the element with the highest priority from the priority queue
	 * @return the element with the highest priority
	 */
	public E peek(){
		return heap.getMin();
	}

	/**
	 * Finds the number of elements in the priority queue
	 * @return the number of elements in the priority queue
	 */
	public int getLength(){
		return heap.size();
	}

	/**
	 * Remove all the elements from the priority queue.
	 */
	public void clear(){
		heap.clear();
	}
}