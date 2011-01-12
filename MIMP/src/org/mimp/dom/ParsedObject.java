package org.mimp.dom;

import java.util.List;
import java.util.Vector;

import org.mimp.dom.gpx.WptType;

import com.google.android.maps.GeoPoint;

public interface ParsedObject {

    String getName();
    String getDescr();
    Vector<GeoPoint> getPoints();
    List<WptType> getPOIs();
}
