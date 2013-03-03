/*
 * Programs.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

import java.util.ArrayList;

/**
 * This class is to new and get the ArrayList<Program>
 * @author Daphne Z
 */

public class Programs {

    private ArrayList<Program> programs;

    /**
     * Constructor
     * new a ArrayList<Program>
     */
    public Programs()
    {
        programs = new ArrayList<Program>();        
    }

    /**
     * Returns the ArrayList<Program>
     * @return
     */
    public ArrayList<Program> getPrograms()
    {
        return programs;
    }
}
