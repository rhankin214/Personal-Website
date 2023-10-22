///////////////////////////////////////////////////////////////////////////////
// Main Class File:    ServerQueueTester.java
// File:               BasicUser.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A CONCRETE class that maintains information about a basic user.
 *
 * Bugs: None found.
 *
 * @author Rocky Hankin
 */
public class BasicUser extends User {

    /**
     * Constructor to initialize a Basic User.
     * 
     * DO NOT MODIFY THE CONSTRUCTOR DECLARATION.
     *
     * @param name - the name of the Basic User.
     */
    public BasicUser(String name) {
        this.setName(name);
    }

    /**
     * Returns if the Basic User has priority.
     * However, all Basic Users are uneligible for priority,
     *  so this method should return false.
     * 
     * @return false
     */
    public boolean getPriority() {
        return false;
    }

    /**
     * Getter method to return information about the Basic User.
     * 
     * DO NOT MODIFY THE CONSTRUCTOR DECLARATION.
     *
     * @return a string that gives some information about the VIP User.
     */
    @Override
    public String getInformation() {
        return "BASIC USER: " + this.getName(); 
    }
}
