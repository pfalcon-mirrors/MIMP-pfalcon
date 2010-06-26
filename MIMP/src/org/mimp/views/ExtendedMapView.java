package org.mimp.views;

import org.mimp.displayables.LineMapOverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.SensorListener;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.maps.MapView;

@SuppressWarnings("deprecation")
public class ExtendedMapView extends MapView implements SensorListener {

    /*****************************************************************************
     * 
     * Members
     * 
     *****************************************************************************/
    
    private long mLastTouchTime = -1;
	private Matrix mMatrix = new Matrix();
	private boolean mPerspective = false;
	private final SmoothCanvas mCanvas = new SmoothCanvas();
	private float mHeading = 0;
	private int lastEvent;

	
    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/
	
	public ExtendedMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ExtendedMapView(Context context, String apiKey) {
		super(context, apiKey);
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
    
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(ev);
    }
    
    /*****************************************************************************
     * 
     * Moves handling
     * 
     *****************************************************************************/
    
    protected void applyMapViewListener() {
        // System.out.println("~~~~~~~~~~~~~~~~ New GestureDetector");
        final GestureDetector gd = new GestureDetector(
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2,float velocityX, float velocityY) {
                        return true;
                    }
                });
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                System.out.println(ev);
                if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                    long thisTime = System.currentTimeMillis();
                    if (thisTime - mLastTouchTime < 250) {
                        getController().zoomInFixing((int) ev.getX(),(int) ev.getY());
                        mLastTouchTime = -1;
                        if (getOverlays().size() > 1)
                            ((LineMapOverlay)getOverlays().get(1)).forceCalc();
                    } 
                    else {
                        mLastTouchTime = thisTime;
                    }
                    lastEvent = MotionEvent.ACTION_DOWN;
                }
                if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                    lastEvent = MotionEvent.ACTION_MOVE;
                }
                if (ev.getAction() == MotionEvent.ACTION_UP) {
                    if (getOverlays().size() > 1 && lastEvent == MotionEvent.ACTION_MOVE)
                        ((LineMapOverlay)getOverlays().get(1)).forceCalc();
                }
                return gd.onTouchEvent(ev);
            }
        });
    }
}