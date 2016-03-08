package com.example.asamoahfamily.alzheimers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class TaskManagerActivity extends AppCompatActivity implements TasksMenuFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener ,
        GlobalVariables {


    private static final String TAG = "asamoahDebug";
    private Tasks act;

    private ScrollView scroller;
    private Button upBut, downBut, upBut2, downBut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        scroller = (ScrollView) findViewById(R.id.menuScroller);
        upBut = (Button) findViewById(R.id.upBut);
        upBut2 = (Button) findViewById(R.id.upBut2);
        downBut = (Button) findViewById(R.id.downBut);
        downBut2 = (Button) findViewById(R.id.downBut2);
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

    public void addTaskMenu(View v) {
        Fragment mFragment = new TasksMenuFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.menuContainer, mFragment, MENU_TAG);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void TaskMenuInteraction(Button b) {
        b.setBackgroundColor(ContextCompat.getColor(this, R.color.pink));

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
                break;
            case 1:
                act = new Medicine(Tasks.HIGH_PRIO, MEDICINE, true, ContextCompat.getColor(this, R.color.deepViolet));
                break;
            case 2:
                act = new Food(Tasks.HIGH_PRIO, FOOD, true, ContextCompat.getColor(this, R.color.deepGreen), false, null, 0);
                break;
            case 3:
                act = new PhysicalActivity(Tasks.LOW_PRIO, PHYSICAL_ACTIVITY, true, ContextCompat.getColor(this, R.color.colorAccentGreen), false, 0);
                break;
            case 4:
                act = new MentalActivity(Tasks.MED_PRIO, MENTAL_ACTIVITY, true, ContextCompat.getColor(this, R.color.colorAccentPurp), 0, true);
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

    public void scroll(View v) {

        int id = v.getId();
        if (id == upBut.getId() || id == upBut2.getId())
            scroller.arrowScroll(View.FOCUS_UP);
        else if (id == downBut.getId() || id == downBut2.getId())
            scroller.arrowScroll(View.FOCUS_DOWN);
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