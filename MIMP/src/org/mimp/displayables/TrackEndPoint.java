package org.mimp.displayables;

import org.mimp.newimp.GeoPoint;
import org.mimp.newimp.MapView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class TrackEndPoint extends MapPointOverlay {

    private Paint corePaint;

    public TrackEndPoint(GeoPoint location, Context context) {
        super(location, context);
        setHeight(SIZE.BIG);
        setCorePaint(getCorePaint());
    }
    
    @Override
    public boolean onTap(GeoPoint p, MapView mapView) {
        if (isTapOnElement(p, mapView)) {

        }
        return false;
    }

    private Paint getCorePaint() {
        if (corePaint == null) {
            corePaint = new Paint();
            corePaint.setARGB(255, 25, 25, 25); // black
            corePaint.setAntiAlias(true);
        }
        return corePaint;
    }

    @Override
    protected boolean draw(Canvas canvas, MapView mapView, boolean shadow,
            long when) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean onTouchEvent(MotionEvent e, MapView mapView) {
        // TODO Auto-generated method stub
        return false;
    }
}
