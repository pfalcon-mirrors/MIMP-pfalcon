package org.mimp.newimp;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.mimp.R;
import org.mimp.displayables.BubbleOverlay;
import org.mimp.newimp.interfaces.IMyLocationOverlay;
import org.mimp.views.ExtendedMapView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;

public class MyLocationOverlay extends Overlay implements IMyLocationOverlay, SensorEventListener,
        LocationListener {

    private Context mContext;
    private MapView mView;
    private boolean mLocationEnabled = false;
    private boolean mCompassEnabled = false;
    private Runnable mFirstFix = null;
    private boolean mIsFirstFix = false;
    private Bitmap ARROW = null;
    private Bitmap ARROW_ROTATED = null;
    private LocationManager mLocationManager = null;
    private Location mLocation = null;
    private SensorManager mSensorManager = null;
    private float mAzimuth = -1.0f;
    private Matrix mMatrix = null;
    private Sensor mSensorOrientation = null;
    private Paint mCircleInnerPaint = null;
    private Paint mCircleBorderPaint = null;
    private String mCurrentProvider;
    
    /**
     * MyLocationOverlay Constructor
     * @param context
     * @param view
     */
    public MyLocationOverlay(Context context, MapView view) {
        mContext = context;
        mView = view;
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        ARROW = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.arrow);
        ARROW = Bitmap.createScaledBitmap(ARROW, 32, 32, true);
        ARROW_ROTATED = ARROW;
        
        mCircleInnerPaint = new Paint();
        mCircleInnerPaint.setColor(0xff6666ff);
        mCircleInnerPaint.setStyle(Style.STROKE);
        mCircleInnerPaint.setAntiAlias(true);
        mCircleInnerPaint.setStrokeWidth(1.5f);

        mCircleBorderPaint = new Paint();
        mCircleBorderPaint.setColor(0x1f6666ff);
        mCircleBorderPaint.setStyle(Style.FILL);
        mCircleBorderPaint.setAntiAlias(true);
    }
    
    @Override
    public boolean enableMyLocation() {
        mLocationEnabled = true;
        List<String> mProviders = mLocationManager.getProviders(true);
        if (mProviders.size() > 0) {
            mCurrentProvider = LocationManager.NETWORK_PROVIDER;
            for (String string : mProviders) {
                if (string.equals(LocationManager.GPS_PROVIDER))
                    mCurrentProvider = LocationManager.GPS_PROVIDER;
            }
            mLocation = mLocationManager.getLastKnownLocation(mCurrentProvider);
            mLocationManager.requestLocationUpdates(mCurrentProvider,1000, 1, this);
        }
        return false;
    }

    @Override
    public void disableMyLocation() {
        mLocationEnabled = false;
        mLocationManager.removeUpdates(this);
        mLocation = null;
    }

    @Override
    public boolean isMyLocationEnabled() {
        return mLocationEnabled;
    }

    @Override
    public boolean enableCompass() {
        mCompassEnabled = true;
        mSensorOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(this, mSensorOrientation, SensorManager.SENSOR_DELAY_NORMAL);
        return true;
    }

    @Override
    public void disableCompass() {
        mCompassEnabled = false;
        mSensorManager.unregisterListener(this);
        mAzimuth = -1.0f;
    }

    @Override
    public boolean isCompassEnabled() {
        return mCompassEnabled;
    }

    @Override
    public boolean runOnFirstFix(Runnable runnable) {
        if (runnable != null) {
            mFirstFix = runnable;
            return true;
        }
        return false;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    @Override
    public Location getLastFix() {
        return mLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (mIsFirstFix) {
            mFirstFix.run();
            mIsFirstFix = false;
        }
        mLocation = location;
    }

    @Override
    public void onProviderDisabled(String provider) {
        List<String> mProviders = mLocationManager.getProviders(true);
        if (mProviders.size() > 0) {
            mCurrentProvider = LocationManager.NETWORK_PROVIDER;
            for (String string : mProviders) {
                if (string.equals(LocationManager.GPS_PROVIDER))
                    mCurrentProvider = LocationManager.GPS_PROVIDER;
            }
            mLocation = mLocationManager.getLastKnownLocation(mCurrentProvider);
            mLocationManager.requestLocationUpdates(mCurrentProvider,1000, 1, this);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        List<String> mProviders = mLocationManager.getProviders(true);
        if (mProviders.size() > 0) {
            mCurrentProvider = LocationManager.NETWORK_PROVIDER;
            for (String string : mProviders) {
                if (string.equals(LocationManager.GPS_PROVIDER))
                    mCurrentProvider = LocationManager.GPS_PROVIDER;
            }
            mLocation = mLocationManager.getLastKnownLocation(mCurrentProvider);
            mLocationManager.requestLocationUpdates(mCurrentProvider,1000, 1, this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            if (event.values != null) {
                mAzimuth  = event.values[0];
                mView.invalidate();
            }
        }
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        if (mLocation == null || mLocationEnabled == false || shadow == true)
            return;
        double[] ll = new double[] {mLocation.getLatitude(), mLocation.getLongitude()};
        int[] xy = TileFactory.LatLngToPixel(ll, mapView.getZoomLevel());
        Point center = mView.getController().getCenter();
        xy[0] = xy[0] - center.x + mView.getWidth()/2;
        xy[1] = xy[1] - center.y + mView.getHeight()/2;
        
        float[] result = new float[1];
        Location.distanceBetween(ll[0], ll[1], ll[0], ll[1] + 1, result);
        float longitudeLineDistance = result[0];
        float accuracy = getLastFix().getAccuracy();
        
        int[] pa = TileFactory.LatLngToPixel(new double[] { ll[0] / 1E6,ll[1] / 1E6 },mView.getZoomLevel());
        int[] pb = TileFactory.LatLngToPixel(new double[] {ll[0] / 1E6, (ll[1] / 1E6) - (accuracy/longitudeLineDistance)},mView.getZoomLevel());
        
        Point mScreenPoint = new Point(pa[0], pa[1]);
        Point distPoint = new Point(pb[0],pb[1]);
                
        int radius = mScreenPoint.x - distPoint.x;
        
        canvas.drawCircle(xy[0], xy[1], radius, mCircleInnerPaint);
        canvas.drawCircle(xy[0], xy[1], radius, mCircleBorderPaint);
        
        float bearing = 0.0f;
        if (mAzimuth > 0.0f) {
            bearing = mAzimuth;
        }
        mMatrix = new Matrix();
        mMatrix.preRotate(bearing);

        ARROW_ROTATED = Bitmap.createBitmap(ARROW, 0, 0, ARROW.getWidth(), ARROW.getHeight(), mMatrix, true);
        canvas.drawBitmap(ARROW_ROTATED, xy[0] - ARROW_ROTATED.getWidth()/2, xy[1]-ARROW_ROTATED.getWidth()/2, null);
    }

    @Override
    protected boolean draw(Canvas canvas, MapView mapView, boolean shadow,
            long when) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean onTap(GeoPoint p, MapView mapView) {
        Geocoder geoCoder = new Geocoder(mContext, Locale.getDefault());
        try {
            GeoPoint geoPoint = new GeoPoint(mLocation.getLatitude(), mLocation.getLongitude());
            List<Address> addresses = geoCoder.getFromLocation(
                    geoPoint.getLatitudeE6() / 1E6,
                    geoPoint.getLongitudeE6() / 1E6, 1);
            Vector<String> address = new Vector<String>();
            if (addresses.size() > 0) {
                for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                    address.add(addresses.get(0).getAddressLine(i).trim());
            }
            ((ExtendedMapView)mView).setBubbleOverlay(new BubbleOverlay(address, geoPoint, mContext));
            mView.invalidate();
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean onTouchEvent(MotionEvent e, MapView mapView) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Returns the current location in the form of a Geopoint
     * @return Geopoint : The current location
     */
    public GeoPoint getMyLocation() {
        return new GeoPoint(mLocation.getLatitude(), mLocation.getLongitude(), mLocation.getAltitude());
    }

    @Override
    public boolean isTapOnElement(GeoPoint p, MapView mapView) {
        Point screenCoords = new Point();
        if (mLocation == null)
            return false;
        GeoPoint geoPoint = new GeoPoint(mLocation.getLatitude(), mLocation.getLongitude());
        mapView.getProjection().toPixels(geoPoint, screenCoords);
        RectF hitTestRecr = new RectF();
        hitTestRecr.set(-24, -24, 24, 24);
        hitTestRecr.offset(screenCoords.x, screenCoords.y);
        mapView.getProjection().toPixels(p, screenCoords);
        if (hitTestRecr.contains(screenCoords.x, screenCoords.y)) {
            return true;
        }
        return false;
    }
}
