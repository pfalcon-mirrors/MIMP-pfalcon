package org.mimp.globals;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author hellhand
 * 
 */

public class S {

    /**
     * preferences filename
     */
    
    public static final String PREFS_NAME = "Preferences";
    
    /**
     * MENU
     */

    public static final int MAP = 200000;
    public static final int INFO = 200001;
    public static final int SET = 200002;
    public static final int POS = 200003;
    public static final int COMPASS = 200004;
    public static final int MODE = 200005;
    public static final int SEARCH = 200006;

    /**
     * map providers list
     */
    public static final int GOOGLE_PROVIDER = 0;
    public static final int OSM_PROVIDER = 1;

    /**
     * database connection
     */

    public static final String DATABASE_NAME = "gpsdata.db";
    public static final int DATABASE_VERSION = 1;
    public static final String AUTHORITY = "org.mapping";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/gpspoint");
}
