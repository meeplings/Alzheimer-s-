package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class TaskManagerActivity extends AppCompatActivity implements TasksMenuFragment.OnFragmentInteractionListener, GlobalVariables {



    private static final String MENU_TAG = "MENU_FRAG";
    private static final String TAG = "asamoahDebug";
    private float screenScalar;
    private View infoView;

    private ScrollView scroller;
    private ImageButton upBut,downBut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenScalar = dm.density;

        scroller = (ScrollView) findViewById(R.id.menuScroller);
        upBut = (ImageButton) findViewById(R.id.upBut);
        downBut = (ImageButton) findViewById(R.id.downBut);
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

        return super.onOptionsItemSelected(item);
    }

    public void addTaskMenu(View v) throws IllegalAccessException, InstantiationException {
        Fragment mFragment = new TasksMenuFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.addTask, mFragment, MENU_TAG);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void OnFragmentInteraction(View v) {
        infoView = v;
    }

    public void passData(View v) {

    }

    public void toStart() {
        Intent i = new Intent(this,StartActivity.class);
        startActivity(i);
        finish();
    }


    public void scroll(View v){
        int s = p.y/4;

        int id = v.getId();
        if(id == upBut.getId())
            scroller.arrowScroll(s);
        else if(id == downBut.getId())
            scroller.arrowScroll(-s);
    }


}