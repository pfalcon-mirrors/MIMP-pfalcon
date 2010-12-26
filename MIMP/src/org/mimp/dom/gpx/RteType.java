package org.mimp.dom.gpx;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RteType {

    protected String name;
    protected String cmt;
    protected String desc;
    protected String src;
    protected List<LinkType> link;
    protected BigInteger number;
    protected String type;
    protected ExtensionsType extensions;
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

    public ExtensionsType getExtensions() {
        return extensions;
    }

    public void setExtensions(ExtensionsType value) {
        this.extensions = value;
    }

    public List<WptType> getRtept() {
        if (rtept == null) {
            rtept = new ArrayList<WptType>();
        }
        return this.rtept;
    }

}
