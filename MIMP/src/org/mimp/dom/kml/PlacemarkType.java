package org.mimp.dom.kml;

public class PlacemarkType {

    String name;
    String styleUrl;
    String description;
    PointType point;
    LineStringType line;

    public PlacemarkType() {

    }

    public PlacemarkType (String name, String styleUrl, String description, PointType point, LineStringType line) {
        this.name = name;
        this.styleUrl = styleUrl;
        this.description = description;
        this.point = point;
        this.line = line;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyleUrl() {
        return styleUrl;
    }

    public void setStyleUrl(String styleUrl) {
        this.styleUrl = styleUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PointType getPoint() {
        if (point == null) {
            this.point = new PointType();
        }
        return point;
    }

    public void setPoint(PointType point) {
        this.point = point;
    }

    public LineStringType getLine() {
        if (line == null) {
            this.line = new LineStringType();
        }
        return line;
    }

    public void setLine(LineStringType line) {
        this.line = line;
    }
}
