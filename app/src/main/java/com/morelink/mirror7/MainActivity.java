package com.morelink.mirror7;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        WifiUtil wu = new WifiUtil((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE));
        wu.openWifi();

        setTypeface((TextView) findViewById(R.id.action_open));
        // 启动天气服务
        Intent intent = new Intent(this, WeatherService.class);
        startService(intent);

        Common.getInstance().goActivity(this, WeatherActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void actionOpen(View view) {
        Common.getInstance().goActivity(this);
    }

}
