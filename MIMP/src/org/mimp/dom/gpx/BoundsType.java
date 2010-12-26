package org.mimp.dom.gpx;

import java.math.BigDecimal;

public class BoundsType {

    protected BigDecimal minlat;
    protected BigDecimal minlon;
    protected BigDecimal maxlat;
    protected BigDecimal maxlon;

    public BoundsType() {
    }

    public BoundsType(BigDecimal minlat, BigDecimal minlon, BigDecimal maxlat, BigDecimal maxlon) {
        this.minlat = minlat;
        this.minlon = minlon;
        this.maxlat = maxlat;
        this.maxlon = maxlon;
    }

    public BigDecimal getMinlat() {
        return minlat;
    }

    public void setMinlat(BigDecimal value) {
        this.minlat = value;
    }

    public BigDecimal getMinlon() {
        return minlon;
    }

    public void setMinlon(BigDecimal value) {
        this.minlon = value;
    }

    public BigDecimal getMaxlat() {
        return maxlat;
    }

    public void setMaxlat(BigDecimal value) {
        this.maxlat = value;
    }

    public BigDecimal getMaxlon() {
        return maxlon;
    }

    public void setMaxlon(BigDecimal value) {
        this.maxlon = value;
    }
}
