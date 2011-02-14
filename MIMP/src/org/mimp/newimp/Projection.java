package org.mimp.newimp;

import org.mimp.newimp.interfaces.IProjection;

import android.graphics.Point;

public class Projection implements IProjection {

    MapView mView;
    
    public Projection(MapView view) {
        this.mView = view;
    }
    
    @Override
    public Point toPixels(GeoPoint in, Point out) {
        double[] coords = new double[] {in.getLatitudeE6()/1E6,in.getLongitudeE6()/1E6};
        int[] pixels = TileFactory.LatLngToPixel(coords, mView.getZoomLevel());
        Point center = mView.getController().getCenter();
        out.x = pixels[0] - center.x + mView.getWidth()/2;
        out.y = pixels[1] - center.y + mView.getHeight()/2;
        return out;
    }

    @Override
    public GeoPoint fromPixels(int x, int y) {
        Point center = mView.getController().getCenter();
        x = x + center.x - mView.getWidth()/2;
        y = y + center.y - mView.getHeight()/2;
        double[] coords = TileFactory.PixelToLatLng(new int[] {x,y}, mView.getZoomLevel());
        GeoPoint geoPoint = new GeoPoint(coords[0], coords[1]);
        return geoPoint;
    }

    @Override
    public float metersToEquatorPixels(float meters) {
        return 0;
    }

}
