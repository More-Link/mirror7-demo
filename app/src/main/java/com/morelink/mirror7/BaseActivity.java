package com.morelink.mirror7;

import android.content.ContentResolver;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

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
}
