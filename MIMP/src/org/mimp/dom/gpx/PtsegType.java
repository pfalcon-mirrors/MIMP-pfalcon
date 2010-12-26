package org.mimp.dom.gpx;

import java.util.ArrayList;
import java.util.List;

public class PtsegType {

    protected List<PtType> pt;

    public PtsegType() {
    }

    public PtsegType(List<PtType> pt) {
        this.pt = pt;
    }

    public List<PtType> getPt() {
        if (pt == null) {
            pt = new ArrayList<PtType>();
        }
        return this.pt;
    }
}
