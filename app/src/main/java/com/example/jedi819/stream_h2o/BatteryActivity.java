package com.example.jedi819.stream_h2o;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jedi819 on 2/19/2018.
 */

public class BatteryActivity extends AppCompatActivity {
    private TextView batteryTxt;
    private TextView batteryStatusTxt;
    private TextView batteryChargeTxt;
    private String batteryStatusStr;
    private String batteryChargeStr;
    private int level;
    private ProgressBar mProgressBar;
    private TextView mLoadingText;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    Toolbar mToolbar;
    private Integer mColorPref;
    private Integer mSwitch;

    //private TextView batteryStatusTxt;
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            mProgressBar.setProgress(level);
            batteryTxt.setText(String.valueOf(level) + "%");
            if(level==100 && isCharging){
                batteryStatusStr = "Full";
            }
            else if(level<100 && isCharging){
                batteryStatusStr = "Charging";
            }
            else if(!isCharging){
                switch(status){
                    case 1:
                        batteryStatusStr = "Unknown" ;
                        break;
                    case 2:
                        batteryStatusStr = "Charging" ;
                        break;
                    case 3:
                        batteryStatusStr = "Discharging" ;
                        break;
                    case 4:
                        batteryStatusStr = "Not Charging" ;
                        break;
                    case 5:
                        batteryStatusStr = "Full" ;
                        break;
                    default:
                        batteryStatusStr = "Check Battery" ;
                        break;
                }
                //batteryStatusStr = String.valueOf(status) ;
            }
            if(chargePlug>0){
                if(usbCharge){
                    batteryChargeStr ="USB Charging";
                }
                else if(acCharge){
                    batteryChargeStr ="AC Charging";
                }
            }
            else {
                batteryChargeStr ="Not Charging";

            }

            batteryStatusTxt.setText(batteryStatusStr);
            batteryChargeTxt.setText(batteryChargeStr);
            //batteryChargeTxt.setText(String.valueOf(chargePlug)+": chargePlug");
           // batteryStatusTxt.setText(String.valueOf(isCharging));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        //

        //SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        //int SelectedColor = mSharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimaryDark));
        mColorPref = mSettings.getInt("color", -16496020);
        mSwitch = mSettings.getInt("textColor", -16496020);
        //LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        //@SuppressLint("InflateParams")
        //View contentView = inflater.inflate(R.layout.battery_layout, null, false);
                setContentView(R.layout.battery_layout);

        mToolbar = (Toolbar) findViewById(R.id.batteryToolbar);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setBackgroundDrawable(new ColorDrawable(mColorPref));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(mColorPref);
        }
//        drawer.addView(contentView, 0);
//        navigationView.setCheckedItem(R.id.nav_technology);
        batteryTxt = (TextView) this.findViewById(R.id.batteryTxt);
        batteryStatusTxt = (TextView) this.findViewById(R.id.batteryStatusTxt);
        batteryChargeTxt = (TextView) this.findViewById(R.id.batteryChargeTxt);

        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        //mLoadingText = (TextView) findViewById(R.id.loadingCompleteTextView);



        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Hello First Activity", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        Snackbar snackbar = Snackbar
//                .make(contentView, "S C I E N C E !!!", Snackbar.LENGTH_LONG)
//                .setAction("Action", null);
//
//        View sbView = snackbar.getView();
//        sbView.setBackgroundColor(getResources().getColor(R.color.colorRed));
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(getResources().getColor(R.color.colorWhite));
//        snackbar.show();
////         Set drawer nav highlight
//        navIndex = 0;
//        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.getMenu().getItem(navIndex).setChecked(true);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
                super.onBackPressed();
        Intent battery = new Intent(BatteryActivity.this, ToolsActivity.class);
        startActivity(battery);
       // }
    }

}
