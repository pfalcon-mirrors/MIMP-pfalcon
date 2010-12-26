package org.mimp.dom;

import java.util.Vector;

import org.mimp.sax.gpx.PointOfInterest;

public interface ParsedObject {

    String getName();
    String getDescr();
    Vector<GeoPointer> getGeoPoints();
    Vector<PointOfInterest> getPoints();
}
