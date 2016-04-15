package com.example.asamoahfamily.alzheimers;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class TaskDescriptionActivity extends BaseAct {


    private Tasks mTask;
    private TextView prioText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_task_description);
        super.onCreate(savedInstanceState);

        mTask = new Tasks(Tasks.NONE,"---");

        LinearLayout bot = (LinearLayout) findViewById(R.id.botbar);
        assert bot!=null;
        bot.setBackgroundColor(ThemeHandler.getmDark());


        screenTools();
        if(getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null) != null)
            theme = getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null);
        updateTheme();

        Intent i = getIntent();
        String name = i.getStringExtra("TASK_NAME");

        int buffer =  Math.round(6*screenScale);

        prioText = (TextView) findViewById(R.id.taskPrio);
        final TextView nameText = (TextView) findViewById(R.id.taskName);
        assert nameText!=null;
        if(!name.equals(""))
            nameText.setText(name);
        final EditText inputName = (EditText) findViewById(R.id.nameInput);
        assert inputName!=null;
            inputName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    String taskName = v.getText().toString();
                    TaskDescriptionActivity.this.
                            mTask.setName(taskName);
                    nameText.setText(taskName);
                    v.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.WarningGreen));
                    v.setEnabled(false);
                    v.setText("");
                    hideKeyboard(v);
                return true;
                }
            });

        final Button frequency = (Button) findViewById(R.id.frequency);
        assert frequency!=null;
        frequency.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog mFreq = new DatePickerDialog(TaskDescriptionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mTask.setDay(dayOfMonth);
                        mTask.setMonth(monthOfYear);
                        mTask.setYear(year);
                        frequency.append("\nTotal hours until Reset: " + Math.round(TaskDescriptionActivity.this.mTask.getDay()*24));
                    }
                },0,0,0);
            mFreq.show();
            }
        });

        final Button time = (Button) findViewById(R.id.time);
        assert time!=null;
        time.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                final TimePickerDialog mTime = new TimePickerDialog(
                        TaskDescriptionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mTask.setHour(hourOfDay);
                        mTask.setMinute(minute);
                        time.append("\nHours: " + TaskDescriptionActivity.this.mTask.getHour() +
                        "\nMinutes: " + TaskDescriptionActivity.this.mTask.getMinute());
                    }
                },0,0,true);
                mTime.show();
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
        SharedPreferences.Editor mEdits = getApplicationContext().getSharedPreferences(SHARE,0).edit();
        mEdits.clear();
        mEdits.apply();
        recreate();
    }

    private void saveData(){

        String timeFormat = mTask.getHour()+"_"+mTask.getMinute();
        String dateFormat = mTask.getMonth()+"_"+mTask.getDay()+"_"+mTask.getYear();

        SharedPreferences.Editor mEdits = getApplicationContext().getSharedPreferences(SHARE,0).edit();
        Set<String> butnames = getApplicationContext().getSharedPreferences(SHARE,0).getStringSet(ALL_BUTS, new HashSet<String>());
        mEdits.remove(ALL_BUTS);
        butnames.add(mTask.getName());

        mEdits.putString(mTask.getName().concat(TIME),timeFormat);
        mEdits.putString(mTask.getName().concat(FREQ),dateFormat);
        mEdits.putInt(mTask.getName().concat(PRIO), mTask.getPrio());

        mEdits.putStringSet(ALL_BUTS, butnames);
        mEdits.apply();
        if(getApplicationContext().getSharedPreferences(SHARE,0).getString(mTask.getName(),FAILED).equals(mTask.getName()))
            Toast.makeText(this, R.string.saveFailed, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, R.string.saveSuccessful, Toast.LENGTH_LONG).show();

    }

}
