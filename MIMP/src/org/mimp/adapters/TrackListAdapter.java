package org.mimp.adapters;

import java.util.ArrayList;

import org.mimp.R;
import org.mimp.parser.GPXFile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TrackListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<GPXFile> mGpxFiles;
    private TrackListBodyAdapter trackListBodyAdapter;

    public TrackListAdapter(Context context, ArrayList<GPXFile> gpxFiles) {
        mGpxFiles = gpxFiles;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mGpxFiles.size();
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
            convertView = mInflater.inflate(R.layout.tracklistbody, parent);
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

        trackListBodyAdapter.type.setText(mGpxFiles.get(position)
                .getExtention());
        trackListBodyAdapter.title.setText(mGpxFiles.get(position)
                .getGpxObject().getName());
        trackListBodyAdapter.name
                .setText(mGpxFiles.get(position).getFileName());
        trackListBodyAdapter.descr.setText(mGpxFiles.get(position)
                .getGpxObject().getDescr());

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
}
