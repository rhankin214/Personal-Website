/**
 * 
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * File description: Implementation of ArrayList data structure. Implements MyReverseList interface and 
 * the reverseRegion() method
 */

/**
 * A list that can dynamically increment its size as entries are added, although that feature has not
 * been implemented yet
 */
public class MyArrayList<E> implements MyReverseList<E> {
    static final int DEFAULT_CAPACITY = 5;
    
    Object[] data;
    int size;

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!

    /**
     * Constructor to create an array list with the given array's elements
     * @param arr - array of elements to be used to construct the ArrayList
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[DEFAULT_CAPACITY];
        } else {
            this.data = arr.clone();
            this.size = arr.length;
        }
    }
    @Override
    /**
	 * Reverses the order of contents in a segment of an arrayList defined by the parameters
	 */
    public void reverseRegion(int fromIndex, int toIndex){
       if(toIndex < 0 || toIndex >= size || fromIndex < 0 || fromIndex >= size)
       {
           throw new IndexOutOfBoundsException();
       }
       Object temp;
       //runs # of times equal to number of entries/2 rounded up.
       if(toIndex > fromIndex)
        for(int i = 0; i < (toIndex - fromIndex)/2 + (toIndex - fromIndex)%2; i++)
        {
            temp = data[fromIndex + i];
            data[fromIndex + i] = data[toIndex - i];
            data[toIndex - i] = temp;
        }
    }

    @Override
    /**
     * A method that returns the number of valid elements
     * in the ArrayList 
     * @return - number of valid elements in the arraylist
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * A method that returns an Element at the specified index
     * @param index - the index of the return Element
     * @return Element at specified index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }
}
