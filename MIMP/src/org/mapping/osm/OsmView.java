package org.mapping.osm;

import java.util.List;
import java.util.Vector;

import org.mapping.google.impl.Gmaps;
import org.mimp.displayables.DrawableMapOverlay;
import org.mimp.displayables.ExtendedLocationOverlay;
import org.mimp.displayables.LineMapOverlay;
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
            gotoString("Paris");
            setScale(10);
        }
    }

    public void removeTrack() {
        if (!backed) {
            backupOverlays();
            for (; 1<liov.size() ;) {
                liov.remove(1);
            }
        }
        else {
            liov = null;
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
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == View.GONE) {
            onClose();
        }
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

    public void backupOverlays() {
        if (getOverlays().size() > 0) {
            liov = new Vector<Overlay>(getOverlays());
            getOverlays().clear();
        }
    }

    public void restoreOverlays() {
        if (liov != null && liov.size() > 0) {
            getOverlays().addAll(liov);
        }
        liov = null;
    }

    public void gotoString(String txt) {
        double[] ll = Gmaps.findLocation(txt);
        if (ll != null)
            gotoLatLon(ll);
    }

    public void gotoLatLon(GeoPoint gp) {
        if (gp != null) {
            double[] ll = {
                    (double)gp.getLatitudeE6() / 1E6,
                    (double)gp.getLongitudeE6() / 1E6};
            gotoLatLon(ll);
        }
    }

    public void gotoLatLon(double[] ll) {
        if (ll != null && ll.length >= 2) {
            int[] pix = OsmApi.LatLngToPixel(ll, z0);
            x0 = pix[0];
            y0 = pix[1];
            if (canvas != null)
                draw(canvas);
            invalidate();
        }
    }

    public void setScale(int level) {
        double[] ll = OsmApi.PixelToLatLng(new int[] { x0, y0 }, z0);
        z0 = level;
        gotoLatLon(ll);
        invalidate();
        if (canvas != null)
            draw(canvas);
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
        this.canvas = canvas;
        int xc = wi >> 1, yc = he >> 1;
        int x1 = x0 - xc, y1 = y0 - yc, x2 = x1 + wi, y2 = y1 + he;
        int tx1 = x1 >> 8, ty1 = y1 >> 8, tx2 = x2 >> 8, ty2 = y2 >> 8;
        for (int ty = ty1; ty <= ty2; ty++) {
            for (int tx = tx1; tx <= tx2; tx++) {
                t = tl.getTile(ortho, tx, ty, z0, force);
                if (t != null && t.state == 1)
                    canvas.drawBitmap(t.bitmap, tx * 256 - x1, y2 - (ty * 256 + 255), null);
            }
        }
    }

    synchronized public void drawOverlays() {
        for (int i = 0; i < getOverlays().size(); i++) {
            Overlay ov = getOverlays().get(i);
            if (ov.getClass() == LineMapOverlay.class)
                ((LineMapOverlay) ov).draw(canvas, this, false, x0, y0);
            else if (ov.getClass() == DrawableMapOverlay.class)
                ((DrawableMapOverlay) ov).draw(canvas, this, false, x0, y0);
            else if (ov.getClass() == ExtendedLocationOverlay.class) {
                ((ExtendedLocationOverlay) ov).draw(canvas, this, false, x0, y0);
            }
        }
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
                case MotionEvent.ACTION_DOWN:
                    depla = true;
                    p2 = p1 = new PointF(event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    depla = false;
                    if (backed) {
                        restoreOverlays();
                        backed = false;
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!backed) {
                        backupOverlays();
                        backed = true;
                    }
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
                return gd.onTouchEvent(event);
            }
        });
    }

    public void onClose() {
        tl.thread.stop();
    }
}
