package com.example.jedi819.stream_h2o;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jedi819 on 2/19/2018.
 */

public class HomeActivity extends MainActivity {

    private TextView mDateTextView;
    private TextView mNickNameTextView;
    private String mNewDate;
    private String mPattern;
    //private String mPattern2;
    private String dayOfTheWeek;
    private String timeOfTheDay;
    private String mMeridian = "";
    private String mGreeting = "";
    private String mNickName;
    private Integer mColorPref;
    //private String mBack;
    private LinearLayout mBack;
    private Integer mSwitch;
    private ImageButton mScienceButton;
    private ImageButton mTechnologyButton;
    private ImageButton mReadingButton;
    private ImageButton mSocialStudiesButton;
    private ImageButton mArtsButton;
    private ImageButton mMathButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.home_layout, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_home);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Hello First Activity", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_refresh_light);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                homeMsg = "As you wish - swipe up!";
//
//                if(navIndex == 1){
                    homeMsg = "You are already home!";
                    // make random string array
                //}

                gotoHomeLayout(1);

//                Toast.makeText(HomeActivity.this    , homeMsg,
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // starting day of week
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);
        int myNum = 0;

        try {
            myNum = Integer.parseInt(dayOfTheWeek.toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        switch (myNum % 10) {
            case 1:
                mPattern = "EEEE, MMMM d'st', yyyy ' at ' h:mm";
            case 2:
                mPattern = "EEEE, MMMM d'nd', yyyy ' at ' h:mm";
            case 3:
                mPattern = "EEEE, MMMM d'rd', yyyy ' at ' h:mm";
            default:
                mPattern = "EEEE, MMMM d'th', yyyy ' at ' h:mm";
        }

        SimpleDateFormat sdf2 = new SimpleDateFormat("H");
        Date d2 = new Date();
        timeOfTheDay = sdf2.format(d2);
        int myNum2 = 0;
        try {
            myNum2 = Integer.parseInt(timeOfTheDay.toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        if(myNum2 >= 0 && myNum2 < 12){
            mMeridian = "AM";
            mGreeting = "Good morning ";
        }
        if (myNum2 >= 12 && myNum2 <= 24){
            mMeridian = "PM";
            if(myNum2>=17){
                mGreeting = "Good evening ";
            }
            if(myNum2<17){
                mGreeting = "Good afternoon ";
            }

        }
//            else{
//                mMeridian = "";
//            }


        // ending day of week
        //String pattern = "EEEE, MMMM d, yyyy h:mm ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mPattern);

        mNewDate = simpleDateFormat.format(new Date())+" "+ mMeridian;
        //mNewDate = mCrime.getmDate();
        mDateTextView = contentView.findViewById(R.id.txtTime);
        mDateTextView.setText(mNewDate);
        //mDateTextView = (TextView) myView.findViewById(R.id.txtTime);

        /// 20180216 begin preference retrieval
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        //

        //SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        //int SelectedColor = mSharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimaryDark));
        mColorPref = mSettings.getInt("color", -16496020);
        //String namePref = mSettings.getString("example_text", "3");
        //SharedPreferences mSettings = getActivity().getSharedPreferences("pref_general", Context.MODE_PRIVATE);
        mNickName = mSettings.getString("example_text", "R2-D2");
        //mColorPref = mSettings.getString("color", "3");
        //String cookieName = mShared.getString("color", String.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
        //int SelectedColor = mSettings.getInt("color",getResources().getColor(R.color.colorPrimaryDark));
        //mSwitch = mSettings.getBoolean("example_switch", false);
        mSwitch = mSettings.getInt("textColor", -16496020);
        mNickNameTextView = contentView.findViewById(R.id.txtNickName);
        mBack = (LinearLayout) contentView.findViewById(R.id.backdrop);

        //mBack.setBackgroundColor(Color.parseColor("#000000"));
        mBack.setBackgroundColor(mColorPref);

//
        if(mSwitch == 0){
            //mNickNameTextView.setTextColor(getResources().getColor(R.color.white));
            mNickNameTextView.setTextColor(Color.WHITE);
            mDateTextView.setTextColor(Color.WHITE);
        }
        else if(mSwitch == 1){
            mNickNameTextView.setTextColor(Color.BLACK);
            mDateTextView.setTextColor(Color.BLACK);
        }


        mNickNameTextView.setText(mGreeting+mNickName);

        mScienceButton = (ImageButton) findViewById(R.id.imageViewScience);
        mTechnologyButton = (ImageButton) findViewById(R.id.imageViewTechnology);
        mReadingButton = (ImageButton) findViewById(R.id.imageViewReading);
        mSocialStudiesButton = (ImageButton) findViewById(R.id.imageViewSocialStudies);
        mArtsButton = (ImageButton) findViewById(R.id.imageViewArts);
        mMathButton = (ImageButton) findViewById(R.id.imageViewMath);

        mScienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoScienceLayout();
            }
        });
        mTechnologyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoTechnologyLayout();
            }
        });
        mReadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoReadingLayout();
            }
        });
        mSocialStudiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSocialStudiesLayout();
            }
        });
        mArtsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoArtsLayout();
            }
        });
        mMathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMathLayout();
            }
        });
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
//        View view = findViewById(R.id.content_frame);
//        FragmentManager fragmentManager = getFragmentManager();
//        switch(id){
//            case R.id.action_settings:
//                Intent settings = new Intent(this, SettingsActivity.class);
//                startActivity(settings);
//                break;
//
//            case R.id.action_exit:
//                Hide(0);
//                break;
//
//            case R.id.action_about:
//                Intent about = new Intent(this, AboutActivity.class);
//                startActivity(about);
//                break;
//
//
//            default:
//                //For all other cases, do this
//
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            } else {
                super.onBackPressed();
            }
        }
    }

}
