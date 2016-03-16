package com.example.asamoahfamily.alzheimers;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TaskManagerActivity extends AppCompatActivity implements TasksMenuFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener ,
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

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBundle("MENU_FRAG_BUNDLE", SaveToActivity());
//    }


    public void addTaskMenu(View v) {
        Fragment mFragment = new TasksMenuFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.menuContainer, mFragment, MENU_TAG);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void TaskMenuInteraction(Button b) {
        int type = Integer.parseInt(b.getTag().toString().substring(b.getTag().toString().length()-1));

        switch (type) {
            /*
            1 - MED
            2 - FOOD
            3 - PHYS
            4 - MENT
            5 - OTHER
             */
            //int prio, String na, boolean al, Color col
            default:
                Log.d(TAG, "NO TYPE DETECTED");
                //TODO: add in other type thing
                break;
            case 1:
                act = new Medicine(Tasks.HIGH_PRIO, MEDICINE, true, Color.YELLOW);
                break;
            case 2:
                act = new Food(Tasks.HIGH_PRIO, FOOD, true, Color.GREEN, false, null, 0);
                break;
            case 3:
                act = new PhysicalActivity(Tasks.LOW_PRIO, PHYSICAL_ACTIVITY, true, Color.BLUE, false, 0);
                break;
            case 4:
                act = new MentalActivity(Tasks.MED_PRIO, MENTAL_ACTIVITY, true, Color.RED, 0, true);
                break;


        }


        Fragment mFragment = new ItemFragment();
        Bundle viewB = new Bundle();
        viewB.putString("BUTTON_TAG", b.getTag().toString());
        viewB.putInt("BUTTON_TYPE",type);
        mFragment.setArguments(viewB);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.menuContainer, mFragment, "LIST_FRAG");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public Bundle SaveToActivity(Bundle b) {
        return b;
    }


    public void warningColors(View x){

        TextView v = (TextView) findViewById(R.id.contentPrio);
        String viewHint = v.getHint().toString();

        switch(viewHint){
            case "NONE":
                v.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningYellow));
                v.setHint(R.string.low);
                break;
            case "LOW":
                v.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningOrange));
                v.setHint(R.string.med);
                break;
            case "MED":
                v.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningOrangeRed));
                v.setHint(R.string.high);
                break;
            case "HIGH":
                v.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningRed));
                v.setHint(R.string.top);
                break;
            case "TOP":
                v.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningGreen));
                v.setHint(R.string.none);
                break;

        }

    }


    public void isDone(View x){
        TextView t = (TextView) findViewById(R.id.contentDone);

        if(t.getHint() == getResources().getString(R.string.pendingBut)){
            t.setHint(R.string.doneBut);
            t.setBackgroundResource(R.drawable.check_mark);
        }
        else if(t.getHint() == getResources().getString(R.string.doneBut)){
            t.setHint(R.string.pendingBut);
            t.setBackgroundResource(R.drawable.dash_mark);
        }
    }

    @Override
    public void onListFragmentInteraction(Tasks act, View v,int type) {

    }
}