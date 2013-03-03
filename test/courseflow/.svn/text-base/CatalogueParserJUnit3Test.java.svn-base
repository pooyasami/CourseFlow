/*
 * CatalogueParserJUnit3Test.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */
package courseflow;

import java.util.ArrayList;
import java.util.HashMap;
import junit.framework.TestCase;

/**
 *
 * @author Pooya Samizadeh-Yazd
 */
public class CatalogueParserJUnit3Test extends TestCase {

    public CatalogueParserJUnit3Test(String testName) {
        super(testName);
    }

    /**
     * Test of parse method, of class CatalogueParser.
     */
    public void testParse() {
    }

    /**
     * Test of getCoursePrereqPhrase method, of class CatalogueParser.
     */
    public void testGetCoursePrereqPhrase() {
        System.out.println("GetCoursePrereqPhrase");
        CatalogueParser parser = new CatalogueParser();
        String temp = parser.getCoursePrereqPhrase("MATH 213	3:3-0 Vector Calculus A study of vector functions and "
                + "functions of several variables and their derivatives. Applied maximum and minimum problems,"
                + " Lagrange multipliers, multiple integration, integration in polar, cylindrical and spherical"
                + " coordinates. Green's, Stokes' and the Divergence Theorem. *** Prerequisite: MATH 111 and"
                + " 122 with a grade of at least 60% *** * Note: This course is designed for engineering and"
                + " science students. Students cannot receive credit for this course if they have received"
                + " credit for MATH 214. *");

        assertEquals(0, temp.compareTo("*** Prerequisite: MATH 111 and 122 with a grade of at least 60% ***"));

        temp = parser.getCoursePrereqPhrase("ENPE 440	3:3-3 Well Logging and Formation Evaluation Fundamentals "
                + "of well logging for the determination of petrophysical properties in the near bore region, "
                + "types of well logging devices, and applications of well logs for petroleum system management. "
                + "*** Prerequisite: ENPE 241 *** *** Corequisite: ENPE 460 ***");

        assertEquals(0, temp.compareTo("*** Prerequisite: ENPE 241 ***"));
    }

    /*
     * Test of getCourseCoreqPhrase method, of class CatalogueParser.
     */
    public void testgetCourseCoreqPhrase(){
        System.out.println("GetCourseCoreqPhrase");
        CatalogueParser parser = new CatalogueParser();

        String temp = parser.getCourseCoreqPhrase("ENPE 440     3:3-3 Well Logging and Formation Evaluation Fundamentals of "
                + "well logging for the determination of petrophysical properties in the near bore "
                + "region, types of well logging devices, and applications of well logs for petroleum "
                + "system management. ***Prerequisite: ENPE 241*** ***Corequisite: ENPE 460*** ");

        assertEquals(0, temp.compareTo("***Corequisite: ENPE 460***"));

    }

    /**
     * Test of findLogic method, of class CatalogueParser.
     */
    public void testFindLogic() {
        System.out.println("FindLogic");
        CatalogueParser parser = new CatalogueParser();
        HashMap<String, ArrayList<String>> result = parser.findLogic("*** Prerequisite: A grade of at "
                + "least 60% in either MATH 105 or 110. ***");
        assertEquals(true, result.get("or").contains("MATH 105"));
        assertEquals(true, result.get("or").contains("MATH 110"));

        result = parser.findLogic("***Prerequisite: ENPE 241, ENPE 251, ENPE 300, ENPE 302, "
                + "and ENPE 370. Concurrent enrolment allowed in ENPE 302 and 370.***");
        assertEquals(true, result.get("and").contains("ENPE 241"));
        assertEquals(true, result.get("and").contains("ENPE 251"));
        assertEquals(true, result.get("and").contains("ENPE 300"));
        assertEquals(true, result.get("and").contains("ENPE 370"));

        result = parser.findLogic("***Prerequisite: PHYS 111 or 112 or 119 or 129, and MATH 213. "
                + "MATH 213 and PHYS 129 may be taken concurrently.***");
        assertEquals(true, result.get("or").contains("PHYS 111"));
        assertEquals(true, result.get("or").contains("PHYS 112"));
        assertEquals(true, result.get("or").contains("PHYS 119"));
        assertEquals(true, result.get("or").contains("PHYS 129"));
        assertEquals(true, result.get("and").contains("MATH 213"));

        result = parser.findLogic("*** Prerequisite: MATH 105 or 110 (may be taken concurrently) "
                + "plus CS 110\n with a minimum grade of 65%. ***");
        assertEquals(true, result.get("or").contains("MATH 105"));
        assertEquals(true, result.get("or").contains("MATH 110"));
        assertEquals(true, result.get("and").contains("CS 110"));

        result = parser.findLogic("*** Prerequisite: PHYS 111 or 119, and MATH 213. Math 213 may be taken concurrently. ***");
        assertEquals(true, result.get("or").contains("PHYS 111"));
        assertEquals(true, result.get("or").contains("PHYS 119"));
        assertEquals(true, result.get("and").contains("MATH 213"));

    }

}
