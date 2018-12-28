// (c) Robert Carver 2018
package Yahtzee_Assignment_3_4;
import java.util.Random;

public class Die 
{
	private int numSides;
	//private String name;
	private int currentVal;
	
	public Die(int num)
	{
		this.numSides = num;
		//this.name = "D"+num;
		this.currentVal = 1;
	}
	
	public void Roll()
	{
		Random rng = new Random();
		//return rng.nextInt(numSides);		
		currentVal = rng.nextInt(numSides+1);
	}
	
	public int getVal()
	{
		return currentVal;
	}
	
	public int numSides()
	{
		return numSides;
	}
}
