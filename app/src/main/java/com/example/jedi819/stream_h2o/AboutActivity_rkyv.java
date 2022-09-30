package com.example.jedi819.stream_h2o;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class AboutActivity_rkyv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
  //      setSupportActionBar(toolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);

        Toast.makeText(getApplicationContext(),"Latest Version Installed", Toast.LENGTH_LONG).show();

    }
}
