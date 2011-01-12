package org.mimp.dom.kml;

import java.util.List;
import java.util.Vector;

import org.mimp.dom.ParsedObject;
import org.mimp.dom.gpx.WptType;

import com.google.android.maps.GeoPoint;

public class KmlType implements ParsedObject  {

    DocumentType document;
    String xmlns;
    
    public KmlType() {

    }
    
    public KmlType(DocumentType document, String xmlns) {
        super();
        this.document = document;
        this.xmlns = xmlns;
    }

    public DocumentType getDocument() {
        if (this.document == null){
            this.document = new DocumentType();
        }
        return document;
    }

    public void setDocument(DocumentType document) {
        this.document = document;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
    
    @Override
    public String getName() {
        return document.getName();
    }

    @Override
    public String getDescr() {
        return document.getDescription();
    }

    @Override
    public Vector<GeoPoint> getPoints() {
        //TODO: handle multiple tracks
        Vector<GeoPoint> geoPoints = new Vector<GeoPoint>();
        List<CoordinatesType> coords = null;
        for (PlacemarkType placemark : getDocument().getPlacemarks()) {
            if (placemark.line != null) {
                coords = placemark.line.getCoordinates();
                for (CoordinatesType coord : coords) {
                    geoPoints.add(new GeoPoint(coord.getLatE6(), coord.getLonE6()));
                }
                break;
            }
        }
        return geoPoints;
    }

    @Override
    public List<WptType> getPOIs() {
        // TODO Auto-generated method stub
        return null;
    }

}
