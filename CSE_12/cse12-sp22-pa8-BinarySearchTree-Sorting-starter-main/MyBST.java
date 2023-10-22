/**
 * File for a binary search tree data structure
 * Name: Rocky Hankin 
 * Email: rhankin@ucsd.edu
 * Sources used: none
 * 
 * File for a binary search tree data structure, where each node is greater
 * than it's left child and smaller than their right child (if they exist).
 * Additionally, all descendants of a node's left child are also smaller,
 * and all descendants of a node's right child are bigger. This allows for
 * fast and easy searching.
 */
import java.util.ArrayList;

/**
 * Class for a binary search tree. Instance variables keep track of the root
 * node and size. Each node stores a reference to it's up to two children and 
 * its parent.
 */
public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;
    /**
     * @return the number of nodes in the tree
     */
    public int size(){
        return size;
    }
    /**
     * Sets the value of a node of the given key
     * If none exist, inserts it at an appropriate location.
     * @param key the key of the node to insert
     * @param value the value 
     * @return the changed value of the existing node,
     * or none if there wasn't one
     */
    public V insert(K key, V value){
        if(key == null)
        {
            throw new NullPointerException();
        }
        MyBSTNode<K,V> curr = this.root;
        //if the root is null, insert at the root
        MyBSTNode<K,V> toInsert = new MyBSTNode<K,V>(key, value, null);
        if(curr == null)
        {
            root = toInsert;
            size++;
            return null;
        }
        while(curr != null)
        {
            if(key.compareTo(curr.getKey()) == 0)
            {
                V temp = curr.getValue();
                curr.setValue(value);
                return temp;
            }
            //if toInsert is < curr
            if(key.compareTo(curr.getKey()) < 0)
            {
                //if there is no left child, it becomes one
                if(curr.getLeft() == null)
                {
                    curr.setLeft(toInsert);
                    toInsert.setParent(curr);
                    size++;
                    return null;
                }
                // if it's less than or equal to the left child too, 
                // progress down the tree
                else if(key.compareTo(curr.getLeft().getKey()) <= 0)
                {
                    curr = curr.getLeft();
                }
                //if the key is between curr and it's left child, insert
                //it between them
                else if(key.compareTo(curr.getLeft().getKey()) > 0)
                {
                    toInsert.setParent(curr);
                    toInsert.setLeft(curr.getLeft());
                    toInsert.getLeft().setParent(toInsert);
                    toInsert.getParent().setLeft(toInsert);
                    size++;
                    return null;
                }
            }

            else if(key.compareTo(curr.getKey()) > 0)
            {
                //if there is no right child, it becomes one
                if(curr.getRight() == null)
                {
                    curr.setRight(toInsert);
                    toInsert.setParent(curr);
                    size++;
                    return null;
                }
                // if it's greater than or equal to the right child too, 
                // progress down the tree
                if(key.compareTo(curr.getRight().getKey()) >= 0)
                {
                    curr = curr.getRight();
                }
                //if the key is between curr and it's right child, insert
                //it between them
                else if(key.compareTo(curr.getRight().getKey()) < 0)
                {
                    toInsert.setParent(curr);
                    toInsert.setRight(curr.getRight());
                    toInsert.getRight().setParent(toInsert);
                    toInsert.getParent().setRight(toInsert);
                    size++;
                    return null;
                }
            }
        }
        
        return null;
    }

    /**
     * Gets the value of the node of the specified key. 
     * Returns null if that node doesn't exist
     * @param key the key of the node to search for
     * @return the value of that node, or null if it doesn't exist.
     */
    public V search(K key){
        MyBSTNode<K,V> curr = this.root;
        if(key == null)
        {
            return null;
        }
        while(curr != null)
        {
            if(curr.getKey().compareTo(key) == 0)
            {
                return curr.getValue();
            }
            else if(curr.getKey().compareTo(key) > 0)
            {
                curr = curr.getLeft();
            }
            else if(curr.getKey().compareTo(key) < 0)
            {
                curr = curr.getRight();
            }
        }
        return null;
    }
    /**
     * helper method to search for a node for the remove method.
     * Similar to the normal search method but returns the node instead of
     * the value.
     * @param key key of the node to search for
     * @return the node, or null if it doesn't exist
     */
    private MyBSTNode<K,V> searchNode(K key)
    {
        MyBSTNode<K,V> curr = this.root;
        while(curr != null)
        {
            if(curr.getKey().compareTo(key) == 0)
            {
                return curr;
            }
            else if(curr.getKey().compareTo(key) > 0)
            {
                curr = curr.getLeft();
            }
            else if(curr.getKey().compareTo(key) < 0)
            {
                curr = curr.getRight();
            }
        }
        return null;
    }
    /**
     * Removes the node of the matching key from the BST
     * If the node has children, the node is replaced by its successor and then
     * removed. We implement this by just changing the data because it'd be
     * tedious to actually swap them.
     * @param key the key of the node to remove
     * @return the value of the removed node, or null if there's no such node
     */
    public V remove(K key){
        if(search(key) == null)
        {
            return null;
        }
        MyBSTNode<K,V> toRemove = searchNode(key);
        V toReturn = toRemove.getValue();
        if(toRemove.getLeft() == null && toRemove.getRight() == null)
        {
            //if the leaf node is the root, set root to null
            //if the leaf node is the right node
            if(toRemove.getParent() == null)
            {
                this.root = null;
                return toRemove.getValue();
            }

            else if(toRemove.getParent().getRight() == toRemove)
                toRemove.getParent().setRight(null);
            else
                toRemove.getParent().setLeft(null);

            toRemove.setParent(null);
            size--;
            return toRemove.getValue();
        }
        else if(toRemove.getLeft() == null)
        {
            swapAndRemove(toRemove, toRemove.getRight());
            return toRemove.getValue();
        }
        else if(toRemove.getRight() == null)
        {
            swapAndRemove(toRemove, toRemove.getLeft());
            return toRemove.getValue();
        }
        else {
            toRemove.setValue(toRemove.successor().getValue());
            K temp = toRemove.successor().getKey();
            remove(toRemove.successor().getKey());
            toRemove.setKey(temp);
            return toReturn;          
        }
    }
    /**
     * Helper method to swap a kid with it's parent and remove the parent
     * Precondition: the kid is the only kid
     * @param toRemove the node to remove
     * @param kid the child to take its place
     */
    private void swapAndRemove(MyBSTNode<K,V> toRemove, MyBSTNode<K,V> kid)
    {       
        if(toRemove.getParent().getRight() == toRemove)
        {
            toRemove.getParent().setRight(kid);
        }
        else {
            toRemove.getParent().setLeft(kid);
        }
        kid.setParent(toRemove.getParent());
        toRemove.setParent(null);
        
        size--;
        toRemove.setLeft(null);
        toRemove.setRight(null);
    }
    
    /**
     * Creates an arrayList of all the elements in the tree in ascending
     * order.
     * @return that arrayList
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList<MyBSTNode<K, V>> toReturn = new ArrayList<>();
        if(this.root != null)
        {
            inorderHelper(toReturn, root);
        }
        return toReturn;
    }

    /**
     * Recursive method to do what inorder() does without me technically
     * changing the method header. Recursively adds all a node's left 
     * children, then itself, then the right children.
     * Could've just done a loop of the successor method but that felt
     * like cheating somehow
     * @param toReturn the ArrayList to modify
     * @param startNode the node to start at. First call is the root.
     */
    private void inorderHelper(ArrayList<MyBSTNode<K,V>> toReturn, 
        MyBSTNode<K,V>startNode)
    {
        if(startNode.getLeft() != null)
        {
            inorderHelper(toReturn, startNode.getLeft());
            
        }
        toReturn.add(startNode);
        if(startNode.getRight() != null)
        {
            inorderHelper(toReturn, startNode.getRight());
        }
    }
    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            /**
             * The successor is the smallest node that's larger than the one
             * in question
             */
            if(this.getRight() != null){
                //so we climb one step to the right to get a bigger one
                MyBSTNode<K,V> curr = this.getRight();
                //then go as far left as we can
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                return curr;
            }
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // or, if there's no right child, we climb up until the parent
                // is bigger than the starting one, i.e. when we're in the left
                // child and the current node isn't the right child.
                // Or we return the root, if that comes first.
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /**
         * Finds the largest node that's smaller than the node in question
         * @return said node.
         */
        public MyBSTNode<K, V> predecessor(){
            if(this.getLeft() != null)
            {
                MyBSTNode<K,V> curr = this.getLeft();
                while(curr.getLeft() != null)
                {
                    curr = curr.getRight();
                }
                return curr;
            }
            else {
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;

                while(parent != null && curr == parent.getLeft())
                {
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>) obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}