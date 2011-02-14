package org.mimp.dom.gpx;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.mimp.dom.ParsedObject;
import org.mimp.newimp.GeoPoint;

public class GpxType implements ParsedObject {

    protected MetadataType metadata;
    protected List<WptType> wpt;
    protected List<RteType> rte;
    protected List<TrkType> trk;
    protected String extensions;
    protected String version;
    protected String creator;

    public GpxType() {
    }

    public GpxType(MetadataType metadata, List<WptType> wpt, List<RteType> rte, List<TrkType> trk, String extensions, String version, String creator) {
        this.metadata = metadata;
        this.wpt = wpt;
        this.rte = rte;
        this.trk = trk;
        this.extensions = extensions;
        this.version = version;
        this.creator = creator;
    }

    public MetadataType getMetadata() {
        if (metadata == null) {
            metadata = new MetadataType();
        }
        return this.metadata;
    }

    public List<WptType> getWpt() {
        if (wpt == null) {
            wpt = new ArrayList<WptType>();
        }
        return this.wpt;
    }
    
    public WptType getLastWpt() {
        return this.wpt.get(wpt.size()-1);
    }
    
    public List<RteType> getRte() {
        if (rte == null) {
            rte = new ArrayList<RteType>();
        }
        return this.rte;
    }

    public RteType getLastRte() {
        return this.rte.get(rte.size()-1);
    }
    
    public List<TrkType> getTrk() {
        if (trk == null) {
            trk = new ArrayList<TrkType>();
        }
        return this.trk;
    }
    
    public TrkType getLastTrk() {
        return this.trk.get(trk.size()-1);
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String value) {
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
    
    public Vector<GeoPoint> getPoints() {
        if (rte != null) {
            return rte.get(0).getPoints();
        }
        else if (trk != null) {
            return trk.get(0).getPoints();
        }
        return null;
    }

    @Override
    public String getName() {
        if (metadata == null) {
            return "";
        }
        return metadata.getName();
    }

    @Override
    public String getDescr() {
        if (metadata == null) {
            return "";
        }
        return metadata.getDesc();
    }

    @Override
    public List<WptType> getPOIs() {
        return wpt;
    }
}
