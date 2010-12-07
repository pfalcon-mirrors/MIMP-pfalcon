package org.mimp.adapters;

import java.util.ArrayList;

import org.mimp.R;
import org.mimp.parser.GPXFile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TrackListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<GPXFile> mGpxFiles;
	
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
			
		}
		return null;
	}

	private class TrackListBodyAdapter {
		
	}
}
