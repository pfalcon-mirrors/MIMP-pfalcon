package org.mimp.dom.gpx;

public class MetadataType {

    protected String name = "";
    protected String desc = "";
    protected PersonType author;
    protected CopyrightType copyright;
    protected LinkType link;
    protected String time = "";
    protected String keywords = "";
    protected BoundsType bounds;
    protected String extensions = "";

    public MetadataType() {
    }

    public MetadataType(String name, String desc, PersonType author, CopyrightType copyright, LinkType link, String time, String keywords, BoundsType bounds, String extensions) {
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
        if (author == null) {
            author = new PersonType();
        }
        return this.author;
    }

    public CopyrightType getCopyright() {
        if (copyright == null) {
            copyright = new CopyrightType();
        }
        return this.copyright;
    }

    public LinkType getLink() {
        if (link == null) {
            link = new LinkType();
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
        if (bounds == null) {
            bounds = new BoundsType();
        }
        return this.bounds;
    }

    public void setBounds(BoundsType value) {
        this.bounds = value;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String value) {
        this.extensions = value;
    }
    
    /**
     * This is just for testing purposes, it will display all related fields of
     * the MetadataType class
     */
    public String toString() {
        return new String(
                "<metadata>" + "\n" +
                "   <name>" + name + "</name>" + "\n" +
                "   <desc>" + desc + "</desc>" + "\n" +
                "   <author>" + "\n" +
                "      <name>" + getAuthor().name + "</name>" + "\n" +
                "      <email" + "\n" +
                "          id='" + getAuthor().getEmail().id + "'" + "\n" +
                "          domain='" + getAuthor().getEmail().domain + "'" + "\n" +
                "      />" + "\n" +
                "      <link href='" + author.getLink().href + "'>" + "\n" +
                "          <text>" + author.getLink().text + "</text>" + "\n" +
                "          <type>" + author.getLink().type + "</type>" + "\n" +
                "      </link>" + "\n" +
                "   </author>" + "\n" +
                "   <copyright author='" + getCopyright().author + "'>" + "\n" +
                "       <year>" + getCopyright().year + "</year>" + "\n" +
                "       <license>" + getCopyright().license + "</license>" + "\n" +
                "   </copyright>" + "\n" +
                "   <link href='" + getLink().href + "'>" + "\n" +
                "       <text>" + getLink().text + "</text>" + "\n" +
                "       <type>" + getLink().type + "</type>" + "\n" +
                "   </link>" + "\n" +
                "   <time>" + time + "</time>" + "\n" +
                "   <keywords>" + keywords + "</keywords>" + "\n" +
                "   <bounds" + "\n" +
                "       minlat='" + getBounds().minlat + "'" + "\n" +
                "       minlon='" + getBounds().minlon + "'" + "\n" +
                "       maxlat='" + getBounds().maxlat + "'" + "\n" +
                "       maxlon='" + getBounds().maxlon + "'/>" + "\n" +
                "   <extensions>" + extensions + "</extensions>" + "\n" +
                "</metadata>" + "\n");
    }
}
