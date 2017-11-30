package com.morelink.mirror7;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

enum LayoutMode {
    LIST, SETTER
}

enum KeyboardMode {
    UPPER, LOWER, NUMBER, SYMBOL
}

public class WirelessActivity extends BaseActivity {

    private WifiManager wifiManager;
    private WifiUtil wifiUtil;
    private LayoutMode mode = LayoutMode.LIST;
    private ListView lvWifi = null;
    private Button bLeft = null;
    private Button bRight = null;
    private LinearLayout lList = null;
    private RelativeLayout lSetter = null;
    private TextView tvSsid = null;
    private TextView tvPwd = null;

    private List<ScanResult> results = new ArrayList<ScanResult>();
    private ScanResult result = null;
    private int curPosition = 0;

    private int xPoint = 0;
    private int yPoint = 0;
    private String words = "";
    private char[][] LOWER_KEYBOARD = {
        "qwertyuiop".toCharArray(),
        "asdfghjkl".toCharArray(),
        "zxcvbnm".toCharArray()
    };
    private char[][] UPPER_KEYBOARD = {
        "QWERTYUIOP".toCharArray(),
        "ASDFGHJKL".toCharArray(),
        "ZXCVBNM".toCharArray()
    };
    private char[][] NUMBER_KEYBOARD = {
        "1234567890".toCharArray(),
        "-/:;()¥&@\"".toCharArray(),
        ".,?!'".toCharArray()
    };
    private char[][] SYMBOL_KEYBOARD = {
        "[]{}#%^*+=".toCharArray(),
        "_\\|~<>$€£•".toCharArray(),
        ".,?!'".toCharArray()
    };
    private KeyboardMode curKeyboardMode = KeyboardMode.LOWER;
    private char[][] curKeyboard = null;
    private Button[][] bKeyboard = {
            new Button[10], new Button[10], new Button[9], new Button[3]
    };

