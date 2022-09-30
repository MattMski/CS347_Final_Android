package com.example.jedi819.stream_h2o;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jedi819 on 2/19/2018.
 */

public class ToolsActivity extends AppCompatActivity {
    Toolbar mToolbar;
    private Integer mColorPref;
    private Integer mSwitch;
    private Button btnBattery;
    private Button btnWeather;
    private Button btnCalc;
    private Button btnMap;

    public long startTime, betweenTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        //

        //SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        //int SelectedColor = mSharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimaryDark));
        mColorPref = mSettings.getInt("color", -16496020);
        mSwitch = mSettings.getInt("textColor", -16496020);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_layout);
        btnBattery = (Button)findViewById(R.id.btn_battery);
        btnBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ToolsActivity.this    , "blah",
//                        Toast.LENGTH_LONG).show();
                Intent battery = new Intent(ToolsActivity.this, BatteryActivity.class);
                startActivity(battery);


            }

        });
        btnWeather = (Button)findViewById(R.id.btn_weather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startTime = SystemClock.elapsedRealtime();
                //betweenTime = SystemClock.elapsedRealtime() - startTime;
//                Toast.makeText(ToolsActivity.this    , "blah",
//                        Toast.LENGTH_LONG).show();
                Intent weather = new Intent(ToolsActivity.this, WeatherActivity.class);
                startActivity(weather);


            }

        });
        btnCalc = (Button)findViewById(R.id.btn_calc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setClassName("com.android.calculator2","com.android.calculator2.Calculator");
                startActivity(i);


            }

        });
        btnMap = (Button)findViewById(R.id.btn_showMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoMapsLayout();


            }

        });
        mToolbar = (Toolbar) findViewById(R.id.toolsToolbar);
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

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
        super.onBackPressed();
        //Intent Tools = new Intent(ToolsActivity.this, SettingsActivity.class);
        //startActivity(Tools);
    }

    public void MapsView (View view) {
        gotoMapsLayout();
    }

    public void gotoMapsLayout() {
        Intent i = new Intent(ToolsActivity.this, MapsActivity.class);

        startActivity(i);
    }
}
