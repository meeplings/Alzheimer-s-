package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StartActivity extends AppCompatActivity implements GlobalVariables{

    private Button editorBut;
    private boolean themeChanged = false;
    private OutputStream sendData;
    private InputStream getData;
    private String theme;

    private static final String TAG = "asamoahDebug";
    private static final String THEME_FILE = "tData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editorBut = (Button) findViewById(R.id.button);

        try{
            getData = openFileInput(THEME_FILE);
            FileInputStream mFile = openFileInput(THEME_FILE);
            int counter;
            String mData = "";

            while ((counter = mFile.read()) !=-1){
                mData+= Character.toString((char) counter);
            }
            theme = mData;
            themeChanged = true;
            updateTheme();
            Toast.makeText(this,"PREVIOUS THEME LOADED",Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            Toast.makeText(this,"NO THEME SELECTED",Toast.LENGTH_SHORT).show();
        }

        if(!themeChanged)
            editorBut.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningRed));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //TODO: Change overflowMenu in styles
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        item.setChecked(true);

        switch (id){
            case R.id.menuBr:
                theme = "BROWN_THEME";
                themeChanged = true;
                break;
            case R.id.menudB:
                theme = "DARK_BLUE_THEME";
                themeChanged = true;
                break;
            case R.id.menuY:
                theme = "YELLOW_THEME";
                themeChanged = true;
                break;
            case R.id.menuO:
                theme = "ORANGE_THEME";
                themeChanged = true;
                break;
            case R.id.menuR:
                theme = "RED_THEME";
                themeChanged = true;
                break;
            default:
                themeChanged = false;
               //TODO: add default popup
        }

        if(themeChanged){
            Toast.makeText(this,theme + "WAS SAVED",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        try {
            FileOutputStream toFile = openFileOutput(THEME_FILE, MODE_PRIVATE);
            toFile.write(theme.getBytes());
            toFile.close();
            updateTheme();
        }catch (IOException e){
            Toast.makeText(this,"COULDN'T SAVE THEME",Toast.LENGTH_SHORT).show();
        }
    }

    public void toManager(View v) {
        if(!themeChanged){
            Toast.makeText(this,"PLEASE SELECT A THEME 1ST",Toast.LENGTH_SHORT).show();
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

    public void updateTheme(){
        new ThemeHandler(this);
        ThemeHandler.setThemeCols(theme);

        editorBut.setEnabled(true);
        editorBut.setBackgroundColor(ThemeHandler.getmPrime());
    }


}
