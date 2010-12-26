package org.mimp.dom.gpx;

public class EmailType {

    protected String id;
    protected String domain;

    public EmailType() {
    }

    public EmailType(String id, String domain) {
        this.id = id;
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String value) {
        this.domain = value;
    }
}
