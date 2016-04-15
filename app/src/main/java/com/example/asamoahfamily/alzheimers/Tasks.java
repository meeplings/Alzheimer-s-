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
        time = 0;
        mark = "";
	}
	
	private int priority;
	private String name;


    private float hour,minute;
    private float time;
    private String mark;
    
    public float getTime(){
    	return time;
    }
    
    public void setTime(float time){
    	this.time = time;
    }
    
    public void getMark(){
    	return mark;
    }
    
    public void setMark(String mark){
    	this.mark = mark;
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
