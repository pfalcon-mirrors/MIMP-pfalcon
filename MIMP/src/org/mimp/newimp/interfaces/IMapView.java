package org.mimp.newimp.interfaces;

import org.mimp.newimp.GeoPoint;
import org.mimp.newimp.Projection;

public interface IMapView {

    IMapController getController();
    int getZoomLevel();
    int getMaxZoomLevel();
    int getLatitudeSpan();
    int getLongitudeSpan();
    GeoPoint getMapCenter();
    void setMapCenter(int[] values);
    Projection getProjection();
    void displayZoomControls(boolean b);
}
