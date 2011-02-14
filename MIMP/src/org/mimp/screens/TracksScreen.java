package org.mimp.screens;

import java.io.File;

import org.mimp.R;
import org.mimp.adapters.TrackListAdapter;
import org.mimp.dom.ParsedFile;
import org.mimp.dom.ParsedFileFactory;
import org.mimp.globals.S;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
    private File mExtFolder = Environment.getExternalStorageDirectory();
    private File mBaseFolder;
    private File[] files;
    private Thread reader;

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
        
        String path = mExtFolder.getAbsolutePath() + File.separator + "MIMP" + File.separator + "Tracks" + File.separator;
        mBaseFolder = new File(path);
        mBaseFolder.mkdirs();
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
        menu.add(0, S.POS, 0, R.string.tracks_menu_refresh).setIcon(
                android.R.drawable.ic_menu_search);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        mTrackListAdapter.clear();
        fetchFiles();
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        finish();
    }

    //TODO: return ParsedObject and not File
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent();
        intent.putExtra("file", files[arg2]);
        if (reader != null) {
            reader.interrupt();
        }
        setResult(S.TracksScreen_LOADTRACK, intent);
        finish();
    }

    /*****************************************************************************
     * 
     * Files Threaded Method
     * 
     *****************************************************************************/

    public void fetchFiles() {
        files = mBaseFolder.listFiles();
        reader = new Thread(new Runnable() {
            @Override
            public void run() {
                ParsedFile parsedFile;
                for (int i = 0; i < files.length; i++) {
                    try {
                        parsedFile = ParsedFileFactory.getLightParsedFile(files[i]);
                        if (parsedFile != null)
                            mTrackListAdapter.addTrack(parsedFile);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        reader.start();
    }
}
