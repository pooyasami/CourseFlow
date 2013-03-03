/*
 * gCachedCourses.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.util.Hashtable;

/**
 * This class is to store the courses which are not offered in Engineering Program,
 * once such couseNum is inputed, the output would be its related course entry,
 * which is got from here
 * @author Daphne Z
 */

public class gCachedCourses {
    private Hashtable<String, String> hashOfCourseNum2Entry;


    /**
     * Constructor
     */
    public gCachedCourses(){
        hashOfCourseNum2Entry = new Hashtable<String, String>();
    }

    /**
     * Returns the course entry based on the input course number
     * @param courseNum
     * @return
     */
    public String getCourseEntryByCourseNumber(String courseNum){

        return this.hashOfCourseNum2Entry.get(courseNum);
    }

    /**
     * It inserts new course number and new course entry to the hashtable
     * @param newCourseNum
     * @param newCourseEntry
     */
    public void insertNewCourse(String newCourseNum, String newCourseEntry){
        this.hashOfCourseNum2Entry.put(newCourseNum, newCourseEntry);
    }

    /**
     * Returns the size of the hashtable
     * @return
     */
    public int getSize(){
        return hashOfCourseNum2Entry.size();
    }

    /**
     * Returns the hashtable
     * @return
     */
    public Hashtable<String, String> getAllCourses(){

            return hashOfCourseNum2Entry;
    }
}
