package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.ZoomListAdapter;
import org.mimp.globals.S;
import org.mimp.newimp.Tile;
import org.mimp.newimp.TileFactory;
import org.mimp.newimp.MapProvider;
import org.mimp.newimp.MapProviderFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;

public class TileDownloadScreen extends Activity implements OnClickListener, OnItemSelectedListener {

    private Button mDownloadButton;
    private Spinner mMapChooser;
    private ListView mZoomList;
    private ZoomListAdapter mZoomListAdapter;
    private int[] bounds;
    private boolean mStatuses[];
    private Downloader downloader;
    private ProgressDialog progressDialog;
    private Context mContext = this;
    private MapProvider mMapProvider;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tiledownload);
        
        mMapChooser = (Spinner) findViewById(R.id.tiledownload_mapchooser);
        ArrayAdapter<MapProvider> mapsAdapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item,
          MapProviderFactory.i().getProviders(MapProvider.Type.map));
        mapsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMapChooser.setAdapter(mapsAdapter);
        mMapChooser.setOnItemSelectedListener(this);
        mDownloadButton = (Button) findViewById(R.id.tiledownload_downloadbutton);
        mDownloadButton.setOnClickListener(this);
        mZoomList = (ListView) findViewById(R.id.tiledownload_zoomlist);
        mZoomListAdapter = new ZoomListAdapter(this);
        mZoomList.setAdapter(mZoomListAdapter);
        mStatuses = new boolean[20];
    }

    private void showDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Calculating tiles to download");
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setOnCancelListener(new OnCancelListener() {            
            @Override
            public void onCancel(DialogInterface dialog) {
                downloader.run = false;
                dialog.dismiss();
            }
        });
        progressDialog.show();
    }
    
    public boolean getStatus(int position) {
        return mStatuses[position];
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiledownload_downloadbutton:
                showDialog();
                downloader = new Downloader();
                downloader.start();
                break;
        }
        if (v.getId() >= 0 && v.getId() < 20) {
            mStatuses[v.getId()] = ((CheckBox)v).isChecked();
        }
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
        mMapProvider = (MapProvider)parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        
    }
    
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                progressDialog.hide();
                return;
            }
            Bundle bundle = msg.getData();
            progressDialog.setTitle("Downloading .. Zoom : " + bundle.getInt("zoom"));
            progressDialog.setMax(bundle.getInt("max"));
            progressDialog.setProgress(bundle.getInt("current"));
            progressDialog.setMessage("");
        }
    };
    
    private class Downloader extends Thread {
        
        boolean run = true;
        Message mess;
        Bundle bundle;
        
        public Downloader() {
            bundle = new Bundle();
        }
        
        @Override
        public void run() {
            bounds = getIntent().getExtras().getIntArray("bounds");
            double[] lowerLeft = new double[] {bounds[0]/1E6, bounds[2]/1E6};
            double[] upperRight = new double[] {bounds[1]/1E6, bounds[3]/1E6};
            
            for (int z=0; z < 20 ;z++) {
                if (!mStatuses[z])
                    continue;

                int[] llp = TileFactory.LatLngToPixel(lowerLeft, z);
                llp[0] = llp[0] / 256;
                llp[1] = llp[1] / 256;
                
                int[] urp = TileFactory.LatLngToPixel(upperRight, z);
                urp[0] = urp[0] / 256;
                urp[1] = urp[1] / 256;
                
                int cx = (urp[0] - llp[0] + 1);
                int cy = (llp[1] - urp[1] + 1);
                
                for (int x=0; x < cx ;x++) {
                    for (int y=0; y < cy ;y++) {
                        if (!run)
                            interrupt();
                        /**
                         * Message handling
                         */
                        bundle.putInt("zoom", z);
                        bundle.putInt("current", x*(cy) + y+1);
                        bundle.putInt("max", cx*cy);
                        mess = new Message();
                        mess.setTarget(handler);
                        mess.setData(bundle);
                        handler.sendMessage(mess);
                        
                        /**
                         * download handling
                         */
                        Tile t = new Tile(x+llp[0], y+urp[1], z, mMapProvider);
                        t.save(mMapProvider);
                    }                    
                }
            }
            handler.sendEmptyMessage(-1);
        }
        
        @Override
        public void interrupt() {
            handler.sendEmptyMessage(-1);
            super.interrupt();
        }
    }
}
