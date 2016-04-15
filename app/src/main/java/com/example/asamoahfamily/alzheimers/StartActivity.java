package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;

public class StartActivity extends BaseAct {

    private Button editorBut;

    private GridLayout pending,done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start);
        super.onCreate(savedInstanceState);
        screenTools();
        openTheme();
        openTasks();

        pending = (GridLayout) findViewById(R.id.pendingTasks);
        pending.setBackgroundColor(ThemeHandler.getmDark());
        done = (GridLayout) findViewById(R.id.finishedTasks);
        done.setBackgroundColor(ThemeHandler.getmLight());
        editorBut = (Button) findViewById(R.id.button);
        Button m = (Button) findViewById(R.id.startM);
        m.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(StartActivity.this,MemoryActivity.class);
                startIntent(i);
                finish();
            }
        });
        m.setBackgroundColor(ThemeHandler.getmAdj2());
        m.setOnLongClickListener(new Button.OnLongClickListener(){
            public boolean onLongClick(View v){
                popup("Times Played: " + getSharedPreferences(SHARE,0).getString(count,0));
            }
        });
        tutPopup("Welcome to Cactus!");
        tutPopup("To get started, first go to settings on the top bar");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void toManager(View v) {
        Intent i = new Intent(this, TaskManagerActivity.class);
        i.putExtra(T_NAME, theme);
        startActivity(i);
        finish();
    }

    private void openTasks(){
        SharedPreferences allSettings = getApplicationContext().getSharedPreferences(SHARE, 0);
        Set<String> todo = allSettings.getStringSet(ALL_BUTS,new HashSet<String>());
        for (String aTodo : todo) {
            final boolean isDone = false;
            Button b = new Button(this);
            b.setBackgroundColor(ContextCompat.getColor(this,R.color.WarningRed));
            b.setText(aTodo);
            final int prio = allSettings.getInt(aTodo.concat(PRIO), 0);
            StringTokenizer mTime = new StringTokenizer(
                    allSettings.getString(aTodo.concat(TIME),""),"_"
            );
            try {
                final String time = mTime.nextToken() + ":" + mTime.nextToken();
                StringTokenizer mDate = new StringTokenizer(
                        allSettings.getString(aTodo.concat(FREQ), ""), "_"

                );
                final String date = mDate.nextToken() + "  " + mDate.nextToken();

                b.setOnLongClickListener(new Button.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View v) {
                        popup("Time for task: " + time +
                        "\nFrequency of reminders: " + date +
                        "\n Status: " + isDone +
                        "\n Priority: " + prio);
                        return false;
                    }
                });
            }catch (NoSuchElementException e){
                Toast.makeText(this,"NOT SET UP PROPERLY",Toast.LENGTH_SHORT).show();
            }
            b.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v){
                    if(v.getParent() == pending) {
                        v.setBackgroundColor(ContextCompat.getColor(StartActivity.this,R.color.WarningGreen));
                        ((GridLayout )v.getParent()).removeView(v);
                        done.addView(v);
                        Toast.makeText(getBaseContext(),R.string.done,Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getBaseContext(), R.string.notDone, Toast.LENGTH_SHORT).show();
                        v.setBackgroundColor(ContextCompat.getColor(StartActivity.this, R.color.WarningRed));
                        ((GridLayout) v.getParent()).removeView(v);
                        pending.addView(v);
                    }
                    }
                 }
            );

            pending.addView(b);
        }
    }

    private void openTheme(){

        if(getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null) != null) {
            theme = getSharedPreferences(SHARE, MODE_PRIVATE).getString(THEME_FILE, null);
            themeChanged = true;
            Toast.makeText(this,theme + " theme loaded",Toast.LENGTH_SHORT).show();
            updateTheme();
        }
        else
            Toast.makeText(this, "No theme selected", Toast.LENGTH_SHORT).show();
    }

    public void updateTheme(){
        super.updateTheme();
        tutPopup("Now that you have changed the theme, you can start adding new tasks todo!");
        editorBut.setEnabled(true);
        editorBut.setBackgroundColor(ThemeHandler.getmPrime());
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
                theme = BROWN;
                themeChanged = true;
                break;
            case R.id.menudB:
                theme = BLUE;
                themeChanged = true;
                break;
            case R.id.menuY:
                theme = YELLOW;
                themeChanged = true;
                break;
            case R.id.menuT:
                theme = TEAL;
                themeChanged = true;
                break;
            case R.id.menuR:
                theme = RED;
                themeChanged = true;
                break;
            default:
                themeChanged = false;
                break;
            //TODO: add default popup
        }
        if(themeChanged){
            updateTheme();
            Toast.makeText(this,theme + " was saved!",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
