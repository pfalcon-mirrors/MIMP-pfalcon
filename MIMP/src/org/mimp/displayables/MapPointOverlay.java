package org.mimp.displayables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public abstract class MapPointOverlay extends Overlay {

	private int POINTER_WIDTH = 20;
	private int POINTER_HEIGHT = 20;
	private int BLOC_WIDTH = 20;
	private int BLOC_HEIGHT = 10;
	
	private int CORE_POINTER_WIDTH = 10;
	private int CORE_POINTER_HEIGHT = 10;
	private int CORE_BLOC_WIDTH = 10;
	private int CORE_BLOC_HEIGHT = 10;
	
	private GeoPoint selectedMapLocation;
	private Paint innerPaint;
	private Paint corePaint;
	private Paint borderPaint;
	
	public enum SIZE {
		BIG,
		MEDIUM,
		SMALL
	}
	
	public MapPointOverlay(GeoPoint location, Context context) {
		selectedMapLocation = location;
	}
	
	public void setHeight(SIZE size) {
		if (size == SIZE.BIG) {
			BLOC_HEIGHT = CORE_BLOC_HEIGHT = 25;
		}
		else if (size == SIZE.MEDIUM) {
			BLOC_HEIGHT = CORE_BLOC_HEIGHT = 15;
		}
		else if (size == SIZE.SMALL) {
			return;
		}
	}
	
	public void setCorePaint(Paint paint) {
		corePaint = paint;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		if (shadow == false) { // bored to draw a shadow if you do it send me the code ;)
			//  First determine the screen coordinates of the selected MapLocation
			Point selDestinationOffset = new Point();
			mapView.getProjection().toPixels(selectedMapLocation, selDestinationOffset);
	    	
			// Setup the poi position pointer
			RectF pointerRect = new RectF(0,0,POINTER_WIDTH,POINTER_HEIGHT);
			int pointerOffsetX = selDestinationOffset.x-POINTER_WIDTH/2;
			int pointerOffsetY = selDestinationOffset.y-POINTER_HEIGHT;			
			pointerRect.offset(pointerOffsetX,pointerOffsetY);
			
			//  Setup the poi bloc coords
			RectF blocRect = new RectF(0,0,BLOC_WIDTH,BLOC_HEIGHT);
			int blocOffsetX = selDestinationOffset.x-BLOC_WIDTH/2;
			int blocOffsetY = selDestinationOffset.y-BLOC_HEIGHT-POINTER_HEIGHT;
			blocRect.offset(blocOffsetX,blocOffsetY);
			
			// Setup the core
			RectF corePointerRect = new RectF(0,0,CORE_POINTER_WIDTH,CORE_POINTER_HEIGHT);
			int corePointerOffsetX = selDestinationOffset.x-CORE_POINTER_WIDTH/2;
			int corePointerOffsetY = selDestinationOffset.y-CORE_POINTER_HEIGHT-POINTER_HEIGHT/2;
			corePointerRect.offset(corePointerOffsetX,corePointerOffsetY);
			
			//  Setup the poi bloc coords
			RectF coreBlocRect = new RectF(0,0,CORE_BLOC_WIDTH,CORE_BLOC_HEIGHT);
			int coreBlocOffsetX = selDestinationOffset.x-CORE_BLOC_WIDTH/2;
			int coreBlocOffsetY = selDestinationOffset.y-CORE_BLOC_HEIGHT-CORE_POINTER_HEIGHT-POINTER_HEIGHT/2;
			coreBlocRect.offset(coreBlocOffsetX,coreBlocOffsetY);
			
			/**
			 * DRAW POI
			 */
			
			float points[] = {
					pointerOffsetX,pointerOffsetY,
				    pointerOffsetX+POINTER_WIDTH/2,pointerOffsetY+POINTER_HEIGHT,
					pointerOffsetX+POINTER_WIDTH,pointerOffsetY,
					blocOffsetX+BLOC_WIDTH,blocOffsetY,
					blocOffsetX,blocOffsetY
			};
			
			Path path = new Path();
			path.moveTo(blocOffsetX,blocOffsetY);
			for (int i=0; i < points.length ;i+=2)
				path.lineTo(points[i], points[i+1]);
			path.lineTo(pointerOffsetX,pointerOffsetY);
			
			canvas.drawPath(path, getBorderPaint());			
			canvas.drawPath(path, getInnerPaint());
			
			/**
			 * DRAW CORE
			 */
			
			float corepoints[] = {
					corePointerOffsetX,corePointerOffsetY,
					corePointerOffsetX+CORE_POINTER_WIDTH/2,corePointerOffsetY+CORE_POINTER_HEIGHT,
					corePointerOffsetX+CORE_POINTER_WIDTH,corePointerOffsetY,
					coreBlocOffsetX+CORE_BLOC_WIDTH,coreBlocOffsetY,
					coreBlocOffsetX,coreBlocOffsetY,
			};

			Path corepath = new Path();
			corepath.moveTo(coreBlocOffsetX,coreBlocOffsetY);
			for (int i=0; i < corepoints.length ;i+=2)
				corepath.lineTo(corepoints[i], corepoints[i+1]);
			corepath.lineTo(corePointerOffsetX,corePointerOffsetY);
			
			canvas.drawPath(corepath, getBorderPaint());			
			canvas.drawPath(corepath, getCorePaint());
		}
	}
    /*****************************************************************************
     * 
     * poi color definitions
     * 
     *****************************************************************************/
	
	private Paint getInnerPaint() {
		if ( innerPaint == null) {
			innerPaint = new Paint();
			innerPaint.setARGB(255, 231, 235, 231); //gray
			innerPaint.setAntiAlias(true);
		}
		return innerPaint;
	}

	private Paint getCorePaint() {
		if ( corePaint == null) {
			corePaint = new Paint();
			corePaint.setARGB(255, 231, 15, 19); //red
			corePaint.setAntiAlias(true);
		}
		return corePaint;
	}
	
	private Paint getBorderPaint() {
		if ( borderPaint == null) {
			borderPaint = new Paint();
			borderPaint.setARGB(255, 0, 0, 0);
			borderPaint.setAntiAlias(true);
			borderPaint.setStyle(Style.STROKE);
			borderPaint.setStrokeWidth(2);
		}
		return borderPaint;
	}
	
    /*****************************************************************************
     * 
     * Key controls
     * 
     *****************************************************************************/
	
	@Override
	public abstract boolean onTap(GeoPoint p, MapView mapView);

	public boolean isTapOnElement(GeoPoint p, MapView mapView) {
		Point screenCoords = new Point();
		mapView.getProjection().toPixels(selectedMapLocation, screenCoords);
		RectF hitTestRecr = new RectF();
		hitTestRecr.set(-BLOC_WIDTH/2,-(BLOC_HEIGHT+BLOC_HEIGHT),BLOC_WIDTH/2,0);
		hitTestRecr.offset(screenCoords.x,screenCoords.y);
		mapView.getProjection().toPixels(p, screenCoords);
		if (hitTestRecr.contains(screenCoords.x,screenCoords.y)) {
			return true;
		}
		return false;
	}
}
