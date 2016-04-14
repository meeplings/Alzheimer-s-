package com.example.asamoahfamily.alzheimers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MemoryActivity extends BaseAct {
    ImageButton[][] buttons= new ImageButton[4][4];
    Memory game;
    Button startButton;
    TextView win;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_memory);
        super.onCreate(savedInstanceState);

        screenTools();
        if(getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null) != null)
            theme = getSharedPreferences(SHARE,MODE_PRIVATE).getString(THEME_FILE,null);
        updateTheme();

        int id = R.id.button6;
        for(int i = 0; i < buttons.length; i++){
            for(int j =0; j < buttons[i].length;j++){
                buttons[i][j] = (ImageButton) findViewById(id);
                id++;
            }
        }

        win = (TextView) findViewById(R.id.youWin);

        /*buttons[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.onTestClick(v);
            }
        });
        /*
        y=0;
        x=0;
        for(y=0;y<4;y++){
            for(x=0;x<4;x++){
                buttons[y][x].setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v ){
                        MemoryActivity.this.buttonInput(y,x,v);
                    }
                });
            }
        }
        */
        startButton = (Button) findViewById(R.id.Start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.onStartClick(v);
            }
        });
        game = new Memory();


    }
    protected void startGame(){
        game.startGame(buttons,this);
        buttons[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(0, 0, v);
            }
        });
        buttons[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(0,1,v);
            }
        });
        buttons[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(0,2,v);
            }
        });
        buttons[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(0,3,v);
            }
        });
        buttons[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(1,0,v);
            }
        });
        buttons[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(1,1,v);
            }
        });
        buttons[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(1,2,v);
            }
        });
        buttons[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(1, 3, v);
            }
        });
        buttons[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(2,0,v);
            }
        });
        buttons[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(2,1,v);
            }
        });
        buttons[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(2,2,v);
            }
        });
        buttons[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(2,3,v);
            }
        });
        buttons[3][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(3,0,v);
            }
        });
        buttons[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(3,1,v);
            }
        });
        buttons[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(3,2,v);
            }
        });
        buttons[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoryActivity.this.buttonInput(3, 3, v);
            }
        });
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                buttons[y][x].setEnabled(true);

            }
        }
        win.setText("");

        //game.run();
    }
    public void onStartClick(View v){
        startGame();
    }
    public void buttonInput(int y, int x, View v){
        game.makeMoves(x, y);
    }
    public void Win(){
        popup(getResources().getString(R.string.win));
    }
}
