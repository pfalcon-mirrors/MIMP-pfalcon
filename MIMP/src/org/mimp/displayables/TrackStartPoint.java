package org.mimp.displayables;

import android.content.Context;
import android.graphics.Paint;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class TrackStartPoint extends MapPointOverlay {
	
	private Paint corePaint;
	
	public TrackStartPoint(GeoPoint location, Context context) {
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
			corePaint.setARGB(255, 15, 215, 19); //green
			corePaint.setAntiAlias(true);
		}
		return corePaint;
	}
}
