package org.mimp.sax.gpx;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

import org.mimp.sax.GeoPointer;
import org.mimp.sax.ParsedObject;
import org.mimp.sax.PointOfInterest;

/**
 * @author hellhand
 * 
 */

public class GPXObject implements Serializable, ParsedObject {

    private static final long serialVersionUID = 328690485076301013L;
    private static final String ENDLINE = "\n";
    private String name = "";
    private String descr = "";
    private String author = "";
    private String copyright = "";
    private String link = "";
    private String email = "";
    private String date = "";
    private float minLat = 0;
    private float minLon = 0;
    private float maxLat = 0;
    private float maxLon = 0;
    private Vector<PointOfInterest> points = new Vector<PointOfInterest>();
    
    private Hashtable<String, String> gpxInfoDescr = new Hashtable<String, String>();
    private Hashtable<String, String> gpxInfoName = new Hashtable<String, String>();

    private GPXTrack track = new GPXTrack();
    private GPXRoute route = new GPXRoute();

    public GPXObject() {

    }

    public GPXObject(GPXObject gpxa) {
        // TODO Auto-generated constructor stub
    }

    public String toString() {
        StringBuffer representation = new StringBuffer();

        representation.append(name);
        representation.append(ENDLINE);
        representation.append(descr);
        representation.append(ENDLINE);
        representation.append(author);
        representation.append(ENDLINE);
        representation.append(email);
        representation.append(ENDLINE);
        representation.append(copyright);
        representation.append(ENDLINE);
        representation.append(date);
        representation.append(ENDLINE);

        return representation.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescr(String data) {
        descr = data;
    }

    public String getDescr() {
        return descr;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getMinLat() {
        return minLat;
    }

    public float getMinLon() {
        return minLon;
    }

    public float getMaxLat() {
        return maxLat;
    }

    public float getMaxLon() {
        return maxLon;
    }

    public void setLatLon(float minLat, float maxLat, float minLon, float maxLon) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
    }

    public GPXTrack getTrack() {
        return track;
    }

    public void setTrack(GPXTrack track) {
        this.track = track;
    }

    public GPXRoute getRoute() {
        return route;
    }

    public void setRoute(GPXRoute route) {
        this.route = route;
    }

    public void addPoint(PointOfInterest poi) {
        points.add(poi);
    }

    public Vector<PointOfInterest> getPoints() {
        return points;
    }

    @Override
    public Vector<GeoPointer> getGeoPoints() {
        if (track != null) {
            return track.getGeoPoints();
        }
        else if (route != null) {
            return route.getGeoPointers();
        }
        else {
            return null;
        }
    }
    
    public void addGpxInfoDescr(String value, String data) {
        gpxInfoDescr.put(value, data);
    }

    public Hashtable<String, String> getGpxInfoDescr() {
        return gpxInfoDescr;
    }

    public void addGpxInfoName(String value, String data) {
        gpxInfoName.put(value, data);
    }

    public Hashtable<String, String> getGpxInfoName() {
        return gpxInfoName;
    }
}
