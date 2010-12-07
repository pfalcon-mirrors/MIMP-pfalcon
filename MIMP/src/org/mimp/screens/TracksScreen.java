package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.TrackListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TracksScreen extends Activity implements OnItemClickListener {

	ListView mListView;
	TrackListAdapter mTrackListAdapter;
	
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
	}
	
	public void onBackPressed() {
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
