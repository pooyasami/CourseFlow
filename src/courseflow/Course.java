/*
 * Course.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.util.ArrayList;

/**
 * This class stores all the course information
 * @author Daphne Z
 */
public class Course {

    private String number;
    private String creditHourInfo;

    private ArrayList<Course> prereq;
    private ArrayList<Course> prereqOr;
    private ArrayList<Course> prereqAnd;

    private ArrayList<String> strPrereq;
    private ArrayList<String> strPrereqOr;
    private ArrayList<String> strPrereqAnd;

    private ArrayList<Course> coreq;
    private String prereqPhrase;
    private String coreqPhrase;

    private String entry;    
    private ArrayList<Integer> strProgram = new ArrayList<Integer>();

    /**
     * Constructor
     * @param number: course number string
     */
    public Course(String number)
    {
        this.number = number;
        this.prereq = new ArrayList<Course>();
        this.coreq = new ArrayList<Course>();
    }

    /**
     * Returns course number
     * @return
     */
    public String getCourseNum(){
        return number;
    }

    /**
     * Returns course credit hour
     * @return
     */
    public String getCreditHourInfo(){
        return this.creditHourInfo;
    }

    /**
     * Sets course credit hour
     * @param creditHourInfo
     */
    public void setCreditHourInfo(String creditHourInfo){
        this.creditHourInfo = creditHourInfo;
    }

    /**
     * Returns course prerequisite phrase
     * @return
     */
    public String getPrereqPhrase(){
        return prereqPhrase;
    }

    /**
     * Sets course prerequisite phrase 
     * @param prereqPhrase
     */
    public void setPrereqPhrase(String prereqPhrase){
        this.prereqPhrase = prereqPhrase;
    }

    /**
     * Returns corequisite phrase
     * @return
     */
    public String getCoreqPhrase(){
        return coreqPhrase;
    }

    /**
     * Sets corequisite phrase
     * @param coreqPhrase
     */
    public void setCoreqPhrase(String coreqPhrase){
        this.coreqPhrase = coreqPhrase;
    }

   /**
    * Sets ArrayList<String> course prerequisite
    * @param newCourse
    */
    public void setStrPrereq(ArrayList<String> newCourse){
       this.strPrereq = newCourse;
   }

    /**
     * Returns ArrayList<String> course prerequisite
     * @return
     */
    public ArrayList<String> getStrPrereq(){
        return this.strPrereq;
    }

   /**
    * Sets ArrayList<Course> course prerequisite
    * @param newCourse
    */
    public void setPrereq(ArrayList<Course> newCourse){
       this.prereq = newCourse;
   }

    /**
     * Returns ArrayList<Course> course prerequisite
     * @return
     */
    public ArrayList<Course> getPrereq() {
        return this.prereq;
    }

    /**
     * Returns ArrayList<Course> course prerequisite containing "or" logic
     * @return
     */
    public ArrayList<Course> getPrereqOr(){

        return this.prereqOr;
    }

    /**
     * Returns ArrayList<Course> course prerequisite containing "and" logic
     * @return
     */
    public ArrayList<Course> getPrereqAnd(){

        return this.prereqAnd;
    }

    /**
     * Sets ArrayList<String> course prerequisite containing "or" logic
     * @param newCourse
     */
    public void setStrPrereqOr(ArrayList<String> newCourse){
        this.strPrereqOr = newCourse;
    }

    /**
     * Returns ArrayList<String> course prerequisite containing "or" logic
     * @return
     */
    public ArrayList<String> getStrPrereqOr(){
        return this.strPrereqOr;
    }

    /**
     * Sets ArrayList<Course> course prerequisite containing "or" logic
     * @param newCourse
     */
    public void setPrereqOr(ArrayList<Course> newCourse){
        this.prereqOr = newCourse;
    }

    /**
     * Returns ArrayList<String> course prerequisite containing "and" logic
     * @return
     */
    public ArrayList<String> getStrPrereqAnd(){
        return this.strPrereqAnd;
    }

    /**
     * Sets ArrayList<String> course prerequisite containing "and" logic
     * @param newCourse
     */
    public void setStrPrereqAnd(ArrayList<String> newCourse){
        this.strPrereqAnd = newCourse;
    }

    /**
     * Sets ArrayList<Course> course prerequisite containing "and" logic
     * @param newCourse
     */
    public void setPrereqAnd(ArrayList<Course> newCourse){
        this.prereqAnd = newCourse;
    }


    /**
     * Sets ArrayList<Course> course corequisite
     * @param newCourse
     */
    public void setCoreq(ArrayList<Course> newCourse){
        this.coreq = newCourse;

    }

    /**
     * Returns ArrayList<Course> course corequisite
     * @return
     */
    public ArrayList<Course> getCoreq() {
        return coreq;
    }

    /**
     * Returns whole course entry
     * @return
     */
    public String getEntry(){
        return this.entry;
    }

    /**
     * Sets whole course entry
     * @param entry
     */
    public void setEntry(String entry){
        this.entry = entry;
    }
    
    /**
     * It adds the program number into ArrayList<Integer> strProgram,
     * checks if the program number alreday been added, return,
     * otherwise, it adds the new program number into the arraylist
     * @param programNum
     */
    public void addProgramNum(int programNum){
        for(int i=0; i<strProgram.size(); i++){
            int strp = strProgram.get(i);
            if(strp == programNum){
                return;
            }
        }
        strProgram.add(programNum);

    }

    /**
     * Returns the ArrayList<Integer> strProgram
     * @return
     */
    public ArrayList<Integer> getProgramNums(){
        return strProgram;
    }
}
