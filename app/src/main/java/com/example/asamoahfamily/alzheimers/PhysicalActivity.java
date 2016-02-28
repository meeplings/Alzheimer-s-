package com.example.asamoahfamily.alzheimers;

import android.graphics.Color;

/**
 * Created by Asamoah Family on 2/24/2016.
 */
public abstract class PhysicalActivity extends Tasks{


    protected PhysicalActivity(int prio, String na, boolean al, Color col, boolean fav, double hrs) {
        super(MED_PRIO, na, al, col);
        favorite = fav;
        hoursRecorded = hrs;
    }

    private boolean favorite;
    private double hoursRecorded;

    protected boolean isFavorite(){
        return favorite;
    }
    protected double getHours(){
        return hoursRecorded;
    }
}