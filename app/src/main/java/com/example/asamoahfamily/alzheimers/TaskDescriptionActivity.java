package com.example.asamoahfamily.alzheimers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class TaskDescriptionActivity extends BaseAct {


    private Tasks mTask;
    private TextView prioText;

    private SharedPreferences allSettings;

    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_task_description);
        super.onCreate(savedInstanceState);

        screenTools();
        if(getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null) != null)
            theme = getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null);
        updateTheme();

        Intent i = getIntent();
        type = i.getIntExtra("TASK_TYPE", 0);
        String name = i.getStringExtra("TASK_NAME");

        switch (type){
            case 1:
                mTask = new Medicine(0,name);
                break;
            case 2:
                mTask = new Food(0,name, 0);
                break;
            case 3:
                mTask = new Recreation(0,name,0);
                break;
            default:
                throw new RuntimeException("NO TYPE DETECTED");
        }

        int buffer =  Math.round(6*screenScale);

        allSettings=getApplicationContext().getSharedPreferences(SHARE,0);

        prioText = (TextView) findViewById(R.id.taskPrio);
        final TextView nameText = (TextView) findViewById(R.id.taskName);
        assert nameText!=null;
        if(!name.equals(""))
            nameText.setText(name);
        final EditText inputName = (EditText) findViewById(R.id.nameInput);
        if(inputName !=null)
            inputName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    String taskName = v.getText().toString();
                    TaskDescriptionActivity.this.
                            mTask.setName(taskName);
                    nameText.append("\n" + taskName);
                    v.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.WarningGreen));
//                        Toast.makeText(getBaseContext(), "COULDN'T READ DOSAGE", Toast.LENGTH_SHORT).show();
//                        v.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.WarningRed));
                    v.setText("");
                    hideKeyboard(v);
                return true;
                }
            });

        TableRow.LayoutParams rowLP = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        rowLP.gravity = Gravity.CENTER;
        rowLP.setMargins(buffer, buffer, buffer, buffer);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void changePrio(View v) {
        prioText.setText(R.string.listPrio);
        switch (((Button) v).getText().toString()) {
            case "NONE":
                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningYellow));
                ((Button) v).setText(R.string.low);
                mTask.setPrio(Tasks.LOW_PRIO);
                prioText.append(" LOW");
                break;
            case "LOW":
                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningOrange));
                ((Button) v).setText(R.string.med);
                mTask.setPrio(Tasks.MED_PRIO);
                prioText.append(" MED");
                break;
            case "MED":
                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningOrangeRed));
                ((Button) v).setText(R.string.high);
                mTask.setPrio(Tasks.HIGH_PRIO);
                prioText.append(" HIGH");
                break;
            case "HIGH":
                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningRed));
                ((Button) v).setText(R.string.top);
                mTask.setPrio(Tasks.TOP_PRIO);
                prioText.append(" TOP");
                break;
            case "TOP":
                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningGreen));
                ((Button) v).setText(R.string.none);
                mTask.setPrio(Tasks.NONE);
                prioText.append(" NONE");
                break;
            default:
                v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.WarningGreen));
                ((Button) v).setText(R.string.none);
                mTask.setPrio(Tasks.NONE);
                prioText.append(" NONE");
                break;
        }
    }
    
    public void clear(View v){
        recreate();
    }
    
    public void commit(View v){
        saveData();
        recreate();
    }

    public void close(View v){
        SharedPreferences.Editor mEdits = allSettings.edit();
        mEdits.clear();
        mEdits.apply();
        recreate();
    }

    private void saveData(){
        SharedPreferences.Editor mEdits = allSettings.edit();
        Set<String> butnames = allSettings.getStringSet(ALL_BUTS, new HashSet<String>());
        mEdits.remove(ALL_BUTS);
        butnames.add(mTask.getName());
        mEdits.putStringSet(ALL_BUTS, butnames);
        mEdits.putInt(mTask.getName().concat("_TYPE"), type);
        mEdits.putInt(mTask.getName().concat("_PRIO"), mTask.getPrio());
        //mEdits.putFloat(TIME, (float) time);
        mEdits.apply();
        Log.d(TAG,mTask.getName()+"||"+allSettings.getString(mTask.getName(),FAILED));
        if(allSettings.getString(mTask.getName(),FAILED).equals(mTask.getName()))
            Toast.makeText(this, R.string.saveFailed, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, R.string.saveSuccessful, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        if(popup("Are you sure you want to exit?"))
            super.onPause();
    }
}
