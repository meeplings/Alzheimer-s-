package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class TaskManagerActivity extends AppCompatActivity implements TasksMenuFragment.OnFragmentInteractionListener,
        GlobalVariables {


    private static final String TAG = "asamoahDebug";
    private Tasks act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        RelativeLayout tLayout = (RelativeLayout) findViewById(R.id.taskLayout);
        tLayout.setBackgroundColor(ThemeHandler.getmDiv());
    }

    public void addTaskMenu(View v) {
        Fragment mFragment = new TasksMenuFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.menuContainer, mFragment, MENU_TAG);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void TaskMenuInteraction(Button b) {
        int type = Integer.parseInt(b.getTag().toString().substring(0,1));

        switch (type) {
            /*
            1 - MED
            2 - FOOD
            3 - RECREATION
            DEFAULT - OTHER
             */default:
                Log.d(TAG, "NO TYPE DETECTED");
                //TODO: add in other type thing
                break;
            case 1:
                act = new Medicine(Tasks.HIGH_PRIO, MEDICINE);
                break;
            case 2:
                act = new Food(Tasks.HIGH_PRIO, FOOD,0);
                break;
            case 3:
                act = new Recreation(Tasks.LOW_PRIO, RECREATION,0);
                break;
        }
        Intent i = new Intent(this,TaskDescriptionActivity.class);
        Log.d(TAG,"TYPE:" + type);
        i.putExtra("TASK_TYPE",type);
        i.putExtra("TASK_NAME", b.getText().toString());
        startActivity(i);
        finish();
    }

    @Override
    public Bundle SaveToActivity(Bundle b) {
        return b;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}