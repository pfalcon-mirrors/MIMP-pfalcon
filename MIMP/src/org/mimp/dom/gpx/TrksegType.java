package org.mimp.dom.gpx;

import java.util.ArrayList;
import java.util.List;

public class TrksegType {

    protected List<WptType> trkpt;
    protected ExtensionsType extensions;

    public TrksegType() {
    }

    public TrksegType(List<WptType> trkpt, ExtensionsType extensions) {
        this.trkpt = trkpt;
        this.extensions = extensions;
    }

    public List<WptType> getTrkpt() {
        if (trkpt == null) {
            trkpt = new ArrayList<WptType>();
        }
        return this.trkpt;
    }

    public ExtensionsType getExtensions() {
        return extensions;
    }

    public void setExtensions(ExtensionsType value) {
        this.extensions = value;
    }
}
