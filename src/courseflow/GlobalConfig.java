/*
 * GlobalConfig.java
 *
 * Copyright(c) 2011 Pooya Samizadeh-Yazd and Yuan Zhou All Rights Reserved.
 *
 */

package courseflow;

/**
 * This class is for globally set the version of calendar and catalogue,
 * and uses Singleton Pattern to let other class call this function without newing the object
 * @author Daphne Z
 */

public class GlobalConfig {
    private static GlobalConfig _instance = null;
    private int version;   //1,2

    private GlobalConfig(){
    }

    /**
     * Here's using Singleton Pattern to let other class call this function without newing the object
     * @return
     */
    public static GlobalConfig instance(){
        if(_instance == null)
            _instance = new GlobalConfig();

        return _instance;
    }

    /**
     * Sets the version
     * @param version
     */
    public void setVersion(int version){
        this.version = version;
    }

    /**
     * Returns the version
     * @return
     */
    public int getVersion(){
        return this.version;
    }
}
