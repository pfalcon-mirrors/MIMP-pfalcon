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

    private Context context;

    public ListButtonAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * The number of items in the list is determined by the number of speeches
     * in our array.
     * 
     * @see android.widget.ListAdapter#getCount()
     */
    public int getCount() {
        return mMenus.length;
    }

    /**
     * Since the data comes from an array, just returning the index is sufficent
     * to get at the data. If we were using a more complex data structure, we
     * would return whatever object represents one row in the list.
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
            sl.btv = (TextView) convertView.findViewById(R.id.BigText);
            sl.stv = (TextView) convertView.findViewById(R.id.SmallText);
            convertView.setId(10000 + position);
            convertView.setTag(sl);
        }
        else {
            sl = (SettingsList) convertView.getTag();
        }

        sl.liv.setImageResource(mLeftImages[position]);
        sl.btv.setText(mMenus[position]);
        sl.btv.setTextAppearance(context, android.R.style.TextAppearance_Large);
        sl.stv.setText(mInfos[position]);
        sl.stv.setTextAppearance(context,
                android.R.style.TextAppearance_Small_Inverse);
        return convertView;
    }

    private LayoutInflater mInflater;

    private int[] mLeftImages = { android.R.drawable.ic_menu_call,
            android.R.drawable.ic_menu_view, android.R.drawable.ic_menu_search };
    private int[] mMenus = { R.string.settings_voices,
            R.string.settings_resolution, R.string.settings_search };

    private int[] mInfos = { R.string.settings_summary_voices,
            R.string.settings_summary_resolution,
            R.string.settings_summary_search };

    // private Context mContext;

    static class SettingsList {
        ImageView liv;
        TextView btv;
        TextView stv;
    }
}
