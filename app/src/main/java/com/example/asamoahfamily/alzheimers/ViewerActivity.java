package com.example.asamoahfamily.alzheimers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewerActivity extends AppCompatActivity {

    private MyDBHandler dbHandler;
    private TextView dbText;
    private static final String TAG = "asamoahDebug";
    private EditText inputText;

    int[] c = {0,0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbText = (TextView) findViewById(R.id.textView3);
        inputText = (EditText) findViewById(R.id.editText);

        dbHandler = new MyDBHandler(this,null);
        Log.d(TAG,"handler created successfully");

    }

    public void del(View v){
        String input = inputText.getText().toString();
        dbHandler.delInfo(input);
        printDB();

    }

    public void add(View v){
        c[0] ++;
        PatientInfo pat = new PatientInfo((inputText.getText().toString()));
//        dbHandler.addInfo(pat);
        printDB();
    }

    public void printDB(){
        c[1] ++;
        String dbString = dbHandler.dbToString();
        dbText.setText(dbString);
         inputText.setText("");

    }

    public void clear(View v){
        dbHandler.delAll();
    }



}
