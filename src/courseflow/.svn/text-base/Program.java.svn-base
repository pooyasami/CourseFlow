 /*
 * Program.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This class is to set the program number, let the program number become the related program name,
 * and set the Hashtable<Integer, ArrayList<Course> > hashProgram
 * @author Daphne Z
 */

public class Program {

    private int programNum;
    private Hashtable<Integer, ArrayList<Course> > hashProgram;

    /**
     * Constructor
     * @param pNum
     */
    public Program(int pNum){
        programNum = pNum;
    }

    /**
     * Returns programNum
     * @return
     */
    public int getProgramNum(){
        return programNum;
    }
    
    /**
     * Sets Hashtable<Integer, ArrayList<Course> > hashProgram
     * @param hashProgram
     */
    public void setHashProgram(Hashtable<Integer, ArrayList<Course> > hashProgram)
    {
        this.hashProgram = hashProgram;
    }

    /**
     * Returns Hashtable<Integer, ArrayList<Course> > hashProgram
     * @return
     */
    public Hashtable<Integer, ArrayList<Course> > getHashProgram(){
        return hashProgram;
    }

    /**
     * Returns the program name based on the program number
     * @return
     */
    public String getProgramName()
    {
        if(this.programNum ==  0)
            return "ESE";
        else if(this.programNum == 1)
            return "EVSE";
        else if (this.programNum ==2)
            return "ISE";
        else if(this.programNum == 3)
            return "PSE";
        else if(this.programNum ==4)
            return "SSE";
        else
            return "Unknown";

    }
}

