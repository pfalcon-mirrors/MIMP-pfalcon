package org.mimp.screens;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mimp.R;
import org.mimp.directions.DrivingDirections;
import org.mimp.directions.DrivingDirections.IDirectionsListener;
import org.mimp.directions.DrivingDirections.Mode;
import org.mimp.directions.DrivingDirectionsFactory;
import org.mimp.directions.Route;
import org.mimp.directions.impl.DrivingDirectionsGoogleKML;
import org.mimp.directions.impl.Locator;
import org.mimp.dom.ParsedFile;
import org.mimp.dom.ParsedFileFactory;
import org.mimp.dom.ParsedObject;
import org.mimp.dom.UnknownType;
import org.mimp.globals.S;
import org.mimp.newimp.GeoPoint;
import org.mimp.newimp.MapController;
import org.mimp.views.ExtendedMapView;

import android.app.Activity;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MapScreen extends Activity implements LocationListener,
        IDirectionsListener {

    private ExtendedMapView mMapView;
    private MapController mMapController;
    private LocationManager mLocationManager;
    private DrivingDirectionsGoogleKML mDirectionsGoogleKML;
    private boolean mTrackLoaded;
    private static File mExtFolder = Environment.getExternalStorageDirectory();

    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/

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
        mMapView.getZoomControls().hide();

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mDirectionsGoogleKML = (DrivingDirectionsGoogleKML) DrivingDirectionsFactory
                .createDrivingDirections();
        mTrackLoaded = false;

        /**
         * Setting preferences or previous status
         */        
        assetToSD();
        doChecks();
    }

    @Override
    public void onNewIntent(final Intent newIntent) {  
        super.onNewIntent(newIntent);
        final String queryAction = newIntent.getAction();     
        if (Intent.ACTION_SEARCH.equals(queryAction)) {
            doSearchQuery(newIntent, "onNewIntent()");
        }
    }
    
    /**
     * handling different relsults of other activities (add poi, waypoint, ... )
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        else if (requestCode == S.TracksScreen_RQC) {
            if (resultCode == S.TracksScreen_LOADTRACK) {
                File file = (File) data.getSerializableExtra("file");
                loadTracksFile(file);
            }
        }
        else if (requestCode == S.SettingsScreen_RQC) {
            mMapView.getTileController().reset();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    @Override
    protected void onDestroy() {
        mLocationManager.removeUpdates(this);
        mMapView.saveState();
        super.onDestroy();
    }

    private void doChecks() {
        checkCompass();
        checkListener();
        checkPerspective();
    }
    
    public void assetToSD() {
        String[] files = null;
        try {
            files = this.getResources().getAssets().list("");
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        File folder = new File(mExtFolder.getAbsolutePath() + File.separator
                + "MIMP" + File.separator + "Tiles" + File.separator);
        folder.mkdirs();
        
        for(String fileName : files)
        {
            Log.d(getClass().toString(), fileName);
            if(fileName.compareTo("images") == 0 ||
                    fileName.compareTo("sounds") == 0 ||
                    fileName.compareTo("webkit") == 0)
                continue;
            try
            {
                File outFile = new File(folder, fileName);
                if(outFile.exists()) continue;

                InputStream in = getAssets().open(fileName);
                OutputStream out = new FileOutputStream(outFile);

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0)
                {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*****************************************************************************
     * 
     * User configuration loading
     * 
     *****************************************************************************/

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

    private DrivingDirections.Mode getDirectionsMode() {
        String[] modes = getResources().getStringArray(
                R.array.entries_list_directions_modes);
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        String mode = settings.getString("map_directions_mode", "driving");
        if (mode.equals(modes[0])) {
            return Mode.WALKING;
        }
        else if (mode.equals(modes[2])) {
            return Mode.TRANSIT;
        }
        else if (mode.equals(modes[3])) {
            return Mode.BICYCLING;
        }
        else {
            return Mode.DRIVING;
        }
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
        startActivityForResult(new Intent(MapScreen.this, SettingsScreen.class),S.SettingsScreen_RQC);
    }
    
    private void showTracks() {
        startActivityForResult(new Intent(MapScreen.this, TracksScreen.class),S.TracksScreen_RQC);
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
        menu.add(2, S.SET, 0, R.string.map_menu_settings).setIcon(
                android.R.drawable.ic_menu_preferences);
        menu.add(2, S.CLEAR, 0, R.string.clear).setIcon(
                android.R.drawable.ic_menu_revert);
        menu.add(2, S.SEARCH, 0, R.string.map_menu_search).setIcon(
                android.R.drawable.ic_menu_search);
        menu.add(2, S.LOADTRKFILE, 0, R.string.map_menu_load).setIcon(
                android.R.drawable.ic_menu_directions);
        menu.add(2, S.MODE, 0, R.string.map_menu_perspective).setIcon(
                android.R.drawable.ic_menu_revert);
        menu.add(1, S.INFO, 0, R.string.map_menu_infos).setIcon(
                android.R.drawable.ic_menu_info_details);
        return true;
    }

    /**
     * Handle item selections in the above created menu
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
                showTracks();
                return true;
            case S.CLEAR:
                removeOverlays();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadTracksFile(File trackfile) {
        try {
            mMapView.unloadTracks();
            ParsedFile parsedFile = ParsedFileFactory.getParsedFile(trackfile);
            ParsedObject parsedObject = parsedFile.getParsedObject();
            mMapView.setCurrentTrack(parsedObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeOverlays() {
        mMapView.unloadTracks();
        mMapView.removeBubble();
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

    @Override
    public void onLocationChanged(Location location) {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        boolean mode = settings.getBoolean("follow_user", false);
        if (location != null && mode == true) {
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
        GeoPoint start = new GeoPoint(location.getLatitude(),
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
        UnknownType directionsTrack = new UnknownType();
        directionsTrack.setGeoPoints(route.getGeoPoints());
        mMapView.setCurrentTrack(directionsTrack);
        mMapView.invalidate();
    }

    @Override
    public void onDirectionsNotAvailable() {

    }
    
    /*****************************************************************************
     * 
     * Directions
     * 
     *****************************************************************************/
    
    @Override
    public boolean onSearchRequested() {
        startSearch(null, false, null, false);
        return true;
    }
    
    private void doSearchQuery(final Intent queryIntent, final String entryPoint) {
        Bundle bundle = queryIntent.getExtras();
        Set<String> keySet = bundle.keySet();
        List<Address> list = Locator.getLocations(getApplicationContext(), (String) bundle.get((String) keySet.toArray()[1]), 15);
        if (list.size() > 1) {
            Toast toast = new Toast(getApplicationContext());
            toast.setText(R.string.select);
            toast.show();
            throw new UnsupportedOperationException(); 
        }
        else {
            double [] coords = {list.get(0).getLatitude(),list.get(0).getLongitude()};
            GeoPoint geoPoint = new GeoPoint((int)(coords[0]*1E6),(int)(coords[1]*1E6));
            mMapController.setZoom(19);
            mMapController.animateTo(geoPoint);
        }
    }
}