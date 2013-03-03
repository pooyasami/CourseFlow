/*
 * CourseCalendar.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This class is passing the program, calendar file, "<table>" location in the calendar file to class CalendarParser,
 * calls the parse() in class CalendarParser to parse the course calendar, and sets the hashtable got from CalendarParser to program
 * @author Daphne Z
 */

public class CourseCalendar {

    private Program program;
    private CalendarParser calendarParser;
    private String url;
    private int location; // the place where the program responding table in the html code

    /**
     * Constructor
     */
    public CourseCalendar()
    {
        calendarParser = new CalendarParser();
    }

    /**
     * Sets the program
     * @param program
     */
    public void setProgram(Program program)
    {
        this.program = program;
    }

    /**
     * Sets the program related calendar file path
     * @param url
     */
    public void setProgramURL(String url)
    {
        this.url = url;
    }

    /**
     * Sets the location where "<table>" html code in the related calendar file
     * @param location
     */
    public void setPosition(int location)
    {
        this.location = location;
    }

    /**
     * This method sets the calendar file path, "<table>" location in the calendar file, and program,
     * calls the parse() in the class CalendarParser, and gets the Hashtable<Integer, ArrayList<Course>>,
     * sets the hashtable into the program
     */
    public void setHashSemester2Program()
    {
        calendarParser.setTargetFileName(url);
        calendarParser.setPosition(this.location);
        calendarParser.setProgram(this.program);

        calendarParser.parse();

        Hashtable<Integer, ArrayList<Course>> tmp = calendarParser.getAllSemester(); //return hashSemesters from calendarParser
        program.setHashProgram(tmp); //set the program-related courses by semester number       
    }


}
