/*
 * CoursesFlowChartJUnit3Test.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */
package courseflow;

import junit.framework.TestCase;

/**
 *
 * @author Pooya
 */
public class CoursesFlowChartJUnit3Test extends TestCase {

    /**
     * Test of makePrerequisiteGraphScriptForCourse method, of class CoursesFlowChart.
     */
    public void testMakePrerequisiteGraphScriptForCourse() throws Exception {

        GlobalConfig config = GlobalConfig.instance();
        config.setVersion(1);
        ModelManagement mm = ModelManagement.instance();
        mm.processCourseCalendarAndCatalogue();

        CoursesFlowChart flowchart = new CoursesFlowChart(new Program(1));
        flowchart.setAllSemestersHashtable(mm.getHashSemesterCourse(3));

        // ENEL 280
        assertEquals(true, flowchart.makePrerequisiteGraphScriptForCourse(flowchart.getAllSemestersHashtable().get(3).get(1)).contains("\"PHYS 119\"->\"ENEL 280\";"));
        assertEquals(true, flowchart.makePrerequisiteGraphScriptForCourse(flowchart.getAllSemestersHashtable().get(3).get(1)).contains("\"MATH 111\"->\"ENEL 280\";"));

    }

    /**
     * Test of makeCorequisiteGraphScriptForCourse method, of class CoursesFlowChart.
     */
    public void testMakeCorequisiteGraphScriptForCourse() throws Exception {
        GlobalConfig config = GlobalConfig.instance();
        config.setVersion(1);
        ModelManagement mm = ModelManagement.instance();
        mm.processCourseCalendarAndCatalogue();

        CoursesFlowChart flowchart = new CoursesFlowChart(new Program(3));
        flowchart.setAllSemestersHashtable(mm.getHashSemesterCourse(3));
        flowchart.setProgramNum(3);

        //System.out.println(flowchart.getAllSemestersHashtable().get(3).get(2).getCourseNum());
        //System.out.println(flowchart.getAllSemestersHashtable().get(3).get(2).getCoreq().get(0).getCourseNum());
        //System.out.println(flowchart.getAllSemestersHashtable().get(3).get(2).getProgramNums());
        assertEquals(true,flowchart.makeCorequisiteGraphScriptForCourse(flowchart.getAllSemestersHashtable().get(3).get(2)).contains("\"GEOL 102\"->\"ENPE 241\""));

    }
}
