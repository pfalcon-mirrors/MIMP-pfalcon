package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.BubbleInfoListAdapter;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BubbleInteractionScreen extends Activity implements
		OnItemClickListener, OnClickListener {

	private ListView mListView;
	BubbleInfoListAdapter mBubbleInfoListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.bubbleinfolist);
        
        mListView = (ListView) findViewById(R.id.bubble_info_list);
        mBubbleInfoListAdapter = new BubbleInfoListAdapter(this, false);
        mListView.setAdapter(mBubbleInfoListAdapter);
        mListView.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		System.out.println(">>>>>>>>>>>>>>> " + v);
		switch (v.getId()) {
			
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		System.out.println(">>>>>>>>>>>>>>> " + arg1);
	}
}