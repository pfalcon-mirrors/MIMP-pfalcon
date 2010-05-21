package org.mimp.globals;

import android.net.Uri;
import android.widget.EditText;
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
     * windows sweet sweet numbers
     */

    public static final int MAP = 100;
    public static final int SETTINGS = 101;
    public static final int ABOUT = 102;
    
    /**
     * settings list
     */

    public static final int M_MAP = 200000;
    public static final int M_HELP = 200001;
    public static final int M_DL = 200002;
    public static final int M_SHOW = 200003;
    public static final int M_CART = 200004;
    public static final int M_ARROW = 200005;
    public static final int M_SMTH = 200006;

    public static final int RIGHT = 800;
    public static final int MID = 801;
    public static final int LEFT = 802;

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

    public static class ElementsList {
        public ImageView type;
        public ImageView star1;
        public ImageView star2;
        public ImageView star3;
        public ImageView star4;
        public ImageView star5;
        public TextView trackname;
        public TextView time;
        public TextView distance;
        public TextView lenght;
        public ImageView bar1;
        public ImageView bar2;
        public ImageView bar3;
        public ImageView bar4;
        public ImageView bar5;
        public EditText descr;
    }

    public static class SettingsList {
        public ImageView liv;
        public TextView tv;
        public ImageView riv;
    }
}
