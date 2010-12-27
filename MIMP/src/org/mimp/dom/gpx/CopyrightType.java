package org.mimp.dom.gpx;


public class CopyrightType {

    protected String year = "";
    protected String license = "";
    protected String author = "";

    public CopyrightType() {
    }

    public CopyrightType(String year, String license, String author) {
        this.year = year;
        this.license = license;
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String value) {
        this.year = value;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String value) {
        this.license = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String value) {
        this.author = value;
    }
}
