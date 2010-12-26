package org.mimp.sax.parser.kml;

import java.util.Vector;

import org.mimp.sax.parser.GeoPointer;
import org.mimp.sax.parser.ParsedObject;
import org.mimp.sax.parser.PointOfInterest;

@SuppressWarnings("unused")
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

    @Override
    public Vector<GeoPointer> getGeoPoints() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector<PointOfInterest> getPoints() {
        // TODO Auto-generated method stub
        return null;
    }

}
