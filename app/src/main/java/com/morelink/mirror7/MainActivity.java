package com.morelink.mirror7;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        Common.getIn().goActivity(this, WirelessActivity.class);
    }

    public void actionOpen(View view) {
        Common.getIn().goActivity(this);
    }

}