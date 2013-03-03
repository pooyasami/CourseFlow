/*
 * ModelManagement.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class uses the Singleton Design Patter method,
 * it manages all stuff related to course flow project required in the use cases
 * @author Daphne Z
 */

public class ModelManagement implements IModelManagement {

    private Programs programs;
    private CourseCalendar courseCalendar;
    private CourseCatalogue courseCatalogue;
    private CachedCourses cachedCourses;
    private gCachedCourses gCached;
    private static int version = -1;

    private Hashtable<String, Integer> URLs = new Hashtable<String, Integer>();
    private Hashtable<Integer, String> hashProgramNum2FilePath = new Hashtable<Integer, String>();
    private String catalogueFilePath;
    private int programNum;

    private static ModelManagement _instance = null;

    /**
     * Constructor
     * new the programs, and sets each program its related program number and related filepath(course calendar, course catalogue)
     * @throws Exception
     */
    private ModelManagement() throws Exception{

        if(version == 2){

            URLs.put("Downloads/esev2.htm", 6);     //ESE  0  //6: the order of <table> in the html code
            URLs.put("Downloads/evsev2.htm", 7);     //EVSE 1
            URLs.put("Downloads/isev2.htm", 6);     //ISE  2
            URLs.put("Downloads/psev2.htm", 7);     //PSE  3
            URLs.put("Downloads/ssev2.htm", 6);     //SSE  4

            programs = new Programs();

             ArrayList<Program> pList1 = programs.getPrograms();

             Program p0 = new Program(0);
             pList1.add(p0);
             hashProgramNum2FilePath.put(0, "Downloads/esev2.htm");

             Program p1 = new Program(1);
             pList1.add(p1);
             hashProgramNum2FilePath.put(1, "Downloads/evsev2.htm");

             Program p2 = new Program(2);
             pList1.add(p2);
             hashProgramNum2FilePath.put(2, "Downloads/isev2.htm");

             Program p3 = new Program(3);
             pList1.add(p3);
             hashProgramNum2FilePath.put(3, "Downloads/psev2.htm");

             Program p4 = new Program(4);
             pList1.add(p4);
             hashProgramNum2FilePath.put(4, "Downloads/ssev2.htm");

            this.catalogueFilePath = "Downloads/0910UGCourseCatalog.txt";
        }
        else {
            URLs.put("Downloads/esev1.htm", 6);     //ESE  0
            URLs.put("Downloads/evsev1.htm", 7);     //EVSE 1
            URLs.put("Downloads/isev1.htm", 6);     //ISE  2
            URLs.put("Downloads/psev1.htm", 7);     //PSE  3
            URLs.put("Downloads/ssev1.htm", 6);     //SSE  4

            programs = new Programs();

             ArrayList<Program> pList1 = programs.getPrograms();

             Program p0 = new Program(0);
             pList1.add(p0);
             hashProgramNum2FilePath.put(0, "Downloads/esev1.htm");

             Program p1 = new Program(1);
             pList1.add(p1);
             hashProgramNum2FilePath.put(1, "Downloads/evsev1.htm");

             Program p2 = new Program(2);
             pList1.add(p2);
             hashProgramNum2FilePath.put(2, "Downloads/isev1.htm");

             Program p3 = new Program(3);
             pList1.add(p3);
             hashProgramNum2FilePath.put(3, "Downloads/psev1.htm");


             Program p4 = new Program(4);
             pList1.add(p4);
             hashProgramNum2FilePath.put(4, "Downloads/ssev1.htm");

             version = 1;

             catalogueFilePath = "Downloads/1011UGCourseCatalog.txt";
        }

        courseCalendar = new CourseCalendar();
        cachedCourses = new CachedCourses();
        gCached = new gCachedCourses();
    }

    /**
     * Here's using Singleton Pattern to let other class call this function without newing the object,
     * gets the version from class GlobalConfig
     * @return
     */
    public static ModelManagement instance() throws Exception{
        int v = GlobalConfig.instance().getVersion();
        if(v != ModelManagement.version && v != -1)
        {
            version = v;
            _instance = null;//clear the version, amd reset it

        }
        if(_instance == null){
            _instance = new ModelManagement();

        }
        return _instance;

    }

