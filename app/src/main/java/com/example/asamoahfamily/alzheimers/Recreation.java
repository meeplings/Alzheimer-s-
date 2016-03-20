package com.example.asamoahfamily.alzheimers;

/**
 * Created by Asamoah Family on 2/24/2016.
 */
public class Recreation extends Tasks{


    protected Recreation(int prio, String na, double hrs) {
        super(MED_PRIO, na);
        hoursRecorded = hrs;
    }
    private double hoursRecorded;
    protected double getHours(){
        return hoursRecorded;
    }
    protected void incHours(int newHours){hoursRecorded+=newHours;}

    protected boolean checkTime(){
        return false;
    }
}
