/*
 * CachedCourses.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;
import java.util.Hashtable;

/**
 * This class is for storing the courses which are offered in Engineering Program
 * @author Daphne Z
 */

public class CachedCourses {

    private Hashtable<String, Course> hashOfCourseNum2Course;

    /**
     * Constructor
     * Initialize hashOfCourseNum2Course
     * 
     */
    public CachedCourses(){
        hashOfCourseNum2Course = new Hashtable<String, Course>();
    }

    /**
     * Returns the Course object from the hashtable
     * @param courseNum: the user input String course number
     * @return
     */
    public Course getCourseByCourseNumber(String courseNum){

        return this.hashOfCourseNum2Course.get(courseNum);
    }


    /**
     * It inserts the new course object into the hashtable 
     * @param newCourse
     */
    public void insertNewCourse(Course newCourse){
        this.hashOfCourseNum2Course.put(newCourse.getCourseNum(), newCourse);
    }

    /**
     * Returns the size of the hashtable
     * @return
     */
    public int getSize(){
        return hashOfCourseNum2Course.size();
    }
    /**
     * Returns the Hashtable hashOfCourseNum2Course
     * @return
     */
    public Hashtable<String, Course> getAllCourses(){

            return hashOfCourseNum2Course;
    }
}
