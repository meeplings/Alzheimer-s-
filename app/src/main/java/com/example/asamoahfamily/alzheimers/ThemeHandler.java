package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

public class ThemeHandler {


    private int mPrime,mDark,mLight,mAcc,mTextPrime,mTextSecond,mDiv;
    private TypedValue readAttrs;

    public ThemeHandler(){
        readAttrs = new TypedValue();

        mPrime = 0;
        mDark = 0;
        mLight = 0;
        mAcc = 0;
        mTextPrime = 0;
        mTextSecond = 0;
        mDiv = 0;
    }

    public void setThemeCols(Context c) {
        c.getTheme().resolveAttribute(R.attr.colorPrime, readAttrs, true);
        int[] themeCols = {R.attr.colorPrime,R.attr.colorDark,R.attr.colorLight,R.attr.colorAccent, R.attr.textColorPrimary, R.attr.textColorSecondary,R.attr.divide};
        TypedArray colArray = c.obtainStyledAttributes(readAttrs.data, themeCols);
        mPrime = colArray.getColor(0, 0);
        mDark = colArray.getColor(1, 0);
        mLight = colArray.getColor(2,0);
        mAcc = colArray.getColor(3,0);
        mTextPrime = colArray.getColor(4,0);
        mTextSecond = colArray.getColor(5,0);
        mDiv = colArray.getColor(6,0);
        colArray.recycle();
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
