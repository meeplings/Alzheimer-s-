package com.example.asamoahfamily.alzheimers;
import android.graphics.Color;

public abstract class Medicine extends Tasks{
	
	protected Medicine(int prio, String na, boolean al, Color col, String dose){
		super(HIGH_PRIO,na,al,col);
		dosage = dose;
	}

	String dosage;
	protected void checkTime(int alertTime){
		if(calendar.get(calendar.HOUR_OF_DAY) > alertTime)
			System.out.println("'" + getName() + "'" + " haven't been taken yet!");
	}

	
	protected void setDosage(String d){
		dosage = d;
	}
	protected String getDosage(){
		return "The dosage for this medicine = " + dosage;
	}

}
