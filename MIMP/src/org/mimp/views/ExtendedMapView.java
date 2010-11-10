package org.mimp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.SensorListener;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.devoteam.quickaction.QuickActionWindow;
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
	private View self = this;
	
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
            	
            	private View parent;
            	public void setParent(View v) {
            		parent = v;
            	}
            	
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2,float velocityX, float velocityY) {
                    return true;
                }
                
                @Override
                public void onLongPress(MotionEvent e) {
                	super.onLongPress(e);
                	/*
                	GeoPoint gp = getProjection().fromPixels((int)e.getX(), (int)e.getY());
                	BubbleOverlay bubbleOverlay = new BubbleOverlay("test Title", "test Description", gp,mContext);
                	getOverlays().add(bubbleOverlay);
                	*/
    				//array to hold the coordinates of the clicked view
    				int[] xy = new int[2];
    				//fills the array with the computed coordinates
    				getLocationInWindow(xy);
    				//rectangle holding the clicked view area
    				Rect rect = new Rect(xy[0], xy[1], xy[0]+getWidth(), xy[1]+getHeight());
    				
    				//a new QuickActionWindow object
    				final QuickActionWindow qa = new QuickActionWindow(mContext, self, rect);
    				qa.addItem(getResources().getDrawable(android.R.drawable.ic_menu_agenda), "agenda", new OnClickListener() {
    					public void onClick(View v) {
    						Toast.makeText(mContext, "agenda", Toast.LENGTH_SHORT).show();
    						qa.dismiss();
    					}
    				});
    				qa.show();
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
	                System.out.println(ev);
	                return gd.onTouchEvent(ev);
	            }
    		}
		);
    }
}