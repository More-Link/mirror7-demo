package com.morelink.mirror7;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherService extends Service {

    private WeatherBinder binder = new WeatherBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private Timer timer = null;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    binder.downloadWeatherInfo();
                }
            }, 0,1000 * 5);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}

class WeatherBinder extends Binder {
    private String[] APIS = {
            "https://www.baidu.com/home/other/data/weatherInfo", // 百度
            "http://weixin.jirengu.com/weather", // 饥人谷
    };
    private int curApiIndex = 0;
    private long last_update_date = 0;

    private WeatherBean weathers = null;
    private Thread getWeatherThread = null;

    private void getWeatherJSON(int index) throws Exception {
        Message msg = new Message();
        if (index != APIS.length) {
            String res = Common.getInstance().get(APIS[index]);
            msg.obj = res;
        }
        msg.what = index;
        mHandler.sendMessage(msg);
    }

    private Handler pHandler = null;
    public void setHandler (Handler handler) {
        this.pHandler = handler;
    }

    public void downloadWeatherInfo() {
        if (getWeatherThread != null) return;
        if (last_update_date + (1000 * 60 * 20 /* 20min */) >= System.currentTimeMillis()) return;

        getWeatherThread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    curApiIndex = 0;
                    getWeatherJSON(curApiIndex);
                } catch (Exception e) {
                    try {
                        getWeatherJSON(++curApiIndex);
                    } catch (Exception e1) {
                    }
                }
            }
        };
        getWeatherThread.start();
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String res = (String) msg.obj;
            JSONObject obj = null;
            WeatherBean bean = new WeatherBean();
            switch (msg.what) {
                case 1: // 饥人谷
                    System.out.println(res);
                    try {
                        JSONObject object = new JSONObject(res);
                        if (!object.getString("status").equals("OK")) {
                            getWeatherJSON(++msg.what);
                            return;
                        }
                        obj = object.getJSONArray("weather").getJSONObject(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        OneDayWeatherBean[] beans = { bean.current, bean.futures[0], bean.futures[1], bean.futures[2] };

                        for (int i = 0; i < beans.length; i++) {
                            JSONObject mObj = obj.getJSONArray("future").getJSONObject(i);
                            beans[i].date = mObj.getString("date");
                            beans[i].day = mObj.getString("day");
                            beans[i].low = mObj.getString("low");
                            beans[i].high = mObj.getString("high");
                            beans[i].weather = mObj.getString("text").replaceAll("\\/.+", "");
                            String url = "http://weixin.jirengu.com/images/weather/code/" + mObj.getInt("code1") + ".png";
                            beans[i].imageUrl = url;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    weathers = bean;
                    break;
                case 0: // 百度
                    System.out.println(res);
                    try {
                        JSONObject object = new JSONObject(res);
                        if (object.getInt("errNo") != 0) {
                            getWeatherJSON(++msg.what);
                            return;
                        }
                        obj = object.getJSONObject("data").getJSONObject("weather").getJSONObject("content");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        OneDayWeatherBean[] beans = { bean.current, bean.futures[0], bean.futures[1], bean.futures[2] };
                        JSONObject[] objs = {
                            obj.getJSONObject("today"), obj.getJSONObject("tomorrow"),
                            obj.getJSONObject("thirdday"), obj.getJSONObject("fourthday")
                        };
                        for (int i = 0; i < beans.length; i++) {
                            beans[i].temp = objs[i].getString("temp");
                            beans[i].date = objs[i].getString("date");
                            beans[i].day = objs[i].getString("time");
                            beans[i].weather = objs[i].getString("condition");

                            String oldUrl = objs[i].getJSONArray("img").getString(0);
                            String code = oldUrl.substring(oldUrl.lastIndexOf("/")).replace(".png", "");
                            String newUrl = "http://weixin.jirengu.com/images/weather/code/" + code + ".png";
                            beans[i].imageUrl = newUrl;
                        }
                        beans[0].day = beans[0].day.split(" ")[0];
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    weathers = bean;
                    break;
                default:
                    Log.e(this.getClass().getSimpleName(), "No Weather Info");
                    break;
            }
            last_update_date = System.currentTimeMillis();
            getWeatherThread = null;
            if (pHandler != null) {
                Message newMsg = new Message();
                newMsg.obj = weathers;
                pHandler.sendMessage(newMsg);
            }
        }
    };

    public WeatherBean getWeathers() {
        if (weathers == null) {
            this.downloadWeatherInfo();
        }
        return weathers;
    };
}

class OneDayWeatherBean {
    String low;
    String high;
    String imageUrl;
    String date;
    String day;
    String weather;
    String temp;
}

class WeatherBean {
    OneDayWeatherBean current = new OneDayWeatherBean();
    OneDayWeatherBean[] futures = {
            new OneDayWeatherBean(), new OneDayWeatherBean(), new OneDayWeatherBean()
    };
}