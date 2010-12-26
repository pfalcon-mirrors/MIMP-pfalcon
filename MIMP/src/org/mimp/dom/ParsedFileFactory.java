package org.mimp.dom;

import java.io.File;



public class ParsedFileFactory {
        
    public static ParsedFile getParsedFile(File file) {
        ParsedFile mParsedFile = null;
        String mPath = file.getAbsolutePath();
        int dot = mPath.lastIndexOf(".");
        String mExtention = mPath.substring(dot + 1);        
        try {
            if (mExtention.equalsIgnoreCase("gpx")) {            
                mParsedFile = (ParsedFile) new GPXFile(file.getAbsolutePath());
            }
            else if (mExtention.equalsIgnoreCase("kml")) {
                mParsedFile = (ParsedFile) new KMLFile(file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mParsedFile;
    }
}
