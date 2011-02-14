package org.mimp.displayables;

import org.mimp.newimp.GeoPoint;
import org.mimp.newimp.MapView;
import org.mimp.newimp.Overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

public class DrawableMapOverlay extends Overlay {

    public final static int LEFT = -1;
    public final static int CENTER = 0;
    public final static int RIGHT = 1;
    private GeoPoint geoPoint;
    private Context context;
    private int drawable;
    private int type;

    /**
     * @param context
     *            the context in which to display the overlay
     * @param geoPoint
     *            the geographical point where the overlay is located
     * @param drawable
     *            the ID of the desired drawable
     * @return
     */
    public void setImageMapOverlay(Context context, GeoPoint geoPoint,
            int drawable, int type) {
        this.context = context;
        this.geoPoint = geoPoint;
        this.drawable = drawable;
        this.type = type;
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        // Convert geo coordinates to screen pixels
        Point screenPoint = new Point();
        mapView.getProjection().toPixels(geoPoint, screenPoint);

        // Read the image
        Bitmap markerImage = BitmapFactory.decodeResource(
                context.getResources(), drawable);

        // Draw it around the given coordinates
        if (type == LEFT)
            canvas.drawBitmap(markerImage,
                    screenPoint.x - markerImage.getWidth(), screenPoint.y
                            - markerImage.getHeight(), null);
        else if (type == CENTER)
            canvas.drawBitmap(markerImage,
                    screenPoint.x - markerImage.getWidth() / 2, screenPoint.y
                            - markerImage.getHeight(), null);
        else if (type == RIGHT)
            canvas.drawBitmap(markerImage, screenPoint.x, screenPoint.y
                    - markerImage.getHeight(), null);
    }

    @Override
    public boolean onTap(GeoPoint p, MapView mapView) {
        return true;
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

    @Override
    public boolean isTapOnElement(GeoPoint p, MapView mapView) {
        // TODO Auto-generated method stub
        return false;
    }
}
