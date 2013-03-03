/*
 * Main.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */
package courseflow;

import java.io.IOException;

/**
 * This Main class starts with downloading all the required files(course calendar .htm files, and course catalogues) from online,
 * and calls UI
 * @author Pooya
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
    
        Download download = new Download();

        if (download.convert()) {
            UI ui = new UI();

            ui.mainMenu();
        }


    }
}
