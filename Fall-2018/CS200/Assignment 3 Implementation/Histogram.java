// (c) Robert Carver 2018
//package HistogramDieInterface;

import java.util.ArrayList;
import java.util.Collections;

public class Histogram 
{
	private ArrayList<Integer> data = new ArrayList<Integer>();
	private String name;
	public Histogram(ArrayList<Integer> dataPassed, String namePassed)
	{
		this.data = dataPassed;
		this.name = namePassed;
		OrderData();
	}
	
	public String generateHistogramVert()
	{
		String str = "";
		ArrayList<Integer> temp = genOccurList();
		ArrayList<Integer> nums = getNums();
		for(int i = 0; i <nums.size(); i++)
		{
			str+=nums.get(i)+" | ";
			for(int k = 0; k < temp.get(i); k++)
			{
				str+=" X ";
			}
			str+="\n";
		}
		return str;
	}
	
	public String generateHistogramHor()
	{
		return "";
	}
	
	public int getOccurences(int num)
	{
		return Collections.frequency(data, num);
	}
	
	public ArrayList<Integer> genOccurList()
	{
		ArrayList<Integer> occur = new ArrayList<Integer>();	
		
		for(int n : getNums())
		{
			occur.add(getOccurences(n));
		}
		return occur;
	}
	
	public ArrayList<Integer> getNums()
	{
		ArrayList<Integer> nums = new ArrayList<Integer>();
		// Determine the distinct values in data
		for(int n : data)
		{
			if(!nums.contains(n)) {nums.add(n);}
		}
		return nums;
	}
	
	public void OrderData()
	{
		Collections.sort(data);
	}
	
}
