package org.mimp.screens;

import org.mimp.R;
import org.mimp.dialogs.ColorPickerDialog;
import org.mimp.dialogs.ColorPickerDialog.OnColorChangedListener;
import org.mimp.globals.S;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.Window;

public class SettingsScreen extends PreferenceActivity implements OnPreferenceClickListener, OnColorChangedListener {

    private ColorPickerDialog colorPickerDialog;
    private int innerColor = 0;
    private int outerColor = 0;
    private Preference innerPref;
    private Preference outerPref;
    private int baseColor = Color.argb(200, 100, 170, 240);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(S.PREFS_NAME);
        showSettingsScreen();
    }

    private void showSettingsScreen() {
        addPreferencesFromResource(R.xml.preferences);
        innerPref = findPreference("track_inner_color");
        outerPref = findPreference("track_outer_color");
        innerPref.setOnPreferenceClickListener(this);
        outerPref.setOnPreferenceClickListener(this);
    }
    
    /**
     * Handle physical Back button pression
     */
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onPreferenceClick(Preference arg0) {
        getColors();
        if (arg0.getKey().equals("track_inner_color")) {
           colorPickerDialog = new ColorPickerDialog(this, this, arg0.getKey(), innerColor, baseColor );
        }
        if (arg0.getKey().equals("track_outer_color")) {
           colorPickerDialog = new ColorPickerDialog(this, this, arg0.getKey(), outerColor, baseColor );
        }
        colorPickerDialog.show();
        return true;
    }

    @Override
    public void colorChanged(String key, int color) {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        if (key.equals("track_inner_color")) {
            editor.putInt("inner_color", color);            
        }
        if (key.equals("track_outer_color")) {
            editor.putInt("outer_color", color);            
        }        
        editor.commit();
    }
    
    public void getColors() {
        SharedPreferences settings = getSharedPreferences(S.PREFS_NAME, 0);
        innerColor = settings.getInt("inner_color", -932926736);
        outerColor = settings.getInt("outer_color", -932926736);
    }
}