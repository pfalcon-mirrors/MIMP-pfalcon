package org.mimp.dom;

import java.util.List;
import java.util.Vector;

import org.mimp.dom.gpx.WptType;
import org.mimp.newimp.GeoPoint;

public interface ParsedObject {

    String getName();
    String getDescr();
    Vector<GeoPoint> getPoints();
    List<WptType> getPOIs();
}
