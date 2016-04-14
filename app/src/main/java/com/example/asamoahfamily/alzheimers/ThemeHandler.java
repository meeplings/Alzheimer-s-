package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class ThemeHandler {
    
    private static int[] YELLOW_THEME;
    
    private static int[] DARK_BLUE_THEME;
    
    private static int[] RED_THEME;
    
    private static int[] BROWN_THEME;
    
    private static int[] TEAL_THEME;


    private static int mPrime,mDark,mLight,mAcc,mTextPrime,mTextSecond,mDiv,mAdj1,mAdj2;
    private static int[] mCols;
    public ThemeHandler(Context c){
        YELLOW_THEME = new int[]{ContextCompat.getColor(c,R.color.yPrime), ContextCompat.getColor(c, R.color.yLight),
                ContextCompat.getColor(c, R.color.yDark),ContextCompat.getColor(c, R.color.yAccent),
                ContextCompat.getColor(c,R.color.divideDark),ContextCompat.getColor(c,R.color.darkPrimeT),
                ContextCompat.getColor(c,R.color.yAdj1), ContextCompat.getColor(c,R.color.yAdj2)};

        RED_THEME = new int[]{ContextCompat.getColor(c,R.color.rPrime), ContextCompat.getColor(c, R.color.rLight),
                ContextCompat.getColor(c, R.color.rDark),ContextCompat.getColor(c, R.color.rAccent),
                ContextCompat.getColor(c,R.color.divideDark),ContextCompat.getColor(c,R.color.darkPrimeT),
                ContextCompat.getColor(c,R.color.rAdj1), ContextCompat.getColor(c,R.color.rAdj2)};

        TEAL_THEME = new int[]{ContextCompat.getColor(c,R.color.tPrime), ContextCompat.getColor(c, R.color.tLight),
                ContextCompat.getColor(c, R.color.tDark),ContextCompat.getColor(c, R.color.tAccent),
                ContextCompat.getColor(c,R.color.divideLight),ContextCompat.getColor(c,R.color.darkPrimeT),
                ContextCompat.getColor(c,R.color.tAdj1), ContextCompat.getColor(c,R.color.tAdj2)};

        DARK_BLUE_THEME = new int[]{ContextCompat.getColor(c,R.color.dBPrime), ContextCompat.getColor(c, R.color.dBLight),
                ContextCompat.getColor(c, R.color.dBDark),ContextCompat.getColor(c, R.color.dbAccent),
                ContextCompat.getColor(c,R.color.divideLight),ContextCompat.getColor(c,R.color.lightPrimeT),
                ContextCompat.getColor(c,R.color.dBAdj1), ContextCompat.getColor(c,R.color.dBAdj2)};

        BROWN_THEME = new int[]{ContextCompat.getColor(c,R.color.brPrime), ContextCompat.getColor(c, R.color.brLight),
                ContextCompat.getColor(c, R.color.brDark),ContextCompat.getColor(c, R.color.brAccent),
                ContextCompat.getColor(c,R.color.divideLight),ContextCompat.getColor(c,R.color.lightPrimeT),
                ContextCompat.getColor(c,R.color.brAdj1), ContextCompat.getColor(c,R.color.brAdj2)};

        mCols = new int[6];
        
        mPrime = 0;
        mDark = 0;
        mLight = 0;
        mAcc = 0;
        mTextPrime = 0;
        mTextSecond = 0;
        mDiv = 0;
    }

    public static void setThemeCols(String t) {
        
        switch(t){
            case "RED_THEME":
                mCols = RED_THEME;
                break;
            case "DARK_BLUE_THEME":
                mCols = DARK_BLUE_THEME;
                break;
            case "BROWN_THEME":
                mCols = BROWN_THEME;
                break;
            case "YELLOW_THEME":
                mCols = YELLOW_THEME;
                break;
            case "TEAL_THEME":
                mCols = TEAL_THEME;
                break;
            default:
                //TODO: add error to stop thing
        }
        
        mPrime = mCols[0];
        mLight = mCols[1];
        mDark = mCols[2];
        mAcc = mCols[3];
        mDiv = mCols[4];
        mTextPrime = mCols[5];
        mAdj1 = mCols[6];
        mAdj2 = mCols[7];
        mTextSecond = R.color.secondT;
    }

    public static int getmPrime() {
        return mPrime;
    }

    public static int getmDark() {
        return mDark;
    }

    public static int getmLight() {
        return mLight;
    }

    public static int getmAcc() {
        return mAcc;
    }

    public static int getmTextPrime() {
        return mTextPrime;
    }

    public static int getmTextSecond() {
        return mTextSecond;
    }

    public static int getmDiv() {
        return mDiv;
    }

    public static int getmAdj1() {
        return mAdj1;
    }

    public static int getmAdj2() {
        return mAdj2;
    }

    public static void setBGC(View bot, View side, View bg, View back, View bar){

        bg.setBackgroundColor(mLight);
        back.setBackgroundColor(mAdj1);
        bot.setBackgroundColor(mAdj2);
        side.setBackgroundColor(mDark);
        bar.setBackgroundColor(mDiv);
    }
}


