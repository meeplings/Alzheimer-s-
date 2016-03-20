
package com.example.asamoahfamily.alzheimers;
import java.util.Random;
/**
 * Created by Jared on 3/14/2016.
 */
public class Memory {
    private int[][] board;
    private int difficulty;
    //difficulty will determine the amount of copies of each card
    public void startGame(int x, int y, int difficulty){
        board=new int[x][y];
        Random rand = new Random();
        int numCards=(x*y)/difficulty;

        for(int i=0;i<numCards;i++){
            for(int card=0;card<difficulty;card++) {
                boolean setCard=false;
                while(!setCard) {
                    int checkX = rand.nextInt(x);
                    int checkY = rand.nextInt(y);
                    if (board[checkX][checkY] == 0) {
                        board[checkX][checkY] = i + 1;
                        setCard=true;
                    }
                }
            }
        }
        this.difficulty=difficulty;
    }

    protected boolean checkTime(){
        return false;
    }


}