    /**
     * It proccesses the course calendar with setting the calendar filepath, location of "<table>" in the related program .htm file,
     * program, and calls the function setHashSemester2Program() in class CourseCalendar
     */
    private void processCourseCalendar(){
        ArrayList<Program> pList1 = programs.getPrograms();
        for(int i=0; i<pList1.size(); i++){
             String key_url = this.hashProgramNum2FilePath.get(i); //get filePath

             Program tmp = pList1.get(i); //get program name

             courseCalendar.setProgramURL(key_url);

             courseCalendar.setPosition(URLs.get(key_url));

             courseCalendar.setProgram(tmp);

             courseCalendar.setHashSemester2Program();
        }
    }

    /**
     * It processes course catalogue by passing catalogue file path, and calls the bindData() in class CourseCatalogue
     */
    private void processCourseCatalogue(){

        courseCatalogue = new CourseCatalogue(this.catalogueFilePath);
        courseCatalogue.bindData();

    }

    /**
     * It is a wrapper to process both course calendar and course catalogue
     */
    public void processCourseCalendarAndCatalogue(){

        this.processCourseCalendar();
        this.processCourseCatalogue();
    }

    /**
     * Returns cachedCourses
     * @return
     */
    public CachedCourses getCachedCourses(){
        return cachedCourses;
    }

    /**
     * Returns gCachedCourses
     * @return
     */
    public gCachedCourses getGCachedCourses(){
        return gCached;
    }

