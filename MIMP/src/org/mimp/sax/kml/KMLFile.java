package org.mimp.sax.kml;

import java.io.File;

import org.mimp.sax.ParsedFile;
import org.mimp.sax.ParsedObject;

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
