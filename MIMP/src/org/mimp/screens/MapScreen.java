package org.mimp.screens;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.mapping.google.DrivingDirections;
import org.mapping.google.DrivingDirectionsFactory;
import org.mapping.google.Route;
import org.mapping.google.DrivingDirections.IDirectionsListener;
import org.mapping.google.DrivingDirections.Mode;
import org.mapping.google.impl.DrivingDirectionsGoogleKML;
import org.mimp.R;
import org.mimp.displayables.DrawableMapOverlay;
import org.mimp.displayables.LineMapOverlay;
import org.mimp.views.ExtendedMapView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.MapView.LayoutParams;

public class MapScreen extends MapActivity implements LocationListener, IDirectionsListener {

	private static final int MAP = 200000;
	private static final int INFO = 200001;
	private static final int SET = 200002;
	private static final int POS = 200003;
	private static final int COMPASS = 200004;
	private static final int MODE = 200005;
	private static final String PREFS_NAME = "preferences";
	private boolean ROUTE = false;

	private ExtendedMapView mapView;
	private MapController mapController;
	private LocationManager locationManager;
	private MyLocationOverlay mapLocationOverlay;
	private EditText searchBox;
	private List<Address> addresses = null;
	private WindowManager windowManager;
	private Display display;
	private DrivingDirectionsGoogleKML directionsGoogleKML = null;

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
		mapView = (ExtendedMapView) findViewById(R.id.mapView);
		mapController = mapView.getController();

		LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.zoom);
		View zoomView = mapView.getZoomControls();

		zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mapView.displayZoomControls(true);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mapLocationOverlay = new MyLocationOverlay(getApplicationContext(),
				mapView);
		mapLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapController.animateTo(mapLocationOverlay.getMyLocation());
			}
		});
		mapView.getOverlays().add(mapLocationOverlay);
		
		directionsGoogleKML = (DrivingDirectionsGoogleKML) DrivingDirectionsFactory.createDrivingDirections();
		Vector<GeoPoint> geoPoints = new Vector<GeoPoint>();
		Double lat = 50* 1E6;
		Double lng = 5 * 1E6;
		geoPoints.add(new GeoPoint(lat.intValue(), lng.intValue()));
		lng = 6 * 1E6;
		geoPoints.add(new GeoPoint(lat.intValue(), lng.intValue()));
		directionsGoogleKML.driveTo(geoPoints, DrivingDirections.Mode.DRIVING, this);
		
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
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean mode = settings.getBoolean("mapstyle", true);
		mapView.setSatellite(mode);
	}

	public void checkCompass() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean mode = settings.getBoolean("compass", false);
		if (mode) {
			enableCompass();
		} else {
			disableCompass();
		}
	}

	public void checkListener() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean mode = settings.getBoolean("listener", false);
		if (mode) {
			enableLocationListener();
		} else {
			disableLocationListener();
		}
	}

	public void checkPerspective() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean mode = settings.getBoolean("perspective", false);
		if (mode) {
		    mapView.setPerspective(true);
		} else {
		    mapView.setPerspective(false);
		}
	}

	private void checkFollow() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
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
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();

		boolean mode = settings.getBoolean("mapstyle", true);
		mapView.setSatellite(!mode);

		editor.putBoolean("mapstyle", !mode);
		editor.commit();
	}

	private void changePerspective() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();

		boolean mode = settings.getBoolean("perspective", true);
		mapView.setPerspective(!mode);

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
	 * Screens handling // TOBE removed and replaced by dispatcher
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
		searchBox = new EditText(getApplicationContext());
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.research).setCancelable(false).setView(searchBox)
				.setPositiveButton(android.R.string.search_go, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						searchLocation(searchBox.getText().toString());
						searchBox = null;
					}
				}).setNegativeButton(android.R.string.cancel, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						searchBox = null;
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
		return true;
	}

	public void searchLocation(String location) {
		Geocoder geocoder = new Geocoder(getApplicationContext());
		try {
			addresses = geocoder.getFromLocationName(location, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (addresses != null) {
			String tempAdress;
			String[] mArray = new String[addresses.size()];
			for (int i = 0; i < addresses.size(); i++) {
				tempAdress = "";
				if (addresses.get(i).getThoroughfare() != null)
					tempAdress = tempAdress
							+ addresses.get(i).getThoroughfare() + " ";
				if (addresses.get(i).getCountryName() != null)
					tempAdress = tempAdress + addresses.get(i).getCountryName()
							+ " ";
				if (addresses.get(i).getLocality() != null)
					tempAdress = tempAdress + addresses.get(i).getLocality();
				mArray[i] = tempAdress;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			ListAdapter listAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_single_choice, mArray);
			builder.setTitle("Search : ").setCancelable(false)
					.setSingleChoiceItems(listAdapter, -1,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int item) {
									displayLocation(addresses, item);
									dialog.dismiss();
								}
							}).setNegativeButton("Cancel",
							new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		MapController mc = mapView.getController();
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
		menu.add(0, POS, 0, R.string.position).setIcon(
				android.R.drawable.ic_menu_mylocation);
		menu.add(0, COMPASS, 0, R.string.compass).setIcon(
				android.R.drawable.ic_menu_compass);
		menu.add(1, MAP, 0, R.string.mapstyle).setIcon(
				android.R.drawable.ic_menu_mapmode);
		menu.add(1, INFO, 0, R.string.minfo).setIcon(
				android.R.drawable.ic_menu_info_details);
		menu.add(2, MODE, 0, R.string.perspective).setIcon(
				android.R.drawable.ic_menu_revert);
		menu.add(2, SET, 0, R.string.settings).setIcon(
				android.R.drawable.ic_menu_preferences);
		return true;
	}

	/**
	 * Handle item selections in the above created menu
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MAP:
			changeMapStyle();
			return true;
		case SET:
			showSettings();
			return true;
		case POS:
			if (isListening()) {
				disableLocationListener();
			} else {
				enableLocationListener();
			}
			return true;
		case COMPASS:
			if (isCompassEnabled()) {
				disableCompass();
			} else {
				enableCompass();
			}
			return true;
		case INFO:
			showInfo();
			return true;
		case MODE:
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
		mapController.animateTo(point);
	}
	
	public void enableLocationListener() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("listener", true);
		editor.commit();

		locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1l, 5, this);
		mapLocationOverlay.enableMyLocation();
		mapView.postInvalidate();
	}

	public void disableLocationListener() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("listener", false);
		editor.commit();

		mapLocationOverlay.disableMyLocation();
		locationManager.removeUpdates(this);
		mapView.postInvalidate();
	}

	public boolean isListening() {
		return mapLocationOverlay.isMyLocationEnabled();
	}

	public void enableCompass() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("compass", true);
		editor.commit();

		mapLocationOverlay.enableCompass();
	}

	public void disableCompass() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("compass", false);
		editor.commit();

		mapLocationOverlay.disableCompass();
	}

	public boolean isCompassEnabled() {
		return mapLocationOverlay.isCompassEnabled();
	}

	private void disableFollow() {
		// TODO Auto-generated method stub
	}

	private void enableFollow() {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if (location != null) {
			Double lat = location.getLatitude() * 1E6;
			Double lng = location.getLongitude() * 1E6;

			GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
			mapController.animateTo(point);
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
        windowManager = getWindowManager();
        display = windowManager.getDefaultDisplay();

        Vector<GeoPoint> geo = new Vector<GeoPoint>(route.getGeoPoints());
        List<Overlay> overlays = mapView.getOverlays();

        LineMapOverlay lineMapOverlay = new LineMapOverlay();
        lineMapOverlay.setLineMapOverlay(getApplicationContext(), geo, display.getHeight(), display.getHeight());
        overlays.add(lineMapOverlay);
        
        DrawableMapOverlay dmo = new DrawableMapOverlay();
        dmo.setImageMapOverlay(getApplicationContext(), geo.get(0), R.drawable.bubble, DrawableMapOverlay.CENTER);
        overlays.add(dmo);
        
        dmo = new DrawableMapOverlay();
        dmo.setImageMapOverlay(getApplicationContext(), geo.get(geo.size()-1), R.drawable.bubble, DrawableMapOverlay.CENTER);
        overlays.add(dmo);
        
        mapView.invalidate();
	}

	@Override
	public void onDirectionsNotAvailable() {
		
	}
}