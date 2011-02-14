package org.mimp.newimp;

import org.mimp.globals.S;
import org.mimp.newimp.interfaces.IMapController;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;

public class MapController implements IMapController {
    
    private MapView mView;
    private Context mContext;
    private Point center = new Point(0,0);
    private int zoom = 0;
    public static final int MAX = 19;
    public static final int MIN = 0;
    
    public MapController(Context context, MapView mapView) {
        mContext = context;
        mView = mapView;
        SharedPreferences settings = mContext.getSharedPreferences(S.PREFS_NAME,0);
        GeoPoint geoPoint = new GeoPoint();
        geoPoint.setLatitudeE6(settings.getInt("latE6", 0));
        geoPoint.setLongitudeE6(settings.getInt("lonE6", 0));
        zoom = settings.getInt("zoom", 0);
        animateTo(geoPoint);
        mView.invalidate();
    }

    @Override
    public void animateTo(GeoPoint geoPoint) {
        int[] pxy = TileFactory.LatLngToPixel(new double[] {geoPoint.getLatitudeE6()/1E6,geoPoint.getLongitudeE6()/1E6},zoom);
        center.x = pxy[0];
        center.y = pxy[1];
        mView.invalidate();
    }

    @Override
    public Point getCenter() {
        return center;
    }

    @Override
    public void setCenter(Point point) {
        center = point;
    }
    
    @Override
    public void setCenter(GeoPoint geoPoint) {
        int[] pxy = TileFactory.LatLngToPixel(new double[] {geoPoint.getLatitudeE6()/1E6,geoPoint.getLongitudeE6()/1E6},zoom);
        center.x = pxy[0];
        center.y = pxy[1];
        mView.invalidate();
    }
    
    @Override
    public int getZoom() {
        return zoom;
    }

    @Override
    public int setZoom(int zoomLevel) {
        this.zoom = zoomLevel;
        if (this.zoom == MAX) {
            mView.getZoomControls().setIsZoomInEnabled(false);   
        }
        else if (this.zoom == MIN) {
            mView.getZoomControls().setIsZoomOutEnabled(false);   
        }
        else {
            mView.getZoomControls().setIsZoomOutEnabled(true);   
            mView.getZoomControls().setIsZoomInEnabled(true);   
        }
        mView.getTileController().shoke();
        return zoom;
    }

    @Override
    public void zoomToSpan(int latSpanE6, int lonSpanE6) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public boolean zoomIn() {
        if (zoom == MAX)
            return false;
        double [] ll = TileFactory.PixelToLatLng(new int[] {center.x,center.y}, zoom);
        setZoom(zoom+1);
        int [] pxy = TileFactory.LatLngToPixel(ll, zoom);
        mView.setMapCenter(pxy);
        mView.invalidate();
        return true;
    }

    @Override
    public boolean zoomInFixing(int xPixel, int yPixel) {
        if (zoom == MAX)
            return false;
        xPixel = xPixel + center.x - mView.getWidth()/2;
        yPixel = yPixel + center.y - mView.getHeight()/2;
        double [] ll = TileFactory.PixelToLatLng(new int[] {xPixel,yPixel}, zoom);
        setZoom(zoom+1);
        int [] pxy = TileFactory.LatLngToPixel(ll, zoom);
        mView.setMapCenter(pxy);
        mView.invalidate();
        return true;
    }

    @Override
    public boolean zoomOut() {
        if (zoom == MIN)
            return false;
        double [] ll = TileFactory.PixelToLatLng(new int[] {center.x,center.y}, zoom);
        setZoom(zoom-1);
        int [] pxy = TileFactory.LatLngToPixel(ll, zoom);
        mView.setMapCenter(pxy);
        mView.invalidate();
        return true;
    }

    @Override
    public boolean zoomOutFixing(int xPixel, int yPixel) {
        if (zoom == MIN)
            return false;
        xPixel = xPixel + center.x - mView.getWidth()/2;
        yPixel = yPixel + center.y - mView.getHeight()/2;
        double [] ll = TileFactory.PixelToLatLng(new int[] {xPixel,yPixel}, zoom);
        if (Math.abs(ll[0]) > TileFactory.MAX_LAT || ll[1] > 360)
            return false;
        setZoom(zoom-1);
        int [] pxy = TileFactory.LatLngToPixel(ll, zoom);
        mView.setMapCenter(pxy);
        mView.invalidate();
        return true;
    }
}
