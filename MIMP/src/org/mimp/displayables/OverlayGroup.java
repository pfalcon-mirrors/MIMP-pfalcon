package org.mimp.displayables;

import java.util.Vector;

import android.graphics.Canvas;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class OverlayGroup extends Overlay {

	/**
	 * this is used in order to have as many tracks and waypoints as i want
	 * without interfering with other overlays
	 * this allows me to handle directions with waypoints
	 */
	
	private Vector<Overlay> mOverlaysVector;
	
	public OverlayGroup() {
		mOverlaysVector = new Vector<Overlay>();
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		for (Overlay mOverlay : mOverlaysVector) {
			mOverlay.draw(canvas, mapView, shadow);
		}
		super.draw(canvas, mapView, shadow);
	}
	
	public void clear() {
		mOverlaysVector = new Vector<Overlay>();
	}
	
	public void add(Overlay overlay) {
		mOverlaysVector.add(overlay);
	}
	
	public void remove(Overlay overlay) {
		mOverlaysVector.remove(overlay);
	}
	
	public void remove(int position) {
		mOverlaysVector.remove(position);
	}
	
	public Vector<Overlay> getOverlays() {
		return mOverlaysVector;
	}
}
