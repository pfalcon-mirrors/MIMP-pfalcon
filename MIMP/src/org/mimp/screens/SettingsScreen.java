package org.mimp.screens;

import org.mimp.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Window;

public class SettingsScreen extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        showSettingsScreen();
    }

    private void showSettingsScreen() {
    	addPreferencesFromResource(R.xml.preferences);
	}

	/**
	 * Handle physical Back button pression
	 */
	public void onBackPressed() {
		System.exit(0);
	}
}