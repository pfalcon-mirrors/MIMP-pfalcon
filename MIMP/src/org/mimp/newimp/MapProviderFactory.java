package org.mimp.newimp;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.preference.ListPreference;
import android.util.Log;

import org.mimp.globals.S;


public class MapProviderFactory {

    private static final String TAG = "MapProviderFactory";

    private static final MapProvider MAPS_ARRAY[] = new MapProvider[] {
        new MapProvider(MapProvider.Type.map, "osm", "Open Street Map",
          "http://tile.openstreetmap.org/{z}/{x}/{y}.png", true),
        new MapProvider(MapProvider.Type.map, "opencyclemap", "Open Cycle Map",
          "http://a.tile.opencyclemap.org/cycle/{z}/{x}/{y}.png", true),
        new MapProvider(MapProvider.Type.map, "cloudmade", "Cloud Made",
          "http://a.tile.cloudmade.com/333d990d389d5e65a7714dd738b2fc77/1/256/{z}/{x}/{y}.png", true),
    };

    private static final MapProvider MAP_OVERLAYS_ARRAY[] = new MapProvider[] {
        new MapProvider(MapProvider.Type.overlay, "", "None", "", false),
    };

    public static final String DEFAULT_MAP = "osm";

    private List<MapProvider> mMapProviders;
    private List<MapProvider> mOverlayProviders;

    private static MapProviderFactory mInstance;


    protected MapProviderFactory() {
    }

    public static MapProviderFactory i() {
        if (mInstance == null) {
            mInstance = new MapProviderFactory();
            mInstance.rescan();
        }
        return mInstance;
    }

    public void rescan() {
        mMapProviders = new ArrayList<MapProvider>();
        mOverlayProviders = new ArrayList<MapProvider>();
        mMapProviders.addAll(Arrays.asList(MAPS_ARRAY));
        mOverlayProviders.addAll(Arrays.asList(MAP_OVERLAYS_ARRAY));
        mInstance.loadProviders(new File(Environment.getExternalStorageDirectory(), S.APP_SUBDIR));
    }

    public static MapProvider getByShortName(Collection<MapProvider> providers, String name) {
        for (MapProvider p : providers) {
            if (name.equals(p.getShortName())) {
                return p;
            }
        }
        return null;
    }

    public List<MapProvider> getProviders(MapProvider.Type providerType) {
        if (providerType == MapProvider.Type.map) {
            return mMapProviders;
        } else if (providerType == MapProvider.Type.overlay) {
            return mOverlayProviders;
        }
        return null;
    }

    public MapProvider getCurrentProvider(Context context, MapProvider.Type providerType) {
        Collection<MapProvider> providers = getProviders(providerType);
        String prefName = null, defaultValue = null;
        if (providerType == MapProvider.Type.map) {
            prefName = "map_provider_name";
            defaultValue = DEFAULT_MAP;
        } else if (providerType == MapProvider.Type.overlay) {
            prefName = "map_overlay_provider_name";
            defaultValue = "";
        }
        String shortName = context.getSharedPreferences(S.PREFS_NAME, 0).getString(prefName, defaultValue);
        return getByShortName(providers, shortName);
    }

    public void setListPreferenceEntries(ListPreference pref, MapProvider.Type providerType) {
        List<MapProvider> providers = getProviders(providerType);

        String entries[] = new String[providers.size()];
        String entryValues[] = new String[providers.size()];
        for (int i = 0; i < providers.size(); i++) {
            entries[i] = providers.get(i).getDisplayName();
            entryValues[i] = providers.get(i).getShortName();
        }
        pref.setEntries(entries);
        pref.setEntryValues(entryValues);
    }

    class MapFileFilter implements FileFilter {
        public boolean accept(File file) {
            if (!file.isFile()) {
                return false;
            }
            if (!file.getName().endsWith(".txt")) {
                return false;
            }
            return true;
        }
    }

    public void loadProviders(File dir) {
        for (File f : dir.listFiles(new MapFileFilter())) {
            MapProvider mp = MapProvider.fromFile(f);
            if (mp != null) {
                Log.i(TAG, "Loading map file: " + f);
                if (mp.getType() == MapProvider.Type.map) {
                    mMapProviders.add(mp);
                } else if (mp.getType() == MapProvider.Type.overlay) {
                    mOverlayProviders.add(mp);
                }
            }
        }
    }
}
