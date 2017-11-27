package com.morelink.mirror7;

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
        Common.getIn().hideSystemNavigationBar(this);
    }

    public void actionClose(View view) {
        Common.getIn().goActivity(this, MainActivity.class);
    }
}
