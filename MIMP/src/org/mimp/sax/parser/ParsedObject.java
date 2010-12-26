package org.mimp.sax.parser;

import java.util.Vector;

public interface ParsedObject {

    String getName();
    String getDescr();
    Vector<GeoPointer> getGeoPoints();
    Vector<PointOfInterest> getPoints();
}
