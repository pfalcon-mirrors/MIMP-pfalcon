package org.mimp.displayables;

import java.util.Vector;

import org.mimp.dom.ParsedObject;
import org.mimp.globals.S;
import org.mimp.newimp.GeoPoint;
import org.mimp.newimp.MapView;
import org.mimp.screens.TrackDetailsScreen;
import org.mimp.views.ExtendedMapView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;

public class TrackStartPoint extends MapPointOverlay {

    private Paint corePaint;
    private Context mContext;

    public TrackStartPoint(GeoPoint location, Context context) {
        super(location, context);
        mContext = context;
        selectedMapLocation = location;
        setHeight(SIZE.BIG);
        setCorePaint(getCorePaint());
    }

    @Override
    public boolean onTap(GeoPoint p, MapView mapView) {
        if (isTapOnElement(p, mapView)) {
            ParsedObject parsedObject = ((ExtendedMapView)mapView).getCurrentTrack();
            
            /**
             * calculating track bounds this is safer than using potentially
             * erroneous bound coordinates from GPX or KML
             */
            Vector<GeoPoint> geoPoints = parsedObject.getPoints();
            int coords[] = new int[4];
            coords[0] = geoPoints.get(0).getLatitudeE6();
            coords[1] = geoPoints.get(0).getLatitudeE6();
            coords[2] = geoPoints.get(0).getLongitudeE6();
            coords[3] = geoPoints.get(0).getLongitudeE6();
            
            for (GeoPoint gp : geoPoints) {
                if (gp.getLatitudeE6() < coords[0])
                    coords[0] = gp.getLatitudeE6();
                if (gp.getLatitudeE6() > coords[1])
                    coords[1] = gp.getLatitudeE6();
                if (gp.getLongitudeE6() < coords[2])
                    coords[2] = gp.getLongitudeE6();
                if (gp.getLongitudeE6() > coords[3])
                    coords[3] = gp.getLongitudeE6();
            }
            
            System.out.println("ll : " + coords[0]/1E6 + " " + coords[2]/1E6);
            System.out.println("ur : " + coords[1]/1E6 + " " + coords[3]/1E6);
            
            String infos[] = new String[2];
            infos[0] = parsedObject.getName();
            infos[1] = parsedObject.getDescr();
            
            Intent mIntent = new Intent(mContext, TrackDetailsScreen.class)
                    .putExtra("bounds", coords).putExtra("infos", infos);
            ((Activity) mContext).startActivityForResult(mIntent,
                    S.BubbleInteractionScreen_RQC);
            return true;
        }
        return false;
    }

    private Paint getCorePaint() {
        if (corePaint == null) {
            corePaint = new Paint();
            corePaint.setARGB(255, 15, 215, 19); // green
            corePaint.setAntiAlias(true);
        }
        return corePaint;
    }
    
    public boolean isTapOnElement(GeoPoint p, MapView mapView) {
        Point screenCoords = new Point();
        mapView.getProjection().toPixels(selectedMapLocation, screenCoords);
        RectF hitTestRecr = new RectF();
        hitTestRecr.set(-BLOC_WIDTH / 2, -(BLOC_HEIGHT + BLOC_HEIGHT),
                BLOC_WIDTH / 2, 0);
        hitTestRecr.offset(screenCoords.x, screenCoords.y);
        mapView.getProjection().toPixels(p, screenCoords);
        if (hitTestRecr.contains(screenCoords.x, screenCoords.y)) {
            return true;
        }
        return false;
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
