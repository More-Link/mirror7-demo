package com.morelink.mirror7;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectivityManager cm = null;
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            this.injectRemoteData();
        }
    }

    public void actionWireless(View view) {
        Common.getIn().goActivity(this, WirelessActivity.class);
    }

    private void injectRemoteData() {
        this.getRemoteData();
    }

    private boolean getRemoteData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://weixin.jirengu.com/weather";
                debug("Start");
                try {
                    debug(get(url));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return false;
    }
    public void debug(String text) {
        Log.d(this.getClass().getSimpleName(), text);
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    private String get(String path) throws Exception {
        String response = null;
        Log.d(this.getClass().getSimpleName(), "url " + path);
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            Log.d(this.getClass().getSimpleName(), "start convert");
            response = convertStreamToString(in);
        } catch (Exception e) {
            Log.e(WeatherActivity.class.getSimpleName(), "Exception: " + e.getMessage());
        } finally {
            return response;
        }
    }
    private String convertStreamToString(InputStream is) {
        Log.d(this.getClass().getSimpleName(), "have convert");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
