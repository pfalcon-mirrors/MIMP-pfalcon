package org.mimp.parser;

import java.io.File;
import java.io.FileInputStream;

import org.xml.sax.InputSource;

public class GPXFile {

    private String mPath = "";
    private File mFile;
    private GPXObject gpxObject;

    public GPXFile(String path) throws Exception {
        mPath = path;
        mFile = new File(path);
        GPXHandlerImpl gpxHandlerImpl = new GPXHandlerImpl();
        GPXParserLight gpxParserLight = new GPXParserLight(gpxHandlerImpl, null);
        FileInputStream byteStream = null;
        byteStream = new FileInputStream(path);
        InputSource inputSource = new InputSource(byteStream);
        gpxParserLight.parse(inputSource);
    }

    public String getPath() {
        return mPath;
    }

    public String getExtention() {
        int dot = mPath.lastIndexOf(".");
        return mPath.substring(dot + 1);
    }

    public String getFileName() {
        return mFile.getName();
    }

    public GPXObject getGpxObject() {
        return gpxObject;
    }
}
