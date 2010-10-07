package org.mimp.screens;

import java.util.List;
import java.util.Vector;

import org.mapping.google.DrivingDirectionsFactory;
import org.mapping.google.Route;
import org.mapping.google.DrivingDirections.IDirectionsListener;
import org.mapping.google.DrivingDirections.Mode;
import org.mapping.google.impl.DrivingDirectionsGoogleKML;
import org.mapping.google.impl.Locator;
import org.mimp.R;
import org.mimp.displayables.DrawableMapOverlay;
import org.mimp.displayables.LineMapOverlay;
import org.mimp.globals.S;
import org.mimp.views.ExtendedMapView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.MapView.LayoutParams;

public class MapScreen extends MapActivity implements LocationListener, IDirectionsListener {

    private boolean ROUTE = false;

    private ExtendedMapView mMapView;
    private MapController mMapController;
    private LocationManager mLocationManager;
    private MyLocationOverlay mMapLocationOverlay;
    private WindowManager mWindowManager;
    private Display mDisplay;
    private DrivingDirectionsGoogleKML mDirectionsGoogleKML;
    private Locator mLocator;

    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.map);
        mMapView = (ExtendedMapView) findViewById(R.id.mapView);
        mMapController = mMapView.getController();

        LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.zoom);
        View zoomView = mMapView.getZoomControls();

        zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mMapView.displayZoomControls(true);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mMapLocationOverlay = new MyLocationOverlay(getApplicationContext(),
                mMapView);
        mMapLocationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                mMapController.animateTo(mMapLocationOverlay.getMyLocation());
            }
        });
        mMapView.getOverlays().add(mMapLocationOverlay);
        
        mLocator = new Locator(this, mMapView);
        mDirectionsGoogleKML = (DrivingDirectionsGoogleKML) DrivingDirectionsFactory.createDrivingDirections();
        Vector<GeoPoint> geoPoints = new Vector<GeoPoint>();
        Double lat = 50* 1E6;
        Double lng = 5 * 1E6;
        geoPoints.add(new GeoPoint(lat.intValue(), lng.intValue()));
        lng = 6 * 1E6;
        geoPoints.add(new GeoPoint(lat.intValue(), lng.intValue()));
        mDirectionsGoogleKML.driveTo(geoPoints, org.mapping.google.DrivingDirections.Mode.DRIVING, this);
        
        checkMapStyle();
        checkCompass();
        checkListener();
        checkTrack();
        checkPerspective();
        checkFollow();
    }

    @Override
    protected void onDestroy() {
        if (isListening()) {
            disableLocationListener();
        }
        super.onDestroy();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return ROUTE;
    }

    /*****************************************************************************
     * 
     * Settings configuration handling
     * 
     *****************************************************************************/

    private void checkMapStyle() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("mapstyle", true);
        mMapView.setSatellite(mode);
    }

    public void checkCompass() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("compass", false);
        if (mode) {
            enableCompass();
        } else {
            disableCompass();
        }
    }

    public void checkListener() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("listener", false);
        if (mode) {
            enableLocationListener();
        } else {
            disableLocationListener();
        }
    }

    public void checkPerspective() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("perspective", false);
        if (mode) {
            mMapView.setPerspective(true);
        } else {
            mMapView.setPerspective(false);
        }
    }

    private void checkFollow() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("follow", false);
        if (mode) {
            enableFollow();
        } else {
            disableFollow();
        }
    }

    private void checkTrack() {
        // TODO check whenever a track should be displayed from a search
    }
    
    private void changeMapStyle() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        boolean mode = settings.getBoolean("mapstyle", true);
        mMapView.setSatellite(!mode);

        editor.putBoolean("mapstyle", !mode);
        editor.commit();
    }

    private void changePerspective() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        boolean mode = settings.getBoolean("perspective", true);
        mMapView.setPerspective(!mode);

        editor.putBoolean("perspective", !mode);
        editor.commit();
    }

    /**
     * Shows info for a selected point
     */
    private void showInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Info").setMessage(R.string.by).setCancelable(false)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*****************************************************************************
     * 
     * Screens handling // TODO to remove and replace by dispatcher
     * 
     *****************************************************************************/

    /**
     * Shows setings screen
     */
    private void showSettings() {
        startActivity(new Intent(MapScreen.this, SettingsScreen.class));
    }

    /*****************************************************************************
     * 
     * Key controls and menu handling
     * 
     *****************************************************************************/

    @Override
    public boolean onSearchRequested() {
        mLocator.proposeSearch();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MapController mc = mMapView.getController();
        switch (keyCode) {
        case KeyEvent.KEYCODE_PLUS:
            mc.zoomIn();
            return true;
        case KeyEvent.KEYCODE_MINUS:
            mc.zoomOut();
            return true;
        case KeyEvent.KEYCODE_SEARCH:
            onSearchRequested();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Creates the menu items when physical Menu button is pressed
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, S.POS, 0, R.string.position).setIcon(
                android.R.drawable.ic_menu_mylocation);
        menu.add(0, S.COMPASS, 0, R.string.compass).setIcon(
                android.R.drawable.ic_menu_compass);
        menu.add(1, S.MAP, 0, R.string.mapstyle).setIcon(
                android.R.drawable.ic_menu_mapmode);
        menu.add(1, S.INFO, 0, R.string.minfo).setIcon(
                android.R.drawable.ic_menu_info_details);
        menu.add(2, S.MODE, 0, R.string.perspective).setIcon(
                android.R.drawable.ic_menu_revert);
        menu.add(2, S.SET, 0, R.string.settings).setIcon(
                android.R.drawable.ic_menu_preferences);
        return true;
    }

    /**
     * Handle item selections in the above created menu
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case S.MAP:
            changeMapStyle();
            return true;
        case S.SET:
            showSettings();
            return true;
        case S.POS:
            if (isListening()) {
                disableLocationListener();
            } else {
                enableLocationListener();
            }
            return true;
        case S.COMPASS:
            if (isCompassEnabled()) {
                disableCompass();
            } else {
                enableCompass();
            }
            return true;
        case S.INFO:
            showInfo();
            return true;
        case S.MODE:
            changePerspective();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*****************************************************************************
     * 
     * User location stuff
     * 
     *****************************************************************************/

    protected void displayLocation(List<Address> addresses, int arg2) {
        Address address = addresses.get(arg2);
        Double lat = address.getLatitude() * 1E6;
        Double lng = address.getLongitude() * 1E6;
        GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
        mMapController.animateTo(point);
    }
    
    public void enableLocationListener() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("listener", true);
        editor.commit();
        mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1l, 5, this);
        mMapLocationOverlay.enableMyLocation();
        mMapView.postInvalidate();
    }

    public void disableLocationListener() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("listener", false);
        editor.commit();
        mMapLocationOverlay.disableMyLocation();
        mLocationManager.removeUpdates(this);
        mMapView.postInvalidate();
    }

    public boolean isListening() {
        return mMapLocationOverlay.isMyLocationEnabled();
    }

    public void enableCompass() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("compass", true);
        editor.commit();
        mMapLocationOverlay.enableCompass();
    }

    public void disableCompass() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("compass", false);
        editor.commit();
        mMapLocationOverlay.disableCompass();
    }

    public boolean isCompassEnabled() {
        return mMapLocationOverlay.isCompassEnabled();
    }

    private void disableFollow() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("follow", true);
        editor.commit();
    }

    private void enableFollow() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("follow", false);
        editor.commit();    
    }
    
    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Double lat = location.getLatitude() * 1E6;
            Double lng = location.getLongitude() * 1E6;

            GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
            mMapController.animateTo(point);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void onDirectionsAvailable(Route route, Mode mode) {
        mWindowManager = getWindowManager();
        mDisplay = mWindowManager.getDefaultDisplay();

        Vector<GeoPoint> geo = new Vector<GeoPoint>(route.getGeoPoints());
        List<Overlay> overlays = mMapView.getOverlays();

        LineMapOverlay lineMapOverlay = new LineMapOverlay();
        lineMapOverlay.setLineMapOverlay(getApplicationContext(), geo, mDisplay.getHeight(), mDisplay.getHeight());
        overlays.add(lineMapOverlay);
        
        DrawableMapOverlay dmo = new DrawableMapOverlay();
        dmo.setImageMapOverlay(getApplicationContext(), geo.get(0), R.drawable.bubble, DrawableMapOverlay.CENTER);
        overlays.add(dmo);
        
        dmo = new DrawableMapOverlay();
        dmo.setImageMapOverlay(getApplicationContext(), geo.get(geo.size()-1), R.drawable.bubble, DrawableMapOverlay.CENTER);
        overlays.add(dmo);
        
        mMapView.invalidate();
    }

    @Override
    public void onDirectionsNotAvailable() {
        
    }
}