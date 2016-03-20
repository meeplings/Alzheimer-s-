package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.Arrays;

public class ThemeHandler {

    private static final String TAG = "asamoahDebug";
    
    private static int[] YELLOW_THEME;
    
    private static int[] DARK_BLUE_THEME;
    
    private static int[] RED_THEME;
    
    private static int[] BROWN_THEME;
    
    private static int[] ORANGE_THEME;


    private static int mPrime,mDark,mLight,mAcc,mTextPrime,mTextSecond,mDiv;
    private static int[] mCols;
    public ThemeHandler(Context c){
        YELLOW_THEME = new int[]{ContextCompat.getColor(c,R.color.yPrime), ContextCompat.getColor(c, R.color.yLight),
                ContextCompat.getColor(c, R.color.yDark),ContextCompat.getColor(c, R.color.yAccent),
                ContextCompat.getColor(c,R.color.divideDark),ContextCompat.getColor(c,R.color.darkPrimeT)};

        RED_THEME = new int[]{ContextCompat.getColor(c,R.color.rPrime), ContextCompat.getColor(c, R.color.rLight),
                ContextCompat.getColor(c, R.color.rDark),ContextCompat.getColor(c, R.color.rAccent),
                ContextCompat.getColor(c,R.color.divideDark),ContextCompat.getColor(c,R.color.darkPrimeT)};

        ORANGE_THEME = new int[]{ContextCompat.getColor(c,R.color.oPrime), ContextCompat.getColor(c, R.color.oLight),
                ContextCompat.getColor(c, R.color.oDark),ContextCompat.getColor(c, R.color.oAccent),
                ContextCompat.getColor(c,R.color.divideDark),ContextCompat.getColor(c,R.color.darkPrimeT)};

        DARK_BLUE_THEME = new int[]{ContextCompat.getColor(c,R.color.dBPrime), ContextCompat.getColor(c, R.color.dBLight),
                ContextCompat.getColor(c, R.color.dBDark),ContextCompat.getColor(c, R.color.dbAccent),
                ContextCompat.getColor(c,R.color.divideLight),ContextCompat.getColor(c,R.color.lightPrimeT)};

        BROWN_THEME = new int[]{ContextCompat.getColor(c,R.color.brPrime), ContextCompat.getColor(c, R.color.brLight),
                ContextCompat.getColor(c, R.color.brDark),ContextCompat.getColor(c, R.color.brAccent),
                ContextCompat.getColor(c,R.color.divideLight),ContextCompat.getColor(c,R.color.lightPrimeT)};

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
            case "ORANGE_THEME":
                mCols = ORANGE_THEME;
                break;
            default:
                //TODO: add error to stop thing
        }

        Log.d(TAG, Arrays.toString(mCols));
        
        mPrime = mCols[0];
        mLight = mCols[1];
        mDark = mCols[2];
        mAcc = mCols[3];
        mDiv = mCols[4];
        mTextPrime = mCols[5];
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
}
