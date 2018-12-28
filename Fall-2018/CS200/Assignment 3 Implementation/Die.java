// (c) Robert Carver 2018
//package HistogramDieInterface;
import java.util.Random;

public class Die 
{
	private int numSides;
	private String name;
	
	public Die(int num)
	{
		this.numSides = num;
		this.name = "D"+num;
	}
	
	public int Roll()
	{
		Random rng = new Random();
		return rng.nextInt(numSides);		
	}
}
