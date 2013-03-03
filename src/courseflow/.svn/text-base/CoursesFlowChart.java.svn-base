/*
 * CoursesFlowChart.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */
package courseflow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


/**
 * This class is for generating the DiGraph. It uses dot language to generate
 * the script for the graph and uses graphviz to generate the graph.
 * @author Pooya Samizadeh-Yazd
 */

public class CoursesFlowChart {

    private Program program;
    private ArrayList<Course> courses;
    private Hashtable<Integer, ArrayList<Course>> allSemestersHashMap;

    /**
     * Initializer
     * @param program
     */
    public CoursesFlowChart(Program program) {
        this.program = program;

    }

    /**
     * Initializer. Deprecated
     * @param courses
     */
    public CoursesFlowChart(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns the hashtable of all semesters
     * @return
     */
    public Hashtable<Integer, ArrayList<Course>> getAllSemestersHashtable() {
        return allSemestersHashMap;
    }

    /**
     * It's the setter method for the hashtable
     * @param allSemestersHashtable
     */
    public void setAllSemestersHashtable(Hashtable<Integer, ArrayList<Course>> allSemestersHashtable) {
        this.allSemestersHashMap = allSemestersHashtable;
    }

    /**
     * This method is for generating the dot file. it uses other sub methods
     * to get the results and inserts it into a file called generated-dot-file.txt
     * @throws IOException
     */
    public void Draw1() throws IOException {

        File file = new File("generated-dot-file.txt");
        BufferedWriter bwriter = new BufferedWriter(new FileWriter(file));
        bwriter.write("digraph CourseFlowChart { ranksep = 1.5;");
        for (int i = 1; i < 9; i++) {
            bwriter.write("\"Semester" + Integer.toString(i) + "\"->\"" + "Semester" + Integer.toString(i + 1) + "\";");
        }

        for (int i = 1; i < 10; i++) {

            ArrayList<Course> currentIterationCourses = allSemestersHashMap.get(i);
            Iterator iterator = currentIterationCourses.iterator();
            while (iterator.hasNext()) {
                Course courseUnderIteration = (Course) iterator.next();

                bwriter.write("\"" + courseUnderIteration.getCourseNum() + "\"[color=red];");
                bwriter.write(makeCorequisiteGraphScriptForCourse(courseUnderIteration));
                bwriter.write(makePrerequisiteGraphScriptForCourse(courseUnderIteration));
                bwriter.write("{rank = same; \"Semester" + Integer.toString(i) + "\";\""
                        + courseUnderIteration.getCourseNum() + "\";}");
                bwriter.newLine();
            }
        }

        bwriter.write("}");
        bwriter.close();

        if(generatePNGFile())
            System.out.println("The graph in png format has been generated, and saved in generated-dot-file.txt.png");

    }

    /**
     * It generates the PNG file. It has the ability to find out which OS is it
     * running on and according to that runs it in the proper way.
     * @return true if the graph is generated.
     */
    public boolean generatePNGFile() {
        String operatingSystem = System.getProperty("os.name");
        //System.out.println(operatingSystem);
        if (operatingSystem.contains("Mac")) {
            try {
                Runtime.getRuntime().exec("/usr/local/bin/dot -Tpng -O generated-dot-file.txt");
                return true;
            } catch (IOException ex) {
                System.out.println("Cannot find the dot file in the system.");
            }

        }

        if (operatingSystem.contains("Windows")) {
            try {
                Runtime.getRuntime().exec("dot -Tpng -O generated-dot-file.txt");
                return true;
            } catch (IOException ex) {
                System.out.println("Cannot find the graphviz dot.exe in the system.");
            }

        }

        if (operatingSystem.contains("Linux")) {
            try {
                Runtime.getRuntime().exec("dot -Tpng -O generated-dot-file.txt");
                return true;
            } catch (IOException ex) {
                System.out.println("Cannot find the graphviz in the system.");
            }

        }
        return false;

    }

    private int programNum;

    /**
     * For setting the programNum
     * @param programnum
     */
    public void setProgramNum(int programnum) {
        programNum = programnum;

    }

    /**
     * for getting the programNum
     * @return
     */
    public int getProgramNum() {
        return programNum;
    }

    /**
     * It generates the prerequisite dot context for the given course
     * @param course the given course
     * @return a dot formatted string
     */
    public String makePrerequisiteGraphScriptForCourse(Course course) {
        ArrayList<Course> list1 = course.getPrereq();
        ArrayList<Course> list2 = course.getPrereqOr();
        ArrayList<Course> list3 = course.getPrereqAnd();

        String result = new String();

        for (int i = 0; i < list2.size(); i++) {
            Course currentCourse = list2.get(i);
            ArrayList<Integer> currentProgramNumsOr = currentCourse.getProgramNums();
            for (int j = 0; j < currentProgramNumsOr.size(); j++) {

                int cInt = (Integer) currentProgramNumsOr.get(j);

                if (cInt == programNum) {
                    result = result + "\"" + currentCourse.getCourseNum() + "\"" + "->" + "\"" + course.getCourseNum() + "\";\n";
                }
            }
        }
        for (int i = 0; i < list3.size(); i++) {
            Course currentCourse = list3.get(i);
            ArrayList<Integer> currentProgramNumsOr = currentCourse.getProgramNums();
            for (int j = 0; j < currentProgramNumsOr.size(); j++) {

                int cInt = (Integer) currentProgramNumsOr.get(j);

                if (cInt == programNum) {
                    result = result + "\"" + currentCourse.getCourseNum() + "\"" + "->" + "\"" + course.getCourseNum() + "\";\n";
                }
            }
        }
        if ((list1 != null) && (list2 == null) && (list3 == null)) {
            for (int i = 0; i < list1.size(); i++) {
                Course currentCourse = list1.get(i);
                ArrayList<Integer> currentProgramNumsOr = currentCourse.getProgramNums();
                for (int j = 0; j < currentProgramNumsOr.size(); j++) {

                    int cInt = (Integer) currentProgramNumsOr.get(j);
                    if (cInt == programNum) {
                        result = result + "\"" + currentCourse.getCourseNum() + "\"" + "->" + "\"" + course.getCourseNum() + "\";\n";
                    }
                }
            }
        }
        return result;

        /*Iterator iterator = course.getPrereq().iterator();
        String result = new String();
        while (iterator.hasNext()) {
        Course preReq = (Course) iterator.next();
        //System.out.println(preReq.getCourseNum() + "->" + course.getCourseNum() + ";");
        result = result + "\"" + preReq.getCourseNum() + "\"" + "->" + "\"" + course.getCourseNum() + "\";\n";
        }
        return result;*/
    }

    /**
     * It generates the corequisite dot context for the given course
     * @param course the given course
     * @return a dot formatted string
     */
    public String makeCorequisiteGraphScriptForCourse(Course course) {
        ArrayList<Course> list = course.getCoreq();

        String result = new String();

        for (int i = 0; i < list.size(); i++) {
            Course currentCourse = list.get(i);
            ArrayList<Integer> currentProgramNums = currentCourse.getProgramNums();
            for (int j = 0; j < currentProgramNums.size(); j++) {

                int cInt = (Integer) currentProgramNums.get(j);

                if (cInt == programNum) {
                    result = result + "\"" + currentCourse.getCourseNum() + "\"" + "->" + "\"" + course.getCourseNum() + "\"[style=dotted];\n";
                }
            }
        }

        return result;
    }

    /**
     * <<Deprecated>> This method generates the dot syntax for the proper ranks in the dot file
     * it puts the courses in the proper ranks so the graph becomes separated
     * for different semesters
     * @return
     */
    public String makeRanksForCourses() {
        String result = new String();
        for (int i = 1; i < 10; i++) {
            Iterator iterator = allSemestersHashMap.get(i).iterator();
            while (iterator.hasNext()) {
                Course temp = (Course) iterator.next();
                result = result + "{rank = same; \"" + i + "\";\"" + temp.getCourseNum() + "\";}";
            }
        }

        return result;
    }
}
