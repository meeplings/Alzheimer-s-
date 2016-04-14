package com.example.asamoahfamily.alzheimers;
public class Card {
	private int val;
	private boolean showing;
	public Card(int val){
		this.val=val;
		showing=false;
	}
	public void setShowing(boolean showing){
		this.showing=showing;
	}
	public boolean getShowing(){
		return showing;
	}
	public int getVal(){
		return val;
	}
	public void setVal(int val){
		this.val=val;
	}
	
	public void displayCard(){
		//will be replaced with graphical code
		
		if(showing){
			if(val>0)
				System.out.print(val+" ");
			
			else
				System.out.print("  ");
				
		}
		else
			System.out.print("- ");
	}
}
