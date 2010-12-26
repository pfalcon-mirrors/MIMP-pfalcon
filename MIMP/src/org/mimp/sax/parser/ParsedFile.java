package org.mimp.sax.parser;

public interface ParsedFile {

    String getExtention();
    String getPath();
    String getFileName();
    ParsedObject getParsedObject();
}
