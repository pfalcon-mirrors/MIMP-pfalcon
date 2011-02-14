package org.mimp.newimp.interfaces;

import java.util.EventListener;

import android.view.MotionEvent;

public interface OnMultiTouchListener extends EventListener {

    public boolean onTouchEvent(MotionEvent event);
    public boolean onTwoFingerScroll(MotionEvent event);
    public boolean onPinch(MotionEvent event);
    public MotionEvent getPreviousEvent();
}
