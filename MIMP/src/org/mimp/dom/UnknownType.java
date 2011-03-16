package org.mimp.dom;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.mimp.dom.gpx.WptType;
import org.mimp.newimp.GeoPoint;

public class UnknownType implements ParsedObject {

    /**
     * 
     */
    private static final long serialVersionUID = 1583069431579360245L;

    private Vector<GeoPoint> mGeoPoints;
    private List<WptType> mPOIs;
    
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescr() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector<GeoPoint> getPoints() {
        if (mGeoPoints == null) {
            mGeoPoints = new Vector<GeoPoint>();
        }
        return mGeoPoints;
    }

    @Override
    public List<WptType> getPOIs() {
        if (mPOIs == null) {
            mPOIs = new ArrayList<WptType>();
        }
        return mPOIs;
    }

    public void setGeoPoints(Vector<GeoPoint> geoPoints) {
        mGeoPoints = geoPoints;
    }

    public void setGeoPoints(List<GeoPoint> geoPoints) {
        if (mGeoPoints == null) {
            mGeoPoints = new Vector<GeoPoint>();
        }
        if (geoPoints != null) {
            for (GeoPoint point : geoPoints) {
                mGeoPoints.add(point);
            }
        }
    }
    
    public void setPOIs(List<WptType> wayPoints) {
        mPOIs = wayPoints;
    }
}
