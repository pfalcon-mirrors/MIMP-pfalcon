package org.mimp.adapters;

import org.mimp.R;
import org.mimp.screens.TileDownloadScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ZoomListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ZoomAdapter za;
    
    public ZoomListAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 20;
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
            convertView = mInflater.inflate(R.layout.tiledownloadlist, null);
            za = new ZoomAdapter();
            za.textView = (TextView) convertView
                    .findViewById(R.id.tiledownloadlist_zoom);
            za.checkBox = (CheckBox) convertView
                    .findViewById(R.id.tiledownloadlist_check);
            za.checkBox.setOnClickListener((TileDownloadScreen)mContext);
            convertView.setTag(za);
        }
        else {
            za = (ZoomAdapter) convertView.getTag();
        }
        
        za.textView.setText(""+position);
        za.checkBox.setChecked(((TileDownloadScreen)mContext).getStatus(position));
        za.checkBox.setId(position);
        return convertView;
    }

    private class ZoomAdapter {
        TextView textView;
        CheckBox checkBox;
    }
}
