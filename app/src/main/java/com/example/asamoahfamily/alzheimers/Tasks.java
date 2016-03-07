package com.example.asamoahfamily.alzheimers;

import android.graphics.Color;

import java.util.Calendar;

public abstract class Tasks {

	static final int HIGH_PRIO = 3;
	static final int MED_PRIO = 2;
	static final int LOW_PRIO = 1;
	
	protected Tasks(int prio, String na, boolean al, Color col){
		priority = prio;
		name = na;
		alert = al;
		alertColor = col;


		calendar = calendar.getInstance();
		
	}
	
	protected void checkTime(int alertTime){
		if(calendar.get(calendar.HOUR_OF_DAY) > alertTime)
			System.out.println("This patient needs to do '" + getName()+ "'");
		
	}

//	protected boolean checkTime(){
//		calendar.DATE
//
//	}
	
	private int priority;
	private String name;
	private boolean alert;
	private Color alertColor;
	
	protected Calendar calendar;
	
	protected int getPrio(){
		return priority;}
	protected String getName(){
		return name;}
	protected boolean isAlert(){
		return alert;}
	protected Color getColor(){
		return alertColor;}
	
	protected void setPrio(int p){
		priority = p;}
	protected void setName(String n){
		name = n;}
	protected void setAlert(boolean a){
		alert = a;}
	protected void setColor(Color c){
		alertColor = c;}
	
	

}
