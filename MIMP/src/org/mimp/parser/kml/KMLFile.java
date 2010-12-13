package org.mimp.parser.kml;

import java.io.File;

import org.mimp.parser.ParsedFile;
import org.mimp.parser.ParsedObject;

public class KMLFile implements ParsedFile {
    
    private String mPath = "";
    private File mFile;
    private KMLObject mKMLObject;
    
    public KMLFile(String path) throws Exception {
        mPath = path;
        mFile = new File(path);
        mKMLObject = new KMLObject();
        //TODO:
    }

    @Override
    public String getExtention() {
        return "kml";
    }

    @Override
    public String getPath() {
        return mPath;
    }

    @Override
    public String getFileName() {
        return mFile.getName();
    }

    @Override
    public ParsedObject getParsedObject() {
        return mKMLObject;
    }

}
