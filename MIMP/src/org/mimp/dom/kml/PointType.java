package org.mimp.dom.kml;

public class PointType {

    CoordinatesType coordinates;
    
    public PointType() {

    }

    public PointType(CoordinatesType coordinates) {
        this.coordinates = coordinates;
    }

    public CoordinatesType getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesType coordinates) {
        this.coordinates = coordinates;
    }
}
