package com.example.asamoahfamily.alzheimers;

import java.util.Calendar;

public class Food extends Tasks{
	
	protected Food(int prio, String na, double aTime){
		super(prio,na);
		alertTime = aTime;
	}
	
	private double alertTime;
	private boolean hasEaten;
	private String meal;
	
	protected void setATime(double alertTime){
		this.alertTime=alertTime;
	}
	protected double getATime(){
		return alertTime;
	}
	protected void setEaten(boolean e){	hasEaten = e;}
	protected boolean getEaten(){
		return hasEaten;
	}
    protected void setMeal(String meal) {this.meal = meal;}

    protected String getMeal() {return meal;}

	@Override
	protected boolean checkTime() {
		int mTime = mFormat.get(Calendar.HOUR_OF_DAY);
		return Math.abs(alertTime - mTime) >= 2;
	}
}
