package org.mimp.dom.kml;

import java.util.ArrayList;
import java.util.List;

public class DocumentType {

    String name;
    String description;
    List<StyleType> styles;
    List<PlacemarkType> placemarks;
    
    public DocumentType() {
    }
    
    public DocumentType(String name, String description, List<StyleType> styles, List<PlacemarkType> placemarks) {
        this.name = name;
        this.description = description;
        this.styles = styles;
        this.placemarks = placemarks;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<StyleType> getStyles() {
        if (this.styles == null) {
            this.styles = new ArrayList<StyleType>();
        }
        return styles;
    }
    
    public StyleType getLastStyle() {
        return this.styles.get(styles.size()-1);
    }
    
    public void setStyles(List<StyleType> styles) {
        this.styles = styles;
    }
    
    public List<PlacemarkType> getPlacemarks() {
        if (this.placemarks == null) {
            this.placemarks = new ArrayList<PlacemarkType>();
        }
        return placemarks;
    }
    
    public PlacemarkType getLastPlacemark() {
        return placemarks.get(placemarks.size()-1);
    }
    
    public void setPlacemarks(List<PlacemarkType> placemarks) {
        this.placemarks = placemarks;
    }
    
    
}
