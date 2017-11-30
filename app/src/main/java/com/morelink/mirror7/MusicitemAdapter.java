package com.morelink.mirror7;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YY on 2017/11/30.
 */

public class MusicitemAdapter extends BaseAdapter {
    private List<Song> list;
    private LayoutInflater mInflater = null;
    private Context context = null;

    public MusicitemAdapter(Context context, List<Song> list) {
        super();
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            Log.d(MusicitemAdapter.class.getSimpleName(), i + " " + list.get(i).song);
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Song getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        //引入布局
        if (i == selectItem) {
            view = mInflater.inflate(R.layout.musicitem_active, null);
        } else {
            view = mInflater.inflate(R.layout.musicitem, null);
        }
        //实例化对象
        holder.song = (TextView) view.findViewById(R.id.item_mymusic_song);
        holder.singer = (TextView) view.findViewById(R.id.item_mymusic_singer);
        holder.duration = (TextView) view.findViewById(R.id.item_mymusic_duration);
        holder.position = (TextView) view.findViewById(R.id.item_mymusic_postion);
        Common.getInstance().setTypeface(context, holder.position);
        //给控件赋值
        holder.song.setText(list.get(i).song.toString());
        holder.singer.setText(list.get(i).singer.toString());
        //时间需要转换一下
        int duration = list.get(i).duration;
        String time = MusicUtils.formatTime(duration);
        holder.duration.setText(time);
        if (playItem == i) {
            holder.position.setText(R.string.icon_play);
        } else {
            holder.position.setText(i + 1 + "");
        }
        return view;
    }
    class ViewHolder{
        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView position;//序号
    }

    private int playItem = -1;
    public void setPlayItem(int i) {
        playItem = i;
    }

    private int selectItem = 0;
    public void setSelectItem(int i) {
        selectItem = i;
    }
}
