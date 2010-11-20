package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.BubbleInfoListAdapter;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;

public class BubbleInteractionScreen extends Activity implements OnClickListener {

	private ListView mListView;
	private LayoutInflater mInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        setContentView(R.layout.map);
        mInflater = LayoutInflater.from(this);
        
        mListView = (ListView) mInflater.inflate(R.layout.bubbleinfolistheader, null);
        mListView.setAdapter(new BubbleInfoListAdapter(this, false));
	}

	@Override
	public void onClick(View v) {
		System.out.println(v);
		switch (v.getId()) {
			
		}
	}
}