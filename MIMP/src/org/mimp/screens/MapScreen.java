package org.mimp.screens;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Vector;

import org.mapping.google.DrivingDirections;
import org.mapping.google.DrivingDirections.IDirectionsListener;
import org.mapping.google.DrivingDirections.Mode;
import org.mapping.google.DrivingDirectionsFactory;
import org.mapping.google.Route;
import org.mapping.google.impl.DrivingDirectionsGoogleKML;
import org.mapping.google.impl.Locator;
import org.mimp.R;
import org.mimp.displayables.LineMapOverlay;
import org.mimp.displayables.OverlayGroup;
import org.mimp.displayables.TrackEndPoint;
import org.mimp.displayables.TrackStartPoint;
import org.mimp.globals.S;
import org.mimp.parser.GPXHandler;
import org.mimp.parser.GPXHandlerImpl;
import org.mimp.parser.GPXObject;
import org.mimp.parser.GPXParser;
import org.mimp.parser.GeoPointer;
import org.mimp.views.ExtendedMapView;
import org.xml.sax.InputSource;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
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
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.Overlay;

public class MapScreen extends MapActivity implements LocationListener,
        IDirectionsListener {

    private boolean ROUTE = false;

    private ExtendedMapView mMapView;
    private MapController mMapController;
    private LocationManager mLocationManager;
    private WindowManager mWindowManager;
    private Display mDisplay;
    private DrivingDirectionsGoogleKML mDirectionsGoogleKML;
    private Locator mLocator;
    private boolean mTrackLoaded;

    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Setting Defaults
         */
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*
         * setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
         */
        setContentView(R.layout.map);
        mMapView = (ExtendedMapView) findViewById(R.id.mapView);
        mMapController = mMapView.getController();

        LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.zoom);
        View zoomView = mMapView.getZoomControls();

        zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mMapView.displayZoomControls(true);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocator = new Locator(this, mMapView);
        mDirectionsGoogleKML = (DrivingDirectionsGoogleKML) DrivingDirectionsFactory
                .createDrivingDirections();
        mTrackLoaded = false;

        /**
         * Setting preferences or previous status
         */
        doChecks();
    }

    /**
     * handling different relsults of other activities (add poi, waypoint, ... )
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("requestCode : " + requestCode + " resultCode : "
                + resultCode);
        if (requestCode == S.BubbleInteractionScreen_RQC) {
            if (resultCode == S.BubbleInteractionScreen_DIRECTIONS) {
                int[] coords = data.getIntArrayExtra("coords");
                findDirectionsFromHereToY(new GeoPoint(coords[0], coords[1]));
            }
            else if (resultCode == S.BubbleInteractionScreen_WAYPOINT) {
                int[] coords = data.getIntArrayExtra("coords");
                addWaypoint(new GeoPoint(coords[0], coords[1]));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isNew", false);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (isListening()) {
            disableLocationListener();
        }
        super.onDestroy();
    }

    private void doChecks() {
        checkMapStyle();
        checkCompass();
        checkListener();
        checkTrack();
        checkPerspective();
        checkFollow();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return ROUTE;
    }

    /*****************************************************************************
     * 
     * User configuration loading
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
        }
        else {
            disableCompass();
        }
    }

    public void checkListener() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("listener", false);
        if (mode) {
            enableLocationListener();
        }
        else {
            disableLocationListener();
        }
    }

    public void checkPerspective() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("perspective", false);
        if (mode) {
            mMapView.setPerspective(true);
        }
        else {
            mMapView.setPerspective(false);
        }
    }

    private void checkFollow() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("follow", false);
        if (mode) {
            enableFollow();
        }
        else {
            disableFollow();
        }
    }

    private DrivingDirections.Mode getDirectionsMode() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        String mode = settings.getString("map_directions_mode", "car");
        if (mode.equals("foot")) {
            return Mode.WALKING;
        }
        else if (mode.equals("bus")) {
            return Mode.BUS;
        }
        else {
            return Mode.DRIVING;
        }
    }

    private void checkTrack() {
        // TODO: check whenever a track should be displayed from a search
    }

    /*****************************************************************************
     * 
     * Screens handling // TODO to remove and replace by dispatcher or not
     * Dialog handling
     * 
     *****************************************************************************/

    /**
     * Shows setings screen
     */
    private void showSettings() {
        startActivity(new Intent(MapScreen.this, SettingsScreen.class));
    }

    private void showInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Map In My Poket")
                .setMessage(R.string.about)
                .setCancelable(false)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
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
     * Creates the menu items when Menu button is pressed
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, S.POS, 0, R.string.map_menu_position).setIcon(
                android.R.drawable.ic_menu_mylocation);
        menu.add(0, S.COMPASS, 0, R.string.map_menu_compass).setIcon(
                android.R.drawable.ic_menu_compass);
        menu.add(1, S.MAP, 0, R.string.map_menu_mapstyle).setIcon(
                android.R.drawable.ic_menu_mapmode);
        menu.add(2, S.SET, 0, R.string.map_menu_settings).setIcon(
                android.R.drawable.ic_menu_preferences);
        menu.add(2, S.MODE, 0, R.string.map_menu_perspective).setIcon(
                android.R.drawable.ic_menu_revert);
        menu.add(2, S.SEARCH, 0, R.string.map_menu_search).setIcon(
                android.R.drawable.ic_menu_search);
        menu.add(2, S.LOADTRKFILE, 0, R.string.map_menu_unload).setIcon(
                android.R.drawable.ic_menu_directions);
        menu.add(1, S.INFO, 0, R.string.map_menu_infos).setIcon(
                android.R.drawable.ic_menu_info_details);
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
                }
                else {
                    enableLocationListener();
                }
                return true;
            case S.COMPASS:
                if (isCompassEnabled()) {
                    disableCompass();
                }
                else {
                    enableCompass();
                }
                return true;
            case S.INFO:
                showInfo();
                return true;
            case S.MODE:
                changePerspective();
                return true;
            case S.SEARCH:
                onSearchRequested();
                return true;
            case S.LOADTRKFILE:
                if (isTrackLoaded()) {
                    unloadTracks();
                }
                else {
                    loadTracksFile();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadTracksFile() { // TODO get rid of this ugly thing with
                                    // selection screen
        try {
            File root = Environment.getExternalStorageDirectory();
            String filename = root.getAbsolutePath() + File.separator;
            filename += "Tracks" + File.separator;
            filename += "track.gpx";
            System.out.println(filename);
            FileInputStream fis = new FileInputStream(new File(filename));
            InputSource is = new InputSource(fis);
            GPXHandler handler = new GPXHandlerImpl();
            GPXParser parser = new GPXParser(handler, null);
            parser.parse(is);
            GPXObject gpxObject = handler.getGPXObject();
            System.out.println(gpxObject);

            mWindowManager = getWindowManager();
            mDisplay = mWindowManager.getDefaultDisplay();

            Vector<GeoPointer> geo = gpxObject.getTrack().getGeoPoints();
            List<Overlay> overlays = mMapView.getOverlays();

            LineMapOverlay lineMapOverlay = new LineMapOverlay();
            lineMapOverlay.setLineMapOverlay(getApplicationContext(), geo,
                    mDisplay.getHeight(), mDisplay.getHeight());
            overlays.add(lineMapOverlay);

            TrackStartPoint startPoint = new TrackStartPoint(geo.get(0),
                    getApplicationContext());
            overlays.add(startPoint);

            TrackEndPoint endPoint = new TrackEndPoint(geo.get(geo.size() - 1),
                    getApplicationContext());
            overlays.add(endPoint);

            setTrackLoaded(true);
            mMapView.invalidate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unloadTracks() {
        List<Overlay> overlays = mMapView.getOverlays();
        int size = overlays.size();
        for (int i = 1; i < size; i++) {
            overlays.remove(1);
        }
        setTrackLoaded(false);
        mMapView.invalidate();
    }

    public boolean isTrackLoaded() {
        return mTrackLoaded;
    }

    public void setTrackLoaded(boolean value) {
        mTrackLoaded = value;
    }

    /*****************************************************************************
     * 
     * User configuration changes
     * 
     *****************************************************************************/

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
        mMapView.getLocationOverlay().enableMyLocation();
        mMapView.postInvalidate();
    }

    public void disableLocationListener() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("listener", false);
        editor.commit();
        mMapView.getLocationOverlay().disableMyLocation();
        mLocationManager.removeUpdates(this);
        mMapView.postInvalidate();
    }

    public boolean isListening() {
        return mMapView.getLocationOverlay().isMyLocationEnabled();
    }

    public void enableCompass() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("compass", true);
        editor.commit();
        mMapView.getLocationOverlay().enableCompass();
    }

    public void disableCompass() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("compass", false);
        editor.commit();
        mMapView.getLocationOverlay().disableCompass();
    }

    public boolean isCompassEnabled() {
        return mMapView.getLocationOverlay().isCompassEnabled();
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

    /*****************************************************************************
     * 
     * Directions
     * 
     *****************************************************************************/

    public void addWaypoint(GeoPoint end) {

    }

    public void findDirectionsFromHereToY(GeoPoint end) {
        String message = "";
        if (mMapView.getLocationOverlay().getLastFix() == null) {
            message += getString(R.string.navigation_position_unavailable);
        }
        if (end == null) {
            message += getString(R.string.navigation_destination_unavailable);
        }
        if (message.equals("") == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error)
                    .setMessage(message)
                    .setCancelable(false)
                    .setNeutralButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int id) {
                                    dialog.dismiss();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }
        Vector<GeoPoint> geoPoints = new Vector<GeoPoint>();
        Location location = mMapView.getLocationOverlay().getLastFix();
        GeoPoint start = new GeoPointer(location.getLatitude(),
                location.getLongitude());
        geoPoints.add(start);
        geoPoints.add(end);
        mMapView.getOverlayGroup().clear();
        mDirectionsGoogleKML.driveTo(geoPoints, getDirectionsMode(), this);
    }

    public void findDirectionsFromXToY(GeoPoint start, GeoPoint end) {
        Vector<GeoPoint> geoPoints = new Vector<GeoPoint>();
        geoPoints.add(start);
        geoPoints.add(end);
        mDirectionsGoogleKML.driveTo(geoPoints, getDirectionsMode(), this);
    }

    @Override
    public void onDirectionsAvailable(Route route, Mode mode) {
        if (route == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error)
                    .setMessage(R.string.navigation_destination_unavailable)
                    .setCancelable(false)
                    .setNeutralButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int id) {
                                    dialog.dismiss();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }
        mWindowManager = getWindowManager();
        mDisplay = mWindowManager.getDefaultDisplay();

        Vector<GeoPoint> geo = new Vector<GeoPoint>(route.getGeoPoints());
        OverlayGroup overlays = mMapView.getOverlayGroup();

        LineMapOverlay lineMapOverlay = new LineMapOverlay();
        lineMapOverlay.setLineMapOverlay(getApplicationContext(), geo,
                mDisplay.getHeight(), mDisplay.getHeight());
        overlays.add(lineMapOverlay);

        TrackStartPoint startPoint = new TrackStartPoint(geo.get(0),
                getApplicationContext());
        overlays.add(startPoint);

        TrackEndPoint endPoint = new TrackEndPoint(geo.get(geo.size() - 1),
                getApplicationContext());
        overlays.add(endPoint);

        mMapView.invalidate();
    }

    @Override
    public void onDirectionsNotAvailable() {

    }
}