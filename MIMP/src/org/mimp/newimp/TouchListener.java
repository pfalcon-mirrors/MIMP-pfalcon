package org.mimp.newimp;

import org.mimp.views.ExtendedMapView;

import android.content.Context;
import android.os.Debug;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchListener implements OnTouchListener {

    GestureListener mGestureListener = null;
    GestureDetector mGestureDetector = null;

    MultiTouchListener mMultiTouchListener = null;

    
    Context mContext = null;
    MapView mView;
    
    public TouchListener(Context context, MapView view) {
        mContext = context;
        mView = view;
        mGestureListener = new GestureListener(mContext, (ExtendedMapView) mView);
        mGestureDetector = new GestureDetector(mGestureListener);
        mMultiTouchListener = new MultiTouchListener(mContext, mView);
    }
    
    public GestureListener getGestureListener() {
        return mGestureListener;
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(mView.getZoomControls() != null && !mView.getZoomControls().isShown()) {
            mView.getZoomControls().show();
        }
        mView.getZoomControls().restartTimeOutMillis();
        if (Debug.isDebuggerConnected())
            dumpEvent(event);
        if (event.getPointerCount() > 1) {
            return mMultiTouchListener.onTouchEvent(event);
        }
        else {
            return mGestureDetector.onTouchEvent(event);
        }
    }
    
    /** Show an event in the LogCat view, for debugging */
    private void dumpEvent(MotionEvent event) {
       String names[] = { "DOWN" , "UP" , "MOVE" , "CANCEL" , "OUTSIDE" ,
          "POINTER_DOWN" , "POINTER_UP" , "7?" , "8?" , "9?" };
       StringBuilder sb = new StringBuilder();
       int action = event.getAction();
       int actionCode = action & MotionEvent.ACTION_MASK;
       sb.append("event ACTION_" ).append(names[actionCode]);
       if (actionCode == MotionEvent.ACTION_POINTER_DOWN
             || actionCode == MotionEvent.ACTION_POINTER_UP) {
          sb.append("(pid " ).append(
          action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
          sb.append(")" );
       }
       sb.append("[" );
       for (int i = 0; i < event.getPointerCount(); i++) {
          sb.append("#" ).append(i);
          sb.append("(pid " ).append(event.getPointerId(i));
          sb.append(")=" ).append((int) event.getX(i));
          sb.append("," ).append((int) event.getY(i));
          if (i + 1 < event.getPointerCount())
             sb.append(";" );
       }
       sb.append("]" );
       System.out.println(sb.toString());
    }
}
