package com.morelink.mirror7;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by YY on 2017/11/28.
 */

public class WifiitemAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;
    private List<ScanResult> list = null;

    public WifiitemAdapter(Context context, List<ScanResult> list) {
        super();
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        Collections.sort(this.list, new Comparator<ScanResult>() {
            @Override
            public int compare(ScanResult t0, ScanResult t1) {
                return t1.level - t0.level;
            }
        });
    }

    private Context context = null;
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public ScanResult getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView tvConnected = null;
        TextView tvSsid = null;
        TextView tvLock = null;
        TextView tvLevel = null;
        if (this.selectItemPosition == i) {
            view = mInflater.inflate(R.layout.wifiitem_active, null);
        } else {
            view = mInflater.inflate(R.layout.wifiitem, null);
        }
        tvConnected = view.findViewById(R.id.wifiitem_connected);
        tvSsid = view.findViewById(R.id.wifiitem_ssid);
        tvLock = view.findViewById(R.id.wifiitem_lock);
        tvLevel = view.findViewById(R.id.wifiitem_level);

        if (context != null) {
            Common.getInstance().setTypeface(context, tvLock);
            Common.getInstance().setTypeface(context, tvLevel);
        }
        tvSsid.setText(this.getItem(i).SSID);
        tvLevel.setText(this.getItem(i).level + "");
        int level = this.getItem(i).level;
        if (level > -40) {
            tvLevel.setText(R.string.icon_signal5);
        } else if (level > -55) {
            tvLevel.setText(R.string.icon_signal4);
        } else if (level > -65) {
            tvLevel.setText(R.string.icon_signal3);
        } else if (level > -75) {
            tvLevel.setText(R.string.icon_signal2);
        } else {
            tvLevel.setText(R.string.icon_signal1);
        }
        if ("[ESS]".equals(this.getItem(i).capabilities)) {
            tvLock.setVisibility(View.INVISIBLE);
        } else {
            tvLock.setVisibility(View.VISIBLE);
        }
        tvConnected.setVisibility(View.INVISIBLE);
//        if (this.selectItemPosition == i) {
//            view.setBackgroundColor(Color.WHITE);
//        } else {
//            view.setBackgroundColor(Color.BLACK);
//        }
        return view;
    }

    private int selectItemPosition = -1;
    public void setSelectItem(int i) {
        this.selectItemPosition = i;
    }
}
