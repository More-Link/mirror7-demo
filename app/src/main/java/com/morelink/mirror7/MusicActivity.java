package com.morelink.mirror7;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YY on 2017/11/30.
 */

public class MusicActivity extends BaseActivity {

    private List<Song> list = new ArrayList<>();
    private MediaPlayer mediaPlayer;//播放音频的
    private ListView mListView;
    MusicitemAdapter adapter;
    private int curPosition = 0;
    private int playPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = new MediaPlayer();

        setContentView(R.layout.activity_music);
        mListView = this.findViewById(R.id.musiclist);
        list = MusicUtils.getMusicData(this);
        adapter = new MusicitemAdapter(this, list);
        mListView.setAdapter(adapter);

        setTypeface((TextView) findViewById(R.id.action_back));
        setTypeface((TextView) findViewById(R.id.action_close));
        setTypeface((TextView) findViewById(R.id.action_lightup));
        setTypeface((TextView) findViewById(R.id.action_lightdown));
        setTypeface((TextView) findViewById(R.id.control_left));
        setTypeface((TextView) findViewById(R.id.control_right));
        setTypeface((TextView) findViewById(R.id.control_up));
        setTypeface((TextView) findViewById(R.id.control_down));
        setTypeface((TextView) findViewById(R.id.control_enter));
        mHandler.sendEmptyMessage(255);
    }

    public void actionBack(View view) {
        Common.getInstance().goActivity(this, WeatherActivity.class);
    }

    public void actionUp(View view) {
        if (curPosition == 0) {
            return;
        }
        curPosition--;
        mHandler.sendEmptyMessage(1);
    }

    public void actionDown(View view) {
        if (curPosition == list.size() - 1) {
            return;
        }
        curPosition++;
        mHandler.sendEmptyMessage(1);
    }

    public void actionLeft(View view) {
        if (list.size() <= 1) {
            return;
        }
        if (playPosition == 0) {
            return;
        }
        playPosition--;
        play(list.get(playPosition).path);
    }

    public void actionRight(View view) {
        if (list.size() <= 1) {
            return;
        }
        if (playPosition == list.size() - 1) {
            return;
        }
        playPosition++;
        play(list.get(playPosition).path);
    }

    public void actionEnter(View view) {
        if (playPosition == curPosition) {
            stop();
            playPosition = -1;
        } else {
            playPosition = curPosition;
            play(list.get(playPosition).path);
        }
    }

    private void stop() {
        mHandler.sendEmptyMessage(1);
        mediaPlayer.stop();
    }

    private void play(String path) {
        mHandler.sendEmptyMessage(1);
        //播放之前要先把音频文件重置
        try {
            mediaPlayer.reset();
            //调用方法传进去要播放的音频路径
            mediaPlayer.setDataSource(path);
            //异步准备音频资源
            mediaPlayer.prepareAsync();
            //调用mediaPlayer的监听方法，音频准备完毕会响应此方法
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();//开始音频
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 1: // Render List
                    adapter.setSelectItem(curPosition);
                    adapter.setPlayItem(playPosition);
                    adapter.notifyDataSetInvalidated();
//                    lvWifi.smoothScrollToPosition(curPosition);
                    mListView.setSelection(curPosition - 4);
                    mHandler.sendEmptyMessage(255);
                    break;
                case 255: // Render Control
                    Button actionEnter = findViewById(R.id.control_enter);
                    Button actionLeft = findViewById(R.id.control_left);
                    Button actionRight = findViewById(R.id.control_right);
                    Button actionUp = findViewById(R.id.control_up);
                    Button actionDown = findViewById(R.id.control_down);
                    if (curPosition == playPosition) {
                        actionEnter.setText(R.string.icon_stop);
                    } else {
                        actionEnter.setText(R.string.icon_play);
                    }
                    if (playPosition == 0) {
                        actionLeft.setVisibility(View.INVISIBLE);
                    } else {
                        actionLeft.setVisibility(View.VISIBLE);
                    }
                    if (playPosition == list.size() - 1) {
                        actionRight.setVisibility(View.INVISIBLE);
                    } else {
                        actionRight.setVisibility(View.VISIBLE);
                    }
                    if (playPosition == -1) {
                        actionLeft.setVisibility(View.INVISIBLE);
                        actionRight.setVisibility(View.INVISIBLE);
                    }
                    if (curPosition == 0) {
                        actionUp.setVisibility(View.INVISIBLE);
                    } else {
                        actionUp.setVisibility(View.VISIBLE);
                    }
                    if (curPosition == list.size() - 1) {
                        actionDown.setVisibility(View.INVISIBLE);
                    } else {
                        actionDown.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };

}
