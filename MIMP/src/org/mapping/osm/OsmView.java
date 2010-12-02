package org.mapping.osm;

import java.util.List;

import org.mimp.globals.S;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

@SuppressWarnings("unused")
public class OsmView extends MapView {
    
    int x0, y0, z0 = 10;
    boolean ortho = true;
    TileLoader tl;
    boolean depla = false;
    PointF p1, p2;
    Tile t;
    int X, Y = 0;
    Canvas canvas;
    private boolean backed = false;
	private List<Overlay> liov;

    public OsmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        tl = new TileLoader(this);
        invalidate();
        
        SharedPreferences settings = context.getSharedPreferences(S.PREFS_NAME, 0);
        x0 = settings.getInt("x0", 0);
        y0 = settings.getInt("y0", 0);
        z0 = settings.getInt("z0", 0);
        
        if (x0 == 0 || y0 == 0) {

        }
    }

    @Override
    public void setSatellite(boolean mode) {
        super.setSatellite(mode);
        ortho = mode;
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        applyMapViewListener();
    }

    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        int wi = getWidth();
        int he = getHeight();
        drawtiles(canvas, wi, he, false);
        this.canvas = canvas;
        drawOverlays();
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    public void gotoLatLon(GeoPoint gp) {

    }

    public void gotoLatLon(double[] ll) {

    }

    public void setScale(int level) {

    }

    public int getX0() {
        return x0;
    }
    
    public int getY0() {
        return y0;
    }
    
    public int getScale() {
        return z0;
    }

    void drawtiles(Canvas canvas, int wi, int he, boolean force) {

    }

    public void drawOverlays() {

    }

    protected void applyMapViewListener() {
        final GestureDetector gd = new GestureDetector(
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2,
                            float velocityX, float velocityY) {
                        return true;
                    }
                });
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
	                case MotionEvent.ACTION_MOVE:
	                    PointF p3 = new PointF(event.getX(), event.getY());
	                    x0 -= p3.x - p2.x;
	                    y0 += p3.y - p2.y;
	                    p2 = p3;
	                    invalidate();
	                    if (!tl.thread.isAlive()) {
	                        tl.thread = new Thread(tl);
	                        tl.thread.start();
	                    }
	                    break;
	                default:
                }
                invalidate();
                return gd.onTouchEvent(event);
            }
        });
    }

    public void onClose() {
        tl.thread.stop();
    }
}
