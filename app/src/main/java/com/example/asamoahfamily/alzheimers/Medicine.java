package com.example.asamoahfamily.alzheimers;

public class Medicine extends Tasks{

	static final String NO_DOSE = "No dosage set";
	
	protected Medicine(int prio, String na){
		super(prio,na);
		dosage = NO_DOSE;
	}
	String dosage;
	
	protected void setDosage(String d){
		dosage = d;
	}
	protected String getDosage(){
		return dosage;
	}

	protected boolean checkTime() {
		return false;
	}

}
