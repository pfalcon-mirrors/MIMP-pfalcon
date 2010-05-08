package org.mimp.screens;


import org.mimp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AboutScreen extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about);
    }
}