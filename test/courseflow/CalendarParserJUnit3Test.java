/*
 * CalendarParserJUnit3Test.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.util.ArrayList;
import java.util.Hashtable;
import junit.framework.TestCase;

/**
 *
 * @author Daphne Z
 */
public class CalendarParserJUnit3Test extends TestCase {
    
    public CalendarParserJUnit3Test(String testName) {
        super(testName);
    }


    /**
     * Test of getAllSemester method, of class CalendarParser.
     */
    public void testGetAllSemester() throws Exception {
        CalendarParser parser = new CalendarParser();
        Programs programs = new Programs();
        
        /*
         * Here's testing the version 1 SSE course calendar
         */
        ArrayList<Program> pList = programs.getPrograms();
        Program p4 = new Program(4); //program 4 is SSE
        pList.add(p4);
        parser.setTargetFileName("Downloads/ssev1.htm");
        parser.setPosition(6);
        parser.setProgram(p4);
        parser.parse();  
        Hashtable<Integer, ArrayList<Course>> tmp = parser.getAllSemester();

        //SSE Semester 1
        ArrayList<Course> cList1 = tmp.get(1);
        //Test first course in the SSE Semester 1
        assertEquals(true, cList1.get(0).getCourseNum().contains("CHEM 104"));

        //SSE Semester 9
        ArrayList<Course> cList2 = tmp.get(9);
        //Test third course in the SSE Semester 9
        assertEquals(true, cList2.get(2).getCourseNum().contains("ENSE 475"));


        /*
         * Here's testing the version 2 PSE course calendar
         */
        Program p3 = new Program(3); //program 3 is PSE
        pList.add(p3);
        parser.setTargetFileName("Downloads/psev2.htm");
        parser.setPosition(7);
        parser.setProgram(p3);
        parser.parse();
        Hashtable<Integer, ArrayList<Course>> tmp1 = parser.getAllSemester();

        //PSE Semester 2
        ArrayList<Course> cList3 = tmp1.get(2);
        //Test second course in the SSE Semester 2
        assertEquals(true, cList3.get(1).getCourseNum().contains("ENGG 100"));

        //PSE Semester 5
        ArrayList<Course> cList4 = tmp.get(5);
        //Test fifth course in the PSE Semester 5
        assertEquals(true, cList4.get(4).getCourseNum().contains("ENEV 223"));


    }

}
