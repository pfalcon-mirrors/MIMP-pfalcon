package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.BubbleInfoListAdapter;
import org.mimp.globals.S;

import android.app.Activity;
import android.content.Intent;
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
    private BubbleInfoListAdapter mBubbleInfoListAdapter;
    private String[] mAddress;
    private int[] mCoords;
    private Intent emailIntent;
    private String mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bubbleinfolist);

        mListView = (ListView) findViewById(R.id.bubble_info_list);
        mListView.setOnItemClickListener(this);
        mBubbleInfoListAdapter = new BubbleInfoListAdapter(this, false);
        mAddress = getIntent().getStringArrayExtra("address");
        mCoords = getIntent().getIntArrayExtra("coords");
        mListView.setAdapter(mBubbleInfoListAdapter);
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
            case R.id.bubble_interactions_header_directions:
                getDirections();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        System.out.println(">>>>>>>>>>>>>>> " + arg1);
        switch (arg2) {
            case 1:
                addWaypoint();
                break;
            case 2:
                sendEmail();
                break;
            default:
                break;
        }
    }

    public void onBackPressed() {
        finish();
    }

    public void getDirections() {
        Intent intent = new Intent();
        intent.putExtra("coords", mCoords);
        setResult(S.BubbleInteractionScreen_DIRECTIONS, intent);
        finish();
    }

    public void addWaypoint() {
        Intent intent = new Intent();
        intent.putExtra("coords", mCoords);
        setResult(S.BubbleInteractionScreen_WAYPOINT, intent);
        finish();
    }

    private void sendEmail() {
        mMessage = "Hey check this location : ";
        for (int i = 0; i < mAddress.length; i++) {
            mMessage += mAddress[i] + ", ";
        }
        mMessage += "\n";
        mMessage += "http://maps.google.com/maps?q=" + mCoords[0] / 1E6 + ","
                + mCoords[1] / 1E6;
        emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[] { "" });
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Location");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mMessage);
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}