package org.mimp.parser;

import java.io.Serializable;

import com.google.android.maps.GeoPoint;

import android.provider.BaseColumns;

/**
 * @author hellhand
 * 
 */

public class GeoPointer extends GeoPoint implements Serializable, BaseColumns {

    private static final long serialVersionUID = -6689455374405295972L;
    int latitudeE6 = 0;
    int longitudeE6 = 0;
    float elevation = 0;
    String time = "";

    public GeoPointer(int latitudeE6, int longitudeE6) {
        super(latitudeE6, longitudeE6);
        elevation = 0;
    }

    public GeoPointer(double latitude, double longitude) {
        super((int) (latitude * 1E6), (int) (longitude * 1E6));
        elevation = 0;
    }

    public GeoPointer(int latitudeE6, int longitudeE6, int height) {
        super(latitudeE6, longitudeE6);
        this.elevation = height;
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return super.toString() + elevation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
