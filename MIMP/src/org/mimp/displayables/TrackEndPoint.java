package org.mimp.displayables;

import android.content.Context;
import android.graphics.Paint;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class TrackEndPoint extends MapPointOverlay {

	private Paint corePaint;
	
	public TrackEndPoint(GeoPoint location, Context context) {
		super(location, context);
		setHeight(SIZE.BIG);
		setCorePaint(getCorePaint());
	}

	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		if (isTapOnElement(p, mapView)) {
			
		}
		return false;
	}
	
	private Paint getCorePaint() {
		if ( corePaint == null) {
			corePaint = new Paint();
			corePaint.setARGB(255, 25, 25, 25); //black
			corePaint.setAntiAlias(true);
		}
		return corePaint;
	}
}
