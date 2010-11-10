package org.mimp.displayables;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.provider.Settings.System;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class BubbleOverlay extends Overlay {

	private GeoPoint selectedMapLocation;
	private Paint innerPaint;
	private Paint borderPaint;
	private Paint textPaint;
	private String bubbleTitle;
	private String bubbleDescription;
	private Context mContext;
	
	public BubbleOverlay(String title, String description, GeoPoint location, Context context) {
		bubbleTitle = title;
		bubbleDescription = description;
		selectedMapLocation = location;
		mContext = context;
	}
		
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		if (shadow == false) {
			//  First determine the screen coordinates of the selected MapLocation
			Point selDestinationOffset = new Point();
			mapView.getProjection().toPixels(selectedMapLocation, selDestinationOffset);
	    	
			// Setup the info window position pointer
			Matrix matrix = new Matrix();
			int INFO_POINTER_WIDTH = 20;
			int INFO_POINTER_HEIGHT = 20;
			RectF infoPointerRect = new RectF(0,0,INFO_POINTER_WIDTH,INFO_POINTER_HEIGHT);
			int infoPointerOffsetX = selDestinationOffset.x-INFO_POINTER_WIDTH/2;
			int infoPointerOffsetY = selDestinationOffset.y-INFO_POINTER_HEIGHT - 10;			
			infoPointerRect.offset(infoPointerOffsetX,infoPointerOffsetY);
			
			
			//  Setup the info window with the right size & location
			int INFO_WINDOW_WIDTH = 25 * bubbleTitle.length();
			int INFO_WINDOW_HEIGHT = 35;
			RectF infoWindowRect = new RectF(0,0,INFO_WINDOW_WIDTH,INFO_WINDOW_HEIGHT);				
			int infoWindowOffsetX = selDestinationOffset.x-INFO_WINDOW_WIDTH/2;
			int infoWindowOffsetY = selDestinationOffset.y-INFO_WINDOW_HEIGHT - 30;
			infoWindowRect.offset(infoWindowOffsetX,infoWindowOffsetY);
			
			
			// Draw Pointer Border
			Path path = new Path();
			path.moveTo(infoPointerOffsetX,infoPointerOffsetY);
			float border[] = {infoPointerOffsetX+10,infoPointerOffsetY+20,
					 infoPointerOffsetX+20,infoPointerOffsetY,
					 infoPointerOffsetX,infoPointerOffsetY};
			
			for (int i=0; i < border.length ;i+=2)
				path.lineTo(border[i], border[i+1]);
			canvas.drawPath(path, getBorderPaint());
			
			//  Draw inner info window
			canvas.drawRoundRect(infoWindowRect, 5, 5, getInnerPaint());
			
			//  Draw border for info window
			canvas.drawRoundRect(infoWindowRect, 5, 5, getBorderPaint());

			// Draw Pointer inner
			path = new Path();
			infoPointerOffsetY = infoPointerOffsetY - 1;
			path.moveTo(infoPointerOffsetX,infoPointerOffsetY);
			float inner[] = {infoPointerOffsetX+10,infoPointerOffsetY+20,
					 infoPointerOffsetX+20,infoPointerOffsetY,
					 infoPointerOffsetX,infoPointerOffsetY};
			
			for (int i=0; i < border.length ;i+=2)
				path.lineTo(inner[i], inner[i+1]);
			canvas.drawPath(path, getInnerPaint());
			
			//  Draw the MapLocation's name
			int TEXT_OFFSET_X = 10;
			int TEXT_OFFSET_Y = 15;
			
			canvas.drawText(bubbleTitle,infoWindowOffsetX+TEXT_OFFSET_X,infoWindowOffsetY+TEXT_OFFSET_Y,getTextPaint());
		}
		else {
			
		}
	}

	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		super.onTap(p, mapView);
    	TextView mTextView = new TextView(mContext);
    	mTextView.setText(bubbleDescription);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(bubbleTitle).setCancelable(false).setView(mTextView)
    		.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        AlertDialog alert = builder.create();
        alert.show();
		return true;
	}
	
	public Paint getInnerPaint() {
		if ( innerPaint == null) {
			innerPaint = new Paint();
			innerPaint.setARGB(255, 245, 245, 245); //gray
			innerPaint.setAntiAlias(true);
		}
		return innerPaint;
	}

	public Paint getBorderPaint() {
		if ( borderPaint == null) {
			borderPaint = new Paint();
			borderPaint.setARGB(255, 0, 0, 0);
			borderPaint.setAntiAlias(true);
			borderPaint.setStyle(Style.STROKE);
			borderPaint.setStrokeWidth(2);
		}
		return borderPaint;
	}

	public Paint getTextPaint() {
		if ( textPaint == null) {
			textPaint = new Paint();
			textPaint.setARGB(255, 0, 0, 0);
			textPaint.setAntiAlias(true);
		}
		return textPaint;
	}

}
