package com.example.jedi819.stream_h2o;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jedi819 on 2/19/2018.
 */

public class AboutActivity extends AppCompatActivity {
    Toolbar mToolbar;
    private Integer mColorPref;
    private Integer mSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        //

        //SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        //int SelectedColor = mSharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimaryDark));
        mColorPref = mSettings.getInt("color", -16496020);
        mSwitch = mSettings.getInt("textColor", -16496020);
        setContentView(R.layout.activity_about);
        View parentLayout = findViewById(android.R.id.content);
        mToolbar = (Toolbar) findViewById(R.id.aboutToolbar);
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


        //LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        //@SuppressLint("InflateParams")
        //View contentView = inflater.inflate(R.layout.activity_about, null, false);
//        drawer.addView(contentView, 0);
//        navigationView.setCheckedItem(R.id.nav_tools);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Hello First Activity", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Snackbar snackbar = Snackbar
                .make(parentLayout, "CURRENT VERSION INSTALLED!", Snackbar.LENGTH_LONG)
                .setAction("Action", null);

        View sbView = snackbar.getView();
        sbView.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        snackbar.show();
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

                super.onBackPressed();
//        Intent home = new Intent(AboutActivity.this, HomeActivity.class);
//        startActivity(home);

        }


}
