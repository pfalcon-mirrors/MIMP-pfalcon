package org.mimp.parser.gpx;

import java.io.Serializable;
import java.util.Vector;

import org.mimp.parser.GeoPointer;

/**
 * @author hellhand
 * 
 */

public class GPXTrack implements Serializable {

    private static final long serialVersionUID = -7245021023937365148L;
    private int duration = 0;
    private int distance = 0;
    private int thg = 0;
    private int thl = 0;
    private int activity = 0;
    private int realdistance = 0;
    private int eleMin = 0;
    private int eleMax = 0;
    private Vector<GeoPointer> geoPointers = new Vector<GeoPointer>();

    public GPXTrack() {

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

    public int getRealdistance() {
        return realdistance;
    }

    public void setRealdistance(int realdistance) {
        this.realdistance = realdistance;
    }

    public int getEleMin() {
        return eleMin;
    }

    public void setEleMin(int eleMin) {
        this.eleMin = eleMin;
    }

    public int getEleMax() {
        return eleMax;
    }

    public void setEleMax(int eleMax) {
        this.eleMax = eleMax;
    }

    public void addGeoPointer(GeoPointer gp) {
        geoPointers.add(gp);
    }

    public Vector<GeoPointer> getGeoPoints() {
        return geoPointers;
    }
}
