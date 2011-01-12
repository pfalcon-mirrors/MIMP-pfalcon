package org.mimp.dom.kml;


public class LineStyleType {

    int color;
    float width;
    
    public LineStyleType() {
        
    }
    
    public LineStyleType(int color, float width) {
        this.color = color;
        this.width = width;
    }

    public int getColor() {
        return color;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    public float getWidth() {
        return width;
    }
    
    public void setWidth(float width) {
        this.width = width;
    }
}
