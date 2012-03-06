package org.mimp.newimp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import android.util.Log;


public class MapProvider {

    private static final String TAG = "MapProvider";

    public enum Type {map, overlay};

    private Type mType;
    private String mShortName; // Used for dir names for example
    private String mDisplayName;
    private String mUrlPattern;
    private boolean mCacheable = true;

    public MapProvider(Type type, String shortName, String displayName, String urlPattern, boolean cacheable) {
        mType = type;
        mShortName = shortName;
        mDisplayName = displayName;
        mUrlPattern = urlPattern;
        mCacheable = cacheable;
    }

    public Type getType() {
        return mType;
    }

    public String getShortName() {
        return mShortName;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public boolean isCacheable() {
        return mCacheable;
    }

    public boolean isEmpty() {
        return "".equals(mShortName);
    }

    public String getUrl(int x, int y, int z) {
        String url = mUrlPattern.replace("{x}", Integer.toString(x));
        url = url.replace("{y}", Integer.toString(y));
        url = url.replace("{z}", Integer.toString(z));
        return url;
    }

    public String toString() {
        return mDisplayName;
    }

    public static MapProvider fromFile(File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            Type type = Type.map;
            String shortName = null, displayName = null, urlPattern = null;
            boolean cacheable = true;
            String l;
            while ((l = in.readLine()) != null) {
                String parts[] = l.split("=", 2);
                String key = parts[0].trim();
                String val = parts[1].trim();
                if ("shortname".equals(key)) {
                    shortName = val;
                } else if ("name".equals(key)) {
                    displayName = val;
                } else if ("url".equals(key)) {
                    urlPattern = val;
                } else if ("cache".equals(key)) {
                    cacheable = Boolean.valueOf(val);
                } else if ("type".equals(key)) {
                    type = Type.valueOf(val);
                }
            }
            if (shortName != null && displayName != null && urlPattern != null) {
                return new MapProvider(type, shortName, displayName, urlPattern, cacheable);
            } else {
                Log.e(TAG, file + ": missing mandatory parameters, skipping");
                return null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception while parsing map file", e);
            return null;
        }
    }
}
