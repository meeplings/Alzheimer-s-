package com.example.asamoahfamily.alzheimers;


import android.os.Handler;
import android.widget.ImageButton;

import java.util.Random;

public class Memory  {
    static Card[][] board;
    static ImageButton[][] imageBoard ;
    static int move;
    public MemoryActivity thing;
    public final static int[] images = {R.drawable.c01, R.drawable.d01, R.drawable.h01, R.drawable.s01,R.drawable.c13, R.drawable.d13, R.drawable.h13, R.drawable.s13};
    //difficulty will determine the amount of copies of each card
    public void startGame(ImageButton[][] imageBoard,MemoryActivity a){
        thing=a;

        this.imageBoard=imageBoard;
        int x=imageBoard.length;
        int y=imageBoard[0].length;
        board=new Card[y][x];
        Random rand = new Random();
        int numCards=(x*y)/2;

        for(int i=0;i<numCards;i++){
            for(int card=0;card<2;card++) {
                boolean setCard=false;
                while(!setCard) {
                    int checkX = rand.nextInt(x);
                    int checkY = rand.nextInt(y);
                    if (board[checkY][checkX]==null) {
                        board[checkY][checkX] = new Card(i);
                        setCard=true;
                    }
                }
            }
        }
        move=0;
        for(int y1=0;y1<4;y1++){
            for(int x1=0;x1<4;x1++){
                imageBoard[y1][x1].setBackgroundResource(R.drawable.back);
            }
        }
    }

    static int[] checkedCards;
    static int[] checkedCards2;
    static int checkedVal;


    public void makeMoves(int x, int y){

        if(move%2==0) {
            checkedCards = new int[2];
            checkedCards2 = new int[2];
            checkedCards[0]=y;
            checkedCards[1]=x;
            checkedVal=board[y][x].getVal();
            imageBoard[y][x].setBackgroundResource(images[board[y][x].getVal()]);
            imageBoard[y][x].setEnabled(false);
            move++;

        }
        else if(move%2==1){
            checkedCards2[0]=y;
            checkedCards2[1]=x;
            imageBoard[y][x].setBackgroundResource(images[board[y][x].getVal()]);
            imageBoard[y][x].setEnabled(false);
            move=-1;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(board[checkedCards2[0]][checkedCards2[1]].getVal()==checkedVal){
                        imageBoard[checkedCards2[0]][checkedCards2[1]].setBackgroundResource(R.drawable.blank);
                        imageBoard[checkedCards[0]][checkedCards[1]].setBackgroundResource(R.drawable.blank);

                        board[checkedCards2[0]][checkedCards2[1]].setVal(-1);
                        board[checkedCards[0]][checkedCards[1]].setVal(-1);
                    }
                    else{
                        imageBoard[checkedCards[0]][checkedCards[1]].setEnabled(true);
                        imageBoard[checkedCards2[0]][checkedCards2[1]].setEnabled(true);
                        imageBoard[checkedCards[0]][checkedCards[1]].setBackgroundResource(R.drawable.back);
                        imageBoard[checkedCards2[0]][checkedCards2[1]].setBackgroundResource(R.drawable.back);

                    }
                    move++;
                    if(getWin()){
                        thing.Win();
                    }

                    }
            }, 500);

        }
    }

    public boolean getWin(){
    	 for(int x=0;x<board.length;x++){
             for(int y=0;y<board[0].length;y++){

                 if(board[x][y].getVal()!=-1)
                	 return false;
             }
         }
    	 return true;
    }



    /*
    public void run(){
    	while(!getWin()){
    		makeMoves();
    		printBoard();
    	}
    	//Will be replaced with graphics
    	System.out.println("You Win");
    }
    */
    

}
