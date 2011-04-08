package org.mimp.newimp;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.mimp.displayables.BubbleOverlay;
import org.mimp.views.ExtendedMapView;

import android.content.Context;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;


public class GestureListener extends SimpleOnGestureListener {

    private ExtendedMapView mView;
    private Context mContext;

    /**
     * Gesture listener for the MapView
     * 
     * @param context
     *            The application Context
     * @param view
     *            The MapView which will be controlled
     */
    public GestureListener(Context context, ExtendedMapView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mView.invalidate();
        return super.onDown(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        GeoPoint p = mView.getProjection().fromPixels((int) e.getX(), (int) e.getY());
        /**
         * if onTap is handled by an overlay
         */
        if (isForChild(p) == true) {
            return false;
        }
        mView.removeBubble();
        mView.invalidate();
        return super.onSingleTapConfirmed(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        GeoPoint geoPoint = mView.getProjection().fromPixels((int) e.getX(),
                (int) e.getY());
        if (TouchListener.lastEvent != null && TouchListener.lastEvent.getPointerCount() > 1)
            return;
        Geocoder geoCoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(
                    geoPoint.getLatitudeE6() / 1E6,
                    geoPoint.getLongitudeE6() / 1E6, 1);
            Vector<String> address = new Vector<String>();
            if (addresses.size() > 0) {
                for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                    address.add(addresses.get(0).getAddressLine(i).trim());
            }
            mView.setBubbleOverlay(new BubbleOverlay(address, geoPoint, mContext));
            mView.invalidate();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        mView.invalidate();
        super.onLongPress(e);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        Point point = mView.getController().getCenter();
        /**
         * Verifications to make sure we don't get out of the MapView Tile
         * drawing area
         */
        if ((point.x + distanceX) < -TileFactory.TILE_SIZE / 2)
            distanceX = 0;
        if ((point.y + distanceY) < -TileFactory.TILE_SIZE)
            distanceY = 0;
        if ((point.x + distanceX) > (TileFactory.TILE_SIZE << mView.getZoomLevel())
                + TileFactory.TILE_SIZE / 2)
            distanceX = 0;
        if ((point.y + distanceY) > (TileFactory.TILE_SIZE << mView.getZoomLevel())
                + TileFactory.TILE_SIZE)
            distanceY = 0;
        point.x += distanceX;
        point.y += distanceY;
        mView.getController().setCenter(point);
        mView.invalidate();
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        mView.getController().zoomInFixing((int) e.getX(), (int) e.getY());
        mView.invalidate();
        return super.onDoubleTap(e);
    }

    /**
     * Check if the touch event is to be consumed by a child
     * 
     * @param p
     * @return true if the touch event is to be consumed by a child otherwise
     *         false
     */
    public boolean isForChild(GeoPoint p) {
        if (mView.getLocationOverlay() != null) {
            if (mView.getLocationOverlay().isTapOnElement(p, mView) == true) {
                mView.getLocationOverlay().onTap(p, mView);
                return true;
            }
        }
        if (mView.getBubbleOverlay() != null) { 
            if (mView.getBubbleOverlay().isTapOnElement(p, mView) == true) {
                mView.getBubbleOverlay().onTap(p, mView);
                return true;
            }
        }
        for (Overlay overlay : mView.getOverlayGroup().getOverlays()) {
            if (overlay.isTapOnElement(p, mView) == true) {
                overlay.onTap(p, mView);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        return super.onFling(e1, e2, velocityX, velocityY);
    }
}