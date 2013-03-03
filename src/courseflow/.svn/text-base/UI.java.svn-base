/*
 * UI.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is to set the textual User Interface
 * @author Daphne Z
 */
public class UI {

    private IModelManagement m;
    private int iChoice;
    private Program program;

    public UI(){

    }

    /**
     * Based on the string input, returns the int type
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

    /**
     * It sets the UI of version choose
     * @throws Exception
     */
    public void uiVersionChoose() throws Exception{
        System.out.println("Please choose a version for Calendar/Catalogue");
        System.out.println("1 - 2010/2011");
        System.out.println("2 - 2009/2010");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String choice = null;

        try{
            choice = input.readLine();
            if(choice.equals("q")||choice.equals("Q")){
                System.out.println("The program is closed!");
            System.exit(0);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        int iChoice1 = this.stringToInt(choice);
        if(iChoice1<1 || iChoice1 >2){
            System.out.println("Invalid input, please type either number 1 or 2!");
            return;

        }
        GlobalConfig.instance().setVersion(iChoice1); // set version

        m = ModelManagement.instance();
        m.processCourseCalendarAndCatalogue();

        while(true){
            this.uiSubMenu();
        }
    }

    /**
     * It sets the UI of sub menu just afte the version choose, letting user to randomly input the course number or input the program number
     * @throws IOException
     * @throws Exception
     */
    public void uiSubMenu() throws IOException, Exception{
        System.out.println("Please choose the option you want to proceed");
        System.out.println("1 - Random course input");
        System.out.println("2 - Program course input");


        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice = null;

        try{
            choice = input.readLine();
            if(choice.equals("q")||choice.equals("Q")){
                System.out.println("The program is closed!");
            System.exit(0);
            }else if(choice.compareToIgnoreCase("0") == 0){
                this.uiVersionChoose();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        int iChoice1 = this.stringToInt(choice);
        if(iChoice1<1 || iChoice1 >2){
            System.out.println("Invalid input, please type either number 1 or 2!");
            return;

        }else if(iChoice1 == 1){
            while(true){
                this.uiRandomCourseInput();

            }
        }else if(iChoice1 == 2){
            while(true){
                this.uiProgramChoose();

            }
        }
    }

    /**
     * It sets the UI of randomly course input
     * @throws Exception
     */
    public void uiRandomCourseInput() throws Exception{
       System.out.println("Please type a course number(e.g. ENSE 400) ");

       BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String randomcNumInput = null;

        try{
            randomcNumInput = input.readLine();
            if(randomcNumInput.equals("q")||randomcNumInput.equals("Q")){
                System.out.println("The program is closed!");
            System.exit(0);
            }else if(randomcNumInput.compareToIgnoreCase("0") == 0){
                this.uiSubMenu();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        //If the input CourseNum is offered in the Engineering Program, then call getProgramSemesterByCourse(randomcNumInput)
        //in MOdelManagement Class to get the course info
        if(m.getProgramSemesterByCourse(randomcNumInput))
            return;     


        //When the input CourseNum is not offered in the Engineering Program, then the output would be the whole course entry of input course
            String entry =null;
            entry = m.getGCachedCourses().getCourseEntryByCourseNumber(randomcNumInput);
            if(entry == null){
                System.out.println("Cannot find the course.");
            }else{
                System.out.println("This course is not offered in the Engineering Program, here's its Course Entry:\n" + entry);
            }

    }

    /**
     * It sets the UI of program number choose
     * @throws IOException
     * @throws Exception
     */
    public void uiProgramChoose() throws IOException, Exception{
        System.out.println("Please choose a program");
        System.out.println("ESE");
        System.out.println("EVSE");
        System.out.println("ISE");
        System.out.println("PSE");
        System.out.println("SSE");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String choice = null;

        try{
            choice = input.readLine();
            if(choice.equals("q")||choice.equals("Q")){
                System.out.println("The program is closed!");
            System.exit(0);
            }else if(choice.compareToIgnoreCase("0") == 0){
                this.uiSubMenu();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        iChoice = -1;
        String c = choice.trim(); // take out the space
        if(c.equals("ESE") || c.equals("ese")){
            iChoice = 0;
        }else if(c.equals("EVSE") || c.equals("evse")){
            iChoice = 1;
        }else if(c.equals("ISE") || c.equals("ise")){
            iChoice = 2;
        }else if(c.equals("PSE") || c.equals("pse")){
            iChoice = 3;
        }else if(c.equals("SSE") || c.equals("sse")){
            iChoice = 4;
        }

        if(iChoice<0 || iChoice >4){
            System.out.println("Invalid input, please type a integer between 1 to 5!");
            return;

        }

        System.out.println("You choose Program "+ choice);
        while(true){
        //this.uiCourseInput(iChoice);
            uiTwoLevelSubMenu();
        }

    }

    /**
     * It sets the UI right after user input program number
     * @throws IOException
     * @throws Exception
     */
    public void uiTwoLevelSubMenu() throws IOException, Exception{
        System.out.println("1 - Generate flow chart");
        System.out.println("2 - Please type a course number(e.g. ENSE 400) ");
        System.out.println("3 - Find the core courses in a given program");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice = null;

        try{
            choice = input.readLine();
            if(choice.equals("q")||choice.equals("Q")){
                System.out.println("The program is closed!");
            System.exit(0);
            }else if(choice.compareToIgnoreCase("0") == 0){
                this.uiProgramChoose();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        int iChoice2 = this.stringToInt(choice);
        if(iChoice2<1 || iChoice2 >3){
            System.out.println("Invalid input, please type either number 1 or 2!");
            return;

        }else if(iChoice2 == 1){

                this.UIFlowChart(iChoice);

        }else if(iChoice2 == 2){
            while(true){
                this.uiCourseInput(iChoice);
            }
        }else if(iChoice2 == 3){
            m.printAllCourseByProgram(iChoice);
        }

    }

    private Course course;
    private String courseInput;
    /**
     * It sets UI once user chooses option 2 in uiTwoLevelSubMenu()
     * @param programNum
     * @throws Exception
     */
    public void uiCourseInput(int programNum) throws Exception{
        System.out.println("Please type a course number(e.g. ENSE 400) ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String courseNumInput = null;

        try{
            courseNumInput = input.readLine();
            if(courseNumInput.equals("q")||courseNumInput.equals("Q")){
                System.out.println("The program is closed!");
            System.exit(0);
            }else if(courseNumInput.compareToIgnoreCase("0") == 0){
                this.uiTwoLevelSubMenu();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        Course c = null;
        try{
            c = m.getCourse(programNum, courseNumInput);
        }
        catch(Exception e){
        }
        if(c == null){ //Check if the input course is offered in thsi program, if not, then return; else print the related course info
           System.out.println("This course is not offered in this program!");
           return;
        }
        this.course = c;
        this.courseInput = courseNumInput;

        this.uiCourseOutputInfo();

    }

    /**
     * It sets the UI once user inputs a course number
     * @throws Exception
     */
    public void uiCourseOutputInfo() throws Exception{
        System.out.println("Please choose one of the options below for input course in a given program:");
        System.out.println("1 - Find its core pre/coreqs");
        System.out.println("2 - Find its entire course entry");
        System.out.println("3 - Find its pre/coreq phrases");
        System.out.println("4 - Find its dependent courses");
        System.out.println("5 - Find its semester");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice = null;

        try{
            choice = input.readLine();
            if(choice.equals("q")||choice.equals("Q")){
                System.out.println("The program is closed!");
            System.exit(0);
            }else if(choice.compareToIgnoreCase("0") == 0){
                this.uiCourseInput(iChoice);
            }
        }catch(IOException e){
        }

        int iChoice1 = this.stringToInt(choice);
        if(iChoice1<1 || iChoice1 >5){
            System.out.println("Invalid input, please type either number 1 or 2!");

        }else if(iChoice1 == 1){
            //core pre/ coreqs
            System.out.println("\nPrerequisite:");
            m.printPrereqCourses(course, iChoice);

            System.out.println("Corequisite:");
            m.printCoreqCourses(course, iChoice);

        }else if(iChoice1 == 2){
            //course entry
            System.out.println("\n"+course.getEntry()+"\n");
        }else if(iChoice1 == 3){
            //pre/coreq phrases
            System.out.println("\nPrerequisite Phrases:\n"+course.getPrereqPhrase());
            System.out.println("\nCorequisite Phrases:\n"+course.getCoreqPhrase()+"\n");
        }else if(iChoice1 == 4){
            m.printDependentCourses(course, iChoice);
        }else if(iChoice1 == 5){
            m.printSemester(course, iChoice);
        }
    }

    /**
     * It sets the UI of main menu
     * @throws Exception
     */
    public void mainMenu() throws Exception{
        while(true){
            System.out.println("\n\n\nCourseFlow Application\n");
            System.out.println("CourseFlow Menu:(you can type 'q' to quit the program)\n");
            System.out.println("CourseFlow Menu:(you can type '0' to go to last menu of the program)\n");
            this.uiVersionChoose();
        }
    }

    /**
     * It sets the UI of generating flowchart
     * @param programNum
     * @throws IOException
     */
    public void UIFlowChart (int programNum) throws IOException {
        CoursesFlowChart flowchart = new CoursesFlowChart(new Program(programNum));
        flowchart.setAllSemestersHashtable(m.getHashSemesterCourse(programNum));       
        flowchart.setProgramNum(programNum);
        flowchart.Draw1();
    }
}
