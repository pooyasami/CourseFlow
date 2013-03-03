/*
 * CalendarParser.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing the course calendar,
 * the parsing process consists of three levels,
 * once parseing is done, it gets the semester number and its related course number,
 * and puts those values into the hashtable called hashSemesters
 * @author Daphne Z
 */

public class CalendarParser implements IParser {

    private String filePath;
    private int location;
    private Hashtable<Integer, ArrayList<Course>> hashSemesters;
    private Program program;

    /**
     * This method is a wrapper for calling all three levels of parsing process
     */
    public void parse(){

         hashSemesters = new Hashtable<Integer, ArrayList<Course>>();

        try {
            this.firstLevelParse();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CalendarParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalendarParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.secondLevelParse();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CalendarParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalendarParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.thirdLevelParse();
        } catch (Exception ex) {
            Logger.getLogger(CalendarParser.class.getName()).log(Level.SEVERE, null, ex);
        }


    }  

    /**
     * It is a setter for setting the target file name
     * @param filePath: course calendar .htm file
     */
    public void setTargetFileName(String filePath) {
        this.filePath = filePath;

    }

    /**
     * It is a setter for setting the position
     * @param location: the order of <table> code in html code of calendar .htm file for each program
     */
    public void setPosition(int location){
        this.location = location;
    }

    /**
     * It is a setter for setting program object
     * @param program
     */
    public void setProgram(Program program){
        this.program = program;
    }
    
    public CalendarParser(){}

    /**
     * Returns the hashtable hashSemesters
     * @return
     */
    public Hashtable<Integer, ArrayList<Course>> getAllSemester(){

        return hashSemesters;//Integer->1-9; ArrayList->each int related courses
    }

/*
 * ---------------- Start Parsing Calendar URL Page ----------------------
 */
    private String firstLevelString = "";

    /**
     * This method parses the given program html code in its "<table>" to "</table>" part
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void firstLevelParse()throws FileNotFoundException, IOException{

            BufferedReader input = new BufferedReader(new FileReader(filePath));

        String line = "";
        String sub1 = "<table";
        String sub2 = "</table>";
        String returnString = "";

        int index = 0;
        int targetIndex = this.location;

        try {

            boolean isContinued = false;
            while ((line = input.readLine()) != null){

               if(isContinued){
                   //find the right location of String "<table>"
                  if(index == targetIndex){
                    returnString = returnString + line + "\n";

                    for(int i=0; i<line.length(); i++){
                        char in = line.charAt(i);
                        if(in == sub2.charAt(0)){
                            //find target </table>
                            int sizeOfSub2 = sub2.length();

                            try{
                               String srcString = line.substring(i, i +sizeOfSub2);
                               if(srcString.equals(sub2)){
                                    isContinued = false;
                               }
                            }catch(IndexOutOfBoundsException e){

                            }
                        }
                    }

                  }else{
                    isContinued = false;
                  }

                }else{
                   for (int i=0; i<line.length(); i++){
                    char in = line.charAt(i);
                    if(in == sub1.charAt(0)){
                        int sizeOfSub1 = sub1.length();
                        int endIndex = i+sizeOfSub1;

                        try{
                            String srcString = line.substring(i, endIndex);
                            if(srcString.equals(sub1)){
                                //find the target line "<table>"
                                isContinued = true;
                                index++;
                                break;
                           }

                        }
                        catch(IndexOutOfBoundsException e){
                            isContinued = false;
                            break;

                        }
                     }
                    }
                }

            }// end of while loop
        }catch (IOException e){
            System.out.println("Error reading in value");
        }

        input.close();

        firstLevelString = returnString;
    }

    private Hashtable<String, String> hash_semester2String = new Hashtable<String, String>();

        /**
         * In the "<table>" to "</table>" html code part parsed from firstLevelParser(),
         * this method parses the "Semester" to "Total" part of html code,
         * in the end, puts the Semester number and its related string containing several course numbers into the hashtable hash_semester2String
         * @throws UnsupportedEncodingException
         * @throws IOException
         */
   private void secondLevelParse() throws UnsupportedEncodingException, IOException
        {
        String subStr = "Semester";
        String returnString = "";
        int index = 0;

        boolean isRead = false;

        //firstLevelString
        if(this.firstLevelString.isEmpty())
            return;

        //read line by line
        InputStream is = new ByteArrayInputStream(this.firstLevelString.getBytes("UTF-8"));
        BufferedReader input = new BufferedReader (new InputStreamReader(is, "UTF-8"));

        String line = "";

        while((line = input.readLine()) != null){

           if(isRead){
               returnString = returnString + line + "\n";
           }
            for (int i=0; i<line.length(); i++){
                    char in = line.charAt(i);
                    if(in == subStr.charAt(0)){
                        int sizeOfSub1 = subStr.length();
                        int endIndex = i+sizeOfSub1;

                        try{
                            String srcString = line.substring(i, endIndex);
                            if(srcString.equals(subStr)){
                                // if find the target line "Semester"
                                if(isRead){
                                index++;
                                hash_semester2String.put(Integer.toString(index), returnString);
                                returnString = "";
                                }

                                isRead = true;
                                returnString = returnString + line + "\n";
                                break;
                           }

                        }
                        catch(IndexOutOfBoundsException e){
                           // isContinued = false;
                            break;

                        }
                    }
                }

        }//end of while
        index ++;
        //let the returnString trim down to the "Semester" to "Total" part
        returnString = lastSemesterParse(returnString);
        hash_semester2String.put(Integer.toString(index), returnString);

    }

