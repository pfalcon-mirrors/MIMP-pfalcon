package org.mimp.adapters;

import org.mimp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListButtonAdapter extends BaseAdapter {
	
    public ListButtonAdapter(Context context) {
    	mInflater = LayoutInflater.from(context);
    	//mContext = context;
	}
    
	/**
     * The number of items in the list is determined by the number of speeches
     * in our array.
     * 
     * @see android.widget.ListAdapter#getCount()
     */
    public int getCount() {
        return mStrings.length;
    }

    /**
     * Since the data comes from an array, just returning the index is
     * sufficent to get at the data. If we were using a more complex data
     * structure, we would return whatever object represents one row in the
     * list.
     * 
     * @see android.widget.ListAdapter#getItem(int)
     */
    public Object getItem(int position) {
        return position;
    }

    /**
     * Use the array index as a unique id.
     * 
     * @see android.widget.ListAdapter#getItemId(int)
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Make a SpeechView to hold each row.
     * 
     * @see android.widget.ListAdapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
    	SettingsList sl;
		
        if (convertView == null) {
        	convertView = mInflater.inflate(R.layout.imagebuttonlist, null);
        	sl = new SettingsList();
        	sl.liv = (ImageView) convertView.findViewById(R.id.LineLeftImage);
        	sl.tv = (TextView) convertView.findViewById(R.id.LineText);
        	sl.riv = (ImageView) convertView.findViewById(R.id.LineRightImage);
        	convertView.setId(10000+position);
        	convertView.setTag(sl);
        }
        else {
            sl = (SettingsList) convertView.getTag();
        }
		
        sl.liv.setImageResource(mLeftImages[position]);
        sl.tv.setText(mStrings[position]);
        sl.riv.setImageResource(arrow);
        
        return convertView;
    }
	
    private LayoutInflater mInflater;

    private int[] mLeftImages = {
    		android.R.drawable.ic_menu_info_details,
    		android.R.drawable.ic_menu_call,
    		android.R.drawable.ic_menu_view,
    		android.R.drawable.ic_menu_search,
    };
    private int[] mStrings = {
    		R.string.info,
    		R.string.voices,
    		R.string.resolution,
    		R.string.search,
    };
    
    private int arrow = android.R.drawable.ic_menu_more;
    
    //private Context mContext;
    
    static class SettingsList {
    	ImageView liv;
    	TextView tv;
    	ImageView riv;
    }
}
