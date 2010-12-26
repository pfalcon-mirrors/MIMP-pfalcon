package org.mimp.sax;

public interface ParsedFile {

    String getExtention();
    String getPath();
    String getFileName();
    ParsedObject getParsedObject();
}
