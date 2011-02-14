package org.mimp.newimp.interfaces;

import org.mimp.newimp.GeoPoint;

import android.graphics.Point;

public interface IProjection {

    Point toPixels(GeoPoint in, Point out);
    GeoPoint fromPixels(int x, int y);
    float metersToEquatorPixels(float meters);
}
