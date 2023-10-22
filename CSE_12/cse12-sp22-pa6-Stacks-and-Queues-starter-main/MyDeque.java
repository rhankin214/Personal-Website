/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources used: Used zybooks for a refresher on queues
 * File that implements a deque data structure, which keeps track
 * of it's front and back and can only modify things there. Uses
 * an array and implements the DequeInterface.
 */

/**
 * Class for implementing a deque
 * data: underlying array housing the deque
 * the deque can occupy any set of sequential indicies 
 * in the array, and may wrap from one end to another
 * front: index of the first entry in the queue
 * rear: index of the last entry in the queue
 * size: number of elements in the queue
 */
import java.util.LinkedList;
public class MyDeque<E> implements DequeInterface<E>{
    
    Object [] data;
    int front;
    int rear;
    int size;

    public static int test() {
        QueueInterface q = new extendingClass();
        
        extendingClass g = new MyDeque(5);
        q = g;
        return 0;
    }
    public static void main (String [] args)
    {
        test();
    }
    /**
     * Initializes an empty array for the queue of size initial capacity
     * Initializes the other three instance variables to 0.
     * @param initialCapacity the size to initialize the array to
     * @throws IllegalAgrumentException if initial capacity < 0
     */
    public MyDeque (int initialCapacity)
    {
        if(initialCapacity < 0)
        {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        E elem = (E)data[2];
        size = 0;
        rear = 0;
        front = 0;
    }
    public int size()
    {
        return size;
    }
    /**
     * Helper method that gets the index to the right in the queue,
     * wrapping to the other side if necessary.
     * @param curr the current index
     * @return curr + 1, or 0 if curr is the last index
     */
    private int advanceRight(int curr)
    {
        //if at the last index, go the first one
        if(curr  == data.length - 1)
        {
            return 0;
        }
        else {
            //otherwise, just move one to the right.
            return curr + 1;
        }
    }

    /**
     * Helper method that gets the index to left in the queue
     * Wraps to other side if necessary
     * @param curr the current index.
     * @return curr -1, or the last index of the array if curr is 0
     */
    private int advanceLeft(int curr)
    {
        //if we're at index 0, wrap to the back
        if(curr == 0)
        {
            return data.length - 1;
        }
        else {
            //if it doesn't have to wrap it just goes one to the left.
            return curr - 1;
        }
    }
    /**
     * Sets capacity to 10 if it's currently 0
     * Maintains all elements and their order, but they're made contiguous
     * The front entry gets placed at index 0, then the second to front at 1
     * third at 2, etc.
     */
    public void expandCapacity()
    {
        if(data.length == 0)
        {
            data = new Object[10];
        }
        else{
            Object [] newArray = new Object[data.length * 2];
            //puts the front element at index 0, second to front at 1, etc.
            int curr = front;
            for(int i = 0; i < this.size(); i++)
            {
                newArray[i] = data[curr];
                //moves to next element and wraps if necessary
                curr = advanceRight(curr);
            }
            data = newArray;
            front = 0;
            if(size == 0)
            {
                rear = 0;
            }
            else
            {
                rear = size - 1;
            }
        }
    }

    /**
     * @return the front element of the queue
     */
    public E peekFirst()
    {
        return (E) data[front];
    }

    /**
     * @return the rear element of the queue
     */
    public E peekLast()
    {
        return (E) data[rear];
    }

    /**
     * If the array is full, expand the capacity
     * Find the index to the left of front, wrap
     * if needed, put element there. Set that as the new front
     * @param element the element to add
     * @throws NullPointerException if element is null.
     */
    @Override
    public void addFirst(E element)
    {
        if(element == null)
        {
            throw new NullPointerException();
        }
        if (size == data.length)
        {
            expandCapacity();
        }
        //if the array is empty we don't change front and rear
        else if(size == 0)
        {
            data[front] = element;
            size += 1;
            return;
        }
        
        int curr = advanceLeft(front);
        data[curr] = element;
        front = curr;
        size += 1;
    }

    /**
     * If full, expand capacity
     * Find index to the right of rear, wrap if needed, put the element there.
     * Set that as the new front
     * @param element the element to add
     * @throws NullPointerException if element is null
     */
    public void addLast(E element)
    {
        if(element == null)
        {
            throw new NullPointerException();
        }
        if (size == data.length)
        {
            expandCapacity();
        }
        //if the array is empty we don't change front and rear
        if(size == 0)
        {
            data[rear] = element;
            size += 1;
            return;
        }
        
        //find index to the right of rear, wrap if needed
        int curr = advanceRight(rear);
        data[curr] = element;
        rear = curr;
        size += 1;
    }

    /**
     * Removes the front element of the queue, and sets the second to front as
     * the new front
     */
    public E removeFirst()
    {
        if(size == 0)
        {
            return null;
        }
        
        E toReturn = (E) data[front];
        data[front] = null;
        //reassign front if we didn't just remove the last element
        if(size != 1)
        {
            front = advanceRight(front);
        }
        size -= 1;
        return toReturn;
    }
    /**
     * removes the rear element and sets the second to rear as the
     * new rear.
     */
    public E removeLast()
    {
        if(size == 0)
        {
            return null;
        }
        
        E toReturn = (E) data[rear];
        data[rear] = null;
        if(size != 1)
        {
            rear = advanceLeft(rear);
        }
        size -= 1;
        return toReturn;
    }

}
