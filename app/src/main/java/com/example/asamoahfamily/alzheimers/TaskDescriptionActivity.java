package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDescriptionActivity extends AppCompatActivity implements GlobalVariables{

    private TableLayout mTable;
    private Tasks mTask;
    private TableRow.LayoutParams rowLP;

    private TextView[] names;


    private int rowCounter;
    private int buffer;
    private float screenScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenScale = dm.density;
        buffer =  Math.round(6*screenScale);

        mTable = (TableLayout) findViewById(R.id.descTable);

        rowLP = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        rowLP.gravity = Gravity.CENTER;
        rowLP.setMargins(buffer, buffer, buffer, buffer);

        Intent i = getIntent();
        int type = i.getIntExtra("TASK_TYPE", 0);
        String name = i.getStringExtra("TASK_NAME");
        int prio = i.getIntExtra("TASK_PRIO", 0);
        double time = i.getDoubleExtra("TASK_TIME",0);

        switch (type){
            case 2:
                mTask = new Food(prio,name, time);
                break;
            case 1:
                mTask = new Medicine(prio,name);
                break;
            case 3:
                mTask = new Recreation(prio,name,time);
                break;
            default:
                throw new RuntimeException("NO TYPE DETECTED");
        }

        descSetup();
    }

    private void newRow(int r) {
        for(int x = 0; x < r; x++) {
            TableRow nr = new TableRow(this);
            final int i = rowCounter;
            rowCounter++;
            nr.setLayoutParams(rowLP);
            if (i % 2 == 0)
                nr.setBackgroundColor(ThemeHandler.getmLight());
            if (i % 2 == 1)
                nr.setBackgroundColor(ThemeHandler.getmDark());
            mTable.addView(nr);
        }
    }

    private void descSetup(){
        int set = 0;
        /* 1 - FOOD
           2 - RECREATION
           3 - MEDICINE
        */
        if(mTask instanceof Food) {
            names = new TextView[4];
            set = 1;

        }
        else if(mTask instanceof Recreation){
            names = new TextView[4];
            set = 2;
        }
        else if(mTask instanceof Medicine) {
            names = new TextView[3];
            set = 3;
        }
        else {
            names = null;
            invalidInput();
        }
        newRow(names.length-1);
        for(int i = 0; i < names.length;i++){
            names[i] = new TextView(this);
            names[i].setLayoutParams(rowLP);
        }

        names[0].setText(R.string.listName);
        EditText mDesc = new EditText(this);
        mDesc.setHint(R.string.listItemName);

        ((TableRow) (mTable.getChildAt(0))).addView(names[0]);
        ((TableRow) (mTable.getChildAt(0))).addView(mDesc);

        names[1].setText(R.string.listPrio);
        Button mPrio = new Button(this);
        mPrio.setText("NONE");
        mPrio.setBackgroundColor(ContextCompat.getColor(this, R.color.WarningRed));
        mPrio.setTextColor(Color.WHITE);
        mPrio.setOnClickListener
                (new Button.OnClickListener() {
                    public void onClick(View v) {
                        names[1].setText(R.string.listPrio);
                        switch (((Button) v).getText().toString()) {
                            case "NONE":
                                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningYellow));
                                ((Button) v).setText(R.string.low);
                                names[1].append(" LOW");
                                break;
                            case "LOW":
                                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningOrange));
                                ((Button) v).setText(R.string.med);
                                names[1].append(" MED");
                                break;
                            case "MED":
                                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningOrangeRed));
                                ((Button) v).setText(R.string.high);
                                names[1].append(" HIGH");
                                break;
                            case "HIGH":
                                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningRed));
                                ((Button) v).setText(R.string.top);
                                names[1].append(" TOP");
                                break;
                            case "TOP":
                                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningGreen));
                                ((Button) v).setText(R.string.none);
                                names[1].append(" NONE");
                                break;
                            default:
                                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningGreen));
                                ((Button) v).setText(R.string.none);
                                names[1].append(" NONE");
                        }

                    }
                });
        ((TableRow) (mTable.getChildAt(1))).addView(names[1]);
        ((TableRow) (mTable.getChildAt(1))).addView(mPrio);

        CheckBox isDone = new CheckBox(this);
        names[2].setText(R.string.pendingBut);
        if(mTask.checkTime())
            isDone.setChecked(true);
        else
            isDone.setChecked(false);
        ((TableRow) (mTable.getChildAt(2))).addView(names[2]);
        ((TableRow) (mTable.getChildAt(2))).addView(isDone);

        switch (set){
            case 1:
                foodList();
                break;
            case 2:
                medList();
                break;
            case 3:
                rectList();
                break;
            default:
                invalidInput();
                break;
        }
    }

    private void invalidInput(){
        String eMessage = "TYPE NOT FOUND";
        Toast.makeText(this, eMessage, Toast.LENGTH_SHORT).show();
        throw new RuntimeException(eMessage);
    }

    private void foodList(){
        EditText mTime = new EditText(this);
        mTime.setHint(R.string.alertTime);
    }

    private void medList(){

    }

    private void rectList(){
        names[3] = new TextView(this);
        names[3].setText("Add hours here");
        EditText mHrs = new EditText(this);
        mHrs.setHint("Add a valid number input");
        try {
            int hrs = Integer.parseInt(mHrs.getText().toString());
            ((Recreation)mTask).incHours(hrs);
            Toast.makeText(this,"HOURS SUCCESSFULLY ADDED",Toast.LENGTH_SHORT).show();
        }catch (IllegalArgumentException e){
            Toast.makeText(this,"NOT A VALID NUMBER",Toast.LENGTH_SHORT).show();
        }finally {
            mHrs.setText("");
        }

    }
}
