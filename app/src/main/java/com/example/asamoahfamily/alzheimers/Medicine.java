package com.example.asamoahfamily.alzheimers;
public class Medicine extends Tasks{

	static final String NO_DOSE = "No dosage set";
	
	protected Medicine(int prio, String na, boolean al, int col){
		super(HIGH_PRIO,na,al,col);
		dosage = NO_DOSE;
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
