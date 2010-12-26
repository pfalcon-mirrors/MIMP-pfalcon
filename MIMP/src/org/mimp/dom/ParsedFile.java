package org.mimp.dom;

public interface ParsedFile {

    String getExtention();
    String getPath();
    String getFileName();
    ParsedObject getParsedObject();
}
