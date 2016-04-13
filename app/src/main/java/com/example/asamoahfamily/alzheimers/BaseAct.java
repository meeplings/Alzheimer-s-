package com.example.asamoahfamily.alzheimers;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class BaseAct extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    protected DisplayMetrics dm;
    protected Toolbar toolbar;
    protected boolean themeChanged = false;
    protected String theme;

    protected Button red,blue,yellow,brown,teal,base;

    protected View bg,side,bot,back;
    protected View snackView;
    protected DrawerLayout drawer;
    protected NavigationView navView;
    protected Button goBack;

    protected Point p;
    protected float screenScale;
    protected boolean showTutorial;
    boolean cont;

    protected static final String TAG = "xxyyzz";

    protected static final String THEME_FILE = "tData";
    protected static final String FAILED = "FAILED";


    protected static final String TUT = "TUTORIALS";
    protected static final String ENABLED = "ENABLE";
    protected static final String SHARE = "QQQ";

    protected static final String ALL_BUTS = "AllOfTheButs";

    protected static final String MEDICINE = "MEDICINE";
    protected static final String FOOD = "FOOD";
    protected static final String RECREATION = "RECREATION";
    protected static final int MED_TAG = 1;
    protected static final int FOOD_TAG =2;
    protected static final int REC_TAG = 3;


    protected static final String T_NAME = "THEME_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);

        snackView = findViewById(R.id.CoordinatorLayout);
        p = new Point();
        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenScale = dm.density;
        cont = false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task,menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void setThemeFile(){
        bg = findViewById(R.id.background);
        side = findViewById(R.id.blockLeft);
        back = findViewById(R.id.blockBack);
        bot = findViewById(R.id.blockBot);

        ThemeHandler.setBGC(bot, side, bg, back, toolbar);

        snackView = bot;
    }

    protected boolean popup(String mess){
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        mDialog.setCancelable(true);
        mDialog.setPositiveButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mDialog.setNegativeButton(R.string.cont, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                cont = true;
            }
        });
        mDialog.setMessage(mess);
        AlertDialog popup = mDialog.create();
        popup.show();
        return cont;
    }

    protected void tutPopup(String mess){
        if(showTutorial){
            final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
            mDialog.setCancelable(true);
            mDialog.setPositiveButton(R.string.ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            mDialog.setMessage(mess + "\n " + getResources().getString(R.string.disableTutorial));
            AlertDialog popup = mDialog.create();
            popup.show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.navToStart:
                toStart(item);
                break;
            case R.id.navToManager:
                toManager(item);
                break;
            case R.id.navToViewer:
                toList(item);
                break;
            case R.id.navToThemes:
                setContentView(R.layout.background);

                red = (Button) findViewById(R.id.redTest);
                blue = (Button) findViewById(R.id.blueTest);
                yellow = (Button) findViewById(R.id.yellowTest);
                teal = (Button) findViewById(R.id.tealTest);
                brown = (Button) findViewById(R.id.brownTest);
                goBack = (Button) findViewById(R.id.themeSelected);

                red.setVisibility(View.VISIBLE);
                red.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        changeTheme(((Button) v).getText().toString());
                    }
                });
                blue.setVisibility(View.VISIBLE);
                blue.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        changeTheme(((Button) v).getText().toString());
                    }
                });
                yellow.setVisibility(View.VISIBLE);
                yellow.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        changeTheme(((Button) v).getText().toString());
                    }
                });
                teal.setVisibility(View.VISIBLE);
                teal.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        changeTheme(((Button) v).getText().toString());
                    }
                });
                brown.setVisibility(View.VISIBLE);
                brown.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        changeTheme(((Button) v).getText().toString());
                    }
                });
                goBack.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_start);
                    }
                });

                break;
            case R.id.tutorials:
                String tutOn = "ON";
                showTutorial= !showTutorial;
                SharedPreferences.Editor mEdits = getSharedPreferences(TUT,MODE_PRIVATE).edit();
                mEdits.putBoolean(ENABLED,showTutorial);
                mEdits.apply();

                if(!showTutorial)
                    tutOn = "OFF";
                Toast.makeText(this,"Tutorials are now " + tutOn, Toast.LENGTH_SHORT).show();
                break;
            default:
                toStart(item);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void toList(MenuItem m){
        Intent i = new Intent(this,ViewerActivity.class);
        startActivity(i);
        finish();
    }

    public void toManager(MenuItem m){
        Intent i = new Intent(this,TaskManagerActivity.class);
        startActivity(i);
        finish();
    }

    public void toStart(MenuItem m){
        Intent i = new Intent(this,StartActivity.class);
        startActivity(i);
        finish();
    }

    protected void screenTools(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar()!=null;
        getSupportActionBar().setTitle(R.string.barName);
        getSupportActionBar().show();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navView = (NavigationView) findViewById(R.id.nav_view);
        assert navView !=null;
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else{
            startActivity(getParentActivityIntent());
            finish();
        }
    }

    public void updateTheme(){
        new ThemeHandler(this);
        ThemeHandler.setThemeCols(theme);
        recreate();
    }


    public void changeTheme(String t){
        if(t.equals(getResources().getString(R.string.themeR)))
            theme = "RED_THEME";
        else if(t.equals(getResources().getString(R.string.themeY)))
            theme = "YELLOW_THEME";
        else if(t.equals(getResources().getString(R.string.themedB)))
            theme = "DARK_BLUE_THEME";
        else if(t.equals(getResources().getString(R.string.themeT)))
            theme = "TEAL_THEME";
        else if(t.equals(getResources().getString(R.string.themeBr)))
            theme = "BROWN_THEME";
        setThemeFile();
        recreate();
    }
}
