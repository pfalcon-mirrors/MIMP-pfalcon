package org.mimp.dom.kml;

import java.io.Serializable;
import java.math.BigDecimal;

public class CoordinatesType implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected BigDecimal ele;
    protected BigDecimal lat;
    protected BigDecimal lon;

    public CoordinatesType() {
    }

    public CoordinatesType(BigDecimal lat, BigDecimal lon, BigDecimal ele) {
        this.ele = ele;
        this.lat = lat;
        this.lon = lon;
    }

    public BigDecimal getEle() {
        return ele;
    }

    public void setEle(BigDecimal value) {
        this.ele = value;
    }

    public BigDecimal getLat() {
        return lat;
    }
    
    public int getLatE6() {
        return (int)(lat.doubleValue()*1E6);
    }

    public void setLat(BigDecimal value) {
        this.lat = value;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public int getLonE6() {
        return (int)(lon.doubleValue()*1E6);
    }
    
    public void setLon(BigDecimal value) {
        this.lon = value;
    }
}
