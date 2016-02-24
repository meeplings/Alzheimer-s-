package com.example.asamoahfamily.alzheimers;

import android.graphics.Color;

import java.util.ArrayList;

public class Breakfast extends Food{
	// Breakfast time before 11, last param
	private final static int BREAKFAST_TIME = 11;
	
	protected Breakfast(int prio, String na, boolean al, Color col,
			boolean e, ArrayList<String> favs){			
		super(prio,na,al,col,e,favs, BREAKFAST_TIME);
		
				

	}
}
