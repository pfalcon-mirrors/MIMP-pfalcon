package org.mimp.dom.gpx;

import java.util.ArrayList;
import java.util.List;

public class TrksegType {

    protected List<WptType> trkpt;
    protected String extensions;

    public TrksegType() {
    }

    public TrksegType(List<WptType> trkpt, String extensions) {
        this.trkpt = trkpt;
        this.extensions = extensions;
    }

    public List<WptType> getTrkpt() {
        if (trkpt == null) {
            trkpt = new ArrayList<WptType>();
        }
        return this.trkpt;
    }
    
    public WptType getLastTrkpt() {
        return this.trkpt.get(trkpt.size()-1);
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String value) {
        this.extensions = value;
    }
}
