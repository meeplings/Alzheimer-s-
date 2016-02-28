package com.example.asamoahfamily.alzheimers;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;


public class Food extends Tasks{
	
	protected Food(int prio, String na, boolean al, Color col, 
			boolean e, ArrayList<String> foods, int aTime){
		super(HIGH_PRIO,na,al,col);
		
		alertTime = aTime;
		hasEaten = e;
		favFoods = foods;

	}
	
	private int alertTime;
	private boolean hasEaten;
	private List<String> favFoods;
	
	protected void setATime(int a){
		alertTime = a;
	}
	protected int getATime(){
		return alertTime;
	}
	protected void setEaten(boolean e){
		hasEaten = e;
	}
	protected boolean getEaten(){
		return hasEaten;
	}
	
	protected void getFavFoods(){
		System.out.println("This patient's favorite foods are: " + favFoods);
	}
	
	protected void addToFavFoods(String f){
		favFoods.add(f);
		System.out.println(f + "has been added!");
	}
	
	protected void removeFromFavFoods(String f){
		favFoods.remove(f);
		System.out.println(f + "has been removed!");
	}

	protected void checkTime(){
		if(calendar.get(calendar.HOUR_OF_DAY) > alertTime)
			System.out.println("This patient eat to do '" +getName());
	}
	
	
	


}
