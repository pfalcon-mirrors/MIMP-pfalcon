package org.mimp.sax;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author hellhand
 * 
 */

public class PointOfInterest implements Serializable {

    private static final long serialVersionUID = -9202249724142528933L;
    private int radius = 0;
    private String sym = "";
    private String type = "";
    private String infossym = "";
    private Hashtable<String, String> name = new Hashtable<String, String>();
    private Hashtable<String, String> desc = new Hashtable<String, String>();
    private Vector<MediaPoint> medias = new Vector<MediaPoint>();

    public PointOfInterest() {
        // TODO Auto-generated constructor stub
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfosSym() {
        return infossym;
    }

    public void setInfosSym(String infossym) {
        this.infossym = infossym;
    }

    public void addName(String lang, String name) {
        this.name.put(lang, name);
    }

    public Hashtable<String, String> getName() {
        return name;
    }

    public void addDesc(String lang, String desc) {
        this.desc.put(lang, desc);
    }

    public Hashtable<String, String> getDesc() {
        return desc;
    }

    public Vector<MediaPoint> getMedias() {
        return medias;
    }

    public void addMedia(MediaPoint media) {
        medias.add(media);
    }
}
