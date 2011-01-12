package org.mimp.dom.kml;

import java.util.ArrayList;
import java.util.List;

public class LineStringType {

    int tesselate;
    List<CoordinatesType> coordinates;
    
    public LineStringType() {
        
    }
    
    public LineStringType(int tesselate, List<CoordinatesType> coordinates) {
        this.tesselate = tesselate;
        this.coordinates = coordinates;
    }

    public int getTesselate() {
        return tesselate;
    }
    
    public void setTesselate(int tesselate) {
        this.tesselate = tesselate;
    }
    
    public List<CoordinatesType> getCoordinates() {
        if (this.coordinates == null) {
            this.coordinates = new ArrayList<CoordinatesType>();
        }
        return coordinates;
    }
    
    public void setCoordinates(List<CoordinatesType> coordinates) {
        this.coordinates = coordinates;
    }
}
