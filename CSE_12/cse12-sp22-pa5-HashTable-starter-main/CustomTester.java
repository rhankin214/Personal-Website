/**
 * Custom tester that tests various test cases
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources: Hashmap documentation
 * https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
 * 
 * This is the testfile created by me to make sure I implemented
 * stuff correctly. Also because I had to.
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * JUnit class for edge cases, exceptions, and other things not
 * tested in the public tester. Contains more basic tests for methods
 * not covered in the public tester. Added an additional test so there's
 * one more than normal.
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    
    Course CSE12;
    Student John;
    Student Joanne;
    Student Steve;
    Student nullStudent;
    /**
     * instantiates some students and a course for convenience
     */
    @Before
    public void setup() {
        CSE12 = new Course("CS", "12", "Data Structure", 196);
        John = new Student("John", "Smith", "A123");
        Joanne = new Student("Joanne", "Planter", "A2342");
        Steve = new Student("Steve", "Samson", "A323");
        nullStudent = null;
    }
    // ----------------Student class----------------
    /**
     * Test the equals method when the student input is null
     */
    
    @Test
    public void testEquals() {
        assertFalse(John.equals(nullStudent));
    }

    /**
     * Test the compareTo method to make sure it checks last name first
     */
    @Test
    public void testCompareTo() {
        assertEquals(John.compareTo(Steve), 
            John.getLastName().compareTo(Steve.getLastName()));
    }

    // ----------------Course class----------------
    /**
     * Test the enroll method when attempting to enroll a null student.
     */
    
    @Test
    public void testEnroll() {
        try
        {
            CSE12.enroll(nullStudent);
            fail();
        }
        catch(IllegalArgumentException e) {}
    }
    /**
     * Test the get students method to make sure the entries are shallow copies
     */
    @Test
    public void testGetStudentsShallowCopy(){
        CSE12.enroll(John);
        CSE12.enroll(Joanne);
        CSE12.enroll(Steve);
        HashSet<Student> shallowCopy = CSE12.getStudents();
        Iterator CSE12Iter = CSE12.enrolled.iterator();
        Iterator shallowCopyIter = shallowCopy.iterator();

        while(CSE12Iter.hasNext())
        {
            //use simple equals comparison to see if each object has the same reference
            if(!(CSE12Iter.next() == shallowCopyIter.next()))
            {
                fail();
            }
        }
    }
    /**
     * Test the unenroll method when attempting to unenroll
     * a student not in the course and a null student
     */
    @Test
    public void testUnenroll() {
        assertFalse(CSE12.unenroll(Joanne));
        try
        {
            CSE12.unenroll(nullStudent);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }

    /**
     * Test the getRoster to make sure it sorts the students
     */
    @Test
    public void testGetRoster() {
        CSE12.enroll(John);
        CSE12.enroll(Joanne);
        CSE12.enroll(Steve);
        ArrayList<Student> roster = CSE12.getRoster();
        
        //check that entry at index 0 is less than index 1, and index 1
        //less than index 2
        assertTrue(roster.get(0).compareTo(roster.get(1)) < 0);
        assertTrue(roster.get(1).compareTo(roster.get(2)) < 0);
    }

    // ----------------Sanctuary class----------------
    /**
     * Test the constructor when putting in invalid arguments
     */
    @Test
    public void testSanctuaryConstructor() {
        Sanctuary sanct;
        try
        {
            sanct = new Sanctuary(-1, 0);
            fail();
        }
        catch (IllegalArgumentException e) {}
        try
        {
            sanct = new Sanctuary(0, -1);
        }
        catch (IllegalArgumentException e) {}
    }

    /**
     * Test the rescue method when adding an new species to a sanctuary with 
     * capped species
     */
    @Test
    public void testRescueTestOne(){
        Sanctuary sanct = new Sanctuary(50, 3);
        sanct.sanctuary.put("Panda", 3);
        sanct.sanctuary.put("Grizzly", 3);
        sanct.sanctuary.put("Polar Bear", 3);

        assertEquals(sanct.rescue("Koala", 4), 4);
    }

    /**
     * Test the rescue method when only able to rescue some of the animals
     */
    @Test
    public void testRescueTestTwo(){
        Sanctuary sanct = new Sanctuary(10, 10);
        sanct.sanctuary.put("Panda", 4);
        sanct.sanctuary.put("Grizzly", 4);

        assertEquals(sanct.rescue("Koala", 6), 4);
        //check that the sanctuary is now full
        assertEquals(sanct.getTotalAnimals(), sanct.maxAnimals);
    }

    /**
     * Test the release method when attempting to remove more than there are
     * Or removing a negative amount
     */
    @Test
    public void testReleaseTestOne(){
        Sanctuary sanct = new Sanctuary(10, 10);
        sanct.sanctuary.put("Penguin", 3);
        try
        {
            sanct.release("Penguin", 4);
            fail();
        }
        catch(IllegalArgumentException e) {}
        try
        {
            sanct.release("Penguin", -1);
            fail();
        }
        catch(IllegalArgumentException e) {}
    }

    /**
     * Test the release method when it removes all of a species.
     */
    @Test
    public void testReleaseTestTwo(){
        Sanctuary sanct = new Sanctuary(10, 10);
        sanct.sanctuary.put("Penguin", 3);
        
        sanct.release("Penguin", 3);
        assertEquals(0, sanct.sanctuary.size());
    }
}

