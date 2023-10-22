/**
 * Name: Rocky Hankin 
 * Email: rhankin@ucsd.edu
 * Sources used: the public tester
 * 
 * Custom tester that tests different stuff from the public tester. Contains
 * 10 test methods
 */
import java.beans.Transient;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Contains 10 test methods for the methods I wrote. Covers edge cases and
 * other stuff not handled in the public tester, like removing a node with 2
 * children.
 */
public class CustomTester {
    MyBST<Integer, Integer> myCompleteTree;

    /**
     * Setup method based on the public tester. Creates a binary search
     * tree as follows:
     *          20
     *         /  \
     *       10    25
     *     /  |    / \
     *    7   15  23 30
     * The values are located as such:
     *          18
     *         /  \
     *       19    17
     *     /  |    / \
     *    90  7   36 35
     */
    @Before
    public void setup(){

        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode<>(20, 18, null);
        MyBST.MyBSTNode<Integer, Integer> twoLeft = 
            new MyBST.MyBSTNode<>(10, 19, root);
        MyBST.MyBSTNode<Integer, Integer> twoRight = 
            new MyBST.MyBSTNode<>(25, 17, root);
        MyBST.MyBSTNode<Integer, Integer> threeFarLeft = 
            new MyBST.MyBSTNode<>(7, 90, twoLeft);
        MyBST.MyBSTNode<Integer, Integer> threeMidLeft = 
            new MyBST.MyBSTNode<>(15, 7, twoLeft);
        MyBST.MyBSTNode<Integer, Integer> threeMidRight = 
            new MyBST.MyBSTNode<>(23, 36, twoRight);
        MyBST.MyBSTNode<Integer, Integer> threeFarRight =
            new MyBST.MyBSTNode<>(30, 35, twoRight);
        this.myCompleteTree = new MyBST<>();
        this.myCompleteTree.root = root;
        root.setLeft(twoLeft);
        root.setRight(twoRight);
        twoLeft.setLeft(threeFarLeft);
        twoLeft.setRight(threeMidLeft);
        twoRight.setLeft(threeMidRight);
        twoRight.setRight(threeFarRight);
        this.myCompleteTree.size = 7;
    }
    /**
     * Removes the root node to test functionality of removing 
     * from a node with two kids
     */
    @Test
    public void removeWithTwoKids() {
        /**
         * expected structure:
         *      23
         *    /    \
         *   10     25
         *  / |       \
         * 7  15      30
         */
        myCompleteTree.remove(20);
        assertEquals(myCompleteTree.root.getRight().getLeft(), null);
        assertEquals(myCompleteTree.root.getKey(), (Integer)23);
        assertEquals(myCompleteTree.root.getValue(), (Integer)36);
        assertEquals(myCompleteTree.size(), 6);
    }

    /**
     * test insert method when attempting to insert null key.
     */
    @Test
    public void insertNull (){
        try
        {
            myCompleteTree.insert(null, 2);
            fail();
        }
        catch (NullPointerException E){}
    }
    /**
     * test the search method when searching for null
     */
    @Test
    public void searchForNull () {
        assertEquals(myCompleteTree.search(null), null);
    }
    /**
     * test next method of the iterator when there is no next node
     * setUp code is based on the public tester
     */
    @Test
    public void endOfIterator() {
        MyBSTIterator<Integer, Integer> treeToIter = new MyBSTIterator<>();
        treeToIter.root = myCompleteTree.root;

        MyBSTIterator<Integer, Integer>.MyBSTValueIterator Iterator = 
            treeToIter.new MyBSTValueIterator(treeToIter.root);
        //set it to the end of the tree
        Iterator.next = treeToIter.root.getRight().getRight(); 
        Iterator.lastVisited = null;
        //first call should return the last node
        assertEquals(Iterator.nextNode().getKey(), (Integer)30);
        //second call should produce exception
        try
        {
            Iterator.next();
            fail();
        }
        catch(NoSuchElementException e) {}
    }
    /**
     * test predecessor on the smallest node
     */
    @Test
    public void testNoPredecessor()
    {
        assertEquals(myCompleteTree.root.getLeft().getLeft().predecessor(),
            null);
    }
    /**
     * Testable methods:
     * predecessor
     * insert
     * search
     * remove
     * inorder
     * nextNode
     * Calendar constructor
     * book
     */
    /**
     * Tests the inorder method on an empty tree
     */
    @Test
    public void inorderEmpty()
    {
        myCompleteTree = new MyBST<>();
        ArrayList<MyBST.MyBSTNode<Integer,Integer>> expected = 
            new ArrayList<>();
        assertEquals(expected, myCompleteTree.inorder());
    }
    /**
     * Test book when start < 0
     */
    @Test
    public void bookIllegal()
    {
        MyCalendar cal = new MyCalendar();
        try
        {
            cal.book(-1, 0);
            fail();
        }
        catch (IllegalArgumentException e)
        {

        }
    }
    /**
     * test book when start >= end
     */
    @Test
    public void bookIllegal2()
    {
        MyCalendar cal = new MyCalendar();
        try
        {
            cal.book(10, 10);
            fail();
        }
        catch (IllegalArgumentException e)
        {
        }
    }
    /**
     * test search on a string based tree
     */
    @Test
    public void stringSearch() {
        MyBST<String, Integer> stringTree = new MyBST<String, Integer>();
        MyBST.MyBSTNode<String, Integer> b = 
            new MyBST.MyBSTNode<>("b", 12, null);
        MyBST.MyBSTNode<String, Integer> a = 
            new MyBST.MyBSTNode<>("a", 19, b);
        MyBST.MyBSTNode<String, Integer> c = 
            new MyBST.MyBSTNode<>("c", 17, b);
        stringTree.root = b;
        stringTree.root.setLeft(a);
        stringTree.root.setRight(c);
        assertEquals(stringTree.search("a"), (Integer)19);
    }
    /**
     * test inorder on a string based tree
     */
    @Test
    public void inorderString() {
        MyBST<String, Integer> stringTree = new MyBST<String, Integer>();
        MyBST.MyBSTNode<String, Integer> b = 
            new MyBST.MyBSTNode<>("b", 12, null);
        MyBST.MyBSTNode<String, Integer> a = 
            new MyBST.MyBSTNode<>("a", 19, b);
        MyBST.MyBSTNode<String, Integer> c = 
            new MyBST.MyBSTNode<>("c", 17, b);
        
        stringTree.root = b;
        stringTree.root.setLeft(a);
        stringTree.root.setRight(c);

        ArrayList<MyBST.MyBSTNode<String,Integer>> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(c);
    }
}
