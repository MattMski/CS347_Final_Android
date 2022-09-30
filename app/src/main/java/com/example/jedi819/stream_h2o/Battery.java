package com.example.jedi819.stream_h2o;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by srsimon on 2/22/2018.
 */

public class Battery extends Activity {
    private TextView batteryTxt;
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            batteryTxt.setText(String.valueOf(level) + "%");
        }
    };

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
       // setContentView(R.layout.main);
        //batteryTxt = (TextView) this.findViewById(R.id.batteryTxt);
        //this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
