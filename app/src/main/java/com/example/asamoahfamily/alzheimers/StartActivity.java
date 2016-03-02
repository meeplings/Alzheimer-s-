package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StartActivity extends AppCompatActivity implements GlobalVariables{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void toTaskManager(View v) throws IllegalAccessException, InstantiationException {
        Intent i = new Intent(this,TaskManagerActivity.class);
        startActivity(i);
        finish();
    }
}
