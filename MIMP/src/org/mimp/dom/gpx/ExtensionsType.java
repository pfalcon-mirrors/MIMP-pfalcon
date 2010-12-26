package org.mimp.dom.gpx;

import java.util.ArrayList;
import java.util.List;

public class ExtensionsType {

    protected List<Object> any;

    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }
}
