package org.mimp.displayables;

import java.util.List;
import java.util.Vector;

import org.mimp.globals.S;
import org.mimp.newimp.GeoPoint;
import org.mimp.newimp.MapView;
import org.mimp.newimp.Overlay;
import org.mimp.newimp.Projection;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;

public class LineMapOverlay extends Overlay {

    /*****************************************************************************
     * 
     * Members
     * 
     *****************************************************************************/

    private Vector<GeoPoint> geoPoints;
    private Point screenPointa = new Point();
    private Point screenPointb = new Point();
    private Paint pathPaint = new Paint();
    private Projection projection;
    private Path thePath = null;
    private int innerColor;
    private int outerColor;
    private Context mContext;
    private boolean showOuter;
    private SharedPreferences settings;

    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/

    public LineMapOverlay() {
        this.pathPaint = new Paint();
        this.pathPaint.setAntiAlias(true);
        this.pathPaint.setStrokeWidth(4);
        this.pathPaint.setARGB(200, 100, 170, 240);
        this.pathPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * @param context
     *            the context in which to display the overlay
     * @param geoPoint
     *            the geographical point where the overlay is located
     * @return
     */
    public void setLineMapOverlay(Context context, List<GeoPoint> geoPoints,
            int height, int width) {
        this.mContext = context;
        this.geoPoints = new Vector<GeoPoint>(geoPoints);
        this.settings = mContext.getSharedPreferences(S.PREFS_NAME, 0);
    }

    public void setLineMapOverlay(Context context, Vector<GeoPoint> geoPoints,
            int height, int width) {
        this.mContext = context;
        this.geoPoints = geoPoints;
        this.settings = mContext.getSharedPreferences(S.PREFS_NAME, 0);
    }

    /*****************************************************************************
     * 
     * Drawing methods
     * 
     *****************************************************************************/

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {;
        if (shadow) {
            return;
        }
        innerColor = settings.getInt("inner_color", -932926736);
        outerColor = settings.getInt("outer_color", -932926736);
        showOuter = settings.getBoolean("enable_track_outer_color", false);
        
        thePath = new Path();
        projection = mapView.getProjection();
        projection.toPixels(geoPoints.get(0), screenPointa);
        thePath.moveTo(screenPointa.x, screenPointa.y);
        for (int i = 0; i < geoPoints.size(); i++) {
            projection.toPixels(geoPoints.get(i), screenPointb);
            thePath.lineTo(screenPointb.x, screenPointb.y);
        }
        if (showOuter) {
            this.pathPaint.setARGB(Color.alpha(outerColor), Color.red(outerColor), Color.green(outerColor), Color.blue(outerColor));
            this.pathPaint.setStrokeWidth(10);
            canvas.drawPath(thePath, pathPaint);
        }
        
        this.pathPaint.setARGB(Color.alpha(innerColor), Color.red(innerColor), Color.green(innerColor), Color.blue(innerColor));
        this.pathPaint.setStrokeWidth(4);
        canvas.drawPath(thePath, pathPaint);    
    }

    public void draw(Canvas canvas, MapView mapView, boolean shadow, int x,
            int y) {

    }

    @Override
    protected boolean draw(Canvas canvas, MapView mapView, boolean shadow,
            long when) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean onTap(GeoPoint p, MapView mapView) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean onTouchEvent(MotionEvent e, MapView mapView) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isTapOnElement(GeoPoint p, MapView mapView) {
        // TODO Auto-generated method stub
        return false;
    }
}
