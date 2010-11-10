package org.mimp.displayables;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

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

    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/
	
    /**
     * @param context the context in which to display the overlay
     * @param geoPoint the geographical point where the overlay is located
     * @return 
     */
    public void setLineMapOverlay(Context context, List<GeoPoint> geoPoints, int height, int width) {
        this.geoPoints = new Vector<GeoPoint>(geoPoints);      
		this.pathPaint = new Paint();
		this.pathPaint.setAntiAlias(false);
		this.pathPaint.setStrokeWidth(4);
		this.pathPaint.setARGB(100, 113, 105, 252);
	}
    
    /*****************************************************************************
     * 
     * Drawing methods
     * 
     *****************************************************************************/
    
    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        super.draw(canvas, mapView, shadow);
        
		thePath = new Path();
		projection = mapView.getProjection();        
		projection.toPixels(geoPoints.get(0), screenPointa);
		thePath.moveTo(screenPointa.x,screenPointa.y);
		for (int i=0; i < geoPoints.size() ;i++) {
	        projection.toPixels(geoPoints.get(i), screenPointb);
        	thePath.lineTo(screenPointb.x, screenPointb.y);
        }
		this.pathPaint.setStyle(Paint.Style.STROKE);
		canvas.drawPath(thePath, pathPaint);
	}
    
    public void draw(Canvas canvas, MapView mapView, boolean shadow, int x, int y) {

	}
}
