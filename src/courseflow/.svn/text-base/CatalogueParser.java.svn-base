/*
 * CatalogueParser.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */
package courseflow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing the course catalogue,
 * it parses the course information(courseNum, courseCreditHours, coursePrereqPhrase, courseCoreqPhrase, courseCoreq, courseEntry, coursePrereq, PrereqOr, PrereqAnd)
 * from the course catalogue, and sets those parsed data to the Course
 * @author Daphne Z & Pooya Samizadeh-Yazd
 */

public class CatalogueParser implements IParser {

    private String filePath;

    /**
     * This method is a wrapper calling all parsing methods,
     * in the end, sets the course information into the Course
     */
    public void parse() {
        try {
            this.getCourseEntry();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        for (int i = 0; i < blocks.size(); i++) {
            String courseEntry = blocks.get(i);
            String courseNum = this.getCourseNum(courseEntry);

            gCachedCourses gCached = null;
            try {
                gCached = ModelManagement.instance().getGCachedCourses();
            } catch (Exception ex) {
                Logger.getLogger(CatalogueParser.class.getName()).log(Level.SEVERE, null, ex);
            }

            //insert new course number and new course entry to the hashtable in the class gCachedCourses
            gCached.insertNewCourse(courseNum, courseEntry);

            String courseCreditHours = this.getCourseCreditHour(courseEntry);
            String coursePrereqPhrase = this.getCoursePrereqPhrase(courseEntry);

            ArrayList<Course> courseCoreq = null;
            
            ArrayList<String> coursePrereq = null;
            ArrayList<String> PrereqOr = null;
            ArrayList<String> PrereqAnd = null;
            try {
                coursePrereq = this.getCoursePrereq(coursePrereqPhrase);
            } catch (Exception ex) {
                Logger.getLogger(CatalogueParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                PrereqOr = this.getPrereqOr(coursePrereqPhrase);
            } catch (Exception ex) {
                Logger.getLogger(CatalogueParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                PrereqAnd = this.getPrereqAnd(coursePrereqPhrase);
            } catch (Exception ex) {
                Logger.getLogger(CatalogueParser.class.getName()).log(Level.SEVERE, null, ex);
            }

            String courseCoreqPhrase = this.getCourseCoreqPhrase(courseEntry);

            try {
                courseCoreq = this.getCourseCoreq(courseCoreqPhrase);
            } catch (Exception ex) {
                Logger.getLogger(CatalogueParser.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                this.bindData2Course(courseNum, courseCreditHours, coursePrereqPhrase, courseCoreqPhrase, courseCoreq, courseEntry, coursePrereq, PrereqOr, PrereqAnd);
            } catch (Exception ex) {
                Logger.getLogger(CatalogueParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//end of for loop

        try {
            //process prereq course
            processPrereq();
        } catch (Exception ex) {
            Logger.getLogger(CatalogueParser.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * This method binds course information data to the Course
     * @param courseNum: course number (e.g. ENSE 470)
     * @param courseCreditHours: course credit hour (e.g. 3:3-3)
     * @param coursePrereqPhrase: course prerequisite phrase
     * @param courseCoreqPhrase: course corequistie phrase
     * @param courseCoreq: course prerequisite
     * @param courseEntry: course entry
     * @param coursePrereq: course prerequisite in the prereq phrase without containing either "or" logic or "and" logic
     * @param coursePrereqOr: course prerequisite in the prereq phrase containing "or" logic
     * @param coursePrereqAnd: course prerequisite in the prereq phrase containing "and" logic
     * @throws Exception
     */
    private void bindData2Course(String courseNum, String courseCreditHours, String coursePrereqPhrase,
            String courseCoreqPhrase, ArrayList<Course> courseCoreq, String courseEntry, ArrayList<String> coursePrereq, ArrayList<String> coursePrereqOr, ArrayList<String> coursePrereqAnd) throws Exception {  //change later

        CachedCourses g = ModelManagement.instance().getCachedCourses();
        Course c = null;


        try {
            c = g.getCourseByCourseNumber(courseNum);
        } catch (Exception e) {
        }

        if (c == null) {
            return;
        }

        c.setCreditHourInfo(courseCreditHours);
        c.setPrereqPhrase(coursePrereqPhrase);
        c.setStrPrereq(coursePrereq);
        c.setStrPrereqOr(coursePrereqOr);
        c.setStrPrereqAnd(coursePrereqAnd);
        c.setCoreqPhrase(courseCoreqPhrase);
        c.setCoreq(courseCoreq);
        c.setEntry(courseEntry);

    }

    /**
     * It converts the String type of prerequisties to the Course type of prerequisties,
     * and sets the Course type of prerequisties into Course
     * @throws Exception
     */
    private void processPrereq() throws Exception {
        //go through all course
        CachedCourses g = ModelManagement.instance().getCachedCourses();
        Hashtable<String, Course> allCourses = g.getAllCourses();

        Enumeration e1 = allCourses.keys();
        while (e1.hasMoreElements()) {
            String key = (String) e1.nextElement();
            Course course = allCourses.get(key);

            //ArrayList<String> preReq
            ArrayList<String> strPrereq = course.getStrPrereq();
            ArrayList<String> strPrereqOr = course.getStrPrereqOr();
            ArrayList<String> strPrereqAnd = course.getStrPrereqAnd();

            ArrayList<Course> preReq = new ArrayList<Course>();
            ArrayList<Course> preReqOr = new ArrayList<Course>();
            ArrayList<Course> preReqAnd = new ArrayList<Course>();

            if (strPrereq != null) {

                for (int i = 0; i < strPrereq.size(); i++) {
                    String tmp = strPrereq.get(i);

                    Course c = g.getCourseByCourseNumber(tmp);
                    if (c != null) {
                        preReq.add(c);
                    }

                }
            }

            if (strPrereqOr != null) {

                for (int i = 0; i < strPrereqOr.size(); i++) {
                    String tmp = strPrereqOr.get(i);
                    Course c = g.getCourseByCourseNumber(tmp);
                    if (c != null) {
                        preReqOr.add(c);
                    }

                }
            }

            if (strPrereqAnd != null) {

                for (int i = 0; i < strPrereqAnd.size(); i++) {
                    String tmp = strPrereqAnd.get(i);
                    Course c = g.getCourseByCourseNumber(tmp);
                    if (c != null) {
                        preReqAnd.add(c);
                    }

                }
            }
            course.setPrereq(preReq);
            course.setPrereqOr(preReqOr);
            course.setPrereqAnd(preReqAnd);

        }


    }

    /*
     * ---------------- Start Parsing Catalogue ----------------------
     */

    private ArrayList<String> blocks = new ArrayList<String>();

    /**
     * It parses the whole course entry from the course catalogue,
     * and add the course entry string to the ArrayList<String> blocks
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void getCourseEntry() throws FileNotFoundException, IOException {

        String course = "\\w{2,4}+\\s{0,2}+\\d{3}+\\s+\\d{1}";
        Pattern coursePattern = Pattern.compile(course);
        Matcher courseMatcher;

        String line = "";
        String courseBlock = "";

        BufferedReader input = new BufferedReader(new FileReader(filePath));

        try {
            boolean isFirst = true;

            while ((line = input.readLine()) != null) {

                courseMatcher = coursePattern.matcher(line);

                while (courseMatcher.find()) {
                    isFirst = false;

                    blocks.add(courseBlock);

                    courseBlock = "";

                    break;
                }

                if (!isFirst) {
                    courseBlock = courseBlock + line + "\n";

                }
            }//end of while loop
        } catch (IndexOutOfBoundsException e) {
        }

        blocks.add(courseBlock);

        input.close();

    }

    /**
     * Based on the whole course entry from getCourseEntry(),
     * it returns the course number
     * @param courseEntry
     * @return
     */
    private String getCourseNum(String courseEntry) {
        String cNum1 = "";
        String cNum = "\\w{1,4}[A-Z]+\\s{0,2}+\\d{3}";
        Pattern cPattern = Pattern.compile(cNum);
        Matcher cMatcher;

        cMatcher = cPattern.matcher(courseEntry);

        if (courseEntry.isEmpty()) {
            return "";
        }

        while (cMatcher.find()) {
            String courseNum = courseEntry.substring(cMatcher.start(), cMatcher.end());
            cNum1 = courseNum;

            return cNum1;
        }

        return "";
    }

    /**
     * Based on the whole course entry from getCourseEntry(),
     * it returns the course credit hour
     * @param courseEntry
     * @return
     */
    private String getCourseCreditHour(String courseEntry) {
        String cCreditHour1 = "";
        String cCreditHour = "\\d{1}:\\d{1}-\\d{1}";
        Pattern cPattern = Pattern.compile(cCreditHour);
        Matcher cMatcher;

        cMatcher = cPattern.matcher(courseEntry);

        if (courseEntry.isEmpty()) {
            return "";
        }

        while (cMatcher.find()) {
            String courseCreditHour = courseEntry.substring(cMatcher.start(), cMatcher.end());
            cCreditHour1 = courseCreditHour;

            return cCreditHour1;
        }

        return "";
    }

    /**
     * Based on the whole course entry from getCourseEntry(),
     * it returns the course prerequisite phrase 
     * @param courseEntry
     * @return
     */
    public String getCoursePrereqPhrase(String courseEntry) {
        String cPrereqPhrase1 = "";
        String cPrereq = "\\*{3}Prerequisite:";
        String cPrereq1 = "\\*{3}\\sPrerequisite:";
        Pattern cPattern = Pattern.compile(cPrereq);
        Pattern cPattern1 = Pattern.compile(cPrereq1);
        Matcher cMatcher;
        Matcher cMatcher1;

        cMatcher = cPattern.matcher(courseEntry);
        cMatcher1 = cPattern1.matcher(courseEntry);

        if (courseEntry.isEmpty()) {
            return "";
        }

        while (cMatcher.find()) {
            int beginIndex = courseEntry.indexOf("***Prerequisite:");

            int endIndex = courseEntry.substring(beginIndex + 3).indexOf("***") + beginIndex + 6;

            String coursePrereqPhrase = courseEntry.substring(beginIndex, endIndex);
            cPrereqPhrase1 = coursePrereqPhrase;

            return cPrereqPhrase1;

        }

        while (cMatcher1.find()) {
            int beginIndex = courseEntry.indexOf("*** Prerequisite:");

            int endIndex = courseEntry.substring(beginIndex + 3).indexOf("***") + beginIndex + 6;

            String coursePrereqPhrase = courseEntry.substring(beginIndex, endIndex);
            cPrereqPhrase1 = coursePrereqPhrase;

            return cPrereqPhrase1;

        }

        return "";

    }

    /**
     * Based on the course prerequisite phrase from getCoursePrereqPhrase(courseEntry),
     * it returns the course prerequisite
     * @param cPrereqPhrase1
     * @return
     * @throws Exception
     */
    private ArrayList<String> getCoursePrereq(String cPrereqPhrase1) throws Exception {
        ArrayList<String> cPrereq1 = new ArrayList<String>();

        String cNum = "\\w{1,4}[A-Z]+\\s{0,2}+\\d{2,3}";
        String cOr = "or";
        String cAnd = "and";
        Pattern cPattern = Pattern.compile(cNum);
        Pattern cOrPattern = Pattern.compile(cOr);
        Pattern cAndPattern = Pattern.compile(cAnd);
        Matcher cMatcher;
        Matcher cOrMatcher;
        Matcher cAndMatcher;

        cMatcher = cPattern.matcher(cPrereqPhrase1);
        cOrMatcher = cOrPattern.matcher(cPrereqPhrase1);
        cAndMatcher = cAndPattern.matcher(cPrereqPhrase1);

        if (cPrereqPhrase1.isEmpty()) {
            return null;
        }
        while (cOrMatcher.find()) {
            this.getPrereqOr(cPrereqPhrase1);
        }

        while (cAndMatcher.find()) {
            this.getPrereqAnd(cPrereqPhrase1);
        }

        while (!cOrMatcher.find() && !cAndMatcher.find() && cMatcher.find()) {
            String coursePrereq = cPrereqPhrase1.substring(cMatcher.start(), cMatcher.end());

            cPrereq1.add(coursePrereq);
        }

        return cPrereq1;
    }

    /**
     * Based on the course prerequisite phrase from getCoursePrereqPhrase(courseEntry),
     * it returns the course prerequisite which contains the "or" logic
     * @param cPrereqPhrase1
     * @return
     * @throws Exception
     */
    private ArrayList<String> getPrereqOr(String cPrereqPhrase1) throws Exception {

        HashMap<String, ArrayList<String>> tmpPrereq = this.findLogic(cPrereqPhrase1);
        ArrayList<String> orLogic = tmpPrereq.get("or");

        return orLogic;

    }

    /**
     * Based on the course prerequisite phrase from getCoursePrereqPhrase(courseEntry),
     * it returns the course prerequisite which contains the "and" logic
     * @param cPrereqPhrase1
     * @return
     * @throws Exception
     */
    private ArrayList<String> getPrereqAnd(String cPrereqPhrase1) throws Exception {

        HashMap<String, ArrayList<String>> tmpPrereq = this.findLogic(cPrereqPhrase1);
        ArrayList<String> andLogic = tmpPrereq.get("and");

        return andLogic;

    }

    /**
     * Based on the course prerequisite phrase from getCoursePrereqPhrase(courseEntry),
     * it returns the HashMap containing "or"/"and" logic related prerequisites string
     * @param cPrereqPhrase1
     * @return
     */
    public HashMap<String, ArrayList<String>> findLogic(String cPrereqPhrase1) {

        String filteredInput = cPrereqPhrase1.replaceAll("\\*\\*\\*", "");
        filteredInput = filteredInput.replaceAll("\n", "");

        if (filteredInput.contains("Concurrent")) {
            filteredInput = filteredInput.substring(0, filteredInput.indexOf("Concurrent"));
        }

        if (filteredInput.contains("concurrently")) {
            if (filteredInput.contains(".")) {
                filteredInput = filteredInput.substring(0, filteredInput.indexOf("."));
            }
        }

        HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
        result.put("or", new ArrayList<String>());
        result.put("and", new ArrayList<String>());

        ArrayList<String> temporaryArray = new ArrayList<String>();

        String courseLetterPattern = "\\w{1,4}[A-Z]";
        String courseNumberPattern1 = "\\d{3}";

        Pattern patternForLetters = Pattern.compile(courseLetterPattern);
        Pattern patternForNumbers1 = Pattern.compile(courseNumberPattern1);

        Matcher matcherForLetters;
        Matcher matcherForNumbers1;

        Stack<String> courseNumberStack = new Stack<String>();

        StringTokenizer tokenizer = new StringTokenizer(filteredInput);
        String tempCourseLetters = new String();

        boolean orFlag = false;
        boolean andFlag = false;

        while (tokenizer.hasMoreTokens()) {
            String currentToken = tokenizer.nextToken();

            matcherForLetters = patternForLetters.matcher(currentToken);
            matcherForNumbers1 = patternForNumbers1.matcher(currentToken);

            if (matcherForLetters.find()) {
                if (!courseNumberStack.empty()) {
                    while (!courseNumberStack.empty()) {
                        temporaryArray.add(tempCourseLetters + " " + courseNumberStack.pop());
                    }
                }

                tempCourseLetters = currentToken;
            }


            if (matcherForNumbers1.find()) {
                if (currentToken.length() == 3) {
                    courseNumberStack.push(currentToken);
                } else {
                    courseNumberStack.push(currentToken.substring(0, 3));
                }
            }

            if (orFlag) {
                matcherForLetters = patternForLetters.matcher(currentToken);
                if (matcherForLetters.find()) {
                    currentToken = tokenizer.nextToken();
                    matcherForNumbers1 = patternForNumbers1.matcher(currentToken);
                }

                if (matcherForNumbers1.find()) {
                    if (currentToken.contains(".")) {
                        currentToken = currentToken.substring(0, currentToken.indexOf("."));
                    }
                    courseNumberStack.push(currentToken);
                }

                while (!courseNumberStack.empty()) {
                    temporaryArray.add(tempCourseLetters + " " + courseNumberStack.pop());
                }

                result.get("or").addAll(temporaryArray);
                temporaryArray = new ArrayList<String>();
                orFlag = false;
            }

            if (andFlag) {

                matcherForLetters = patternForLetters.matcher(currentToken);
                if (matcherForLetters.find()) {
                    currentToken = tokenizer.nextToken();
                    matcherForNumbers1 = patternForNumbers1.matcher(currentToken);
                }
                if (matcherForNumbers1.find()) {
                    if (currentToken.contains(".")) {
                        currentToken = currentToken.substring(0, currentToken.indexOf("."));
                    }

                    courseNumberStack.push(currentToken);
                }

                while (!courseNumberStack.empty()) {
                    temporaryArray.add(tempCourseLetters + " " + courseNumberStack.pop());
                }

                result.get("and").addAll(temporaryArray);
                temporaryArray = new ArrayList<String>();
                andFlag = false;
            }

            if (currentToken.compareTo("or") == 0) {
                orFlag = true;
            }

            if (currentToken.compareTo("and") == 0 || currentToken.compareTo("plus") == 0) {
                andFlag = true;
            }
        }

        if (result.get("or").size() % 2 != 0 && result.get("and").size() > 1) {
            result.get("or").add(result.get("and").get(result.get("and").size() - 1));
            result.get("and").remove(result.get("and").size() - 1);
        }

        if (!courseNumberStack.empty() && !result.get("and").contains(tempCourseLetters + " " + courseNumberStack.peek())) {
            result.get("and").add(tempCourseLetters + " " + courseNumberStack.pop());
        }

        return result;

    }

    /**
     * It returns the Course from class CachedCourses
     * @param cNum: string type course number
     * @return
     * @throws Exception
     */
    private Course getCourseFromCachedCourses(String cNum) throws Exception {
        CachedCourses g = ModelManagement.instance().getCachedCourses();

        Course c = null;


        try {
            c = g.getCourseByCourseNumber(cNum);


        } catch (Exception e) {
        }


        if (c == null) {
            return null;
        }

        return c;


    }
 
    /**
     * Based on the whole course entry from getCourseEntry(),
     * it returns the course corequisite phrase
     * @param courseEntry
     * @return
     */
    public String getCourseCoreqPhrase(String courseEntry) {

        String cCoreqPhrase1 = "";
        String cCoreq = "\\*{3}Corequisite:";
        String cCoreq1 = "\\*{3}\\sCorequisite:";
        Pattern cPattern = Pattern.compile(cCoreq);
        Pattern cPattern1 = Pattern.compile(cCoreq1);
        Matcher cMatcher;
        Matcher cMatcher1;

        cMatcher = cPattern.matcher(courseEntry);
        cMatcher1 = cPattern1.matcher(courseEntry);

        if (courseEntry.isEmpty()) {
            return "";
        }

        while (cMatcher.find()) {
            int beginIndex = courseEntry.indexOf("***Corequisite:");


            int endIndex = courseEntry.substring(beginIndex + 3).lastIndexOf("***") + beginIndex + 6;
            String courseCoreqPhrase = courseEntry.substring(beginIndex, endIndex);
            cCoreqPhrase1 = courseCoreqPhrase;

            return cCoreqPhrase1;

        }

        while (cMatcher1.find()) {
            int beginIndex = courseEntry.indexOf("*** Corequisite:");


            int endIndex = courseEntry.substring(beginIndex + 3).lastIndexOf("***") + beginIndex + 6;
            String courseCoreqPhrase = courseEntry.substring(beginIndex, endIndex);
            cCoreqPhrase1 = courseCoreqPhrase;

            return cCoreqPhrase1;

        }

        return "";
    }

    /**
     * Based on the course corequisite phrase from getCourseCoreqPhrase(courseEntry),
     * it returns the ArrayList<Course> course corequisite
     * @param cCoreqPhrase1
     * @return
     * @throws Exception
     */
    private ArrayList<Course> getCourseCoreq(String cCoreqPhrase1) throws Exception {
        String cNum = "\\w{1,4}[A-Z]+\\s{0,2}+\\d{3}";
        Pattern cPattern = Pattern.compile(cNum);
        Matcher cMatcher;
        cMatcher = cPattern.matcher(cCoreqPhrase1);

        ArrayList<Course> cCoreq1 = new ArrayList<Course>();

        if (cCoreqPhrase1.isEmpty()) {
            return cCoreq1;
        }

        while (cMatcher.find()) {
            String courseCoreq = cCoreqPhrase1.substring(cMatcher.start(), cMatcher.end());

            Course c = this.getCourseFromCachedCourses(courseCoreq);
            if (c != null) {
                cCoreq1.add(c);
            }
        }

        return cCoreq1;

    }

    /**
     * It sets the catalogue file name
     * @param filePath
     */
    public void setTargetFileName(String filePath) {

        this.filePath = filePath;
    }

    public CatalogueParser() {
    }
}
