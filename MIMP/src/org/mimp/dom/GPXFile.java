package org.mimp.dom;

import java.io.File;

import org.mimp.dom.gpx.GpxType;

public class GPXFile implements ParsedFile {

    private String mPath = "";
    private File mFile;
    private GpxType mGpxType = null;

    protected GPXFile(String path, GpxType gpxType) {
        mPath = path;
        mGpxType = gpxType;
        mFile = new File(path);
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
        return mGpxType;
    }
}
