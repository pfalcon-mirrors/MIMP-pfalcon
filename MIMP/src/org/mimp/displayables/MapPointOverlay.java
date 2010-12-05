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

	private int POI_POINTER_WIDTH = 20;
	private int POI_POINTER_HEIGHT = 20;
	private int POI_BLOC_WIDTH = 20;
	private int POI_BLOC_HEIGHT = 10;
	
	private int CORE_POI_POINTER_WIDTH = 10;
	private int CORE_POI_POINTER_HEIGHT = 10;
	private int CORE_POI_BLOC_WIDTH = 10;
	private int CORE_POI_BLOC_HEIGHT = 10;
	
	private GeoPoint selectedMapLocation;
	private Paint innerPaint;
	private Paint corePaint;
	private Paint borderPaint;
	private Context mContext;
	
	public enum SIZE {
		BIG,
		MEDIUM,
		SMALL
	}
	
	public MapPointOverlay(GeoPoint location, Context context) {
		selectedMapLocation = location;
		mContext = context;
	}
	
	public void setHeight(SIZE size) {
		if (size == SIZE.BIG) {
			POI_BLOC_HEIGHT = CORE_POI_BLOC_HEIGHT = 25;
		}
		else if (size == SIZE.MEDIUM) {
			POI_BLOC_HEIGHT = CORE_POI_BLOC_HEIGHT = 15;
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
			RectF pointerRect = new RectF(0,0,POI_POINTER_WIDTH,POI_POINTER_HEIGHT);
			int pointerOffsetX = selDestinationOffset.x-POI_POINTER_WIDTH/2;
			int pointerOffsetY = selDestinationOffset.y-POI_POINTER_HEIGHT;			
			pointerRect.offset(pointerOffsetX,pointerOffsetY);
			
			//  Setup the poi bloc coords
			RectF blocRect = new RectF(0,0,POI_BLOC_WIDTH,POI_BLOC_HEIGHT);
			int blocOffsetX = selDestinationOffset.x-POI_BLOC_WIDTH/2;
			int blocOffsetY = selDestinationOffset.y-POI_BLOC_HEIGHT-POI_POINTER_HEIGHT;
			blocRect.offset(blocOffsetX,blocOffsetY);
			
			// Setup the core
			RectF corePointerRect = new RectF(0,0,CORE_POI_POINTER_WIDTH,CORE_POI_POINTER_HEIGHT);
			int corePointerOffsetX = selDestinationOffset.x-CORE_POI_POINTER_WIDTH/2;
			int corePointerOffsetY = selDestinationOffset.y-CORE_POI_POINTER_HEIGHT-POI_POINTER_HEIGHT/2;
			corePointerRect.offset(corePointerOffsetX,corePointerOffsetY);
			
			//  Setup the poi bloc coords
			RectF coreBlocRect = new RectF(0,0,CORE_POI_BLOC_WIDTH,CORE_POI_BLOC_HEIGHT);
			int coreBlocOffsetX = selDestinationOffset.x-CORE_POI_BLOC_WIDTH/2;
			int coreBlocOffsetY = selDestinationOffset.y-CORE_POI_BLOC_HEIGHT-CORE_POI_POINTER_HEIGHT-POI_POINTER_HEIGHT/2;
			coreBlocRect.offset(coreBlocOffsetX,coreBlocOffsetY);
			
			/**
			 * DRAW POI
			 */
			
			// Draw Pointer Border
			Path path = new Path();
			path.moveTo(pointerOffsetX,pointerOffsetY);
			float border[] = {pointerOffsetX+10,pointerOffsetY+20,
					 pointerOffsetX+20,pointerOffsetY,
					 pointerOffsetX,pointerOffsetY};
			
			for (int i=0; i < border.length ;i+=2)
				path.lineTo(border[i], border[i+1]);
			canvas.drawPath(path, getBorderPaint());
			
			//  Draw inner info window
			canvas.drawRect(blocRect, getInnerPaint());
			
			//  Draw border for info window
			canvas.drawRect(blocRect, getBorderPaint());

			// Draw Pointer inner
			path = new Path();
			pointerOffsetY = pointerOffsetY - 1;
			path.moveTo(pointerOffsetX,pointerOffsetY);
			float inner[] = {pointerOffsetX+10,pointerOffsetY+20,
					 pointerOffsetX+20,pointerOffsetY,
					 pointerOffsetX,pointerOffsetY};
			
			for (int i=0; i < border.length ;i+=2)
				path.lineTo(inner[i], inner[i+1]);
			canvas.drawPath(path, getInnerPaint());
			
			/**
			 * DRAW CORE
			 */
			
			// Draw Pointer Border
			Path corepath = new Path();
			corepath.moveTo(corePointerOffsetX,corePointerOffsetY);
			float coreborder[] = {corePointerOffsetX+5,corePointerOffsetY+10,
					corePointerOffsetX+10,corePointerOffsetY,
					corePointerOffsetX,corePointerOffsetY};
			
			for (int i=0; i < coreborder.length ;i+=2)
				corepath.lineTo(coreborder[i], coreborder[i+1]);
			canvas.drawPath(corepath, getBorderPaint());
			
			//  Draw inner info window
			canvas.drawRect(coreBlocRect, getCorePaint());
			
			//  Draw border for info window
			canvas.drawRect(coreBlocRect, getBorderPaint());

			// Draw Pointer inner
			corepath = new Path();
			corePointerOffsetY = corePointerOffsetY - 1;
			corepath.moveTo(corePointerOffsetX,corePointerOffsetY);
			float coreinner[] = {corePointerOffsetX+5,corePointerOffsetY+10,
					corePointerOffsetX+10,corePointerOffsetY,
					corePointerOffsetX,corePointerOffsetY};
			
			for (int i=0; i < coreborder.length ;i+=2)
				corepath.lineTo(coreinner[i], coreinner[i+1]);
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
		hitTestRecr.set(-POI_BLOC_WIDTH/2,-(POI_BLOC_HEIGHT+POI_BLOC_HEIGHT),POI_BLOC_WIDTH/2,0);
		hitTestRecr.offset(screenCoords.x,screenCoords.y);
		mapView.getProjection().toPixels(p, screenCoords);
		if (hitTestRecr.contains(screenCoords.x,screenCoords.y)) {
			return true;
		}
		return false;
	}
}
