import java.util.InputMismatchException;

/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * File for an ArrayList object implemented by me. Contains several methods to emulate the
 * functionality of an arraylist. 
 */

 /**
  * A class that contains methods for an array that can dynamically change it's capacity
  * as needed.
  */

public class MyArrayList<E> implements MyList<E>
{
    Object[] data;
    int size;
    /**
     * Default constructor. Creates an empty arraylist with the default length of 5
     */
    public MyArrayList()
    {
        data = new Object[5];
        size = 0;
    }
    /**
     * Creates an arraylist of specified length
     * throws exception for lengths less than 0
     * @param initialCapacity is the length
     */
    public MyArrayList(int initialCapacity)
    {
        
        if(initialCapacity < 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            data = new Object[initialCapacity];
            size = 0;
        }
    }
    /**
     * Builds arraylist based on input array. If the input array is null, performs default constructor.
     * Otherwise, copies in all valid objects.
     * @param arr
     */
    public MyArrayList(E[] arr)
    {
        Object validCheck;
        if(arr == null)
        {
            data = new Object[5];
            size = 0;
        }
        else
        {
            data = arr;
            for(int i = 0; i < arr.length; i++)
            {
                try
                {
                    validCheck = arr[i];
                    size++;
                }
                catch(Exception e){
                    break;
                }
            }
        }
    }
    /**
     * Expands the capacity of the array based on required capacity
     * if the array's capacity is 0, it will be set to the default 5,
     * otherwise it will be doubled.
     * if it's still less than requiredCapacity, it gets set to that value
     * @param requiredCapacity
     */
    @Override
    public void expandCapacity(int requiredCapacity)
    {
        
        if(requiredCapacity < data.length)
        {
            throw new IllegalArgumentException();
        }
        else if(data.length == 0)
        {
            data = new Object[5];
        }
        else
        {
            data = new Object[data.length * 2];
        }
        if(data.length < requiredCapacity)
        {
            data = new Object[requiredCapacity];
        }
    }
    /**
     * @return the capacity of the array
     */
    @Override
    public int getCapacity()
    {
        return data.length;
    }
    @Override
    public void insert(int index, E element)
    {
        if(index < 0 || index > data.length)
        {
            throw new IndexOutOfBoundsException();
        }
        if(data.length == size)//ensures that there's space
        {
            expandCapacity(size + 1);
        }
        for(int i = size; i > index; i++)//shifts each element to the left
        {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }
    @Override
    public void append(E element)
    {
        if(size == data.length)
        {
            expandCapacity(size + 1);
        }
        data[size] = element;
        size++;
    }
    @Override
    public void prepend(E element)
    {
        if(size == data.length)
        {
            expandCapacity(size + 1);
        }
        for(int i = size; i > 0; i++)
        {
            data[i] = data[i - 1];
        }
        data[0] = element;
        size++;
    }
    @Override
    public E get(int index)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }
    @Override
    public E set(int index, E element)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        E temp = (E) data[index];
        data[index] = element;
        return temp;
    }
    @Override
    public E remove(int index)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        E temp = (E) data[index];
        for(int i = index; i < size - 1; i++)
        {
            data[i] = data[i + 1];
        }
        size--;
        
    
        return temp;
    }

    @Override
    public int size()
    {
        return size;
    }
}
 // Hint: the 'capacity' (length of data array) is not the same as the 'size'
 // Size is the number of elements in the ArrayList, i.e., the number of valid
 // elements in the array