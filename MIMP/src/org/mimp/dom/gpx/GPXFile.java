package org.mimp.dom.gpx;

import java.io.File;

import org.mimp.dom.ParsedFile;
import org.mimp.dom.ParsedObject;


public class GPXFile implements ParsedFile {

    private String mPath = "";
    private File mFile;
    private GPXObject gpxObject = null;

    public GPXFile(String path) throws Exception {

    }

    public String getPath() {
        return mPath;
    }

    public String getExtention() {
        return "gpx";
    }

    public String getFileName() {
        return mFile.getName();
    }

    public ParsedObject getParsedObject() {
        return (ParsedObject) gpxObject;
    }
}
