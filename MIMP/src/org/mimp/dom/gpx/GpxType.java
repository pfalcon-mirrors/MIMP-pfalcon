package org.mimp.dom.gpx;

import java.util.ArrayList;
import java.util.List;

public class GpxType {

    protected MetadataType metadata;
    protected List<WptType> wpt;
    protected List<RteType> rte;
    protected List<TrkType> trk;
    protected ExtensionsType extensions;
    protected String version;
    protected String creator;

    public GpxType() {
    }

    public GpxType(MetadataType metadata, List<WptType> wpt, List<RteType> rte, List<TrkType> trk, ExtensionsType extensions, String version, String creator) {
        this.metadata = metadata;
        this.wpt = wpt;
        this.rte = rte;
        this.trk = trk;
        this.extensions = extensions;
        this.version = version;
        this.creator = creator;
    }

    public MetadataType getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataType value) {
        this.metadata = value;
    }

    public List<WptType> getWpt() {
        if (wpt == null) {
            wpt = new ArrayList<WptType>();
        }
        return this.wpt;
    }

    public List<RteType> getRte() {
        if (rte == null) {
            rte = new ArrayList<RteType>();
        }
        return this.rte;
    }

    public List<TrkType> getTrk() {
        if (trk == null) {
            trk = new ArrayList<TrkType>();
        }
        return this.trk;
    }

    public ExtensionsType getExtensions() {
        return extensions;
    }

    public void setExtensions(ExtensionsType value) {
        this.extensions = value;
    }

    public String getVersion() {
        if (version == null) {
            return "1.1";
        } else {
            return version;
        }
    }

    public void setVersion(String value) {
        this.version = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String value) {
        this.creator = value;
    }
}
