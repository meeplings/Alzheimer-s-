package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

public class ThemeHandler {
    
    private static final int[] YELLOW_THEME = {R.color.yPrime,  R.color.yLight, R.color.yDark,  R.color.yAccent,
    R.color.divideDark, R.color.darkPrimeT};
    
    private static final int[] DARK_BLUE_THEME = {R.color.dBPrime,  R.color.dBLight, R.color.dBDark,  R.color.dBAccent,
    R.color.divideLight, R.color.lightPrimeT};
    
    private static final int[] RED_THEME = {R.color.rPrime,  R.color.rLight, R.color.rDark,  R.color.rAccent,
    R.color.divideDark, R.color.darkPrimeT};
    
    private static final int[] BROWN_THEME = {R.color.brPrime,  R.color.brLight, R.color.brDark,  R.color.brAccent,
    R.color.divideLight, R.color.lightPrimeT};
    
    private static final int[] ORANGE_THEME = {R.color.oPrime,  R.color.oLight, R.color.oDark,  R.color.oAccent,
    R.color.divideDark, R.color.darkPrimeT};


    private int mPrime,mDark,mLight,mAcc,mTextPrime,mTextSecond,mDiv;
    private int[] mCols;
    public ThemeHandler(){
        mCols = new int[6];
        
        mPrime = 0;
        mDark = 0;
        mLight = 0;
        mAcc = 0;
        mTextPrime = 0;
        mTextSecond = 0;
        mDiv = 0;
    }

    public void setThemeCols(String t) {
        
        switch(t){
            case "RED":
                mCols = RED_THEME;
                break;
            case "DARK_BLUE":
                mCols = DARK_BLUE_THEME;
                break;
            case "BROWN":
                mCols = BROWN_THEME;
                break;
            case "YELLOW":
                mCols = YELLOW_THEME;
                break;
            case "ORANGE":
                mCols = "ORANGE_THEME";
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
        mTextSecond = R.color.secondT;
    }

    public int getmPrime() {
        return mPrime;
    }

    public int getmDark() {
        return mDark;
    }

    public int getmLight() {
        return mLight;
    }

    public int getmAcc() {
        return mAcc;
    }

    public int getmTextPrime() {
        return mTextPrime;
    }

    public int getmTextSecond() {
        return mTextSecond;
    }

    public int getmDiv() {
        return mDiv;
    }
}
