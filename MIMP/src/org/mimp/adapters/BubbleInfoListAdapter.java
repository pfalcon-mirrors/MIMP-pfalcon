package org.mimp.adapters;

import org.mimp.R;
import org.mimp.R.array;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BubbleInfoListAdapter extends BaseAdapter {

	private Context mContext;
	private BubbleInfoListHeaderAdapter mHeaderAdapter;
	private BubbleInfoListBodyAdapter mBodyAdapter;
	private LayoutInflater mInflater;
	private boolean mExists;
	
	public BubbleInfoListAdapter(Context context, boolean exists) {
		mContext = context;
		mExists = exists;
	}
	
	@Override
	public int getCount() {
		return 1 + mContext.getResources().getStringArray(R.array.entries_list_bubble_interactions_body).length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		mInflater = LayoutInflater.from(mContext);
		if (position == 0) {
			if (convertView == null)
				convertView = mInflater.inflate(R.layout.bubbleinfolistheader, null);
            
            mHeaderAdapter = new BubbleInfoListHeaderAdapter();
            mHeaderAdapter.pLocation = (TextView) convertView.findViewById(R.id.bubble_interactions_header_location);
            mHeaderAdapter.pDescription = (TextView) convertView.findViewById(R.id.bubble_interactions_header_descr);
            convertView.setId(position);
            convertView.setTag(mHeaderAdapter);
		}
		else {
            if (convertView == null)
            	convertView = mInflater.inflate(R.layout.bubbleinfolistheader, null);

            mBodyAdapter = new BubbleInfoListBodyAdapter();
        	mBodyAdapter.pAction = (TextView) convertView.findViewById(R.id.bubble_interactions_body_share);
            if (mExists)
            	mBodyAdapter.pAction.setText(mBodyAdapter.pActionText[position-1]);
            else
            	mBodyAdapter.pAction.setText(mBodyAdapter.pActionTextNew[position-1]);
            convertView.setId(position);
            convertView.setTag(mBodyAdapter);
		}
		return convertView;
	}
	
	private class BubbleInfoListHeaderAdapter {
		public TextView pLocation;
		public TextView pDescription;
	}

	private class BubbleInfoListBodyAdapter {
        public String[] pActionTextNew = mContext.getResources().getStringArray(R.array.entries_list_bubble_interactions_body_new);
        public String[] pActionText = mContext.getResources().getStringArray(R.array.entries_list_bubble_interactions_body);
		public TextView pAction;
	}
	
}
