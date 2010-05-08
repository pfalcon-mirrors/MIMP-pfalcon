package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.ListButtonAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SettingsScreen extends Activity implements OnItemClickListener {

	/* Menu Items */
	//private static final int MAP = 200000;
	private static final int HELP = 200001;
	
	/* Map style */
	
	/**
	 * Called when the activity is first created.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* Setting up the environment */
        
        setTheme(android.R.style.Theme_Light_NoTitleBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        /*------------------*/
        
        showSettingsScreen();
    }

    /**
     * 
     * Handling the different screen that can be displayed
     * 
     */

    private void showSettingsScreen() {
        setContentView(R.layout.settings);
        ListView lv = (ListView) findViewById(R.id.SettingsList);
        ListButtonAdapter lba = new ListButtonAdapter(this);
        lv.setAdapter(lba);
        lv.setOnItemClickListener(this);
	}
    
	private void showAboutScreen() {
		startActivity(new Intent(SettingsScreen.this, AboutScreen.class));
    }
	
	/**
	 * 
	 * From here following are the key events
	 * 
	 */
	
	/**
	 * List item onItemClick event dispatcher
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
		case 0:
			showAboutScreen();				
			break;
		default:
			break;
		}
	}
	
	/**
	 *  Creates the menu items when physical Menu button is pressed
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, HELP, 0, "").setIcon(android.R.drawable.ic_menu_help);
	    return true;
	}

	/**
	 *  Handle item selections in the above created menu
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
		    case HELP:

		        return true;
	    }
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Handle physical Back button pression
	 */
	public void onBackPressed() {
		System.exit(0);
	}
}