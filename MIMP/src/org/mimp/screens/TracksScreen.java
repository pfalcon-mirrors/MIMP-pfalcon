package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.TrackListAdapter;
import org.mimp.globals.S;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TracksScreen extends Activity implements OnItemClickListener {

    private ListView mListView;
    private TrackListAdapter mTrackListAdapter;

    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tracklist);
        mTrackListAdapter = new TrackListAdapter(this, null);
        mListView = (ListView) findViewById(R.id.track_list);
        mListView.setOnItemClickListener(this);
        mListView.setAdapter(mTrackListAdapter);
        fetchFiles();
    }

    /*****************************************************************************
     * 
     * Key controls and menu handling
     * 
     *****************************************************************************/

    /**
     * Creates the menu items when Menu button is pressed
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, S.POS, 0, R.string.map_menu_position).setIcon(
                android.R.drawable.ic_menu_mylocation);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

    }

    /*****************************************************************************
     * 
     * Files
     * 
     *****************************************************************************/

    public void fetchFiles() {

    }
}
