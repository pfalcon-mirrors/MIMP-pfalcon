package org.mimp.displayables;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class DrawableMapOverlay extends Overlay {

	public final static int LEFT = -1;
	public final static int CENTER = 0;
	public final static int RIGHT = 1;
    private GeoPoint geoPoint;
    private Context context;
    private int drawable;
    private int type;

    /**
     * @param context the context in which to display the overlay
     * @param geoPoint the geographical point where the overlay is located
     * @param drawable the ID of the desired drawable
     * @return 
     */
    public void setImageMapOverlay(Context context, GeoPoint geoPoint, int drawable, int type) {
        this.context = context;
        this.geoPoint = geoPoint;
        this.drawable = drawable;
        this.type = type;
    }

    @Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
        super.draw(canvas, mapView, shadow);

        // Convert geo coordinates to screen pixels
        Point screenPoint = new Point();
        mapView.getProjection().toPixels(geoPoint, screenPoint);

        // Read the image
        Bitmap markerImage = BitmapFactory.decodeResource(context.getResources(), drawable);

        // Draw it around the given coordinates
        if (type == LEFT)
        	canvas.drawBitmap(markerImage,screenPoint.x - markerImage.getWidth(),screenPoint.y - markerImage.getHeight(), null);
        else if (type == CENTER)
        	canvas.drawBitmap(markerImage,screenPoint.x - markerImage.getWidth()/2,screenPoint.y - markerImage.getHeight(), null);
        else if (type == RIGHT)
        	canvas.drawBitmap(markerImage,screenPoint.x,screenPoint.y - markerImage.getHeight(), null);
        return true;
    }

    @Override
    public boolean onTap(GeoPoint p, MapView mapView) {
        // Handle tapping on the overlay here
        return true;
    }
}

