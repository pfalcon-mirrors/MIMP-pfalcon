package org.mimp.newimp.interfaces;

import org.mimp.newimp.GeoPoint;

import android.graphics.Point;

public interface IMapController {

        void animateTo(GeoPoint geoPoint);
        void setCenter(GeoPoint point);
        int getZoom();
        int setZoom(int zoomLevel);
        boolean zoomIn();
        boolean zoomInFixing(int xPixel, int yPixel);
        boolean zoomOut();
        boolean zoomOutFixing(int xPixel, int yPixel);
        void zoomToSpan(int latSpanE6, int lonSpanE6);
        void setCenter(Point point);
        Point getCenter();
}
