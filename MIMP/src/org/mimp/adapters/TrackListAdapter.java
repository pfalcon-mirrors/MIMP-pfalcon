package org.mimp.adapters;

import java.util.ArrayList;

import org.mimp.R;
import org.mimp.parser.ParsedFile;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TrackListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<ParsedFile> mParsedFiles;
    private TrackListBodyAdapter trackListBodyAdapter;

    public TrackListAdapter(Context context, ArrayList<ParsedFile> parsedFiles) {
        mParsedFiles = parsedFiles;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    
    public void addTrack(ParsedFile parsedFile) {
        if (mParsedFiles == null) 
            mParsedFiles = new ArrayList<ParsedFile>();
        mParsedFiles.add(parsedFile);
        handler.sendEmptyMessage(0);
        System.out.println(">>>>>>>>>>>>>>>> " + mParsedFiles);
    }

    public void clear() {
        mParsedFiles = null;
    }
    
    @Override
    public int getCount() {
        if (mParsedFiles == null)
            return 0;
        return mParsedFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tracklistbody, null);
            trackListBodyAdapter = new TrackListBodyAdapter();
            trackListBodyAdapter.type = (TextView) convertView
                    .findViewById(R.id.track_list_body_type);
            trackListBodyAdapter.title = (TextView) convertView
                    .findViewById(R.id.track_list_body_title);
            trackListBodyAdapter.name = (TextView) convertView
                    .findViewById(R.id.track_list_body_name);
            trackListBodyAdapter.descr = (TextView) convertView
                    .findViewById(R.id.track_list_body_descr);
        }

        trackListBodyAdapter.type.setText(mParsedFiles.get(position)
                .getExtention());
        trackListBodyAdapter.title.setText(mParsedFiles.get(position)
                .getParsedObject().getName());
        trackListBodyAdapter.name
                .setText(mParsedFiles.get(position).getFileName());
        trackListBodyAdapter.descr.setText(mParsedFiles.get(position)
                .getParsedObject().getDescr());

        convertView.setId(position);
        convertView.setTag(trackListBodyAdapter);
        return convertView;
    }

    private class TrackListBodyAdapter {
        public TextView type;
        public TextView title;
        public TextView name;
        public TextView descr;
    }
    
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            notifyDataSetInvalidated();
        }
    };
}
