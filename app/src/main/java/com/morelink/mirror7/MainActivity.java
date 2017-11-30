package com.morelink.mirror7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        Common.getInstance().goActivity(this, WirelessActivity.class);
        setTypeface((TextView) findViewById(R.id.action_open));
    }

    public void actionOpen(View view) {
        Common.getInstance().goActivity(this);
    }

}
