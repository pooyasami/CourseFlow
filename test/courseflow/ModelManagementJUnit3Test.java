/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package courseflow;

import java.util.ArrayList;
import java.util.Hashtable;
import junit.framework.TestCase;

/**
 *
 * @author Daphne Z
 */
public class ModelManagementJUnit3Test extends TestCase {
    
    public ModelManagementJUnit3Test(String testName) {
        super(testName);
    }

    /**
     * Test of printAllCourseByProgram method, of class ModelManagement.
     */
    public void testPrintAllCourseByProgram() {
        System.out.println("printAllCourseByProgram");

        Programs programs = new Programs();
        ArrayList<Program> pList = programs.getPrograms(); //get program list
        CourseCalendar courseCalendar = new CourseCalendar();
        /*
         * here's testing calendar version 1 of program SSE
         */
        Program p4 = new Program(4); //program 4 is SSE
        pList.add(p4);
        courseCalendar.setProgramURL("Downloads/ssev1.htm");
        courseCalendar.setPosition(6);
        courseCalendar.setProgram(p4);
        courseCalendar.setHashSemester2Program();
        Hashtable<Integer, ArrayList<Course>> tmpHash = p4.getHashProgram();

        /*
         * here's testing Semeter 1 of program SSE 
         */
        ArrayList<Course> cS1 = tmpHash.get(1);
        assertEquals(true, cS1.get(0).getCourseNum().contains("CHEM 104"));
        assertEquals(true, cS1.get(1).getCourseNum().contains("ENGG 123"));
        assertEquals(true, cS1.get(2).getCourseNum().contains("MATH 122"));
        assertEquals(true, cS1.get(3).getCourseNum().contains("PHYS 109"));
        assertEquals(true, cS1.get(4).getCourseNum().contains("MATH 110"));

        /*
         * here's testing Semeter 2 of program SSE
         */
        ArrayList<Course> cS2 = tmpHash.get(2);
        assertEquals(true, cS2.get(0).getCourseNum().contains("CS 110"));
        assertEquals(true, cS2.get(1).getCourseNum().contains("ENGG 100"));
        assertEquals(true, cS2.get(2).getCourseNum().contains("ENGL 100"));
        assertEquals(true, cS2.get(3).getCourseNum().contains("MATH 111"));
        assertEquals(true, cS2.get(4).getCourseNum().contains("PHYS 119"));

        /*
         * here's testing Semeter 3 of program SSE
         */
        ArrayList<Course> cS3 = tmpHash.get(3);
        assertEquals(true, cS3.get(0).getCourseNum().contains("PHYS 201"));
        assertEquals(true, cS3.get(1).getCourseNum().contains("CS 115"));
        assertEquals(true, cS3.get(2).getCourseNum().contains("ENEL 280"));
        assertEquals(true, cS3.get(3).getCourseNum().contains("MATH 213"));
        assertEquals(true, cS3.get(4).getCourseNum().contains("ENGG 240"));

        /*
         * here's testing Semeter 4 of program SSE
         */
        ArrayList<Course> cS4 = tmpHash.get(4);
        assertEquals(true, cS4.get(0).getCourseNum().contains("CS 210"));
        assertEquals(true, cS4.get(1).getCourseNum().contains("ENEL 282"));
        assertEquals(true, cS4.get(2).getCourseNum().contains("ENIN 233"));
        assertEquals(true, cS4.get(3).getCourseNum().contains("MATH 217"));
        assertEquals(true, cS4.get(4).getCourseNum().contains("STAT 160"));

        /*
         * here's testing Semeter 5 of program SSE
         */
        ArrayList<Course> cS5 = tmpHash.get(5);
        assertEquals(true, cS5.get(0).getCourseNum().contains("CS 215"));
        assertEquals(true, cS5.get(1).getCourseNum().contains("CS 201"));
        assertEquals(true, cS5.get(2).getCourseNum().contains("ENEL 283"));
        assertEquals(true, cS5.get(3).getCourseNum().contains("ENEL 384"));
        assertEquals(true, cS5.get(4).getCourseNum().contains("ENEV 223"));

        /*
         * here's testing Semeter 6 of program SSE
         */
        ArrayList<Course> cS6 = tmpHash.get(6);
        assertEquals(true, cS6.get(0).getCourseNum().contains("BUS 260"));
        assertEquals(true, cS6.get(1).getCourseNum().contains("CS 372"));
        assertEquals(true, cS6.get(2).getCourseNum().contains("ECON 201"));
        assertEquals(true, cS6.get(3).getCourseNum().contains("ENEL 380"));
        assertEquals(true, cS6.get(4).getCourseNum().contains("ENEL 390"));

        /*
         * here's testing Semeter 7 of program SSE
         */
        ArrayList<Course> cS7 = tmpHash.get(7);
        assertEquals(true, cS7.get(0).getCourseNum().contains("ENSE 350"));
        assertEquals(true, cS7.get(1).getCourseNum().contains("ENEL 387"));

        /*
         * here's testing Semeter 8 of program SSE
         */
        ArrayList<Course> cS8 = tmpHash.get(8);
        assertEquals(true, cS8.get(0).getCourseNum().contains("ENSE 400"));
        assertEquals(true, cS8.get(1).getCourseNum().contains("ENGG 303"));
        assertEquals(true, cS8.get(2).getCourseNum().contains("ENSE 472"));
        assertEquals(true, cS8.get(3).getCourseNum().contains("ENSE 474"));
        assertEquals(true, cS8.get(4).getCourseNum().contains("BUS 250"));

        /*
         * here's testing Semeter 9 of program SSE
         */
        ArrayList<Course> cS9 = tmpHash.get(9);
        assertEquals(true, cS9.get(0).getCourseNum().contains("ENGG 401"));
        assertEquals(true, cS9.get(1).getCourseNum().contains("ENSE 470"));
        assertEquals(true, cS9.get(2).getCourseNum().contains("ENSE 475"));
        assertEquals(true, cS9.get(3).getCourseNum().contains("ENSE 477"));
             
    }


}
