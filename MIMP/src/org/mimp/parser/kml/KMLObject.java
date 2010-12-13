package org.mimp.parser.kml;

import org.mimp.parser.ParsedObject;

public class KMLObject implements ParsedObject {

    private static final long serialVersionUID = 328690485076301013L;
    private static final String ENDLINE = "\n";
    private String name = "";
    private String descr = "";
    private String author = "";
    private String email = "";
    private String copyright = "";
    private String year = "";
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return descr;
    }

}
