package com.example.asamoahfamily.alzheimers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class MemoryActivity extends BaseAct {
    ImageButton[][] buttons= new ImageButton[4][4];
    Memory game;
    Button startButton;

    Chronometer t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_memory);
        super.onCreate(savedInstanceState);
        
        screenTools();

        buttons[0][0] = (ImageButton) findViewById(R.id.button6);
        buttons[0][1] = (ImageButton) findViewById(R.id.button7);
        buttons[0][2] = (ImageButton) findViewById(R.id.button8);
        buttons[0][3] = (ImageButton) findViewById(R.id.button9);
        buttons[1][0] = (ImageButton) findViewById(R.id.button10);
        buttons[1][1] = (ImageButton) findViewById(R.id.button11);
        buttons[1][2] = (ImageButton) findViewById(R.id.button12);
        buttons[1][3] = (ImageButton) findViewById(R.id.button13);
        buttons[2][0] = (ImageButton) findViewById(R.id.button14);
        buttons[2][1] = (ImageButton) findViewById(R.id.button15);
        buttons[2][2] = (ImageButton) findViewById(R.id.button16);
        buttons[2][3] = (ImageButton) findViewById(R.id.button17);
        buttons[3][0] = (ImageButton) findViewById(R.id.button18);
        buttons[3][1] = (ImageButton) findViewById(R.id.button19);
        buttons[3][2] = (ImageButton) findViewById(R.id.button20);
        buttons[3][3] = (ImageButton) findViewById(R.id.button21);
        t = (Chronometer) findViewById(R.id.gameTimer);

        startButton = (Button) findViewById(R.id.Start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                t.start();
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
    }

    public void buttonInput(int y, int x, View v){
        game.makeMoves(x, y);
    }
    public void Win(){
        popup(getResources().getString(R.string.win));
        t.stop();
    }
}
