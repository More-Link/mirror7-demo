package com.morelink.mirror7;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherActivity extends BaseActivity {

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
    private ImageView vFuture1WeatherImage;
    private ImageView vFuture2WeatherImage;
    private ImageView vFuture3WeatherImage;
    private TextView vCurTime;
    private TextView vCurTimeHalf;
    private TextView vCurRoomTemp;

    private WeatherBinder binder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (WeatherBinder) iBinder;
            binder.setHandler(weatherHandler);
            binder.downloadWeatherInfo();
            if (binder.getWeathers() != null) {
                Message msg = new Message();
                msg.obj = binder.getWeathers();
                weatherHandler.sendMessage(msg);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    private void bindViewByIds() {
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
        this.vFuture1WeatherImage = this.findViewById(R.id.fea_1_weather_image);
        this.vFuture2WeatherImage = this.findViewById(R.id.fea_2_weather_image);
        this.vFuture3WeatherImage = this.findViewById(R.id.fea_3_weather_image);

        this.vCurTime = this.findViewById(R.id.cur_time);
        this.vCurTimeHalf = this.findViewById(R.id.cur_time_half);
        this.vCurRoomTemp = this.findViewById(R.id.cur_room_temp);
    }
    private void bindFontTypeByIds() {
        setTypeface((TextView) findViewById(R.id.action_close));
        setTypeface((TextView) findViewById(R.id.action_lightup));
        setTypeface((TextView) findViewById(R.id.action_lightdown));
        setTypeface((TextView) findViewById(R.id.action_wifi));
        setTypeface((TextView) findViewById(R.id.action_music));
    }

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
        this.bindViewByIds();
        this.bindFontTypeByIds();

        // 定时刷新时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                timeHandler.sendEmptyMessage(0);
            }
        }, 0,1000 * 5);

        Intent bindIntent = new Intent(this, WeatherService.class);
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }

    private Handler wifiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (binder != null) binder.downloadWeatherInfo();
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
            if (binder != null) binder.downloadWeatherInfo();
        }
        timeHandler.sendEmptyMessage(0);
        // TODO Room Temp
    }

    /**
     * 渲染时间
     */
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

    /**
     * 跳转到音乐页
     * @param view
     */
    public void actionMusic(View view) {
        Common.getInstance().goActivity(this, MusicActivity.class);
    }

    /**
     * 跳转到Wireless 设置页
     * @param view
     */
    public void actionWireless(View view) {
        Common.getInstance().goActivity(this, WirelessActivity.class);
    }

    private Handler weatherHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            WeatherBean bean = (WeatherBean) msg.obj;
            OneDayWeatherBean[] beans = { bean.current, bean.futures[0], bean.futures[1], bean.futures[2] };

            findViewById(R.id.layout_future).setVisibility(View.VISIBLE);
            findViewById(R.id.layout_future_divider).setVisibility(View.VISIBLE);
            findViewById(R.id.cur_temp).setVisibility(View.VISIBLE);
            // Current Date
            vCurDate.setText(beans[0].date);
            TextView[] tvsWeek = { vCurWeek, vFea1Week, vFea2Week, vFea3Week };
            TextView[] tvsTemp = { vCurTemp, vFea1Temp, vFea2Temp, vFea3Temp };
            TextView[] tvsWeather = { vFea1Weather, vFea2Weather, vFea3Weather };
            ImageView[] ivWeather = {
                vCurWeatherImage, vFuture1WeatherImage,
                vFuture2WeatherImage, vFuture3WeatherImage
            };
            for (int i = 0; i < beans.length; i++) {
                // Weeks
                tvsWeek[i].setText(beans[i].day);
                // Temps
                if (beans[i].low == null || beans[i].high == null) {
                    tvsTemp[i].setText(beans[i].temp);
                } else {
                    tvsTemp[i].setText(beans[i].low + "~" + beans[i].high + "℃");
                }
                // Weather
                ivWeather[i].setImageBitmap(Common.getInstance().getHttpBitmap(beans[i].imageUrl));
            }
            for (int i = 0; i < tvsWeather.length; i++) {
                // Weather
                tvsWeather[i].setText(beans[i + 1].weather);
            }
        }
    };


}
