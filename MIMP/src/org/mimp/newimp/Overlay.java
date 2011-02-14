package org.mimp.newimp;

import android.graphics.Canvas;

public abstract class Overlay {

    /**
     * X Skew value for creating a marker shadow in perspective. 
     */
    protected static float SHADOW_X_SKEW ;
    /**
     * Y Scale value for creating a marker shadow in perspective. 
     */
    protected static float SHADOW_Y_SKEW ;
    
    public Overlay() {

    }

    /**
     * 
     * @param canvas
     * @param mapView
     * @param shadow
     */
    public abstract void draw(Canvas canvas, MapView mapView, boolean shadow);

    /**
     * 
     * @param canvas
     * @param mapView
     * @param shadow
     * @param when
     * @return TRUE if the Overlay has been Drawn
     */
    protected abstract boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when);

    /**
     * 
     * @param p
     * @param mapView
     * @return
     */
    protected abstract boolean onTap(GeoPoint p, MapView mapView);

    /**
     * 
     * @param e
     * @param mapView
     * @return TRUE if the event has been consumed otherwise FALSE
     */
    protected abstract boolean onTouchEvent(android.view.MotionEvent e, MapView mapView);
    
    
    /**
     * 
     * @param p the point touched
     * @param mapView displayed
     * @return TRUE if the Overlay has been touched, FALSE otherwise
     */
    public boolean isTapOnElement(GeoPoint p, MapView mapView) {
        return false;
    }
}
