package org.mimp.globals;

import android.net.Uri;

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
    public static final int LOADTRKFILE = 200007;
    public static final int CLEAR = 200008;

    /**
     * RequestCodes
     */

    public static final int BubbleInteractionScreen_RQC = 1;
    public static final int TracksScreen_RQC = 2;

    /**
     * ResultCodes = RequestCode + 100 to RequestCode + 199
     */

    public static final int BubbleInteractionScreen_DIRECTIONS = 100;
    public static final int BubbleInteractionScreen_WAYPOINT = 101;
    public static final int TracksScreen_LOADTRACK = 200;
    
    
    /**
     * database connection
     */

    public static final String DATABASE_NAME = "gpsdata.db";
    public static final int DATABASE_VERSION = 1;
    public static final String AUTHORITY = "org.mimp";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/gpspoint");
}
