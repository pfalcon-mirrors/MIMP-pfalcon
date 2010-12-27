package org.mimp.dom;

import java.util.List;
import java.util.Vector;

import org.mimp.dom.gpx.WptType;

public interface ParsedObject {

    String getName();
    String getDescr();
    Vector<GeoPointer> getPoints();
    List<WptType> getPOIs();
}
