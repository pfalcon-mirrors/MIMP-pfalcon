package org.mimp.sax.parser.gpx;

import java.io.File;
import java.io.FileInputStream;

import org.mimp.sax.parser.gpx.GPXHandlerImplLight;
import org.mimp.sax.parser.ParsedFile;
import org.mimp.sax.parser.ParsedObject;
import org.xml.sax.InputSource;

public class GPXFile implements ParsedFile {

    private String mPath = "";
    private File mFile;
    private GPXObject gpxObject;

    public GPXFile(String path) throws Exception {
        mPath = path;
        mFile = new File(path);
        GPXHandlerImplLight gpxHandlerImpl = new GPXHandlerImplLight();
        GPXParserLight gpxParser = new GPXParserLight(gpxHandlerImpl, null);
        FileInputStream byteStream = null;
        byteStream = new FileInputStream(path);
        InputSource inputSource = new InputSource(byteStream);
        gpxParser.parse(inputSource);
        gpxObject = gpxHandlerImpl.getGPXObject();
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
        return gpxObject;
    }
}
