
package com.example.asamoahfamily.alzheimers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Memory {

    /* CARD ORDER (ALPHABETICAL)
    0 - Clubs
    1 - Diamonds
    2 - Hearts
    3 - Spades
     */

    private int cards[];

    public Memory(String difficulty){
        Random rand = new Random();
        List<Integer> suits = new ArrayList<>();
        suits.add(0);   suits.add(1);
        suits.add(2);   suits.add(3);
        switch (difficulty){
            case "EASY":
                cards = new int[13];
                cards = cardToSuit(cards,rand.nextInt(4));
                break;
            case "MEDIUM":
                int[] mSuit = new int[2];
                int[] m1 = new int[13];     int[] m2 = new int[13];
                cards = new int[26];
                for(int i = 0; i < mSuit.length; i++){
                    int x = suits.get(rand.nextInt(suits.size()));
                    suits.remove(x);
                    mSuit[i] = x;
                }
                m1 = cardToSuit(m1,mSuit[0]);   m2 = cardToSuit(m2,mSuit[1]);
                for(int i =0; i < 13; i ++) {
                    cards[i] = m1[i];
                    cards[i+13] = m2[i];
                }
                break;
            case "HARD":
                int[] hSuit = new int[4];
                int[] h1 = new int[13];     int[] h2 = new int[13];
                int[] h3 = new int[13];     int[] h4 = new int[13];
                cards = new int[52];
                for(int i = 0; i < hSuit.length; i++){
                    int x = suits.get(rand.nextInt(suits.size()));
                    suits.remove(x);
                    hSuit[i] = x;
                }
                h1 = cardToSuit(h1,hSuit[0]);   h2 = cardToSuit(h2,hSuit[1]);
                h3 = cardToSuit(h3,hSuit[2]);   h4 = cardToSuit(h4,hSuit[3]);
                for(int i =0; i < 13; i ++) {
                    cards[i] = h1[i];
                    cards[i+13] = h2[i];
                    cards[i+26] = h3[i];
                    cards[i+39] = h4[i];
                }

        }
    }

    private Cards[][] board;
    private int xdim;
    private int ydim;
    // Difficulty now for number of suits
//    public void startGame(int x, int y, int difficulty){
//        board=new Cards[x][y];
//        Random rand = new Random();
//        int numCards=(x*y)/difficulty;
//
//        for(int i=0;i<numCards;i++){
//            for(int card=0;card<difficulty;card++) {
//                boolean setCard=false;
//                while(!setCard) {
//                    int checkX = rand.nextInt(x);
//                    int checkY = rand.nextInt(y);
//                    if (board[checkX][checkY] == 0) {
//                        board[checkX][checkY] = i + 1;
//                        setCard=true;
//                    }
//                }
//            }
//        }
//    }

    protected boolean checkTime(){
        return false;
    }

    private int[] cardToSuit(int[] cards, int x){
        int startID;
        switch (x){
            case 0:
                startID = R.drawable.c01;
                break;
            case 1:
                startID = R.drawable.d01;
                break;
            case 2:
                startID = R.drawable.h01;
                break;
            case 3:
                startID = R.drawable.s01;
                break;
            default:
                throw new IllegalArgumentException("INVALID ID TYPE");

        }
        for(int i =0; i< cards.length; i++){
            cards[i] = startID;
            startID++;
        }
        return cards;
    }


}
