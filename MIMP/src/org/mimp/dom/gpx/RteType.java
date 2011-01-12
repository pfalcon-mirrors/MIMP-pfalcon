package org.mimp.dom.gpx;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.android.maps.GeoPoint;

public class RteType {

    protected String name;
    protected String cmt;
    protected String desc;
    protected String src;
    protected List<LinkType> link;
    protected BigInteger number;
    protected String type;
    protected String extensions;
    protected List<WptType> rtept;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String value) {
        this.cmt = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String value) {
        this.desc = value;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String value) {
        this.src = value;
    }

    public List<LinkType> getLink() {
        if (link == null) {
            link = new ArrayList<LinkType>();
        }
        return this.link;
    }
    
    public LinkType getLastLink() {
        return link.get(link.size()-1);
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger value) {
        this.number = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String value) {
        this.extensions = value;
    }

    public List<WptType> getRtept() {
        if (rtept == null) {
            rtept = new ArrayList<WptType>();
        }
        return this.rtept;
    }

    public WptType getLastRtept() {
        return this.rtept.get(rtept.size()-1);
    }

    public Vector<GeoPoint> getPoints() {
        if (rtept == null) {
            rtept = new ArrayList<WptType>();
        }
        Vector<GeoPoint> points = new Vector<GeoPoint>();
        for (WptType point : rtept)
        {
            points.add(new GeoPoint(point.getLatE6(),point.getLonE6()));
        }
        return points;
    }
}
