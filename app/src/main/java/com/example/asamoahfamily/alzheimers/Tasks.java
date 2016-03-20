package com.example.asamoahfamily.alzheimers;


import java.util.Calendar;
import java.util.Date;

public abstract class Tasks{

	static final int TOP_PRIO = 4;
	static final int HIGH_PRIO = 3;
	static final int MED_PRIO = 2;
	static final int LOW_PRIO = 1;
	static final int NONE = 0;
    static final int[] PRIO = {NONE,LOW_PRIO,MED_PRIO,HIGH_PRIO,TOP_PRIO};
	
	protected Tasks(int prio, String na){
		priority = prio;
		name = na;

        mDate = new Date();
        mFormat = Calendar.getInstance();
	}

	protected Tasks(){

	}
	
	protected abstract boolean checkTime();
	private int priority;
	private String name;
	private boolean alert;
	private int alertColor;
	private int id;

	protected Date mDate;
    protected static Calendar mFormat;

	
	protected int getPrio(){
		return priority;}
	protected String getName(){
		return name;}
	protected boolean isAlert(){
		return alert;}
	protected int getColor(){
		return alertColor;}
	protected void setPrio(int p){
		priority = p;}
	protected void setName(String n){
		name = n;}
	protected void setAlert(boolean a){
		alert = a;}
	protected void setColor(int c){
		alertColor = c;}
	protected int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
}
