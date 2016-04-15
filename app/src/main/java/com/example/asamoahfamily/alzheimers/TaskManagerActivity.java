package com.example.asamoahfamily.alzheimers;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        if(getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null) != null)
            theme = getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null);
        updateTheme();

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
            newBut(n, false);

        if(table.getChildCount() == 0) {
            newBut("BREAKFAST",false);
            newBut("LUNCH",false);
            newBut("DINNER",false);
            newBut("VITAMINS",false);
            //Toast.makeText(this,R.string.loadFailed,Toast.LENGTH_SHORT).show();
        }
    }

    public void createButton(View v){
        newBut(((Button) v) .getText().toString(),true);
    }

    private void newBut(final String t, boolean recent) {

        final Button newBut = new Button(this);

        if(recent) {

            final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
            mDialog.setCancelable(true);
            final EditText name = new EditText(this);
            mDialog.setView(name);
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
                    if(!name.getText().toString().equals("")) {
                        newBut.setText(name.getText().toString());
                        dialog.dismiss();
                    }
                }
            });
            mDialog.setMessage(R.string.taskInput);
            AlertDialog popup = mDialog.create();
            popup.show();
        } else
            newBut.setText(t);


        final int id = idCounter;
        idCounter++;
        newBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isPressed())
                     v.setBackgroundColor(ThemeHandler.getmAcc());
                Intent i = new Intent(getBaseContext(),TaskDescriptionActivity.class);
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


}