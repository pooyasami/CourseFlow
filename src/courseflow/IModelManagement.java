/*
 * IModelManagement.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This Interface API defines functions called by UI
 * @author Daphne Z
 */

public interface IModelManagement {

    public void printAllCourseByProgram(int programNum);

    public boolean getProgramSemesterByCourse(String courseNum);

    public Hashtable<Integer, ArrayList<Course>> getHashSemesterCourse(int programNum);

    public Course getCourse(int programNum, String courseNum);

    public void setProgram(int programNum);

    public void processCourseCalendarAndCatalogue();

    public CachedCourses getCachedCourses();

    public gCachedCourses getGCachedCourses();

    public void printPrereqCourses(Course cCourse, int currentProgramNum);

    public void printCoreqCourses(Course cCourse, int currentProgramNum);

    public void printDependentCourses(Course cCourse, int currentProgramNum);

    public void printSemester(Course c, int currentProgramNum);

}
