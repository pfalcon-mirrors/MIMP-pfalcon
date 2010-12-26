package org.mimp.dom.gpx;

import java.util.ArrayList;
import java.util.List;

public class MetadataType {

    protected String name;
    protected String desc;
    protected PersonType author;
    protected CopyrightType copyright;
    protected List<LinkType> link;
    protected String time;
    protected String keywords;
    protected BoundsType bounds;
    protected ExtensionsType extensions;

    public MetadataType() {
    }

    public MetadataType(String name, String desc, PersonType author, CopyrightType copyright, List<LinkType> link, String time, String keywords, BoundsType bounds, ExtensionsType extensions) {
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.copyright = copyright;
        this.link = link;
        this.time = time;
        this.keywords = keywords;
        this.bounds = bounds;
        this.extensions = extensions;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String value) {
        this.desc = value;
    }

    public PersonType getAuthor() {
        return author;
    }

    public void setAuthor(PersonType value) {
        this.author = value;
    }

    public CopyrightType getCopyright() {
        return copyright;
    }

    public void setCopyright(CopyrightType value) {
        this.copyright = value;
    }

    public List<LinkType> getLink() {
        if (link == null) {
            link = new ArrayList<LinkType>();
        }
        return this.link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String value) {
        this.keywords = value;
    }

    public BoundsType getBounds() {
        return bounds;
    }

    public void setBounds(BoundsType value) {
        this.bounds = value;
    }

    public ExtensionsType getExtensions() {
        return extensions;
    }

    public void setExtensions(ExtensionsType value) {
        this.extensions = value;
    }
}
