package org.mimp.newimp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ZoomControls;

public class MapZoomControls extends ZoomControls {

    private Long mTimeOutMillis = System.currentTimeMillis();
    private boolean mIsRunning = false;
    private TimeOutThread mTimeOutThread;
    private MapView mMapView;
    
    public MapZoomControls(Context context, MapView view) {
        super(context);
        this.mMapView = view;
        
        setOnZoomInClickListener(new OnClickListener() {
            public void onClick(View v) {
                mMapView.getController().zoomIn();
                restartTimeOutMillis();
            }
        });
        setOnZoomOutClickListener(new OnClickListener() {
            public void onClick(View v) {
                mMapView.getController().zoomOut();
                restartTimeOutMillis();
            }
        });
        
        this.setHapticFeedbackEnabled(true);
        this.setSoundEffectsEnabled(true);
    }
    
    @Override
    public void show() {
        super.show();
        mTimeOutMillis = System.currentTimeMillis();
        if (!mIsRunning) {
            mIsRunning = true;
            mTimeOutThread = new TimeOutThread();
            mTimeOutThread.start();
        }
    }
    
    public void restartTimeOutMillis() {
        mTimeOutMillis = System.currentTimeMillis();
    }
    
    private class TimeOutThread extends Thread{
        public void run() {
            while ((System.currentTimeMillis() - mTimeOutMillis) < 5000) {
                try {
                    Thread.sleep(250);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mHandler.sendEmptyMessage(0);
            mIsRunning = false;
        }
    }
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            hide();
        }
    };
}
