/**
 * Name: Rocky Hankin
 * ID: A16937539
 * Email: rhankin@ucsd.edu
 * Sources: none
 * 
 * File that manages a student object. Stores the name and PID
 * and has necessary methods to sort and compare them.
 */
import java.util.Objects;

/**
 * Class for a student object. Implements the Comparable interface
 * So the students can be compared and sorted.
 */
public class Student implements Comparable<Student> {
    private final String firstName;
    private final String lastName;
    private final String PID;
    /**
     * Basic constructor that sets variables to parameters
     * @param firstName
     * @param lastName
     * @param PID
     */
    public Student(String firstName, String lastName, String PID) {
        if(firstName == null || lastName == null || PID == null)
        {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }
    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @return PID
     */
    public String getPID() {
        return PID;
    }
    /**
     * @return  true if all instance variables of the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        Student oStu = (Student) o;
        if(o != null && oStu.getFirstName().equals(this.getFirstName())
            && oStu.getLastName().equals(this.getLastName())
            && oStu.getPID().equals(this.getPID()))
        {
            return true;
        }
        return false;
    }
    /**
     * @return a hash based on the instance variables
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, PID);
    }
    /**
     * Compares two student objects based on the compareTo values of their
     * strings.
     * Compares the last names, or the first names if those are equal, or
     * the PIDs if those are equal
     * @return a positive value if this string is after the other
     * alphabetically, negative if before.
     */
    @Override
    public int compareTo(Student o) {
        if(!o.getLastName().equals(this.getLastName()))
        {
            return this.getLastName().compareTo(o.getLastName());
        }
        else if(!o.getFirstName().equals(this.getFirstName()))
        {
            return this.getFirstName().compareTo(o.getFirstName());
        }
        else 
        {
            return this.getPID().compareTo(o.getPID());
        }
    }
    
}
