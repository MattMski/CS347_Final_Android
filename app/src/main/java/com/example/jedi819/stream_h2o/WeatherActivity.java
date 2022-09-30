package com.example.jedi819.stream_h2o;

/**
 * Created by srsimon on 2/27/2018.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    public long startWeatherTime;

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;

    Typeface weatherFont;
    Toolbar mToolbar;
    private Integer mColorPref;
    private Integer mSwitch;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        //

        //SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        //int SelectedColor = mSharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimaryDark));
        mColorPref = mSettings.getInt("color", -16496020);
        mSwitch = mSettings.getInt("textColor", -16496020);

        super.onCreate(savedInstanceState);



        // mToolbar = (Toolbar) findViewById(R.id.weatherToolbar);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar
        //ActionBar ab = getSupportActionBar();

        // Enable the Up button
        //ab.setDisplayHomeAsUpEnabled(true);
//        ab.setBackgroundDrawable(new ColorDrawable(mColorPref));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(mColorPref);
        }


        //getSupportActionBar().hide();
        startWeatherTime = SystemClock.elapsedRealtime();
        setContentView(R.layout.weather);
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weather.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        updatedField = (TextView)findViewById(R.id.updated_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.humidity_field);
        pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);


        WeatherFunctions.placeIdTask asyncTask =new WeatherFunctions.placeIdTask(new WeatherFunctions.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {


                cityField.setText(weather_city);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: "+weather_humidity);
                pressure_field.setText("Pressure: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });

        asyncTask.execute("41.9865", "-87.7271"); //  asyncTask.execute("Latitude", "Longitude")

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent Tools = new Intent(ToolsActivity.this, SettingsActivity.class);
        //startActivity(Tools);
    }

}
