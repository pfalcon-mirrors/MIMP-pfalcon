package org.mimp.displayables;

import org.mapping.osm.OsmView;
import org.mapping.osm.OsmApi;
import org.mimp.R;
import org.mimp.views.ExtendedMapView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.location.Location;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay.Snappable;

public class ExtendedLocationOverlay extends MyLocationOverlay implements
        Snappable {

    private Context context;
    private GeoPoint location;
    private Paint accuracyPaint;

    public ExtendedLocationOverlay(Context context, MapView mapView) {
        super(context, mapView);
        this.context = context;
        accuracyPaint = new Paint();
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        super.draw(canvas, mapView, shadow);
    }

    public void draw(Canvas canvas, MapView mapView, boolean shadow, int x,
            int y) {
        // System.out.println("******************************************** ExtendedLocationOverlay");
        Point screenPoint = new Point();
        location = getMyLocation();
        // System.out.println("******************************************** " +
        // location + " " + mapView.getHeight() + " " + mapView.getWidth());

        if (location != null) {

            int latitude = location.getLatitudeE6();
            int longitude = location.getLongitudeE6();
            float accuracy = getLastFix().getAccuracy();

            float[] result = new float[1];

            Location.distanceBetween(latitude, longitude, latitude,
                    longitude + 1, result);
            float longitudeLineDistance = result[0];

            int[] pa = OsmApi.LatLngToPixel(new double[] { latitude / 1E6,
                    longitude / 1E6 }, ((OsmView) mapView).getScale());

            int[] pb = OsmApi.LatLngToPixel(new double[] { latitude / 1E6,
                    (longitude / 1E6) - (accuracy / longitudeLineDistance) },
                    ((OsmView) mapView).getScale());

            Log.e(this.toString(), result[0] + " " + accuracy
                    / longitudeLineDistance + " " + pa[0] + " " + pa[1] + " "
                    + pb[0] + " " + pb[1]);

            screenPoint = new Point(pa[0] - x + mapView.getWidth() / 2,
                    mapView.getHeight() - (pa[1] - y + mapView.getHeight() / 2));

            Point distPoint = new Point(pb[0] - x + mapView.getWidth() / 2,
                    mapView.getHeight() - (pb[1] - y + mapView.getHeight() / 2));

            Bitmap markerImage = BitmapFactory.decodeResource(
                    context.getResources(), R.drawable.blue_location);

            int radius = screenPoint.x - distPoint.x;
            Log.e(this.toString(), radius + " " + accuracy + " "
                    + longitudeLineDistance + " " + screenPoint.y + " "
                    + distPoint.y);

            accuracyPaint.setColor(0xff6666ff);
            accuracyPaint.setStyle(Style.STROKE);
            accuracyPaint.setAntiAlias(true);
            accuracyPaint.setStrokeWidth(1.5f);
            canvas.drawCircle(screenPoint.x, screenPoint.y, radius,
                    accuracyPaint);

            accuracyPaint.setColor(0x186666ff);
            accuracyPaint.setStyle(Style.FILL);
            accuracyPaint.setAntiAlias(true);
            canvas.drawCircle(screenPoint.x, screenPoint.y, radius,
                    accuracyPaint);

            canvas.drawBitmap(markerImage,
                    screenPoint.x - markerImage.getWidth() / 2, screenPoint.y
                            - markerImage.getHeight() / 2, null);
        }
    }

    @Override
    protected void drawMyLocation(Canvas canvas, MapView mapView,
            Location lastFix, GeoPoint myLocation, long when) {
        if (mapView.getClass() == ExtendedMapView.class)
            super.drawMyLocation(canvas, mapView, lastFix, myLocation, when);
        else
            draw(canvas, mapView, false, ((OsmView) mapView).getX0(),
                    ((OsmView) mapView).getY0());
    }
}
