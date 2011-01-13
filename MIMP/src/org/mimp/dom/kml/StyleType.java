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
        if (iconstyle == null) {
            this.iconstyle = new IconStyleType();
        }
        return iconstyle;
    }
    
    public void setIconstyle(IconStyleType iconstyle) {
        this.iconstyle = iconstyle;
    }
    
    public LineStyleType getLinestyle() {
        if (linestyle == null) {
            this.linestyle = new LineStyleType();
        }
        return linestyle;
    }
    
    public void setLinestyle(LineStyleType linestyle) {
        this.linestyle = linestyle;
    }

}
