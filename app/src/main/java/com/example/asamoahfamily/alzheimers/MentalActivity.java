package com.example.asamoahfamily.alzheimers;

import android.graphics.Color;

/**
 * Created by Asamoah Family on 2/24/2016.
 */
public abstract class MentalActivity extends Tasks{
    protected MentalActivity(int prio, String na, boolean al, Color col, double hrs, boolean ch) {
        super(MED_PRIO, na, al, col);
        hoursRecorded = hrs;
        challenge = ch;
        interactions = 0;
    }

    private double hoursRecorded;
    private boolean challenge;
    private int interactions;

    protected double getHoursRecorded(){
        return hoursRecorded;
    }

    protected void incrementInteraction(){
        interactions++;
    }

    protected int getInteractions(){
        return interactions;
    }

    protected boolean isChallenge(){
        return challenge;
    }
}
