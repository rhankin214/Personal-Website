import javax.swing.plaf.nimbus.NimbusLookAndFeel;

///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    PA6.java
// File:               RedditDataPoint.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin, rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//
///////////////////////////////////////////////////////////////////////////////


public class RedditDataPoint {

    private String name = "";
    private String text = "";
    private double lex_liwc_i = 0.0;
    private double lex_liwc_we = 0.0;
    private double lex_liwc_shehe = 0.0;
    
    /*
     * A constructor for the class.
     */
    public RedditDataPoint(String name, String text, double lex_liwc_i, double lex_liwc_we, double lex_liwc_shehe) {
        this.name = name;
        this.text = text;
        this.lex_liwc_i = lex_liwc_i;
        this.lex_liwc_we = lex_liwc_we;
        this.lex_liwc_shehe = lex_liwc_shehe;
    }

    /*
     * returns the name
     */
    public String getName(){
        return name;
    }

    /*
     * returns the text
     */
    public String getText(){
        return text;
    }

    /*
     * returns lex_liwc_i
     */
    public double getLexLiwcI() {
        return lex_liwc_i;
    }

    /*
     * returns lex_liwc_we
     */
    public double getLexLiwcWe() {
        return lex_liwc_we;
    }

    /*
     * return lex_liwc_shehe
     */
    public double getLexLiwcShehe() {
        return lex_liwc_shehe;
    }
}