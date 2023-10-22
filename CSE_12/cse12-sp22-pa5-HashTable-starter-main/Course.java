/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources: none
 * 
 * File for managing a course. Stores relevant information and
 * allows students to be enrolled or unerolled.
 * Implements Student.java
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class that uses a hash set to keep track of enrolled students
 * stores the maximum number of students and the course infromation
 * as variables.
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;
    private final static String FORMAT = "%s %s [%s]\n%s";

    /**
     * Basic constructor that just initializes an empty hash set and
     * sets variables to parameters.
     * @param department
     * @param number
     * @param description
     * @param capacity
     */
    public Course(String department, String number, String description, 
        int capacity){
        if(department == null || number == null || description == null
            || capacity <= 0)
            {
                throw new IllegalArgumentException();
            }
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
        enrolled = new HashSet<Student>();
    }

    /**
     * @return the department variable
     */
    public String getDepartment(){
        return department;
    }

    /**
     * @return the course number
     */
    public String getNumber(){
        return number;
    }
    /**
     * @return the course description
     */
    public String getDescription(){
        return description;
    }
    /**
     * @return the capacity
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * adds a student to the HashSet
     * Doesn't allow adding if the student is already it or there's no space
     * @param student
     * @return false if the student was not enrolled, true if they were
     */
    public boolean enroll(Student student) {
        if(student == null)
        {
            throw new IllegalArgumentException();
        }
        if (!isFull() && !enrolled.contains(student))
        {
            enrolled.add(student);
            return true;
        }
        return false;
    }
    /**
     * removes a student if they're in the course
     * @param student
     * @return true if they were unenrolled, false otherwise.
     */
    public boolean unenroll(Student student) {
        if(student == null)
        {
            throw new IllegalArgumentException();
        }
        if(enrolled.contains(student))
        {
            enrolled.remove(student);
            return true;
        }
        return false;
    }

    /**
     * cancels the course by caling .clear()
     * and unenrolling all students.
     */
    public void cancel() {
        enrolled.clear();
    }
    /**
     * @return true if capacity = the number of students enrolled.
     * False otherwise
     */
    public boolean isFull() {
        if(enrolled.size() >= capacity)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * @return the number of students enrolled
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }
    /**
     * @return the number of open seats
     */
    public int getAvailableSeats() {
        return capacity - enrolled.size();
    }
    /**
     * Iterates through the HashSet to make a copy of it
     * @return a shallow copy of the HashSet
     */
    public HashSet<Student> getStudents() {
        HashSet<Student> toReturn = new HashSet<Student>();
        Iterator<Student> stuIter = enrolled.iterator();
        
        while(stuIter.hasNext())
        {
            toReturn.add(stuIter.next());
        }
        
        return toReturn;
    }
    /**
     * Iterates through the HashSet to create an array list
     * @return an array list with all the same entries as the HashSet
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> toReturn = new ArrayList<Student>();
        Iterator<Student> stuIter = enrolled.iterator();
        while(stuIter.hasNext())
        {
            toReturn.add(stuIter.next());
        }
        Collections.sort(toReturn);
        return toReturn;
    }

    /**
     * @return a string in the format:
     * CSE 12 [196]
     * Data Structures
     */
    public String toString() {
        return String.format(FORMAT, department, number, capacity, description);
        
    }
}

