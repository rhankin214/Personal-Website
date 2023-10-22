/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * File description: Implementation for a linked list data structure. Implements the interface found in
 * MyReverseList.java, including the reverseRegion method.
 */

/**
 *  A list where each entry contains a reference to the adjacent entries, rather than using indexes.
 *  This makes insertion much easier, for example. 
 *  Does this using the Node subclass. 
 */
public class MyLinkedList<E> implements MyReverseList<E>{

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes
     * This class is used for both MyLinkedList and MyListIterator.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            //Initialise the elements
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the previous node in the list
         * @param p new previous node
         */
        public void setPrev(Node p) {
            //Set the node p on the previous position
            prev = p;
        }

        /** 
         * Set the next node in the list
         * @param n new next node
         */
        public void setNext(Node n) {
            //Set the node n on the next position
            next = n;
        }

        /** 
         * Set the element 
         * @param e new element 
         */
        public void setElement(E e) {
            this.data = e;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!
    /**
     * Constructor to create a doubly linked list 
     * with the argument array's elements
     * @param arr - array of elements to be used to construct the LinkedList
     */
    public MyLinkedList(E[] arr) {

        //Create dummy nodes
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;	

        if(arr != null){
            //create list by inserting each element
            Node currNode = head;
            for(int i=0; i<arr.length; i++){
                Node newNode = new Node(arr[i]);
                currNode.next.prev = newNode;
                newNode.next = currNode.next;
                newNode.prev = currNode;
                currNode.next = newNode;

                //move pointer to the next node
                currNode = currNode.next;
                //increase size of list
                this.size++;
            }
        }
    }


    /**
     * Reverses the order of the nodes in a segment of a linked list
     */
    public void reverseRegion(int fromIndex, int toIndex){
        if(toIndex < 0 || toIndex >= size || fromIndex < 0 || fromIndex >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        Node left = new Node(null);
        Node right = new Node(null);
        Node leftOfLeft = new Node(null); 
        Node rightOfLeft = new Node(null);
        Node leftOfRight = new Node(null);
        Node rightOfRight = new Node(null);
       
        //execute #of times equal to (toIndex - fromIndex)/2 rounded up
        if(toIndex > fromIndex)
            for(int i = 0; i < (toIndex - fromIndex)/2 + (toIndex - fromIndex)%2; i++)
            {
                
                if(toIndex - i < fromIndex + i)
                {
                    break;
                }
                //creates references for the nodes at 
                left.setNext(getNth(fromIndex + i));
                
                right.setNext(getNth(toIndex - i));
                //there was probably a better way to do this, but just keeping references to all relevant
                //nodes seemed a lot easier on my poor little brain
                
                //create reference nodes to all the nodes adjacent to left and right.
                leftOfLeft.setNext(left.getNext().getPrev());
                rightOfLeft.setNext(left.getNext().getNext());
                leftOfRight.setNext(right.getNext().getPrev());
                rightOfRight.setNext(right.getNext().getNext());

                //the "outer pointers" that are closer to the edge of the list don't change behavior
                //if they're adjacent
                leftOfLeft.getNext().setNext(right.getNext());
                rightOfRight.getNext().setPrev(left.getNext());

                right.getNext().setPrev(leftOfLeft.getNext());
                left.getNext().setNext(rightOfRight.getNext());
                
                //run different code if left and right are adjacent
                if(toIndex - i - (fromIndex + i) == 1)
                {
                    //if they're adjacent set their "inner" pointers to each other
                    right.getNext().setNext(left.getNext());
                    left.getNext().setPrev(right.getNext());
                }
                else
                {
                    //if they aren't adjacent, set their "inner" pointers to the ones that were adjacent to
                    //the other. 
                    right.getNext().setNext(rightOfLeft.getNext());
                    rightOfLeft.getNext().setPrev(right.getNext());
                    left.getNext().setPrev(leftOfRight.getNext());
                    leftOfRight.getNext().setNext(left.getNext());
                }
                
            }
       
    }

    @Override
    /** 
     * Returns the number of elements stored
     * @return - number of elements in the linkedlist
    */
    public int size() {
        //Return the number of nodes in the linkedlist
        return this.size;
    }

    @Override
    /** 
     * Get contents at position i
     * @param index - The index position to obtain the data
     * @return the Element at the specified index
     */
    public E get(int index)	{

        Node currNode = this.getNth(index);

        //Get the value of data at the position
        E element = currNode.getElement();

        return element;	
    }


    /** A method that returns the node at a specified index,
     *  not the content
     *  @param index - position to return the node
     * @return - Node at 'index'
     */
    // Helper method to get the Node at the Nth index
    protected Node getNth(int index) {
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException();

        Node currNode = this.head;

        //Loop through the linked list and stop at the position
        for (int i = 0; i <= index; i++) {
            currNode = currNode.getNext();
        }

        //return the node	
        return currNode; 
    }

}