    /**
     * It prints all the core courses according to the program number by semester
     * @param programNum
     */
    public void printAllCourseByProgram(int programNum) {
            int tmpNums = programNum; //get program numbers

        //according to the programs (in Course), go through the Programs
        //to find semester
            ArrayList<Program> tmpPrograms = programs.getPrograms(); //get program arraylist
            for(int j=0; j<tmpPrograms.size(); j++){
                Program tmpProgram = tmpPrograms.get(j);
                int pNums = tmpProgram.getProgramNum();

                if(tmpNums == pNums){
                    Hashtable<Integer, ArrayList<Course> > tmpHash = tmpProgram.getHashProgram();

                    //Get the course number by semester based on the hashtable key
                    System.out.println("------ Semester 1 ------");
                    ArrayList<Course> courseS1 = tmpHash.get(1);
                    for(int i=0; i<courseS1.size(); i++){
                        Course tmpc = courseS1.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

                    System.out.println("------ Semester 2 ------");
                    ArrayList<Course> courseS2 = tmpHash.get(2);
                    for(int i=0; i<courseS2.size(); i++){
                        Course tmpc = courseS2.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

                    System.out.println("------ Semester 3 ------");
                    ArrayList<Course> courseS3 = tmpHash.get(3);
                    for(int i=0; i<courseS3.size(); i++){
                        Course tmpc = courseS3.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

                    System.out.println("------ Semester 4 ------");
                    ArrayList<Course> courseS4 = tmpHash.get(4);
                    for(int i=0; i<courseS4.size(); i++){
                        Course tmpc = courseS4.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

                    System.out.println("------ Semester 5 ------");
                    ArrayList<Course> courseS5 = tmpHash.get(5);
                    for(int i=0; i<courseS5.size(); i++){
                        Course tmpc = courseS5.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

                    System.out.println("------ Semester 6 ------");
                    ArrayList<Course> courseS6 = tmpHash.get(6);
                    for(int i=0; i<courseS6.size(); i++){
                        Course tmpc = courseS6.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

                    System.out.println("------ Semester 7 ------");
                    ArrayList<Course> courseS7 = tmpHash.get(7);
                    for(int i=0; i<courseS7.size(); i++){
                        Course tmpc = courseS7.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

                    System.out.println("------ Semester 8 ------");
                    ArrayList<Course> courseS8 = tmpHash.get(8);
                    for(int i=0; i<courseS8.size(); i++){
                        Course tmpc = courseS8.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

                    System.out.println("------ Semester 9 ------");
                    ArrayList<Course> courseS9 = tmpHash.get(9);
                    for(int i=0; i<courseS9.size(); i++){
                        Course tmpc = courseS9.get(i);
                        String cNum = tmpc.getCourseNum();
                        System.out.println(cNum);
                    }

              }//end if (tmpNums == pNums)
       }//end for
    }

    /**
     * It prints course prerequisites
     * @param cCourse
     * @param currentProgramNum
     */
    public void printPrereqCourses(Course cCourse, int currentProgramNum){

        ArrayList<Course> targetReqList = cCourse.getPrereq();
        ArrayList<Course> targetReqOrList = cCourse.getPrereqOr();
        ArrayList<Course> targetReqAndList = cCourse.getPrereqAnd();

        if(targetReqOrList != null && targetReqOrList.size() > 0){
            System.out.println("Choose one of the courses below(contains or logic)");
        }
         for(int i=0; i<targetReqOrList.size(); i++){
            Course currentCourse = targetReqOrList.get(i);
            ArrayList<Integer> currentProgramNumsOr = currentCourse.getProgramNums();
            for(int j =0; j< currentProgramNumsOr.size(); j++){
                if(currentProgramNum == 99){  
                  System.out.println(currentCourse.getCourseNum());
                  break;
                }else{
                int cInt = (Integer)currentProgramNumsOr.get(j);

                    if(cInt == currentProgramNum){
                        System.out.println(currentCourse.getCourseNum());
                    }
                }
            }
         }     

        if(targetReqAndList != null && targetReqAndList.size() > 0){
            System.out.println("Choose the courses below(contains and logic)");
        }
         for(int i=0; i<targetReqAndList.size(); i++){
            Course currentCourse = targetReqAndList.get(i);
            ArrayList<Integer> currentProgramNumsOr = currentCourse.getProgramNums();
            for(int j =0; j< currentProgramNumsOr.size(); j++){
                if(currentProgramNum == 99){
                   System.out.println(currentCourse.getCourseNum());
                   break;
                }else{

                int cInt = (Integer)currentProgramNumsOr.get(j);
                    if(cInt == currentProgramNum){
                         System.out.println(currentCourse.getCourseNum());
                    }
                }
            }
         }

        if((targetReqList != null) && (targetReqAndList== null) && (targetReqOrList == null)){
          for(int i=0; i<targetReqList.size(); i++){
            Course currentCourse = targetReqList.get(i);
            ArrayList<Integer> currentProgramNumsOr = currentCourse.getProgramNums();
            for(int j =0; j< currentProgramNumsOr.size(); j++){
                if(currentProgramNum == 99){
                  System.out.println(currentCourse.getCourseNum());

                        break;
                }else{
                int cInt = (Integer)currentProgramNumsOr.get(j);
                if(cInt == currentProgramNum){
                    System.out.println(currentCourse.getCourseNum());
                }
                }
            }
         }
        }

        System.out.println("\n");
    }

    /**
     * It prints course corequisites
     * @param cCourse
     * @param currentProgramNum
     */
    public void printCoreqCourses(Course cCourse, int currentProgramNum){

        ArrayList<Course> targetCoreqList = cCourse.getCoreq();


        if(targetCoreqList == null){
            return;
        }

         for(int i=0; i<targetCoreqList.size(); i++){
            Course currentCourse = targetCoreqList.get(i);
            ArrayList<Integer> currentProgramNums = currentCourse.getProgramNums();
            for(int j =0; j< currentProgramNums.size(); j++){
                if(currentProgramNum == 99){
                  System.out.println(currentCourse.getCourseNum()+"\n");
                }else{
                int cInt = (Integer)currentProgramNums.get(j);

                    if(cInt == currentProgramNum){
                        System.out.println(currentCourse.getCourseNum()+"\n");
                    }
                }
            }
         }
    }

    /**
     * It prints out the dependent courses based on the input course from UI:
     * after user chooses a program, and input a course number, it will first find all the prereqs and coreqs of that input program,
     * and try to match with the input course number, as long as the input course equals to either the prereqs or coreqs, it will
     * print out the course number which has those matched prereqs and/or coreqs, the print-out course number would be the dependent course number
     * @param dCourse
     * @param currentProgramNum
     */
    public void printDependentCourses(Course dCourse, int currentProgramNum){
        int tmpNums = currentProgramNum; //get program numbers

            ArrayList<Program> tmpPrograms = programs.getPrograms(); //get program arraylist
            System.out.println("\nDependent Courses:");

            for(int j=0; j<tmpPrograms.size(); j++){
                Program tmpProgram = tmpPrograms.get(j);
                int pNums = tmpProgram.getProgramNum();

                //checking if the program number getting from programs is the same as user input program number
                if(tmpNums == pNums){
                    Hashtable<Integer, ArrayList<Course> > tmpHash = tmpProgram.getHashProgram();
                    Enumeration e1 = tmpHash.keys();
                    while(e1.hasMoreElements()){
                        Integer key = (Integer) e1.nextElement();
                        ArrayList<Course> course = tmpHash.get(key);
                        
                        for(int k=0; k<course.size(); k++){
                            Course tmpc = course.get(k);
                            String cNum = tmpc.getCourseNum();

                            ArrayList<Course> preqOrList = tmpc.getPrereqOr();
                            ArrayList<Course> preqAndList = tmpc.getPrereqAnd();
                            ArrayList<Course> preqList = tmpc.getPrereq();
                            ArrayList<Course> coreqList = tmpc.getCoreq();

                            for(int i=0; i<coreqList.size(); i++){
                                Course cCoreq = coreqList.get(i);
                                String coreqNum = cCoreq.getCourseNum();
                                if(dCourse.getCourseNum().equals(coreqNum)){
                                    System.out.println(cNum);
                                }
                            }
                            for(int i=0; i<preqOrList.size(); i++){
                                Course cpreqOr = preqOrList.get(i);
                                String cpreqOrNum = cpreqOr.getCourseNum();
                                if(dCourse.getCourseNum().equals(cpreqOrNum)){
                                    System.out.println(cNum);
                                }
                            }
                            for(int i=0; i<preqAndList.size(); i++){
                                Course cpreqAnd = preqAndList.get(i);
                                String cpreqAndNum = cpreqAnd.getCourseNum();
                                if(dCourse.getCourseNum().equals(cpreqAndNum)){
                                    System.out.println(cNum);
                                }
                            }
                            if(preqList!=null && preqOrList==null && preqAndList==null){
                               for(int i=0; i<preqList.size(); i++){
                                Course cpreq = preqList.get(i);
                                String cpreqNum = cpreq.getCourseNum();
                                if(dCourse.getCourseNum().equals(cpreqNum)){
                                    System.out.println(cNum);
                                }
                            }
                            }

                        }//end of for loop
                    }
                }//end if
        }
            System.out.println("\n");
    }

    /**
     * It prints out the semester number according to the current program number, and course
     * @param c
     * @param currentProgramNum
     */
    public void printSemester(Course c, int currentProgramNum){
        int tmpNums = currentProgramNum; //get program numbers

        ArrayList<Program> tmpPrograms = programs.getPrograms(); //get program arraylist
        for(int j=0; j<tmpPrograms.size(); j++){
            Program tmpProgram = tmpPrograms.get(j);
            int pNums = tmpProgram.getProgramNum();
             if(tmpNums == pNums){
                 Hashtable<Integer, ArrayList<Course> > tmpHash = tmpProgram.getHashProgram();
                    Enumeration e1 = tmpHash.keys();
                    while(e1.hasMoreElements()){
                        Integer key = (Integer) e1.nextElement();
                        ArrayList<Course> course = tmpHash.get(key);

                        for(int k=0; k<course.size(); k++){
                            Course tmpc = course.get(k);
                            if(c.getCourseNum().equals(tmpc.getCourseNum())){
                                System.out.println("Semester: " + key);
                            }
                        }
                    }
                 
             }
        }

    }

    /**
     * It gets the course info based on the input course number, once success, return true
     * @param courseNum
     * @return
     */
    public boolean getProgramSemesterByCourse(String courseNum) {

        //get the course object from CachedCourses based on the input courseNum
        Course course = cachedCourses.getCourseByCourseNumber(courseNum);

        if(course== null)
            return false;

        System.out.println("Please choose one of the options below for input course:");
        System.out.println("1 - Find its core pre/coreqs");
        System.out.println("2 - Find the entire course entry");
        System.out.println("3 - Find the pre/coreq phrases");
        System.out.println("4 - Find which programs have it, in which semester");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice = null;

        try{
            choice = input.readLine();
            if(choice.equals("q")||choice.equals("Q")){
                System.out.println("The program is closed!");
            System.exit(0);
            }else if(choice.compareToIgnoreCase("0") == 0){
                UI ui = new UI();
                try {
                    ui.uiRandomCourseInput();
                } catch (Exception ex) {
                }
            }
        }catch(IOException e){
        }

        int iChoice1 = this.stringToInt(choice);
        if(iChoice1<1 || iChoice1 >4){
            System.out.println("Invalid input, please type either number 1 or 2!");
            return false;

        }else if(iChoice1 == 1){
            //core pre/ coreqs
            System.out.println("\nPrerequisite: ");
            this.printPrereqCourses(course, 99);


            System.out.println("Corequisite: ");
            this.printCoreqCourses(course, 99);


        }else if(iChoice1 == 2){
            //course entry
            System.out.println("\n"+course.getEntry()+"\n");
        }else if(iChoice1 == 3){
            //pre/coreq phrases
            System.out.println("\nPrerequisite Phrases:\n"+course.getPrereqPhrase());
            System.out.println("\nCorequisite Phrases:\n"+course.getCoreqPhrase()+"\n");
        }else if(iChoice1 == 4){
                //program, semeseter
                //find which programs (in Course) it belongs to
                ArrayList<Integer> tmpProgramNums = course.getProgramNums();
                for(int i=0; i<tmpProgramNums.size(); i++){
                    int tmpNums = tmpProgramNums.get(i); //get program numbers

                //according to the programs (in Course), go through the Programs
                //to find semester
                    ArrayList<Program> tmpPrograms = programs.getPrograms(); //get program arraylist
                    for(int j=0; j<tmpPrograms.size(); j++){
                    Program tmpProgram = tmpPrograms.get(j);
                    int pNums = tmpProgram.getProgramNum();

                    if(tmpNums == pNums){
                    Hashtable<Integer, ArrayList<Course> > tmpHash = tmpProgram.getHashProgram();

                    Enumeration e1 = tmpHash.keys();
                    while(e1.hasMoreElements()){
                        Integer key = (Integer) e1.nextElement();
                        ArrayList<Course> courses = tmpHash.get(key);
                        for(int k=0; k<courses.size(); k++){
                            Course tmpc = courses.get(k);
                            String cNum = tmpc.getCourseNum();

                            if(cNum.compareTo(courseNum) == 0){
                                System.out.println("\n------ Program: " + tmpProgram.getProgramName() + "  ------");
                                System.out.println("\n------ Semeter: " +  Integer.toString(key)  + "  ------\n");
                            }
                        }//end for
                     }//end while
                  }//end if (tmpNums == pNums)
               }//end for

           }
        }//end of Option 4

        return true;
    }

    /**
     * Returns Hashtable<Integer, ArrayList<Course>>
     * @param programNum
     * @return
     */
    public Hashtable<Integer, ArrayList<Course>> getHashSemesterCourse(int programNum){
         ArrayList<Program> tmpPrograms = programs.getPrograms(); //get program arraylist

             Program tmpProgram = tmpPrograms.get(programNum);

             Hashtable<Integer, ArrayList<Course> > tmpHash = tmpProgram.getHashProgram();

             return tmpHash;

    }

    /**
     * Returns Course based on the input program number, and input course number
     * @param programNum
     * @param courseNum
     * @return
     */
    public Course getCourse(int programNum, String courseNum) {
        Course targetCourse = null;

        ArrayList<Program> pList = programs.getPrograms();

             Program tmp = pList.get(programNum);
             Hashtable<Integer, ArrayList<Course> > hashprograms = tmp.getHashProgram();


           System.out.println("\nProgram: " + tmp.getProgramName() + "----------------");

             Enumeration e1 = hashprograms.keys();
             while(e1.hasMoreElements()){
                Integer key = (Integer) e1.nextElement();
                ArrayList<Course> courses = hashprograms.get(key);
                for(int j=0; j<courses.size(); j++){
                    Course tmpc = courses.get(j);
                    String cNum = tmpc.getCourseNum();

                    if(cNum.compareTo(courseNum) == 0){
                        targetCourse = tmpc;
                        return targetCourse;
                    }
                 }

             }

        return null;
    }

   /**
    * It sets the program number
    * @param programNum
    */
    public void setProgram(int programNum) {
        this.programNum =  programNum;
    }

    /**
     * Change input string to int, and returns int
     * @param str
     * @return
     */
    private int stringToInt(String str){
            int intString = 0;
            try{
                intString = Integer.parseInt(str);
            }catch(NumberFormatException e){
                return -1;
            }
            return intString;
        }


}
