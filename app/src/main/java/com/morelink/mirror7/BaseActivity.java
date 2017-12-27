package com.morelink.mirror7;

import android.content.ContentResolver;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arylo on 27/11/17.
 */

public class BaseActivity extends AppCompatActivity {

    private int brightnessValue = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.getInstance().hideSystemNavigationBar(this);
        this.brightnessValue = getBrightness();
        Log.d(this.getClass().getSimpleName(), getBrightness() + "");
    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation() !=  ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    protected void setTypeface(TextView view) {
        Common.getInstance().setTypeface(this, view);
    }

    public void actionClose(View view) {
        Common.getInstance().goActivity(this, MainActivity.class);
    }

    public int getBrightness() {
        int brightValue = 0;
        ContentResolver contentResolver = getContentResolver();
        try {
            brightValue = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brightValue;
    }

    public void setBrightness(int brightValue) {
        int value = brightValue > 255 ? 255 : brightValue <= 30 ? 30 : brightValue;
        this.brightnessValue = value;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = (value <= 0 ? -1.0f : value / 255f);
        getWindow().setAttributes(lp);
        Log.d(this.getClass().getSimpleName(), "Set " + value);
    }

    public void lightUp(View view) {
        this.setBrightness(this.brightnessValue + 25);
    }

    public void lightDown(View view) {
        this.setBrightness(this.brightnessValue - 25);
    }

    public void lightLevelHigh(View view) {
        this.setBrightness(204);
    }

    public void lightLevelMid(View view) {
        this.setBrightness(128);
    }

    public void lightLevelLow(View view) {
        this.setBrightness(76);
    }

    private RelativeLayout noctificationLayout = null;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    noctificationLayout = findViewById(R.id.layout_notification);
                    TextView text = findViewById(R.id.notification_text);
                    noctificationLayout.setVisibility(View.VISIBLE);
                    text.setText(msg.obj.toString());
                    break;
                case 255:
                    noctificationLayout.setVisibility(View.GONE);
                    break;
            }
        }
    };

    public void notification(String text) {
        notification(text, 2);
    }

    public void notification(String text, int delay) {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = text;
        mhandler.sendMessage(msg);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                mhandler.sendEmptyMessage(255);
            }
        }, 1000 * delay);
    }
}
