package Yahtzee_Assignment_3_4;

import java.util.ArrayList;
import java.util.Collections;

public class YahtzeeHand 
{
	private ArrayList<Die> dice;
	
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
	
	// Roll all die in list
	public void rollDice()
	{
		for(Die d : dice)
		{
			d.Roll();
		}
	}
	
	// For asg 5
	public void rollDie(int i)
	{
		dice.get(i).Roll();
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
	
	// scoring methods
	public int faceValue(int n)
	{
		return n*(count(n));
	}
	
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
	
	public int smallStraightValue()
	{
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
			if(i > 3)
			{
				if (values.contains(seq1)) {return 30;}
			}
		}	
		return 0;
	}
	
	public int largeStraightValue()
	{
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
		return 0;
	}
	
	public int yahtzeeValue()
	{
		for(int i = 1; i < dice.get(0).numSides()+1; i++)
		{
			if(count(i) == 5) {return 50;}
		}
		return 0;
	}
	
	public int chanceValue()
	{
		int temp = 0;
		for(Die d: dice)
		{
			temp+=d.getVal();
		}
		return temp;
	}
	
	public void dieSort()
	{
		// Real gross sorting call to sort the array of dice by their current value. 
		Collections.sort(dice, (d1, d2) -> Integer.valueOf(d1.getVal()).compareTo(d2.getVal()));
	}
	

}
