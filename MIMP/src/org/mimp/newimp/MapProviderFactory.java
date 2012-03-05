package org.mimp.newimp;

import android.content.Context;
import android.preference.ListPreference;
import org.mimp.globals.S;


public class MapProviderFactory {

    private static final MapProvider MAPS_ARRAY[] = new MapProvider[] {
        new MapProvider("osm", "Open Street Map", "http://tile.openstreetmap.org/{z}/{x}/{y}.png"),
        new MapProvider("opencyclemap", "Open Cycle Map", "http://a.tile.opencyclemap.org/cycle/{z}/{x}/{y}.png"),
        new MapProvider("cloudmade", "Cloud Made",
                        "http://a.tile.cloudmade.com/333d990d389d5e65a7714dd738b2fc77/1/256/{z}/{x}/{y}.png"),
    };

    private static final MapProvider MAP_OVERLAYS_ARRAY[] = new MapProvider[] {
        new MapProvider("", "None", ""),
    };

    public static final String DEFAULT_MAP = "osm";
    public enum ProviderType {MAPS, MAP_OVERLAYS};


    public static MapProvider getByShortName(MapProvider[] providers, String name) {
        for (MapProvider p : providers) {
            if (name.equals(p.getShortName())) {
                return p;
            }
        }
        return null;
    }

    private static MapProvider[] getProvidersArray(ProviderType providerType) {
        if (providerType == ProviderType.MAPS) {
            return MAPS_ARRAY;
        } else if (providerType == ProviderType.MAP_OVERLAYS) {
            return MAP_OVERLAYS_ARRAY;
        }
        return null;
    }

    public static MapProvider getCurrentProvider(Context context, ProviderType providerType) {
        MapProvider[] providers = getProvidersArray(providerType);
        String prefName = null, defaultValue = null;
        if (providerType == ProviderType.MAPS) {
            prefName = "map_provider_name";
            defaultValue = DEFAULT_MAP;
        } else if (providerType == ProviderType.MAP_OVERLAYS) {
            prefName = "map_overlay_provider_name";
            defaultValue = "";
        }
        String shortName = context.getSharedPreferences(S.PREFS_NAME, 0).getString(prefName, defaultValue);
        return getByShortName(providers, shortName);
    }

    public static void setListPreferenceEntries(ListPreference pref, ProviderType providerType) {
        MapProvider[] providers = getProvidersArray(providerType);

        String entries[] = new String[providers.length];
        String entryValues[] = new String[providers.length];
        for (int i = 0; i < providers.length; i++) {
            entries[i] = providers[i].getDisplayName();
            entryValues[i] = providers[i].getShortName();
        }
        pref.setEntries(entries);
        pref.setEntryValues(entryValues);
    }

}
