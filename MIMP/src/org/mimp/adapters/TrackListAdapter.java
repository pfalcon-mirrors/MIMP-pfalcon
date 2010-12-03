package org.mimp.adapters;

import java.util.ArrayList;

import org.mimp.parser.GPXFile;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TrackListAdapter extends BaseAdapter {

	private ArrayList<GPXFile> mGpxFiles;
	
	public TrackListAdapter(ArrayList<GPXFile> gpxFiles) {
		mGpxFiles = gpxFiles;
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

		return null;
	}

	private class TrackListBodyAdapter {
		
	}
}
