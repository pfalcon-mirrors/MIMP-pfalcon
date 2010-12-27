package org.mimp.dom.gpx;

public class LinkType {

    protected String text = "";
    protected String type = "";
    protected String href = "";

    public LinkType() {
    }

    public LinkType(String text, String type, String href) {
        this.text = text;
        this.type = type;
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String value) {
        this.href = value;
    }

}
