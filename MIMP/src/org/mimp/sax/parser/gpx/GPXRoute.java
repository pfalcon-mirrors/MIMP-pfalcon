package org.mimp.sax.parser.gpx;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

import org.mimp.sax.parser.GeoPointer;

/**
 * @author hellhand
 * 
 */

public class GPXRoute implements Serializable {

    private static final long serialVersionUID = -2834014426672961459L;
    private Hashtable<String, String> gpxRteInfoDescr = new Hashtable<String, String>();
    private Hashtable<String, String> gpxRteInfoName = new Hashtable<String, String>();
    private Vector<String> gpxRteChara = new Vector<String>();
    private Vector<GeoPointer> geoPointers = new Vector<GeoPointer>();
    private int duration = 0;
    private int distance = 0;
    private int maxSpeed = 0;
    private int thg = 0;
    private int thl = 0;
    private int activity = 0;

    public void addRteInfoDescr(String value, String data) {
        gpxRteInfoDescr.put(value, data);
    }

    public Hashtable<String, String> getGpxRteInfoDescr() {
        return gpxRteInfoDescr;
    }

    public void addGpxRteInfoName(String value, String data) {
        gpxRteInfoName.put(value, data);
    }

    public Hashtable<String, String> getGpxRteInfoName() {
        return gpxRteInfoName;
    }

    public void addGpxRteChara(String lang, String data) {
        this.gpxRteChara.add(lang);
        this.gpxRteChara.add(data);
    }

    public Vector<String> getGpxRteChara() {
        return gpxRteChara;
    }

    public Vector<GeoPointer> getGeoPointers() {
        return geoPointers;
    }

    public void addGeoPointer(GeoPointer point) {
        geoPointers.add(point);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getThg() {
        return thg;
    }

    public void setThg(int thg) {
        this.thg = thg;
    }

    public int getThl() {
        return thl;
    }

    public void setThl(int thl) {
        this.thl = thl;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }
}
