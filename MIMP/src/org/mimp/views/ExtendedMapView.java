package org.mimp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.google.android.maps.MapView;

public class ExtendedMapView extends MapView {

	private long lastTouchTime = -1;
	private Matrix mMatrix = new Matrix();
	private boolean perspective = false;
	private final SmoothCanvas mCanvas = new SmoothCanvas();
	@SuppressWarnings("unused")
	private static final float SQ2 = 1.414213562373095f;
	@SuppressWarnings("unused")
	private float mHeading = 0;

	public ExtendedMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ExtendedMapView(Context context, String apiKey) {
		super(context, apiKey);
	}
	
	@Override
	protected void onAnimationStart() {
		Log.e(this.toString(), "Animation Started");
		super.onAnimationStart();
	}
	
	@Override
	protected void onAnimationEnd() {
		Log.e(this.toString(), "Animation Finished");
		super.onAnimationEnd();
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			long thisTime = System.currentTimeMillis();
			if (thisTime - lastTouchTime < 250) {
				this.getController().zoomInFixing((int) ev.getX(),(int) ev.getY());
				lastTouchTime = -1;
			} 
			else {
				lastTouchTime = thisTime;
			}
		}
		return super.onInterceptTouchEvent(ev);
	}
	
	public void setPerspective(boolean perspective) {
		this.perspective  = perspective;
		this.invalidate();
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (perspective) {
	        float src[] = new float[] { 0, 0, getWidth(), 0, getWidth(), getHeight(), 0,getHeight() };
	        float dst[] = new float[] { 0, 0, getWidth(), 0, getWidth()*2, getHeight(), -getWidth(),getHeight() };
			mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
	        canvas.save(Canvas.MATRIX_SAVE_FLAG);
	        canvas.concat(mMatrix);
            //canvas.rotate(-mHeading, getWidth() * 0.5f, getHeight() * 0.5f);
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
	
    public void onSensorChanged(int sensor, float[] values) {
    	if (perspective) {
	        synchronized (this) {
	            mHeading = values[0];
	            invalidate();
	        }
    	}
    }
}