package org.mimp.screens;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class BubbleInteractionWindow extends PopupWindow implements KeyEvent.Callback {

	private Context mContext;
	private View contentView;
	private LayoutInflater mInflater;
	private View mPView;
	
	public BubbleInteractionWindow(Context context, View pView, Rect rect) {
		super(context);
		mContext = context;
		mPView = pView;
		
		mInflater = ((Activity)mContext).getLayoutInflater();
		contentView = mInflater.inflate(org.mimp.R.layout.bubbleinteractions, null);
		super.setContentView(contentView);
				
		setWindowLayoutMode(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		
		final Resources res = mContext.getResources();
		
		setBackgroundDrawable(new ColorDrawable(0));
		setFocusable(true);
		setTouchable(true);
		setOutsideTouchable(true);
	}

	public void show() {
		super.showAtLocation(mPView, Gravity.NO_GRAVITY, -1, -1);	
		this.update(0, 0, mPView.getWidth(), mPView.getHeight());
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			dismiss();
			return true;
		}
		return false;
	}

	public boolean onKeyDown(int arg0, KeyEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
