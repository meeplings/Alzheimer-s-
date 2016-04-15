package com.example.asamoahfamily.alzheimers;


public class Tasks{

	static final int TOP_PRIO = 4;
	static final int HIGH_PRIO = 3;
	static final int MED_PRIO = 2;
	static final int LOW_PRIO = 1;
	static final int NONE = 0;
	
	protected Tasks(int prio, String na){
		priority = prio;
		name = na;
        day=0;
        month = 0;
        year = 0;
        hour = 0;
        minute = 0;

	}

	protected Tasks(){

	}
	private int priority;
	private String name;


    private int hour,minute;
    private int day,month,year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getPrio() {
        return priority;
    }

    public void setPrio(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
