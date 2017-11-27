package com.morelink.mirror7;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;

/**
 * Created by Arylo on 27/11/17.
 */

public class Common {

    private static Common common = null;;

    public static synchronized Common getIn() {
        if (common == null) {

            common = new Common();
        }
        return common;
    }

    private static Class<?> lastactivity = WeatherActivity.class;
    public final void goActivity(Activity activity) {
        this.goActivity(activity, lastactivity);
    }
    public final void goActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        lastactivity = activity.getClass();
        activity.startActivity(intent);
    };

    public void hideSystemNavigationBar(Activity activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View view = activity.getWindow().getDecorView();
            view.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


}
