package org.mimp.newimp;

import org.mimp.newimp.interfaces.OnMultiTouchListener;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;

public class MultiTouchListener implements OnMultiTouchListener {
    
    private enum MODES {
        NONE,
        PINCH,
        SCROLL,
        ROTATE
    }
    
    private Context mContext = null;
    private MapView mView = null;
    @SuppressWarnings("unused")
    private int mViewZoom;
    private int nZooms = 0;
    private MotionEvent mPreviousEvent = null;
    private MODES MODE = MODES.NONE;
    private float scale;

    public MultiTouchListener(Context context, MapView view) {
        mContext = context;
        mView = view;
        scale = mContext.getResources().getDisplayMetrics().density;
    }

    @Override
    public final boolean onTouchEvent(MotionEvent event) {
        if (mPreviousEvent != null && event.getAction() == MotionEvent.ACTION_DOWN) {
            MODE = MODES.NONE;
        }
        else if (mPreviousEvent != null && event.getAction() == MotionEvent.ACTION_MOVE) {
            /**
             * Calculate distance between fingers across the two events
             */
            Point distPrevious = new Point();
            Point distCurrent = new Point();
            distPrevious.x = Math.abs((int) (mPreviousEvent.getX(0) - mPreviousEvent.getX(1)));
            distPrevious.y = Math.abs((int) (mPreviousEvent.getY(0) - mPreviousEvent.getY(1)));
            distCurrent.x = Math.abs((int) (event.getX(0) - event.getX(1)));
            distCurrent.y = Math.abs((int) (event.getY(0) - event.getY(1)));
            
            /**
             * If no mode is set which means no particular movement we try to
             * determinate if one is occurring
             */
            if (MODE == MODES.NONE) {
                
                /**
                 * Testing if scroll event
                 */
                
                /**
                 * Verifying distance between X points between moves with a 
                 * margin of 3 pixels
                 */
                if (Math.abs(distPrevious.x - distCurrent.x) < 4) {
                    if (Math.abs(mPreviousEvent.getY(0) - event.getY(0)) > 1 && 
                            Math.abs(mPreviousEvent.getY(1) - event.getY(1)) > 1) {
                        /**
                         * Verifying if Y directions match, it is then a Scroll
                         */
                        if ((mPreviousEvent.getY(0) - event.getY(0)) > 0 &&
                                (mPreviousEvent.getY(1) - event.getY(1)) > 0 ||
                                (mPreviousEvent.getY(0) - event.getY(0)) < 0 &&
                                (mPreviousEvent.getY(1) - event.getY(1)) < 0) {
                            MODE = MODES.SCROLL;
                            onTwoFingerScroll(event);
                            mPreviousEvent = MotionEvent.obtain(event);
                            return true;
                        }
                    }
                }

                /**
                 * Testing if rotate event
                 *//*
                if (Math.abs(distByHypothenuse(distPrevious) - 
                        distByHypothenuse(distCurrent)) < 8) {
                    mPreviousEvent = MotionEvent.obtain(event);
                    //TODO Delete or DO it
                }*/
                
                /**
                 * Testing if pinch event
                 */
                if (Math.abs(distByHypothenuse(distPrevious) - 
                        distByHypothenuse(distCurrent)) > 10) {
                    mViewZoom = mView.getZoomLevel();
                    MODE = MODES.PINCH;
                    onPinch(event);
                    /*
                     *  Here we keep the original event as it is the base used
                     *  for further comparisons
                     */
                    // mPreviousEvent = MotionEvent.obtain(event);
                    return true;
                }
                
                
                
            }
            else if (MODE == MODES.SCROLL) {
                onTwoFingerScroll(event);
                mPreviousEvent = MotionEvent.obtain(event);
            }
            else if (MODE == MODES.PINCH) {
                onPinch(event);
            }
        }
        else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP) {
            if (nZooms > 0) {
                mView.getController().zoomOutFixing(
                        (int) (event.getX(0) - (event.getX(0) - event.getX(1))/2),
                        (int) (event.getY(0) - (event.getY(0) - event.getY(1))/2));
            }
            else if (nZooms < 0) {
                mView.getController().zoomInFixing(
                        (int) (event.getX(0) - (event.getX(0) - event.getX(1))/2),
                        (int) (event.getY(0) - (event.getY(0) - event.getY(1))/2));
            }
            MODE = MODES.NONE;
            mViewZoom = mView.getZoomLevel();
            mPreviousEvent = null;
        }
        else if (mPreviousEvent == null) {
            mPreviousEvent = MotionEvent.obtain(event);
        }
        return true;
    }
    
    private int distByHypothenuse(Point dist) {
        return (int) Math.sqrt(dist.x * dist.x + dist.y * dist.y);
    }

    public boolean onPinch(MotionEvent event) {
        Point distPrevious = new Point();
        Point distCurrent = new Point();
        distPrevious.x = Math.abs((int) (mPreviousEvent.getX(0) - mPreviousEvent.getX(1)));
        distPrevious.y = Math.abs((int) (mPreviousEvent.getY(0) - mPreviousEvent.getY(1)));
        distCurrent.x = Math.abs((int) (event.getX(0) - event.getX(1)));
        distCurrent.y = Math.abs((int) (event.getY(0) - event.getY(1)));
        nZooms = (distByHypothenuse(distPrevious) - distByHypothenuse(distCurrent));
        nZooms = (int) (scale * nZooms + 0.5f);
        return false;
    }

    @Override
    public boolean onTwoFingerScroll(MotionEvent event) {
        // TODO Check if scroll Up or Down
        return false;
    }

    @Override
    public MotionEvent getPreviousEvent() {
        return mPreviousEvent;
    }
}
