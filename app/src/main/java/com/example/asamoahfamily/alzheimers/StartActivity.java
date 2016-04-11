package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StartActivity extends BaseAct {

    private Button editorBut;

    private GridLayout pending,done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start);
        super.onCreate(savedInstanceState);

        pending = (GridLayout) findViewById(R.id.pendingTasks);
        done = (GridLayout) findViewById(R.id.finishedTasks);
        editorBut = (Button) findViewById(R.id.button);
        openTheme();
        openTasks();

        screenTools();

        tutPopup("Welcome to Cactus!");
        tutPopup("To get started, first go to settings");

        if(!themeChanged)
            editorBut.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningRed));
        setThemeFile();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.getMenuInflater().inflate(R.menu.menu_task, menu);
//        return true;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void toManager(View v) {
        if(!themeChanged){
            Toast.makeText(this, "PLEASE SELECT A THEME 1ST", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this, TaskManagerActivity.class);
            i.putExtra(T_NAME, theme);
            startActivity(i);
            finish();
        }
    }

    public void toList(View v){
        Intent i = new Intent(this,ViewerActivity.class);
        startActivity(i);
        finish();
    }

    private void openTasks(){
        SharedPreferences allSettings = getSharedPreferences(SHARE, 0);
        Set<String> todo = allSettings.getStringSet(ALL_BUTS,new HashSet<String>());
        for (String aTodo : todo) {
            Button b = new Button(this);
            b.setText(aTodo);
            final int prio = allSettings.getInt(aTodo.concat("_PRIO"), 0);
            b.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v){
                    if(prio!=0) {
                        v.setBackgroundColor(ThemeHandler.getmLight());
                        ((GridLayout )v.getParent()).removeView(v);
                        done.addView(v);
                    }else
                        Toast.makeText(getBaseContext(),"TASK NOT DONE",Toast.LENGTH_SHORT).show();
                    }
                 }
            );
            pending.addView(b);
        }
    }

    private void openTheme(){
        try{
            FileInputStream mFile = openFileInput(THEME_FILE);
            int counter;
            String mData = "";

            while ((counter = mFile.read()) !=-1){
                mData+= Character.toString((char) counter);
            }
            theme = mData;
            themeChanged = true;
            Toast.makeText(this,"PREVIOUS THEME LOADED",Toast.LENGTH_SHORT).show();
            mFile.close();
        } catch (IOException e){
            Toast.makeText(this, "NO THEME SELECTED", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateTheme(){
        new ThemeHandler(this);
        ThemeHandler.setThemeCols(theme);

        tutPopup("Now that you have changed the theme, you can start adding new tasks todo!");

        editorBut.setEnabled(true);
        editorBut.setBackgroundColor(ThemeHandler.getmPrime());
        recreate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