    public void actionBack(View view) {
        if (this.mode == LayoutMode.LIST) {
            Common.getInstance().goActivity(this, WeatherActivity.class);
        } else {
            this.result = null;
            this.mode = LayoutMode.LIST;
            mHandler.sendEmptyMessage(2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wireless);

        lvWifi = this.findViewById(R.id.wifilist);
        bLeft = this.findViewById(R.id.control_left);
        bRight = this.findViewById(R.id.control_right);
        lList = this.findViewById(R.id.layout_list);
        lSetter = this.findViewById(R.id.layout_setter);
        tvSsid = this.findViewById(R.id.ssid);
        tvPwd = this.findViewById(R.id.password);
        int[][] ids = {
            { R.id.kb_0_0, R.id.kb_0_1, R.id.kb_0_2, R.id.kb_0_3, R.id.kb_0_4, R.id.kb_0_5, R.id.kb_0_6, R.id.kb_0_7, R.id.kb_0_8, R.id.kb_0_9},
            { R.id.kb_1_0, R.id.kb_1_1, R.id.kb_1_2, R.id.kb_1_3, R.id.kb_1_4, R.id.kb_1_5, R.id.kb_1_6, R.id.kb_1_7, R.id.kb_1_8, R.id.kb_1_9},
            { R.id.kb_action, R.id.kb_2_0, R.id.kb_2_1, R.id.kb_2_2, R.id.kb_2_3, R.id.kb_2_4, R.id.kb_2_5, R.id.kb_2_6, R.id.kb_delete},
            { R.id.kb_change, R.id.kb_space, R.id.kb_join}
        };

        setTypeface((TextView) findViewById(R.id.action_back));
        setTypeface((TextView) findViewById(R.id.action_close));
        setTypeface((TextView) findViewById(R.id.action_lightup));
        setTypeface((TextView) findViewById(R.id.action_lightdown));
        setTypeface((TextView) findViewById(R.id.control_left));
        setTypeface((TextView) findViewById(R.id.control_right));
        setTypeface((TextView) findViewById(R.id.control_up));
        setTypeface((TextView) findViewById(R.id.control_down));
        setTypeface((TextView) findViewById(R.id.control_enter));

        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < ids[i].length; j++) {
                bKeyboard[i][j] = this.findViewById(ids[i][j]);
                Button btn = bKeyboard[i][j];
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btn.getLayoutParams();
                int boardSize = 1;
                lp.setMargins(j == 0 ? 0 : boardSize, i == 0 ? boardSize : 0, 0,boardSize);
                if (btn.getId() == R.id.kb_space || btn.getId() == R.id.kb_action) {
                    int bottom = lp.bottomMargin;
                    int top = lp.topMargin;
                    int left = lp.leftMargin;
                    int right = lp.rightMargin;
                    lp.setMargins(left, -1, right, bottom);
                }
                btn.setLayoutParams(lp);
                btn.setTextSize(20);
            }
        }
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiUtil = new WifiUtil(wifiManager);
        wifiUtil.setHandle(wifiHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "wifi is disabled", Toast.LENGTH_SHORT).show();
            wifiUtil.openWifi();
        } else {
            this.results = wifiManager.getScanResults();
            this.curPosition = 0;
            mHandler.sendEmptyMessage(0);
        }
    }

    private Handler mHandler = new Handler() {
        private WifiitemAdapter adapter = null;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: // Init
                    mHandler.sendEmptyMessage(2);
                    adapter = new WifiitemAdapter(WirelessActivity.this, results);
                    adapter.setContext(WirelessActivity.this);
                    lvWifi.setAdapter(adapter);
                    if (results.size() != 0) {
                        curPosition = 0;
                        mHandler.sendEmptyMessage(1);
                    }
                    break;
                case 1: // Render List
                    adapter.setSelectItem(curPosition);
                    adapter.notifyDataSetInvalidated();
//                    lvWifi.smoothScrollToPosition(curPosition);
                    lvWifi.setSelection(curPosition - 4);
                    break;
                case 2: // Render Layout
                    if (mode == LayoutMode.LIST) {
                        lSetter.setVisibility(View.GONE);
                        lList.setVisibility(View.VISIBLE);
                        bLeft.setVisibility(View.INVISIBLE);
                        bRight.setVisibility(View.INVISIBLE);
                    } else if (mode == LayoutMode.SETTER) {
                        lSetter.setVisibility(View.VISIBLE);
                        lList.setVisibility(View.GONE);
                        bLeft.setVisibility(View.VISIBLE);
                        bRight.setVisibility(View.VISIBLE);
                        tvSsid.setText(result.SSID);
                        tvPwd.setText(words);
                    }
                    break;
                case 4: // Render Keyboard
                    boolean isLower = curKeyboardMode == KeyboardMode.LOWER;
                    boolean isUpper = curKeyboardMode == KeyboardMode.UPPER;
                    boolean isNumber = curKeyboardMode == KeyboardMode.NUMBER;
                    boolean isSymbol = curKeyboardMode == KeyboardMode.SYMBOL;
                    if (isLower) { curKeyboard = LOWER_KEYBOARD;
                    } else if (isUpper) { curKeyboard = UPPER_KEYBOARD;
                    } else if (isNumber) { curKeyboard = NUMBER_KEYBOARD;
                    } else if (isSymbol) { curKeyboard = SYMBOL_KEYBOARD;
                    }
                    // Reset
                    for (int i = 0; i < bKeyboard.length; i++) {
                        for (int j = 0; j < bKeyboard[i].length; j++) {
                            bKeyboard[i][j].setBackgroundColor(Color.BLACK);
                            bKeyboard[i][j].setTextColor(Color.WHITE);
                            bKeyboard[1][i].setVisibility(View.VISIBLE);
                        }
                    }
                    bKeyboard[yPoint][xPoint].setBackgroundColor(Color.WHITE);
                    bKeyboard[yPoint][xPoint].setTextColor(Color.BLACK);
                    // Lv1
                    for (int i = 0; i < curKeyboard[0].length; i++) {
                        bKeyboard[0][i].setText(String.valueOf(curKeyboard[0][i]));
                    }
                    // Lv2
                    for (int i = 0; i < curKeyboard[1].length; i++) {
                        bKeyboard[1][i].setText(String.valueOf(curKeyboard[1][i]));
                    }
                    for (int i = curKeyboard[1].length; i < bKeyboard[1].length; i++) {
                        bKeyboard[1][i].setVisibility(View.GONE);
                    }
                    // Lv3
                    for (int i = 0; i < curKeyboard[2].length; i++) {
                        bKeyboard[2][i+1].setText(String.valueOf(curKeyboard[2][i]));
                    }
                    for (int i = curKeyboard[2].length + 1; i < bKeyboard[2].length - 1; i++) {
                        bKeyboard[2][i].setVisibility(View.GONE);
                    }
                    if (isLower || isUpper) {
                        setTypeface(bKeyboard[2][0]);
                        bKeyboard[2][0].setText(R.string.icon_upper);
                    } else if (isNumber) {
                        bKeyboard[2][0].setText("#+=");
                    } else if (isSymbol) {
                        bKeyboard[2][0].setText("123");
                    }
                    // Lv4
                    if (isLower || isUpper) {
                        bKeyboard[3][0].setText(".?123");
                    } else if (isNumber || isSymbol) {
                        bKeyboard[3][0].setText("ABC");
                    }
                    setTypeface(bKeyboard[3][1]);
//                    bKeyboard[3][2].setText("Join");
                    break;
            }
        }
    };

    public void actionUp(View view) {
        if (this.mode == LayoutMode.LIST) {
            if (curPosition == 0) {
                return;
            }
            this.curPosition--;
            mHandler.sendEmptyMessage(1);
        } else if (this.mode == LayoutMode.SETTER) {
            if (yPoint == 0) {
                return;
            }
            yPoint--;
            if (yPoint == 2 && xPoint == 2) {
                xPoint = this.bKeyboard[yPoint].length - 1;
            }
            if (this.bKeyboard[yPoint][xPoint].getVisibility() == View.GONE) {
                actionLeft(view);
                return;
            }
            mHandler.sendEmptyMessage(4);
        }
    }

    public void actionDown(View view) {
        if (this.mode == LayoutMode.LIST) {
            if (curPosition + 1 == this.results.size()) {
                return;
            }
            this.curPosition++;
            mHandler.sendEmptyMessage(1);
        } else if (this.mode == LayoutMode.SETTER) {
            if (yPoint == curKeyboard.length) {
                return;
            }
            yPoint++;
            if (yPoint == 3) {
                if (xPoint >= 8) {
                    xPoint = 2;
                } else if (xPoint >= 2) {
                    xPoint = 1;
                }
            } else if (yPoint == 2) {
                if (xPoint >= this.bKeyboard[yPoint].length) {
                    xPoint = this.bKeyboard[yPoint].length - 1;
                }
            } else if (xPoint >= curKeyboard[yPoint].length) {
                xPoint = curKeyboard[yPoint].length - 1;
            }
            if (this.bKeyboard[yPoint][xPoint].getVisibility() == View.GONE) {
                actionLeft(view);
                return;
            }
            mHandler.sendEmptyMessage(4);
        }
    }

    public void actionLeft(View view) {
        if (xPoint == 0) {
            if (yPoint == 3) {
                xPoint = 2;
            } else if (yPoint == 2) {
                xPoint = this.bKeyboard[yPoint].length - 1;
            } else {
                xPoint = this.curKeyboard[yPoint].length - 1;
            }
        } else {
            xPoint--;
        }
        if (this.bKeyboard[yPoint][xPoint].getVisibility() == View.GONE) {
            actionLeft(view);
            return;
        }
        mHandler.sendEmptyMessage(4);
    }

    public void actionRight(View view) {
        if (yPoint == 3 && xPoint == 2) {
            xPoint = 0;
        } else if ((yPoint == 0 || yPoint == 1) && xPoint == 9) {
            xPoint = 0;
        } else if (yPoint == 2 && xPoint == this.bKeyboard[yPoint].length - 1) {
            xPoint = 0;
        } else {
            xPoint++;
        }
        if (this.bKeyboard[yPoint][xPoint].getVisibility() == View.GONE) {
            actionRight(view);
            return;
        }
        mHandler.sendEmptyMessage(4);
    }

    public void actionEnter(View view) {
        boolean isLower = curKeyboardMode == KeyboardMode.LOWER;
        boolean isUpper = curKeyboardMode == KeyboardMode.UPPER;
        boolean isNumber = curKeyboardMode == KeyboardMode.NUMBER;
        boolean isSymbol = curKeyboardMode == KeyboardMode.SYMBOL;

        if (this.mode == LayoutMode.LIST) {
            this.result = this.results.get(this.curPosition);
            this.mode = LayoutMode.SETTER;
            this.words = "";
            mHandler.sendEmptyMessage(2);
            mHandler.sendEmptyMessage(4);
            if (this.result.capabilities.equals("[ESS]")) { connectWifi(); }
        } else if (this.mode == LayoutMode.SETTER) {
            Button curBtn = bKeyboard[yPoint][xPoint];
            int id = curBtn.getId();
            if (id == R.id.kb_delete) {
                this.words = this.words.substring(0, this.words.length() - 1);
                mHandler.sendEmptyMessage(2);
            } else if (id == R.id.kb_action) {
                if (isUpper) {
                    curKeyboardMode = KeyboardMode.LOWER;
                } else if (isLower) {
                    curKeyboardMode = KeyboardMode.UPPER;
                } else if (isNumber) {
                    curKeyboardMode = KeyboardMode.SYMBOL;
                } else if (isSymbol) {
                    curKeyboardMode = KeyboardMode.NUMBER;
                }
                mHandler.sendEmptyMessage(4);
            } else if (id == R.id.kb_change) {
                if (isUpper || isLower) {
                    curKeyboardMode = KeyboardMode.NUMBER;
                } else if (isNumber || isSymbol) {
                    curKeyboardMode = KeyboardMode.LOWER;
                }
                mHandler.sendEmptyMessage(4);
            } else if (id == R.id.kb_space) {
                if (this.words.length() == 0) {
                    return;
                }
                this.words += " ";
                mHandler.sendEmptyMessage(2);
            } else if (id == R.id.kb_join) {
                connectWifi();
            } else {
                this.words += curBtn.getText();
                mHandler.sendEmptyMessage(2);
            }
        }
    }

    private Handler wifiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.d(WirelessActivity.class.getSimpleName(), "连接成功!");
                    Common.getInstance().goActivity(WirelessActivity.this, WeatherActivity.class);
                    break;
                case 1:
                    Log.d(WirelessActivity.class.getSimpleName(), "Wifi开启成功!");
                    results = wifiManager.getScanResults();
                    curPosition = 0;
                    mHandler.sendEmptyMessage(0);
                    break;
            }
        }
    };

    public void connectWifi() {
        String ssid = result.SSID;
        String pass = words;
        wifiUtil.connect(
            ssid, pass.toString(),
            pass.toString().equals("")? WifiUtil.WifiCipherType.WIFICIPHER_NOPASS: WifiUtil.WifiCipherType.WIFICIPHER_WPA
        );
    }
}
