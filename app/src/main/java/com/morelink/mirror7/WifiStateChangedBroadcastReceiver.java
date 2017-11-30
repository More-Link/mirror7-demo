package com.morelink.mirror7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public final class WifiStateChangedBroadcastReceiver extends BroadcastReceiver {

    private static Handler mHandler = null;

    public static void setHandler(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if(info.getState().equals(NetworkInfo.State.DISCONNECTED)){
                Log.d("Tag", "--- WIFI状态：Disconnect ---");
                if (mHandler != null) mHandler.sendEmptyMessage(1);
            } else if(info.getState().equals(NetworkInfo.State.CONNECTED)){
                Log.d("Tag", "--- WIFI状态：Connect ---");
                if (mHandler != null) mHandler.sendEmptyMessage(0);
            }
        }

        if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);

            if (wifistate == WifiManager.WIFI_STATE_DISABLED) {
                if (mHandler != null) mHandler.sendEmptyMessage(10);
            }

            if (wifistate == WifiManager.WIFI_STATE_ENABLED) {
                if (mHandler != null) mHandler.sendEmptyMessage(11);
            }
        }
    }
}