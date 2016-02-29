package com.example.asamoahfamily.alzheimers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TaskManagerActivity extends AppCompatActivity implements TasksMenuFragment.OnFragmentInteractionListener, GlobalVariables {



    private static final String MENU_TAG = "MENU_FRAG";
    private static final String TAG = "asamoahDebug";
    private float screenScalar;
    private View infoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenScalar = dm.density;

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
        MenuItem pend = (MenuItem) findViewById(R.id.PendingBox);
        MenuItem done = (MenuItem) findViewById(R.id.DoneBox);
        switch (id){
            case R.id.DoneBox:
                item.setChecked(true);
                pend.setChecked(false);
                break;
            case R.id.PendingBox:
                item.setChecked(true);
                done.setChecked(false);
                break;

        }

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

    @Override
    public void passData(View v) {

    }
}