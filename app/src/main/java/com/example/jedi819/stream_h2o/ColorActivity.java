package com.example.jedi819.stream_h2o;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Random;

import static android.widget.CompoundButton.OnCheckedChangeListener;
import static android.widget.CompoundButton.OnClickListener;

public class ColorActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mRedColor;
    Button mGreenColor;
    Button mBlueColor;
    Button mRandomColor;
    Button mResetColor;
    Button mSaveColor;
    Integer mRandom;
    Switch mSwitch;
    Integer mColorPref;
    Integer mTextColor;

    //Integer mtest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colors);

        /// 20180216 begin preference retrieval
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        //

        //SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        //int SelectedColor = mSharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimaryDark));
        mColorPref = mSettings.getInt("color", -16496020);
        mTextColor = mSettings.getInt("textColor", -16496020);
        mToolbar = (Toolbar) findViewById(R.id.colorToolbar);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_dark);
        mRedColor = (Button) findViewById(R.id.buttonRed);
        mGreenColor = (Button) findViewById(R.id.buttonGreen);
        mBlueColor = (Button) findViewById(R.id.buttonBlue);
        mRandomColor = (Button) findViewById(R.id.buttonRandom);
        mResetColor = (Button) findViewById(R.id.buttonReset);
        mSaveColor = (Button) findViewById(R.id.buttonSave);
        mSwitch = (Switch)findViewById(R.id.switchText) ;
        //mtest = getTextColor();
        //mToolbar.setTitle(getResources().getString(R.string.choose_color_for_background));

        if(getColor() != getResources().getColor(R.color.colorPrimaryDark)){
            mToolbar.setBackgroundColor(mColorPref);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(mColorPref);
            }

        }
        //TextColor is white if value of 0
        if(getTextColor() < 1){
            mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_light);
            storeTextColor(0);
            mSwitch.setChecked(false);
        }
        else if(getTextColor() > 0){
            mToolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_dark);
            storeTextColor(1);
            mSwitch.setChecked(true);
        }

        mRedColor.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorRed));
                //status bar
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
                }

                storeColor(getResources().getColor(R.color.colorRed));
            }
        });

        mGreenColor.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                //status bar
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorGreen));
                }

                storeColor(getResources().getColor(R.color.colorGreen));
            }
        });

        mRandomColor.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                mRandom = getRandomColor();
                mToolbar.setBackgroundColor(mRandom);
                //status bar
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    getWindow().setStatusBarColor(mRandom);
                }

                storeColor(mRandom);
            }
        });

        mBlueColor.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                //status bar
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlue));
                }

                storeColor(getResources().getColor(R.color.colorBlue));
            }
        });

        mResetColor.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                mToolbar.setBackgroundColor(mColorPref);
                if(mTextColor == 0){
                    mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_light);
                    mSwitch.setChecked(false);
                }
                else if(mTextColor == 1){
                    mToolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_dark);
                    mSwitch.setChecked(true);
                }


                //status bar
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    getWindow().setStatusBarColor(mColorPref);
                    mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

                }

                storeColor(mColorPref);
            }
        });
        mSaveColor.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
//                Intent home = new Intent(ColorActivity.this , HomeActivity.class);
//                home.addFlags(
//                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//                startActivity(home);
            }
        });

        mSwitch.setOnCheckedChangeListener(
                new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(ColorActivity.this,
                                    "Dark Text On", Toast.LENGTH_SHORT).show();
                            mToolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));
                            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_dark);
                            storeTextColor(1);
                            mSwitch.setChecked(true);

                        } else {
                            Toast.makeText(ColorActivity.this,
                                    "Light Text On", Toast.LENGTH_SHORT).show();
                            mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
                            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_light);
                            storeTextColor(0);
                            mSwitch.setChecked(false);
                        }
                    }
                });

    }
//
    private void storeColor(int color){
        //SharedPreferences mSharedPreferences = getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        SharedPreferences mSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("color",color);
        mEditor.apply();
    }

    private void storeTextColor(int on){
        //SharedPreferences mSharedPreferences = getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        SharedPreferences mSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("textColor",on);
        mEditor.apply();
    }

    public int getColor(){
        SharedPreferences mSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        int SelectedColor = mSharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimaryDark));

        return SelectedColor;
    }

    private int getTextColor(){
        SharedPreferences mSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        int SelectedTextColor = mSharedPreferences.getInt("textColor",getResources().getColor(R.color.colorPrimaryDark));

        return SelectedTextColor;
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            Intent settings = new Intent(this, SettingsActivity.class);
            settings.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            startActivity(settings);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent Home = new Intent(ColorActivity.this, HomeActivity.class);
        //startActivity(Home);
    }
}
