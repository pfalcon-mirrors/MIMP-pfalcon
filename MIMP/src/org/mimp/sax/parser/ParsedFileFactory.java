package org.mimp.sax.parser;

import java.io.File;

import org.mimp.sax.parser.gpx.GPXFile;
import org.mimp.sax.parser.kml.KMLFile;

public class ParsedFileFactory {
        
    public static ParsedFile getParsedFile(File file) {
        ParsedFile mParsedFile = null;
        String mPath = file.getAbsolutePath();
        int dot = mPath.lastIndexOf(".");
        String mExtention = mPath.substring(dot + 1);        
        try {
            if (mExtention.equalsIgnoreCase("gpx")) {            
                mParsedFile = new GPXFile(file.getAbsolutePath());
            }
            else if (mExtention.equalsIgnoreCase("kml")) {
                mParsedFile = new KMLFile(file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mParsedFile;
    }
}