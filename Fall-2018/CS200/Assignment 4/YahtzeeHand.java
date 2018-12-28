// Written by Robert Carver, (c) October 2018
//package Yahtzee_Assignment_3_4;

import java.util.ArrayList;
import java.util.Collections;

public class YahtzeeHand 
{
	private ArrayList<Die> dice;
	
	/********************* Start Constructors *******************************/
	// Constructor for default 5 dice of numSides = 6
	public YahtzeeHand()
	{
		dice  = new ArrayList<Die>();
		for(int i = 0; i< 5; i++)
		{
			dice.add(new Die(6));
		}
	}
	
	// Constructor for 1 int param, num of dice, and default 6sides
	public YahtzeeHand(int numDice)
	{
		dice = new ArrayList<Die>();
		for(int i = 0; i < numDice; i++)
		{
			dice.add(new Die(6));
		}
	}
	
	// Constructor for 2 int param specifying num dice, num sides
	public YahtzeeHand(int numDice, int numSides)
	{
		dice = new ArrayList<Die>();
		for(int i = 0; i < numDice; i++)
		{
			dice.add(new Die(numSides));
		}
	}
	/*********************** End Constructors *****************************/
	
	/********************** Utility Methods *******************************/
	// Roll all die in list
	public void rollDice()
	{
		for(Die d : dice)
		{
			d.Roll();
		}
	}
	
	// Printout the new roll
	public void printDice()
	{
		String temp = "";
		for(Die d : dice)
		{
			temp+= d.getVal() + ", ";
		}
		System.out.println("Dice: "+temp);
	}
	
	// Get # of die matching the parameter
	public int count(int num)
	{
		int count = 0;
		for(Die d : dice)
		{
			if(d.getVal() == num)
			{
				count++;
			}
		}
		return count;
	}
	
	// Print out all score methods
	public void report(int a)
	{
		System.out.println("+--------------------------+");
		System.out.println("|Face Value of "+a+"   : "+faceValue(a));
		
		System.out.println("|Three of a kind value : "+threeKindValue());
		
		System.out.println("|Four of a kind value  : "+fourKindValue());
		
		System.out.println("|Full house value      : "+fullHouseValue());
		
		System.out.println("|Small straight value  : "+smallStraightValue());
		
		System.out.println("|Large straight value  : "+largeStraightValue());
		
		System.out.println("|Yhatzee value         : "+ yahtzeeValue());
		
		System.out.println("|Chance value          : " +chanceValue());
		System.out.println("+--------------------------+");
	}
	
	/************************** Score Methods **********************/
	// Basic face value method
	public int faceValue(int n)
	{
		return n*(count(n));
	}
	
	// 3 of a kind value using the count method
	public int threeKindValue()
	{
		int temp = 0;
		int bound = dice.get(0).numSides() + 1;
		for(int i = 0; i < bound; i++)
		{
			if( count(i) == 3) 
			{
				temp = i*3;
			}
		}
		return temp;
	}
	
	// 4 of a kind detection using the count method
	public int fourKindValue()
	{
		int temp = 0;
		int bound = dice.get(0).numSides() + 1;
		for(int i = 0; i < bound; i++)
		{
			if (count(i) == 4)
			{
				temp = i*4;
			}
		}
		return temp;
	}
	
	// Full house detection
	public int fullHouseValue()
	{
		int bound = dice.get(0).numSides() + 1;
		boolean a = false;
		boolean b = false;
		for(int i = 0; i < bound; i++)
		{
			if (count(i) == 3)
			{
				a = true;
			}
			if(count(i) == 2)
			{
				b = true;
			}
		}
		if(a && b)
		{
			return 25;
		}
		else
		{
			return 0;
		}
	}
	
	// Detect a small straight using a string comparison 
	public int smallStraightValue()
	{
		/*String values = "";
		String seq1 = "";
		dieSort();
		for(Die d : dice)
		{
			values+=d.getVal();
		}*/
		if(count(1) > 1 && count(2) > 1 && count(3) > 1)
		{
			return 30;
		}
		else if(count(1) > 1 && count(2) > 1 && count(3) > 1 && count(4) > 1)
		{
			System.out.println("Small Straight is actually a large straight!");
			return 30;
		}
		else
		{
			return 0;
		}
		// Edited out the code below/start of method, the above is a simpler way to complete the task, it requires more
		// comparisons but is more complex and can overlook cases where the sequence of dice values has repeats
		// if we wanted to do it using a single comparison instead of 7 count() calls we would clean the 
		// values string to remove any duplicates before doing the comparison below. 
		/*
		// Build the sequence, doing it this way so that it can scale
		// to much longer sequences
		for(int i = 1; i < dice.get(0).numSides()+1; i++)
		{
			seq1 += i;
			if(i > 3)
			{
				if (values.contains(seq1)) {return 30;}
			}
		}	
		return 0;*/
	}
	
	// Detect a large straight and return proper value if present (string comparison)
	public int largeStraightValue()
	{
		if(count(1) > 1 && count(2) > 1 && count(3) > 1 && count(4)> 1)
		{
			return 30;
		}
		else if(count(1) > 1 && count(2) > 1 && count(3) > 1)
		{
			System.out.println("Large Straight is actually a small straight!");
			return 30;
		}
		else
		{
			return 0;
		}
		// See comment in small straight method for explanation of commented out code
		/*
		
		String values = "";
		String seq1 = "";
		dieSort();
		for(Die d : dice)
		{
			values+=d.getVal();
		}
		// Build the sequence, doing it this way so that it can scale
		// to much longer sequences
		for(int i = 1; i < dice.get(0).numSides()+1; i++)
		{
			seq1 += i;
			if(i > 4)
			{
				if (values.contains(seq1)) {return 30;}
			}
		}	
		return 0;*/
	}
	
	// Return Yahtzee value if all dice values = 5
	public int yahtzeeValue()
	{
		for(int i = 1; i < dice.get(0).numSides()+1; i++)
		{
			if(count(i) == 5) {return 50;}
		}
		return 0;
	}
	
	// Return sum of all dice value
	public int chanceValue()
	{
		int temp = 0;
		for(Die d: dice)
		{
			temp+=d.getVal();
		}
		return temp;
	}
	
	// Sort method for utility purposes 
	// This method is not needed! However I am leaving it in as an example of how
	// to sort objects by their attributes
	public void dieSort()
	{
		// Real gross sorting call to sort the array of dice by their current value. 
		Collections.sort(dice, (d1, d2) -> Integer.valueOf(d1.getVal()).compareTo(d2.getVal()));
	}
	

}
