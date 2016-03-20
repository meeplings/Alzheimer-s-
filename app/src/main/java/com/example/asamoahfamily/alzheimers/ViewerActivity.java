package com.example.asamoahfamily.alzheimers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ViewerActivity extends AppCompatActivity {

    private static final String TAG = "asamoahDebug";
    private static final String FILE_NAME = "mfile";
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inputText = (EditText) findViewById(R.id.editText);
        try{
            FileInputStream mFile = openFileInput(FILE_NAME);

            int counter;
            String mData = "";

            while ((counter = mFile.read()) !=-1){
                mData+= Character.toString((char) counter);
            }
            Toast.makeText(this,mData,Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this,"FILE DOESN'T EXIST",Toast.LENGTH_SHORT).show();
        }
    }

    public void add(View v){
        String data = inputText.getText().toString();
        try {
            FileOutputStream toFile = openFileOutput(FILE_NAME, MODE_PRIVATE);
            toFile.write(data.getBytes());
            toFile.close();
            Toast.makeText(this,"FILE MADE SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this,"COULDN'T MAKE FILE",Toast.LENGTH_SHORT).show();
        }finally {
            inputText.setText("");
        }
    }

    public void del(View v){
        try{
            FileInputStream fromFile = openFileInput(FILE_NAME);
            int counter;
            String mData = "";

            while ((counter = fromFile.read()) !=-1){
                mData+= Character.toString((char) counter);
            }
            Toast.makeText(this,mData,Toast.LENGTH_SHORT).show();

        }catch (IOException e){
            Toast.makeText(this,"COULDN'T RETRIEVE DATA FROM FILE",Toast.LENGTH_SHORT).show();
        }

    }
}
