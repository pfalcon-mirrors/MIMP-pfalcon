package org.mimp.newimp;

import android.content.Context;
import android.graphics.Point;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class MultiGestureListener extends SimpleOnGestureListener {
    private MapView mView;
    
    /**
     * Gesture listener for the MapView
     * @param context
     *      The application Context
     * @param view
     *      The MapView which will be controlled
     */
    public MultiGestureListener(Context context,MapView view) {
        mView = view;
    }
    
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
            float distanceX, float distanceY) {
        Point point = mView.getController().getCenter();
        /**
         * Verifications to make sure we don't get out of the MapView
         * Tile drawing area
         */
        if ((point.x + distanceX) < -TileFactory.TILE_SIZE/2)
            distanceX = 0;
        if ((point.y + distanceY) < -TileFactory.TILE_SIZE)
            distanceY = 0;
        if ((point.x + distanceX) > (TileFactory.TILE_SIZE << mView.getZoomLevel())+TileFactory.TILE_SIZE/2)
            distanceX = 0;
        if ((point.y + distanceY) > (TileFactory.TILE_SIZE << mView.getZoomLevel())+TileFactory.TILE_SIZE)
            distanceY = 0;
        point.x += distanceX;
        point.y += distanceY;
        mView.getController().setCenter(point);
        mView.invalidate();
        return super.onScroll(e1, e2, distanceX, distanceY);
    }
    
    /**
     * Check if the touch event is to be consumed by a child
     * @param p
     * @return true if the touch event is to be consumed by a child otherwise false
     */
    public boolean isForChild(GeoPoint p) {
        return false;
        /*
        if (mBubbleOverlay != null
                && mBubbleOverlay.isTapOnElement(p, this) == true) {
            return true;
        }
        return false;
        */
    }
}
