///////////////////////////////////////////////////////////////////////////////
// Main Class File:    SchoolTester.java
// File:               School.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A class that maintains the attributes of a school. This includes the name
 * of the school and a list of students.
 *
 * Bugs: None found.
 *
 * @author (YOUR NAME)
 */
public class School {
    // DO NOT CHANGE THE CODE FOR THE PRIVATE MEMBER VARIABLES.
    private String name;
    private Student[] students;

    /**
     * Constructor to initialize the School object.
     * You should be performing a deep copy.
     * 
     * DO NOT MODIFY THE CONSTRUCTOR DECLARATION.
     *
     * @param name the name to (deep) initialize with
     * @param students the students to (deep) copy from
     */
    public School(String name, Student[] students) {
        this.name = name;
        this.students = new Student[students.length];
        for(int i = 0; i < students.length; i++)
        {
            this.students[i] = new Student(students[i]);
        }
    }

    /**
     * Getter method to return `name`.
     * 
     * DO NOT MODIFY THIS METHOD.
     * 
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method to return `students`.
     * 
     * DO NOT MODIFY THIS METHOD.
     * 
     * @return this.students
     */
    public Student[] getStudents() {
        return this.students;
    }

    /**
     * This method calculates the average (mean) score per student as a
     *  double. If the student name is "N/A", or if the student has
     *  no scores, their corresponding element should be `-1.0`.
     * 
     * No need to round the double to any decimal point - just leave it
     *  as is.
     * 
     * DO NOT MODIFY THE METHOD DECLARATION.
     * 
     * @return an array where each element represents the average score
     *  per student
     */
    public double[] meanScore() {
        //NOTE: I read ahead and decided to implement the next method first
        //I thought it would save me some time.
        double[] meanScores = new double[students.length];
        for(int i = 0; i < students.length; i++)
        {
            meanScores[i] = meanScore(i);
        }
        return meanScores;
    }

    /**
     * This method calculates the average (mean) score as a double for the\
     *  specified student. If the student name is "N/A", or if the student has
     *  no scores, then you should return `-1`. Be sure to check other
     *  edge cases.
     * 
     * No need to round the double to any decimal point - just leave it
     *  as is.
     * 
     * DO NOT MODIFY THE METHOD DECLARATION.
     * 
     * @return the average (mean) score of student at parameter `idx`.
     */
    public double meanScore(int idx) {
        if(idx >= students.length)
        {
            return -1;
        }
        if(idx >= students[idx].getScores().length)
        if(students[idx].getScores().length == 0 || students[idx].getName().equals("N/A"))
        {
            return -1;
        }
        double numScores = 0;
        double totalScore = 0;
        for(int i = 0; i < students[idx].getScores().length; i++)
        {
            totalScore += students[idx].getScores()[i];
            numScores += 1;
        }
        return totalScore/numScores;

    }
}
