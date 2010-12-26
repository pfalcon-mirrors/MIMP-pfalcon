package org.mimp.dom.gpx;

import java.math.BigDecimal;

public class PtType {

    protected BigDecimal ele;
    protected String time;
    protected BigDecimal lat;
    protected BigDecimal lon;

    public BigDecimal getEle() {
        return ele;
    }

    public void setEle(BigDecimal value) {
        this.ele = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal value) {
        this.lat = value;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal value) {
        this.lon = value;
    }
}
