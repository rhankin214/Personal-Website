/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources: Zybooks info on heap sort, javadoc info on queue interface
 * File description: Implementation of an N-ary tree, where each node
 * has N children. Has a method for adding nodes to the tree, checking
 * if the tree contains a given element, and making a sorted array out
 * of the data.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Implementation for a N-ary tree. Relies on an internal node class to store
 * data.
 */
public class CSE12NaryTree<E extends Comparable<E>> {
    
    /**
     * This inner class encapsulates the data and children for a Node.
     * Do NOT edit this inner class.
     */
    protected class Node{
        E data;
        List<Node> children;
    
        /**
         * Initializes the node with the data passed in
         * 
         * @param data The data to initialize the node with
         */
        public Node(E data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    
        /**
         * Getter for data
         * 
         * @return Return a reference to data
         */
        public E getData() {
            return data;
        }

        /**
         * Setter for the data
         * 
         * @param data Data that this node is set to
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Getter for children
         * 
         * @return reference to the list of children
         */
        public List<Node> getChildren() {
            return children;
        }

        /**
         * Returns the number of children
         * 
         * @return number of children
         */
        public int getNumChildren() {
            // assume there are no nulls in list
            return children.size();
        }

        /**
         * Add the given node to this node's list of children
         * 
         * @param node The node to add
         */
        public void addChild(Node node) {
            children.add(node);
        }
    
    }
    
    Node root;
    int size;
    int N;

    /**
     * Constructor that initializes an empty N-ary tree, with the given N
     * 
     * @param N The N the N-tree should be initialized with
     */
    public CSE12NaryTree(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.root = null;
        this.size = 0;
        this.N = N;
    }
    
    /**
     * Adds a node to the tree using inorder traversal.
     * @param element the data of the new node to add
     */
    public void add(E element) {
        if(element == null)
        {
            throw new NullPointerException();
        }

        Node toAdd = new Node(element);
        if(size == 0)
        {
            root = toAdd;
            size++;
            return;
        }
        Queue<Node> traversalQueue = new LinkedList<Node>();
        traversalQueue.add(root);
        while(true)
        {
            //if a node has the maximum number of children, add its kids to the
            //end of the queue to look at later and deque it. If it doesn't
            //have the max children, add it.
            if(traversalQueue.peek().getChildren().size() == N)
            {
                for(Node n: traversalQueue.peek().getChildren())
                {
                    traversalQueue.add(n);
                }
                traversalQueue.poll();
            }
            else{
                traversalQueue.peek().addChild(toAdd);
                size++;
                return;
            }
        }
    }

    /**
     * returns true if the element is in the tree
     * @param element
     * @return
     */
    public boolean contains(E element) {
        if(element == null)
        {
            throw new NullPointerException();
        }
        if(size == 0)
        {
            return false;
        }
        return containsHelper(element, root);
    }
    
    /**
     * Helper method for the contains method. Goes down each branch recursively
     * and returns true if any node matches element.
     * @param element the element we're looking for
     * @param N the node to check, and then check the children of.
     * @return true if the element matches N or any of its descendants, false
     * otherwise.
     */
    private boolean containsHelper(E element, Node currNode)
    {
        if(currNode.getData().compareTo(element) == 0)
        {
            return true;
        }
        for(Node child: currNode.getChildren())
        {
            if(containsHelper(element, child))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Helper method to fill the priority queue of sortTree. Adds the node and
     * then calls itself on all its children. 
     * @param curr
     * @param queue
     */
    private void populateQueue(Node curr, PriorityQueue<E> queue)
    {
        queue.add(curr.getData());
        for(Node child: curr.getChildren())
        {
            populateQueue(child, queue);
        }
    }
    /**
     * Returns an Arraylist containing all elements in the tree in ascending
     * order. Does so by filling a priority queue (which is structured like
     * a min heap) and then filling the arrayList while emptying the queue.
     * @return an ArrayList of the elements in the tree in ascending order. 
     */
    public ArrayList<E> sortTree(){
        ArrayList<E> toReturn = new ArrayList<E>();
        if(size == 0)
        {
            return toReturn;
        }

        PriorityQueue<E> minHeapQueue = new PriorityQueue<E>();
        populateQueue(root, minHeapQueue);
        /**
         * I feel weird about this implementation. It feels "too easy."
         * But since the priority queue is a min heap, removing elements and
         * adding them to toReturn matches heap sort exactly. It just cuts out
         * the swapping step in the zybooks version by being a min heap.
         * I figure if this was against the rules it wouldn't have told me to
         * use a priority queue.
         */
        while(minHeapQueue.size() > 0)
        {
            toReturn.add(minHeapQueue.remove());
        }
        return toReturn;
    }
}
