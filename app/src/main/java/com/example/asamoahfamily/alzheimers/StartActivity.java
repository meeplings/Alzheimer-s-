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

public class StartActivity extends AppCompatActivity implements GlobalVariables{

    private Button editorBut;
    public int themeID;
    private boolean themeChanged = false;
    private static final String TAG = "asamoahDebug";

    private String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editorBut = (Button) findViewById(R.id.button);
        if(!themeChanged){
            editorBut.setEnabled(false);
            editorBut.setHint(R.string.toSettings);
            editorBut.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningRed));
        }
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
            themeID = id;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        getApplication().setTheme(themeID);
        
        new ThemeHandler(this);
        ThemeHandler.setThemeCols(theme);

        editorBut.setEnabled(true);
        editorBut.setBackgroundColor(ThemeHandler.getmPrime());
    }

    public void toManager(View v) {
        Intent i = new Intent(this,TaskManagerActivity.class);
        i.putExtra(T_NAME,theme);
        startActivity(i);
    }

    public void toList(View v){
        Intent i = new Intent(this,ViewerActivity.class);
        startActivity(i);
        finish();
    }


}
