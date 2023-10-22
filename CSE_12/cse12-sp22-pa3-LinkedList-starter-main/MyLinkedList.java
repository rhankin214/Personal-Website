import java.util.AbstractList;
/**
 * Class containing implementation of a linked list. Additionally contains a node class
 * for the list's use
 *
 * Size: the number of items in the list
 * head: a dummy node that points to the actual head
 * tail: a dummy node that points to the actual tail
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
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
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev - new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next - new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element - new element 
         */
        public void setElement(E element) {
            this.data = element;
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

    //  Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */
    public MyLinkedList() {
        head = this.new Node(null);
        tail = this.new Node (null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * @return the number of elements in the list
     */
    @Override
    public int size() { 
        return size;
    }

    @Override
    /**
     * Returns the node at the specified index
     * @param index the index of the desired node. Causes exception if out of bounds.
     */
    public E get(int index) {
        if(index < 0 || index >= size()){ //rejects out of bounds index
            throw new IndexOutOfBoundsException();
        }
        Node curNode;
        curNode = iterateToNode(index);
        E toReturn = curNode.getElement(); //returns element of specified index
        return toReturn;
    }
    private Node iterateToNode(int index) //helper method to get to the node at index i
    {
        Node iteratorNode = new Node(null);
        iteratorNode.setNext(head.getNext()); //starts pointing at the head (index = 0)
        for(int i = 0; i < index; i++) //moves forward index number of times 
        {
            iteratorNode.setNext(iteratorNode.getNext().getNext());
        }
        return iteratorNode.getNext();
    }
    /**
     * Adds a node at a specific index. 
     * @param index the index to add the node at. Causes exception if out of bounds
     * @param data the data to pass into the new node. Causes exception if null
     */
    @Override
    public void add(int index, E data) {
        if(data == null) //reject null
        {
            throw new NullPointerException();
        }
        if(index < 0 || index > size()){ //rejects out of bounds index
            throw new IndexOutOfBoundsException();
        }
        Node nodeToAdd = this.new Node(data);
        Node curNode = iterateToNode(index);
        
        nodeToAdd.setNext(curNode);
        curNode.getPrev().setNext(nodeToAdd);
        nodeToAdd.setPrev(curNode.getPrev());
        curNode.setPrev(nodeToAdd);
        size++;
    }
    
    /**
     * Adds a new node to the end of the linked list
     * @param data the data of the new node. Causes exception if data == null.
     */
    public boolean add(E data) {
        if(data == null) //reject null
        {
            throw new NullPointerException();
        }
        Node curNode = tail.getPrev();
        Node nodeToAdd = this.new Node(data);
        curNode.setNext(nodeToAdd);
        nodeToAdd.setPrev(curNode);
        tail.setPrev(nodeToAdd);
        nodeToAdd.setNext(tail);
        size++;
        return true;
    }
    /**
     * Sets the data at the specified node
     * @param index is the index of said node
     * @param data is the data to pass in
     * @return the data that was previously in the node
     */
    public E set(int index, E data) {
        if(data == null) //reject null
        {
            throw new NullPointerException();
        }
        if(index < 0 || index >= size()){ //rejects out of bounds index
            throw new IndexOutOfBoundsException();
        }
        Node curNode = iterateToNode(index);
        E toReturn = curNode.getElement();
        curNode.setElement(data);
        return toReturn;
    }
    /**
     * Removes the specified node
     * @param index is the index of the node to remove. Causes exception if out of bounds.
     * @return the data in said node
     */
    public E remove(int index) {
        if(index < 0 || index >= size()){ //rejects out of bounds index
            throw new IndexOutOfBoundsException();
        }
        Node curNode = iterateToNode(index);
        curNode.getPrev().setNext(curNode.getNext());
        curNode.getNext().setPrev(curNode.getPrev());
        E toReturn = curNode.getElement();
        size--;
        return toReturn;
    }
    /**
     * runs the remove command until there's no nodes left in the array
     */
    public void clear() {
        while(!(head.getNext().getElement() == null))
        {
            remove(0);
        }
    }
    /**
     * @return true if there are 0 elements in the list, false if more than 0
     */
    public boolean isEmpty() {
        if(size == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Returns the node at index n. Different from get() which just returns the data in the node
     * @param index the index of the node. Causes index if out of bounds.
     * @return the node
     */
    protected Node getNth(int index) {
        if(index < 0 || index >= size()){ //rejects out of bounds index
            throw new IndexOutOfBoundsException();
        }
        Node curNode = iterateToNode(index);
        return curNode;
    }
}