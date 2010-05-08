package org.mimp.screens;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ResolutionScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

	}
}
