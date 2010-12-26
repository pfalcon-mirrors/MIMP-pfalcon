package org.mimp.dom.gpx;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class WptType {

    protected BigDecimal ele;
    protected String time;
    protected BigDecimal magvar;
    protected BigDecimal geoidheight;
    protected String name;
    protected String cmt;
    protected String desc;
    protected String src;
    protected List<LinkType> link;
    protected String sym;
    protected String type;
    protected String fix;
    protected BigInteger sat;
    protected BigDecimal hdop;
    protected BigDecimal vdop;
    protected BigDecimal pdop;
    protected BigDecimal ageofdgpsdata;
    protected Integer dgpsid;
    protected ExtensionsType extensions;
    protected BigDecimal lat;
    protected BigDecimal lon;

    public WptType() {
    }

    public WptType(BigDecimal ele, String time, BigDecimal magvar, BigDecimal geoidheight, String name, String cmt, String desc, String src, List<LinkType> link, String sym, String type, String fix, BigInteger sat, BigDecimal hdop, BigDecimal vdop, BigDecimal pdop, BigDecimal ageofdgpsdata, Integer dgpsid, ExtensionsType extensions, BigDecimal lat, BigDecimal lon) {
        this.ele = ele;
        this.time = time;
        this.magvar = magvar;
        this.geoidheight = geoidheight;
        this.name = name;
        this.cmt = cmt;
        this.desc = desc;
        this.src = src;
        this.link = link;
        this.sym = sym;
        this.type = type;
        this.fix = fix;
        this.sat = sat;
        this.hdop = hdop;
        this.vdop = vdop;
        this.pdop = pdop;
        this.ageofdgpsdata = ageofdgpsdata;
        this.dgpsid = dgpsid;
        this.extensions = extensions;
        this.lat = lat;
        this.lon = lon;
    }

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

    public BigDecimal getMagvar() {
        return magvar;
    }

    public void setMagvar(BigDecimal value) {
        this.magvar = value;
    }

    public BigDecimal getGeoidheight() {
        return geoidheight;
    }

    public void setGeoidheight(BigDecimal value) {
        this.geoidheight = value;
    }

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

    public String getSym() {
        return sym;
    }

    public void setSym(String value) {
        this.sym = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String value) {
        this.fix = value;
    }

    public BigInteger getSat() {
        return sat;
    }

    public void setSat(BigInteger value) {
        this.sat = value;
    }

    public BigDecimal getHdop() {
        return hdop;
    }

    public void setHdop(BigDecimal value) {
        this.hdop = value;
    }

    public BigDecimal getVdop() {
        return vdop;
    }

    public void setVdop(BigDecimal value) {
        this.vdop = value;
    }

    public BigDecimal getPdop() {
        return pdop;
    }

    public void setPdop(BigDecimal value) {
        this.pdop = value;
    }

    public BigDecimal getAgeofdgpsdata() {
        return ageofdgpsdata;
    }

    public void setAgeofdgpsdata(BigDecimal value) {
        this.ageofdgpsdata = value;
    }

    public Integer getDgpsid() {
        return dgpsid;
    }

    public void setDgpsid(Integer value) {
        this.dgpsid = value;
    }

    public ExtensionsType getExtensions() {
        return extensions;
    }

    public void setExtensions(ExtensionsType value) {
        this.extensions = value;
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
