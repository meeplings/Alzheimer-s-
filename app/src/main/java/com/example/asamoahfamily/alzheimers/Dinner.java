package com.example.asamoahfamily.alzheimers;

import android.graphics.Color;

import java.util.ArrayList;

public class Dinner extends Food{
	
	private final static int DINNER_TIME = 21;	
	protected Dinner (int prio, String na, boolean al, Color col,
			boolean e, ArrayList<String> favs){
		//Dinner time before 9pm --> 21, last param
		
		super(prio,na,al,col,e,favs,DINNER_TIME);

	}
	
	protected void checkTime(){
		if(calendar.get(calendar.HOUR_OF_DAY) > getATime())
			System.out.println("This patient needs to eat dinner!");
	}

}
