package com.example.asamoahfamily.alzheimers;

import android.graphics.Color;

import java.util.ArrayList;

public class Lunch extends Food{
	
	private static final int LUNCH_TIME = 15;
	protected Lunch(int prio, String na, boolean al, Color col,
			boolean e, ArrayList<String> favs){
		//Lunch time before 4pm --> 15, last param
		
		super(prio,na,al,col,e,favs,LUNCH_TIME);

	}
	
	protected void checkTime(){
		if(calendar.get(calendar.HOUR_OF_DAY) > getATime())
			System.out.println("This patient needs to eat lunch!");
	}

}