   /**
    * Returns the string between "Semester" to "Total"
    * @param lastSemester
    * @return
    */
   private String lastSemesterParse(String lastSemester){
        String total = "Total";
        Pattern totalPattern = Pattern.compile(total);
        Matcher totalMatcher;

        totalMatcher = totalPattern.matcher(lastSemester);
        while(totalMatcher.find()){
                String sub = lastSemester.substring(0, totalMatcher.end());
                return sub;
        }

        return "";
    }

   /**
    * Based on String from "Semester" to "Total" html code part got from secondLevelParse(),
    * this method extracts the Semester number and its related courses, and puts them into the hashtable hashSemesters
    * @throws Exception
    */
    private void thirdLevelParse() throws Exception
    {
        String course = "\\w{1,4}[A-Z]+\\s{0,2}+\\d{2,3}";
        Pattern coursePattern = Pattern.compile(course);
        Matcher courseMatcher;
        //go through hash_semester2String
        Enumeration e = hash_semester2String.keys();
        while(e.hasMoreElements()){
            Object key = e.nextElement();
            @SuppressWarnings("element-type-mismatch")
            String value = hash_semester2String.get(key);
            courseMatcher = coursePattern.matcher(value);

            ArrayList<Course> c1 = new ArrayList<Course>();

            while(courseMatcher.find()){

                String courseNum = value.substring(courseMatcher.start(), courseMatcher.end());

                    //Replace all the spaces between the courseNum, and replace with only one space
                    courseNum= courseNum.replaceAll("\\s+", " ");
                    //System.out.println(courseNum); //Test which course is generated from the calendar parser

                Course c =this.getCourseFromCachedCourses(courseNum);

                c1.add(c);

                c.addProgramNum(this.program.getProgramNum());

                this.insertCourse2CachedCourses(c);

            }
                Integer integer = new Integer(key.toString());
                hashSemesters.put(integer,c1);
        }

    }

    /**
     * It returns the Course from the class CachedCourses()
     * @param courseNum: the string course number generated from the calendar html code
     * @return
     * @throws Exception
     */
    private Course getCourseFromCachedCourses(String courseNum) throws Exception{
        CachedCourses g = ModelManagement.instance().getCachedCourses();

        Course c = null;
        try{
            c = g.getCourseByCourseNumber(courseNum);
        }catch(Exception e){

        }

        if(c == null)
            c = new Course(courseNum);

        return c;
    }

    /**
     * It inserts the Course into the CachedCourses
     * @param newCourse
     * @throws Exception
     */
    private void insertCourse2CachedCourses(Course newCourse) throws Exception{
        CachedCourses g = ModelManagement.instance().getCachedCourses();

        g.insertNewCourse(newCourse);
    }



}
