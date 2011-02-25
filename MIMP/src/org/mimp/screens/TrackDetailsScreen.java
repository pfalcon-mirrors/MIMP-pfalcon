package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.TrackInfoListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TrackDetailsScreen extends Activity implements
        OnItemClickListener, OnClickListener {

    private ListView mListView;
    private TrackInfoListAdapter mTrackDetailsListAdapter;
    private int[] bounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bubbleinfolist);

        mListView = (ListView) findViewById(R.id.bubble_info_list);
        mListView.setOnItemClickListener(this);
        mTrackDetailsListAdapter = new TrackInfoListAdapter(this);
        mListView.setAdapter(mTrackDetailsListAdapter);
        bounds = getIntent().getExtras().getIntArray("bounds");
    }

    /*****************************************************************************
     * 
     * Key controls and menu handling
     * 
     *****************************************************************************/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bubble_interactions_header_map:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        switch (arg2) {
            case 1:
                dowloadTiles();
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    public void onBackPressed() {
        finish();
    }

    private void dowloadTiles() {
        Intent dowloaderIntent = new Intent(TrackDetailsScreen.this,
                TileDownloadScreen.class);
        dowloaderIntent.putExtra("bounds", bounds);
        startActivity(dowloaderIntent);
    }
}
