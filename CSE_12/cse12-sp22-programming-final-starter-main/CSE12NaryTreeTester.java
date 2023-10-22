/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * File description: File to test the N-ary tree class. Contains 4 tests, 3
 * based on the writeup and one of my own.
 */
 
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * Class for testing the N-ary tree class
 */
public class CSE12NaryTreeTester {
    CSE12NaryTree<Integer> int5aryTree;

    @Before
    public void setup()
    {
        //create tree
        int5aryTree = new CSE12NaryTree<>(5);
        int5aryTree.add(8);
        int5aryTree.add(9);
        int5aryTree.add(2);
        int5aryTree.add(7);
        int5aryTree.add(8);
        int5aryTree.add(9);
        int5aryTree.size = 6;
    }
    /**
     * Tests the add method when it's a 5-ary tree and it's just the root
     * and its 5 kids
     */
    @Test
    public void testAdd(){
        int5aryTree.add(6);
        //new node should be child of root's first child
        assertEquals((Integer) 6, int5aryTree.root.getChildren().get(0)
            .getChildren().get(0).getData());  
        //size should be incremented
        assertEquals(7, int5aryTree.size);     

    }

    /**
     * test contains when the element isn't present
     */
    @Test
    public void testContains(){
        //should return false
        assertFalse(int5aryTree.contains(90));
    }

    /**
     * Test sortTree when it's a 5ary tree and the root node is the only node
     */
    @Test
    public void testSortTree(){
        //get rid of the kids
        int5aryTree.root.children.clear();
        int5aryTree.size = 1;

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(8);
        assertEquals(expected, int5aryTree.sortTree());
    }

    /**
     * Test sortTree when the tree has > 1 node. This makes sure that sortTree
     * isn't moving anything and also that it puts things in the right order.
     */
    @Test
    public void testCustom(){
        //add a few kids to the third row
        int5aryTree.add(17);
        int5aryTree.add(1);
        /**
         * expected order:
         * 1, 2, 7, 8, 8, 9, 9, 17
         */
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(7);
        expected.add(8);
        expected.add(8);
        expected.add(9);
        expected.add(9);
        expected.add(17);

        assertEquals(expected, int5aryTree.sortTree());

        //ensure two of the nodes are where they're supposed to be
        assertEquals((Integer) 17, int5aryTree.root.
            getChildren().get(0).getChildren().get(0).getData());
        
        assertEquals((Integer) 7, int5aryTree.root.
            getChildren().get(2).getData());
    }
}
