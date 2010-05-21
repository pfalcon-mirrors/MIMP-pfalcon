package org.mimp.displayables;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Bitmap.Config;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class LineMapOverlay extends Overlay {

	private Vector<GeoPoint> geoPoints;
	private Point screenPointa = new Point();
	private Point screenPointb = new Point();
	private Paint pathPaint = new Paint();
	private Bitmap renderBuffer;
	private Canvas renderCanvas;
	private Point screenPoint = new Point();
	private Projection projection;
	private float X = 0;
	private float Y = 0;
	private boolean first = true;
	private MapView mapView;
	private Canvas canvas;
	private int oldZoom;
	private int height;
	private int width;
	private Path thePath = null;
	private boolean draw = false;

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
		this.height = height;
		this.width = width;
		this.drawingThread.start();
	}
    
    private Thread drawingThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (draw) {
                    actualDraw();
                }
            }
        }
    });
    
    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    	this.canvas = canvas;
    	this.mapView = mapView;
    	draw = true;
    }
    
    public void draw(Canvas canvas, MapView mapView, boolean shadow, int x, int y) {
        super.draw(canvas, mapView, shadow);
        this.canvas = canvas;
        this.mapView = mapView;
        this.X = x;
        this.Y = y;
        actualDraw();
    }
    
    public void actualDraw() {
    	Matrix matrix = new Matrix();
        projection = mapView.getProjection();
        projection.toPixels(geoPoints.get(0), screenPoint);
        
        initPoints();
        matrix.postTranslate(screenPoint.x-X-width/2, screenPoint.y-Y-height/2);

        int maxX = (int) (screenPoint.x-X);
        if (maxX < 0) maxX *= -1;
        int maxY = (int) (screenPoint.y-Y);
        if (maxY < 0) maxY *= -1;
    	
        if (renderBuffer != null && oldZoom == mapView.getZoomLevel() && maxX+width/4 < width/2 && maxY+height/4 < height/2) {
    		showByBitmap(canvas,matrix,mapView);
        }
    	else {
    		showByCalculation(canvas,matrix,mapView);
    	}
        draw = false;
    }
    
    /**
     * Initialisation on first point screen position
     */
	private void initPoints() {
        if (first) {
            X = screenPoint.x;
            Y = screenPoint.y;
            first = false;
            oldZoom = mapView.getZoomLevel();
        }
	}
	
	/**
	 * @param canvas
	 * @param matrix
	 * @param mapView
	 * If the screen is just refreshing, drawing the Bitmap is faster
	 */
	private void showByBitmap(Canvas canvas, Matrix matrix, MapView mapView) {
        canvas.drawBitmap(renderBuffer, matrix, null);
	}

	/**
	 * Handles the rendering buffers
	 * @param canvas
	 * @param matrix might be used if future to render the path with the bitmap even after a movement
	 * 				   full calculations will only occur after zoom change ( not yet implemented )
	 * @param mapView
	 * If the screen central point has changed a new calculation occurs.
	 */
	private void showByCalculation(Canvas canvas, Matrix matrix, MapView mapView) {
		if (renderBuffer != null) {
            renderBuffer.recycle();
            renderBuffer = null;
            System.gc();
		}
        renderCanvas = null;
        renderBuffer = Bitmap.createBitmap(mapView.getWidth()*2, mapView.getHeight()*2, Config.ARGB_8888);
        renderCanvas = new Canvas(renderBuffer);
        drawPath(renderCanvas, mapView);
        
        screenPoint = new Point();
		projection.toPixels(geoPoints.get(0), screenPoint);
        X = screenPoint.x;
        Y = screenPoint.y;
        oldZoom = mapView.getZoomLevel();
	}
	
	/**
	 * Draws the path
	 * @param canvas
	 * @param mapView
	 */
	private void drawPath(Canvas canvas, MapView mapView) {
		thePath = new Path();
        projection.toPixels(geoPoints.get(0), screenPointa);
		thePath.moveTo(screenPointa.x+width/2,screenPointa.y+height/2);
		for (int i=0; i < geoPoints.size() ;i++) {
	        projection.toPixels(geoPoints.get(i), screenPointb);
        	thePath.lineTo(screenPointb.x+width/2, screenPointb.y+height/2);
        }
		this.pathPaint.setStyle(Paint.Style.STROKE);
		canvas.drawPath(thePath, pathPaint);
	}
}
