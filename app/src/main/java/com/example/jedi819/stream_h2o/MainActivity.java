package com.example.jedi819.stream_h2o;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    FloatingActionButton fab;
    NavigationView navigationView;
    public String namePref;
    public String homeMsg;
    int navIndex = -1;
    private Integer mColorPref;
    private Integer mSwitch;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_menu_home_inverse);


        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeMsg = "Home!";

//                if(navIndex == 1){
//                    homeMsg = "You are already here!";
//                    gotoHomeLayout(1);
//                }
//                else
                    gotoHomeLayout(0);

                //Toast.makeText(MainActivity.this    , homeMsg,
                //                        Toast.LENGTH_SHORT).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        navIndex = 1;
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(navIndex).setChecked(true);
        navigationView.setCheckedItem(R.id.drawer_layout);

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_data_sync, false);

        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String marketPref = sharedPref.getString("sync_frequency", "-1");
        namePref = sharedPref.getString("example_text", "-1");
        Boolean switchPref = sharedPref.getBoolean("example_switch", false);
        String message = getString(R.string.market_message) + marketPref +
                getString(R.string.name_message) + namePref +
                getString(R.string.recommend_message) + switchPref;
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        //

        //SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        //int SelectedColor = mSharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimaryDark));
        mColorPref = mSettings.getInt("color", -16496020);
        mSwitch = mSettings.getInt("textColor", -16496020);
        if(mSwitch == 0){
            //mNickNameTextView.setTextColor(getResources().getColor(R.color.white));
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setNavigationIcon(R.drawable.ic_menu_light);
            toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_settings_light));
        }
        else if(mSwitch == 1){
            toolbar.setTitleTextColor(Color.BLACK);
            toolbar.setNavigationIcon(R.drawable.ic_menu_dark);
            toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_settings_dark));
        }
        toolbar.setBackgroundColor(mColorPref);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(mColorPref);
        }
        //toolbar.setTitleTextColor(mSwitch);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        switch(id){
//            case R.id.action_settings:
//                Toast.makeText(MainActivity.this    , "settings",
//                        Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.action_exit:
//                Hide(0);
//                break;
//
//            case R.id.action_about:
//                Toast.makeText(MainActivity.this    , "about",
//                        Toast.LENGTH_LONG).show();
//                break;
//
//
//            default:
//                //For all other cases, do this
//
//                break;
//        }

        switch(id){
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;

            case R.id.action_settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                settings.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(settings);
                break;

            case R.id.action_exit:
                Hide(0);
                break;

            case R.id.action_about:
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.action_colors:
                Intent colors = new Intent(this, ColorActivity.class);
                startActivity(colors);
                break;
            case R.id.action_tools:
                Intent tools = new Intent(this, ToolsActivity.class);
                startActivity(tools);
                break;

            default:
                //For all other cases, do this

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_science) {
           //startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class));
           gotoScienceLayout();
           //break;
       }
       else if (id == R.id.nav_technology) {
           //startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class));
           gotoTechnologyLayout();
           //break;
       }
       else if (id == R.id.nav_reading) {
           //startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class));
           gotoReadingLayout();
           //break;
       }
       else if (id == R.id.nav_socialstudies) {
           //startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class));
           gotoSocialStudiesLayout();
           //break;
       }
       else if (id == R.id.nav_arts) {
           //startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class));
           gotoArtsLayout();
           //break;
       }
       else if (id == R.id.nav_mathtxt) {
           //startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class));
           gotoMathLayout();
           //break;
       }
       else if (id == R.id.nav_home) {
           //startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class));
           gotoHomeLayout(0);
           //break;
       }
       else if (id == R.id.nav_tools) {
           //startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class));
           gotoToolsLayout();
           //break;
       }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void startAnimatedActivity(Intent intent, String swipe) {
        startActivity(intent);
        if(swipe == "Left") {
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
        else if(swipe == "Right") {
            overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
        }
        else {
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    }
    protected void startWipeActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
    }
    public void Hide(int blah) {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS);
        startActivity(intent);
    }

    public void goToKhan (View view) {
        goToUrl ( "https://hourofcode.com/khan/");
    }

    public void goToLego (View view) {
        goToUrl ( "https://www.smithsonianmag.com/innovation/how-lego-is-constructing-the-next-generation-of-engineers-37671528/");
    }
    public void ScienceView (View view) {
        gotoScienceLayout();
    }

    public void TechnologyView (View view) {
        gotoTechnologyLayout();
    }
    public void ReadingView (View view) {
        gotoReadingLayout();
    }
    public void SocialStudiesView (View view) {
        gotoSocialStudiesLayout();
    }
    public void ArtsView (View view) {
        gotoArtsLayout();
    }
    public void MathView (View view) {
        gotoMathLayout();
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    /** Called when the user taps the button */

    public void gotoFirstLayout() {
        startAnimatedActivity(new Intent(getApplicationContext(), FirstActivity.class),"Left");
    }

    public void gotoScienceLayout() {
        startAnimatedActivity(new Intent(getApplicationContext(), ScienceActivity.class),"Left");
//        Snackbar snackbar = Snackbar
//                .make(view, "S C I E N C E !!!", Snackbar.LENGTH_LONG)
//                .setAction("Action", null);
//
//        View sbView = snackbar.getView();
//        sbView.setBackgroundColor(getResources().getColor(R.color.colorRed));
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(getResources().getColor(R.color.colorWhite));
//        snackbar.show();
        // Set drawer nav highlight
//        navIndex = 0;
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.getMenu().getItem(navIndex).setChecked(true);

    }

    public void gotoTechnologyLayout() {
        startAnimatedActivity(new Intent(getApplicationContext(), TechnologyActivity.class),"Left");
//        Snackbar snackbar = Snackbar
//                .make(view, "S C I E N C E !!!", Snackbar.LENGTH_LONG)
//                .setAction("Action", null);
//
//        View sbView = snackbar.getView();
//        sbView.setBackgroundColor(getResources().getColor(R.color.colorRed));
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(getResources().getColor(R.color.colorWhite));
//        snackbar.show();
        // Set drawer nav highlight
//        navIndex = 0;
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.getMenu().getItem(navIndex).setChecked(true);

    }
    public void gotoReadingLayout() {
        startAnimatedActivity(new Intent(getApplicationContext(), ReadingActivity.class),"Left");
    }
    public void gotoSocialStudiesLayout() {
        startAnimatedActivity(new Intent(getApplicationContext(), SocialStudiesActivity.class),"Left");
    }
    public void gotoArtsLayout() {
        startAnimatedActivity(new Intent(getApplicationContext(), ArtsActivity.class),"Left");
    }
    public void gotoMathLayout() {
        startAnimatedActivity(new Intent(getApplicationContext(), MathActivity.class),"Left");
    }
    public void gotoHomeLayout(int type) {
        if(type < 1){
            startAnimatedActivity(new Intent(getApplicationContext(), HomeActivity.class),"Right");
        }
        else
            startWipeActivity(new Intent(getApplicationContext(), HomeActivity.class));

    }
    public void gotoToolsLayout() {
        startAnimatedActivity(new Intent(getApplicationContext(), ToolsActivity.class),"Left");
    }


}
