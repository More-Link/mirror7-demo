package com.morelink.mirror7;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class WirelessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wireless);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WifiManager wm = null;
        wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (!wm.isWifiEnabled()) {
            Toast.makeText(this, "wifi is disabled", Toast.LENGTH_SHORT).show();
        } else {
            List<ScanResult> results = wm.getScanResults();
            for(ScanResult result: results) {
                System.out.println(result.SSID + ' ' + result.level + ' ' + result.capabilities);
            }
        }
    }
}
