package org.mimp.newimp;

public class MapProvider {
    private String mShortName; // Used for dir names for example
    private String mDisplayName;
    private String mUrlPattern;
    private boolean mCacheable = true;

    public MapProvider(String shortName, String displayName, String urlPattern) {
        mShortName = shortName;
        mDisplayName = displayName;
        mUrlPattern = urlPattern;
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
}
