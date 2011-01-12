package org.mimp.dom.kml;

public class StyleType {
    
    String id;
    IconStyleType iconstyle;
    LineStyleType linestyle;
    
    public StyleType() {

    }
    
    public StyleType(String id, IconStyleType iconstyle, LineStyleType linestyle) {
        this.id = id;
        this.iconstyle = iconstyle;
        this.linestyle = linestyle;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public IconStyleType getIconstyle() {
        return iconstyle;
    }
    
    public void setIconstyle(IconStyleType iconstyle) {
        this.iconstyle = iconstyle;
    }
    
    public LineStyleType getLinestyle() {
        return linestyle;
    }
    
    public void setLinestyle(LineStyleType linestyle) {
        this.linestyle = linestyle;
    }

}
