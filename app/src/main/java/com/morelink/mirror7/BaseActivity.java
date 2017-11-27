package com.morelink.mirror7;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Arylo on 27/11/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.getInstance().hideSystemNavigationBar(this);
    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation() !=  ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    public void actionClose(View view) {
        Common.getInstance().goActivity(this, MainActivity.class);
    }
}
