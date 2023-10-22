import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;
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

    @Override
    public ListIterator<E> listIterator()
    {
        return new MyListIterator();
    }
    @Override
    public Iterator<E> iterator()
    {
        return new MyListIterator();
    }
    /**
     * Class for an iterator object occupies a spot between nodes in a link list
     * and can move up and down it.
     * Can perform operations involving the nodes to the left and right of it.
     */
    protected class MyListIterator implements ListIterator<E> {
        
        Node left;
        Node right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        
        /**
         * Default (and only) constructor . Positions the iterator at index = 0, 
         * with left pointing at the head and right pointing at the first proper node. 
         */
        public MyListIterator(){
            left = head;
            right = head.getNext();
            forward = true;
            canRemoveOrSet = false;
            idx = 0;
        } 
        /**
         * Checks if there's a next node. Returns false if right is pointing at the tail
         * @return a boolean stating whether or not there's a next
         * Used in the next() method to catch illegal argument exceptions.
         */
        public boolean hasNext()
        {
            //right can't point to head, so the only possible null element is the tail. 
            //if right points to tail, then there's no next.
            if(right.getElement() == null)
            {
                return false;
            }
            else {
                return true;
            }
        }
        /**
         * See it's funny because it's used in the next() method which is also the next method
         * Always explain your jokes, they're funnier that way
         * 
         * Moves the pointers both one step to the right and returns the data of
         * the node right one was pointing at. Enables removing and setting. Sets forward to false.
         */
        public E next()
        {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }
            E toReturn = right.getElement();
            left = left.getNext();
            right = right.getNext();
            idx += 1;
            forward = true;
            canRemoveOrSet = true;
            return toReturn;
        }
        /**
         * Does the same things as next but in the other direction. Returns data corresponding to 
         * what the left node is before the method runs. 
         */
        public E previous()
        {
            if(!hasPrevious())
            {
                throw new NoSuchElementException();
            }
            E toReturn = left.getElement();
            left = left.getPrev();
            right = right.getPrev();
            idx -= 1;
            forward = false;
            canRemoveOrSet = true;
            return toReturn;
        }
        /**
         * like hasNext(), is used in the previous method (called previous()) to catch exceptions.
         * Returns false if the left points at the head
         */
        public boolean hasPrevious()
        {
            //left can't point to tail, so the only possible null element is the head. 
            //if left points to head, then there's no prev.
            if(left.getElement() == null)
            {
                return false;
            }
            else {
                return true;
            }
        }
        /**
         * @return the index of the node right points to.
         */
        public int nextIndex()
        {
            return idx;
        }
        /**
         * @return the index of the node left points to which, conveniently,
         * is one less than the one right points to.
         */
        public int previousIndex()
        {
            return idx - 1;
        }
        /**
         * Adds a node between the left and right pointers, sets the left and right's current nodes 
         * accordingly, and moves the left pointer to the newly created node.
         * Increments the index
         */
        public void add(E element)
        {
            if(element.equals(null))
            {
                throw new NullPointerException();
            }
            Node nodeToAdd = new Node(element);
            nodeToAdd.setNext(right); //node goes between right and left
            nodeToAdd.setPrev(left); 
            right.setPrev(nodeToAdd); //update right and left's pointers to new node
            left.setNext(nodeToAdd);
            left = nodeToAdd; //new node becomes the new left
            canRemoveOrSet = false;
            idx += 1;
        }
        /**
         * Sets the data of the node left or right points to, depending on which direction was moved
         * in last.
         * Sets right if it last moved backwards, left if forwards.
         */

        public void set(E element)
        {
            if (element.equals(null))
            {
                throw new NullPointerException();
            }
            else if(!canRemoveOrSet)
            {
                throw new IllegalStateException();
            }
            if(forward) //the element returned by the last next is the current previous
            {
                left.setElement(element);
            }
            else{ //the element returned by the last previous is the current next.
                right.setElement(element);
            }
        }
        /**
         * removes left node if moving forward, right node if backwards
         * Decrements index only if moving forwards because otherwise right's relative position
         * stay's the same.
         * If going forwards, the new left is the node left of the old left
         * If going backwards, the new right is the node right of the old right
         */
        public void remove()
        {
            if(!canRemoveOrSet)
            {
                throw new IllegalStateException();
            }
            if(forward) //node returned by last next is current left.
            {
                left.getPrev().setNext(right); //make nodes adjacent to left point to each other
                right.setPrev(left.getPrev());
                left.setNext(null); //disable left's pointers
                left.setPrev(null);
                left = right.getPrev(); //left now points to the one left of the removed node
                idx -= 1;
            }
            else //node returned by last previous is current right
            {
                right.getNext().setPrev(left); //make nodes adjacent to right point to each other
                left.setNext(right.getNext());
                right.setNext(null); //disable right's pointers
                right.setPrev(null);
                right = left.getNext(); //right now points to the one right of the removed node
            }
            canRemoveOrSet = false;
        }
    }
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