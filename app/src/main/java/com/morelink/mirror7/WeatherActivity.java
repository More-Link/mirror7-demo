package com.morelink.mirror7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherActivity extends BaseActivity {

    private String WEATHER_API_URL = "http://weixin.jirengu.com/weather";
    private TextView vCurDate;
    private TextView vCurWeek;
    private TextView vFea1Week;
    private TextView vFea2Week;
    private TextView vFea3Week;
    private TextView vCurTemp;
    private TextView vFea1Temp;
    private TextView vFea2Temp;
    private TextView vFea3Temp;
    private TextView vFea1Weather;
    private TextView vFea2Weather;
    private TextView vFea3Weather;
    private ImageView vCurWeatherImage;
    private ImageView vFea1WeatherImage;
    private ImageView vFea2WeatherImage;
    private ImageView vFea3WeatherImage;
    private TextView vCurTime;
    private TextView vCurTimeHalf;
    private TextView vCurRoomTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);

        this.vCurDate = this.findViewById(R.id.cur_date);
        this.vCurWeek = this.findViewById(R.id.cur_week);
        this.vFea1Week = this.findViewById(R.id.fea_1_week);
        this.vFea2Week = this.findViewById(R.id.fea_2_week);
        this.vFea3Week = this.findViewById(R.id.fea_3_week);
        this.vCurTemp = this.findViewById(R.id.cur_temp);
        this.vFea1Temp = this.findViewById(R.id.fea_1_temp);
        this.vFea2Temp = this.findViewById(R.id.fea_2_temp);
        this.vFea3Temp = this.findViewById(R.id.fea_3_temp);
        this.vFea1Weather = this.findViewById(R.id.fea_1_weather);
        this.vFea2Weather = this.findViewById(R.id.fea_2_weather);
        this.vFea3Weather = this.findViewById(R.id.fea_3_weather);
        this.vCurWeatherImage = this.findViewById(R.id.cur_weather_image);
        this.vFea1WeatherImage = this.findViewById(R.id.fea_1_weather_image);
        this.vFea2WeatherImage = this.findViewById(R.id.fea_2_weather_image);
        this.vFea3WeatherImage = this.findViewById(R.id.fea_3_weather_image);

        this.vCurTime = this.findViewById(R.id.cur_time);
        this.vCurTimeHalf = this.findViewById(R.id.cur_time_half);
        this.vCurRoomTemp = this.findViewById(R.id.cur_room_temp);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                timeHandler.sendEmptyMessage(0);
            }
        }, 0,1000 * 5);

        setTypeface((TextView) findViewById(R.id.action_close));
        setTypeface((TextView) findViewById(R.id.action_lightup));
        setTypeface((TextView) findViewById(R.id.action_lightdown));
        setTypeface((TextView) findViewById(R.id.action_wifi));
        setTypeface((TextView) findViewById(R.id.action_music));
    }

    private Handler wifiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.d("mirror7", "111111111111");
                    injectRemoteData();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        WifiStateChangedBroadcastReceiver.setHandler(wifiHandler);

        ConnectivityManager cm = null;
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    injectRemoteData();
                }
            }, 0,1000 * 60 * 10 /* 10 min */);
        }
        timeHandler.sendEmptyMessage(0);
        // TODO Room Temp
    }

    private void renderTime() {
        String hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "";
        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 10) {
            hour = "0" + hour;
        }
        String min = Calendar.getInstance().get(Calendar.MINUTE) + "";
        if (Calendar.getInstance().get(Calendar.MINUTE) < 10) {
            min = "0" + min;
        }
        vCurTime.setText(hour + ":" + min);
        vCurTimeHalf.setText(
                Calendar.getInstance().get(Calendar.AM_PM) == 0 ? "AM" : "PM"
        );
    }

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            renderTime();
        }
    };

    public void actionWireless(View view) {
        Common.getInstance().goActivity(this, WirelessActivity.class);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            findViewById(R.id.layout_future).setVisibility(View.VISIBLE);
            findViewById(R.id.layout_future_divider).setVisibility(View.VISIBLE);
            findViewById(R.id.cur_temp).setVisibility(View.VISIBLE);
            // Current Date
            try {
                JSONObject curObj = resource.getJSONArray("future").getJSONObject(0);
                vCurDate.setText(curObj.getString("date"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Weeks
            TextView[] tvsWeek = { vCurWeek, vFea1Week, vFea2Week, vFea3Week };
            try {
                for (int i = 0; i < tvsWeek.length; i++) {
                    JSONObject obj = resource.getJSONArray("future").getJSONObject(i);
                    tvsWeek[i].setText(obj.getString("day"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Temps
            TextView[] tvsTemp = { vCurTemp, vFea1Temp, vFea2Temp, vFea3Temp };
            try {
                for (int i = 0; i < tvsTemp.length; i++) {
                    JSONObject obj = resource.getJSONArray("future").getJSONObject(i);
                    String str = obj.getString("low") + "~" + obj.getString("high") + "℃";
                    tvsTemp[i].setText(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Weather
            TextView[] tvsWeather = { vFea1Weather, vFea2Weather, vFea3Weather };
            try {
                for (int i = 0; i < tvsWeather.length; i++) {
                    JSONObject obj = resource.getJSONArray("future").getJSONObject(i + 1);
                    String str = obj.getString("text").replaceAll("\\/.+", "");
                    tvsWeather[i].setText(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageView[] ivWeather = { vCurWeatherImage, vFea1WeatherImage, vFea2WeatherImage, vFea3WeatherImage };
            try {
                for (int i = 0; i < ivWeather.length; i++) {
                    JSONObject obj = resource.getJSONArray("future").getJSONObject(i + 1);
                    String url = "http://weixin.jirengu.com/images/weather/code/" + obj.getInt("code1") + ".png";
                    ivWeather[i].setImageBitmap(Common.getInstance().getHttpBitmap(url));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private JSONObject resource = null;
    private void injectRemoteData() {
        if (this.resource == null) {
            this.getRemoteData();
            return;
        }
        mHandler.sendEmptyMessage(0);
    }

    private Thread getRemoteDateThread = null;
    private boolean getRemoteData() {
        if (this.getRemoteDateThread != null) {
            return false;
        }
        if (this.resource != null) {
            String last_update = null;
            try {
                last_update = this.resource.getString("last_update");
                Date date = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ").parse(last_update);
                Date nowDate = new Date(new Date().getTime() - 30 * 60 * 1000);
                if (date.after(nowDate)) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.getRemoteDateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(this.getClass().getSimpleName(), "start " + WEATHER_API_URL);
                    String res = Common.getInstance().get(WEATHER_API_URL);
                    if (new JSONObject(res).getString("status").equals("OK")) {
                        JSONObject object = new JSONObject(res);
                        resource = object.getJSONArray("weather").getJSONObject(0);
                        injectRemoteData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    getRemoteDateThread = null;
                }
            }
        });
        getRemoteDateThread.start();
        return true;
    }

}
