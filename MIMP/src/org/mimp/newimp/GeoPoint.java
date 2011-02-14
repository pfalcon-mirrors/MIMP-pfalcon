package org.mimp.newimp;

import java.io.Serializable;

import android.provider.BaseColumns;

/**
 * @author hellhand
 * 
 */

public class GeoPoint implements Serializable, BaseColumns {

    private static final long serialVersionUID = -6689455374405295972L;
    int latitudeE6 = 0;
    int longitudeE6 = 0;
    double elevation = 0;
    String time = "";

    public int getLatitudeE6() {
        return latitudeE6;
    }

    public void setLatitudeE6(int latitudeE6) {
        this.latitudeE6 = latitudeE6;
    }

    public int getLongitudeE6() {
        return longitudeE6;
    }

    public void setLongitudeE6(int longitudeE6) {
        this.longitudeE6 = longitudeE6;
    }

    public GeoPoint() {
    }
    
    public GeoPoint(int latitudeE6, int longitudeE6) {
        this.latitudeE6 = latitudeE6;
        this.longitudeE6 = longitudeE6;
        elevation = 0;
    }

    public GeoPoint(int latitudeE6, int longitudeE6, int height) {
        this.latitudeE6 = latitudeE6;
        this.longitudeE6 = longitudeE6;
        this.elevation = height;
    }

    public GeoPoint(double latitude, double longitude) {
        this.latitudeE6 = (int) (latitude * 1E6);
        this.longitudeE6 = (int) (longitude * 1E6);
        this.elevation = 0;
    }
    
    public GeoPoint(double latitude, double longitude, double altitude) {
        this.latitudeE6 = (int) (latitude * 1E6);
        this.longitudeE6 = (int) (longitude * 1E6);
        this.elevation = altitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return latitudeE6/1E6 + " " + longitudeE6/1E6 + " " + elevation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
