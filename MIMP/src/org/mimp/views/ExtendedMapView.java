package org.mimp.views;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.mimp.displayables.BubbleOverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.SensorListener;
import android.location.Address;
import android.location.Geocoder;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

@SuppressWarnings("deprecation")
public class ExtendedMapView extends MapView implements SensorListener {

    /*****************************************************************************
     * 
     * Members
     * 
     *****************************************************************************/
    
	private Context mContext;
	private Matrix mMatrix = new Matrix();
	private boolean mPerspective = false;
	private final SmoothCanvas mCanvas = new SmoothCanvas();
	private float mHeading = 0;
	BubbleOverlay bubbleOverlay;
	
    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/
	
	public ExtendedMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}
	
	public ExtendedMapView(Context context, String apiKey) {
		super(context, apiKey);
		mContext = context;
	}
	
	@Override
	protected void onFinishInflate() {
	    super.onFinishInflate();
	    applyMapViewListener();
	}
	
    /*****************************************************************************
     * 
     * Drawing methods
     * 
     *****************************************************************************/
	
	public void setPerspective(boolean perspective) {
		this.mPerspective  = perspective;
		this.invalidate();
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (mPerspective) {
	        float src[] = new float[] { 0, 0, getWidth(), 0, getWidth(), getHeight(), 0,getHeight() };
	        float dst[] = new float[] { 0, 0, getWidth(), 0, getWidth()*2, getHeight(), -getWidth(),getHeight() };
			mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
	        canvas.save(Canvas.MATRIX_SAVE_FLAG);
	        canvas.concat(mMatrix);
            canvas.rotate(-mHeading, getWidth() * 0.5f, getHeight() * 0.5f);
	        mCanvas.delegate = canvas;
			super.draw(mCanvas);
	        canvas.restore();
		}
		else {
			canvas.save(Canvas.MATRIX_SAVE_FLAG);
			mCanvas.delegate = canvas;
			super.draw(canvas);
			canvas.restore();
		}
	}
	
    /*****************************************************************************
     * 
     * Sensor handling
     * 
     *****************************************************************************/
	
    public void onSensorChanged(int sensor, float[] values) {
    	if (mPerspective) {
	        synchronized (this) {
	            mHeading = values[0];
	            invalidate();
	        }
    	}
    }

    @Override
    public void onAccuracyChanged(int paramInt1, int paramInt2) {

    }
    
    /*****************************************************************************
     * 
     * Event handling
     * 
     *****************************************************************************/
    
    protected void applyMapViewListener() {
        // System.out.println("~~~~~~~~~~~~~~~~ New GestureDetector");
        final GestureDetector gd = new GestureDetector(
            new GestureDetector.SimpleOnGestureListener() {
            	
            	@Override
            	public boolean onSingleTapConfirmed(MotionEvent e) {
            		GeoPoint p = getProjection().fromPixels((int) e.getX(),(int) e.getY());
            		if (isForChild(p) == true) {
            			return false;
            		}
            		getOverlays().remove(bubbleOverlay);
            		invalidate();
            		return true;
            	}
            	
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2,float velocityX, float velocityY) {
                    return true;
                }
                
                @Override
                public void onLongPress(MotionEvent e) {
                	super.onLongPress(e);
                	
                    GeoPoint p = getProjection().fromPixels((int) e.getX(),(int) e.getY());
                    Geocoder geoCoder = new Geocoder( mContext, Locale.getDefault());
                    try {
                        List<Address> addresses = geoCoder.getFromLocation(p.getLatitudeE6()  / 1E6, 
                            p.getLongitudeE6() / 1E6, 1);
                        Vector<String> add = new Vector<String>();
                        if (addresses.size() > 0)  {
                            for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
                                 i++)
                               add.add(addresses.get(0).getAddressLine(i).trim());
                        }
                        if (bubbleOverlay != null) {
                        	getOverlays().remove(bubbleOverlay);
                        	invalidate();
                        }
                    	bubbleOverlay = new BubbleOverlay(add, "Test Description", p,mContext);
                    	getOverlays().add(bubbleOverlay);
                    	invalidate();
                    }
                    catch (IOException ex) {                
                        ex.printStackTrace();
                    }
                }
                
                @Override
                public boolean onDoubleTapEvent(MotionEvent e) {
                	getController().zoomInFixing((int) e.getX(),(int) e.getY());
                	return super.onDoubleTapEvent(e);
                }
            }
        );
        this.setOnTouchListener(
    		new OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent ev) {
	                return gd.onTouchEvent(ev);
	            }
    		}
		);
    }
    
    public boolean isForChild(GeoPoint p) {
		if (bubbleOverlay != null && bubbleOverlay.isTapOnElement(p, this) == true) {
			return true;
		}
		return false;
    }
    
    public void removeBubble() {
    	getOverlays().remove(bubbleOverlay);
    	invalidate();
    }
}