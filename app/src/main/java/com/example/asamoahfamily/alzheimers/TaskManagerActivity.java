package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.HashSet;
import java.util.Set;

public class TaskManagerActivity extends BaseAct {
    private int buffer;
    private TableRow.LayoutParams rowLP;
    private TableLayout table;
    private int idCounter;
    private int butCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_task_manager);
        super.onCreate(savedInstanceState);

        screenTools();
        setThemeFile();

//        List<String> names = new ArrayList<>();
//        List<String> tags = new ArrayList<>();
//
//        int x = 0;
//        String counter = Integer.toString(x);
//        while(mTasks.contains(counter)){
//            names.add(mTasks.getString(counter,FAILED));
//            Log.d(TAG,"BUTTON ADDED" + names.get(x));
//            tags.add(mTasks.getString(Integer.toString(-x), FAILED));
//            x++;
//            counter = Integer.toString(x);
//        }
//
//        for(int i = 0; i < names.size(); i++)
//            newBut(names.get(i), Integer.parseInt(tags.get(i).substring(0, 1)));

        buffer = Math.round(4* screenScale);
        ScrollView scrollView = (ScrollView) findViewById(R.id.menuScroller);
        assert scrollView!=null;
        scrollView.setBackgroundColor(ThemeHandler.getmDiv());

        idCounter = butCounter = 0;
        rowLP = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        table = (TableLayout) findViewById(R.id.menuTable);
        assert table!=null;

        SharedPreferences mTasks = getApplicationContext().getSharedPreferences(SHARE, 0);
        Set<String> newNames = mTasks.getStringSet(ALL_BUTS, new HashSet<String>());
        for (String n : newNames)
            newBut(n, mTasks.getInt(n, 0));

        if(table.getChildCount() == 0) {
            newBut("BREAKFAST", FOOD_TAG);
            newBut("LUNCH", FOOD_TAG);
            newBut("DINNER", FOOD_TAG);
            newBut("VITAMINS", MED_TAG);
            //Toast.makeText(this,R.string.loadFailed,Toast.LENGTH_SHORT).show();
        }
    }

    private void newBut(final String t, final int type) {

        final Button newBut = new Button(this);
        newBut.setText(t);
        newBut.setTag(type + "_" + t);
        final int id = idCounter;
        idCounter++;
        newBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isPressed())
                     v.setBackgroundColor(ThemeHandler.getmAcc());
                Intent i = new Intent(getBaseContext(),TaskDescriptionActivity.class);
                i.putExtra("TASK_TYPE",type);
                i.putExtra("TASK_NAME",((Button) v).getText().toString());
                startActivity(i);
                finish();
            }
        });
        newBut.setMaxWidth(p.x / 4);
        newBut.setGravity(Gravity.CENTER);
        newBut.setLayoutParams(rowLP);
        newBut.setPadding(buffer / 4, buffer / 4, buffer / 4, buffer / 4);
        newBut.setId(id);
        newBut.setTextColor(ThemeHandler.getmTextPrime());
        newRow(newBut);
    }


    private void newRow(Button b) {

        TableRow nr;
        final int i = butCounter;
        butCounter++;

        if (table.getChildCount() == 0)
            nr = new TableRow(this);
        else {
            if (i % 3 == 0) {
                nr = new TableRow(this);
            } else {
                nr = (TableRow) table.getChildAt(table.getChildCount() - 1);
                nr.addView(b);
                return;
            }
        }
        nr.setLayoutParams(rowLP);
        nr.setGravity(Gravity.CENTER_HORIZONTAL);
        nr.setPadding(buffer, buffer, buffer, buffer);
        nr.addView(b);
        if (i % 2 == 0)
            nr.setBackgroundColor(ThemeHandler.getmLight());
        if (i % 2 == 1)
            nr.setBackgroundColor(ThemeHandler.getmDark());
        table.addView(nr);
    }

    public void addTask(View v){
        int key;
        String type = ((Button) v).getText().toString();
        switch (type) {
            case MEDICINE:
                key = MED_TAG;
                break;
            case RECREATION:
                key = REC_TAG;
                break;
            case FOOD:
                key = FOOD_TAG;
                break;
            default:
                throw new IllegalArgumentException("NO TYPE DETECTED");
        }
        newBut(type,key);
    }

    @Override
    protected void onPause() {
        super.onPause();

//        SharedPreferences.Editor mEdits = getSharedPreferences(SHARE,0).edit();
//        for(int i = 0; i < table.getChildCount(); i++){
//            View v =  table.getChildAt(i);
//            if(v instanceof Button) {
//                Log.d(TAG,Integer.toString(i));
//                mEdits.putString(Integer.toString(i), ((Button) v).getText().toString());
//                mEdits.putString(Integer.toString(-i), v.getTag().toString());
//            }
//        }
//        Toast.makeText(this, R.string.loadSuccessful, Toast.LENGTH_SHORT).show();
//        mEdits.apply();
    }
}