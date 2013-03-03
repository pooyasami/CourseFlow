/*
 * CourseCatalogue.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

/**
 * This class is passing the file name of course catalogue to the class CatalogueParser, and calls the parse() in the class CatalogueParser
 * @author Daphne Z
 */

public class CourseCatalogue {

    private CatalogueParser catalogueParser;
       
    /**
     * Constructor
     * @param filePath: file name of course catalogue
     */
    public CourseCatalogue(String filePath){
        catalogueParser = new CatalogueParser();
        catalogueParser.setTargetFileName(filePath);
    }

    /**
     * It calls the parse() in class catalogueParser
     */
    public void bindData(){
        catalogueParser.parse();
    }

}
