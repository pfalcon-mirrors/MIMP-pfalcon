package org.mimp.parser;

import java.io.Serializable;

/**
 * @author hellhand
 * 
 */

public class MediaPoint implements Serializable {

    private static final long serialVersionUID = 2969160749909407636L;
    private String lang = "";
    private String type = "";
    private int radius = 0;
    private int bearing = 0;
    private String title = "";
    private String url = "";
    private String sourceName = "";
    private String sourceUrl = "";
    private String pubName = "";
    private String pubUrl = "";
    private String authName = "";
    private String authUrl = "";
    private String content = "";

    public MediaPoint() {

    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getBearing() {
        return bearing;
    }

    public void setBearing(int bearing) {
        this.bearing = bearing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubUrl() {
        return pubUrl;
    }

    public void setPubUrl(String pubUrl) {
        this.pubUrl = pubUrl;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
