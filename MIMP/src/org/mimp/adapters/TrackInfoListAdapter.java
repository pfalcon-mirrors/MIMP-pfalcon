package org.mimp.adapters;

import org.mimp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class TrackInfoListAdapter extends BaseAdapter {

    private Context mContext;
    private TrackDetailsListHeaderAdapter mHeaderAdapter;
    private TrackDetailsListBodyAdapter mBodyAdapter;
    private LayoutInflater mInflater;
    private String[] info;

    public TrackInfoListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        info = ((Activity) mContext).getIntent().getExtras().getStringArray("infos");
    }

    @Override
    public int getCount() {
        String[] pInfoText = mContext.getResources().getStringArray(
                R.array.entries_list_track_interactions_body_info);
        return pInfoText.length + 1; // +1 for header
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (position == 0) {
                convertView = mInflater.inflate(R.layout.trackinfolistheader,
                        null);
                mHeaderAdapter = new TrackDetailsListHeaderAdapter();
                mHeaderAdapter.pName = (TextView) convertView
                        .findViewById(R.id.track_interactions_header_name);
                mHeaderAdapter.pDescription = (TextView) convertView
                        .findViewById(R.id.track_interactions_header_descr);
                mHeaderAdapter.pMapButton = (ImageButton) convertView
                        .findViewById(R.id.track_interactions_header_map);

                mHeaderAdapter.pName.setText(info[0]);
                mHeaderAdapter.pDescription.setText(info[1]);
                mHeaderAdapter.pMapButton
                        .setOnClickListener((OnClickListener) mContext);
                convertView.setTag(mHeaderAdapter);
            }
            else {
                convertView = mInflater.inflate(R.layout.trackinfolistbody,
                        null);
                mBodyAdapter = new TrackDetailsListBodyAdapter();
                mBodyAdapter.pInfoTextView = (TextView) convertView
                        .findViewById(R.id.track_interactions_body_info);
                mBodyAdapter.pDescrTextView = (TextView) convertView
                        .findViewById(R.id.track_interactions_body_descr);

                mBodyAdapter.pInfoTextView
                        .setText(mBodyAdapter.pInfoText[position - 1]);
                mBodyAdapter.pDescrTextView
                        .setText(mBodyAdapter.pDescrText[position - 1]);
                convertView.setTag(mBodyAdapter);
            }
            convertView.setId(position);
        }
        else {
            convertView = parent.findViewById(position);
        }
        return convertView;
    }

    private class TrackDetailsListHeaderAdapter {
        public TextView pName;
        public TextView pDescription;
        public ImageButton pMapButton;
    }

    private class TrackDetailsListBodyAdapter {
        public String[] pInfoText = mContext.getResources().getStringArray(
                R.array.entries_list_track_interactions_body_info);
        public String[] pDescrText = mContext.getResources().getStringArray(
                R.array.entries_list_track_interactions_body_descr);
        public TextView pInfoTextView;
        public TextView pDescrTextView;
    }
}
