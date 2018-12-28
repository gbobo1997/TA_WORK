// (c) Robert Carver 2018
//package HistogramDieInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HistInterface 
{
	private String reportName;
	private String comment;
	private ArrayList<Integer> data;
	private Histogram hGram; 
	private Scanner in;
	
	public HistInterface()
	{
		reportName = "";
		comment = "";
	}
	
	public void startInterface()
	{
		in = new Scanner(System.in);
		System.out.println("Welcome to the report interface, please enter a command to begin: \n"+
				"E - Enter raw data manually \n" + 
				"F - File input for raw data \n" + 
				"G - Generate data randomly with dice \n" + 
				"H - Create Histogram based on data and comment \n" + 
				"N - enter a name for current Histogram \n" + 
				"C - Enter a comment for current Histogram \n" + 
				"D - Display current Data \n" + 
				"S - Save histogram data in file \n" + 
				"R - Save histogram report in file \n" + 
				"Q - Quit program \n" + 
				"? - Display help menu");
		Boolean run = true;
		String command = "";
		while(run) 
		{			
			System.out.println("Please enter a command");
			command = in.nextLine();
			switch(command.toUpperCase())
			{
				case "E":
					data = enterData();
					System.out.println(displayData());
					break; 
					
				case "F":
					System.out.println("Enter the name of the file ex: 'file.txt.' \n=> ");
					String fileName = in.nextLine(); 
					data = fileData(fileName);
					System.out.println(displayData());
					break;
				
				case "G":
					System.out.println("Generating Dice Data: \n How many dice per roll? => ");
					int temp = Integer.parseInt(in.nextLine());
					ArrayList<Integer> tempList = new ArrayList<Integer>();
					for(int i = 0; i < temp; i++)
					{
						System.out.println("Enter #Sides for die "+(i+1)+"=> ");
						tempList.add(Integer.parseInt(in.nextLine()));
					}
					System.out.println("How many total rolls? \n=> ");
					int rolls = Integer.parseInt(in.nextLine());
					data = genRandomData(tempList, rolls);
					break;
				
				case "H":
					System.out.println("Generating histogram from supplied data.");
					System.out.println(genReport());
					break;
				
				case "N":
					System.out.println("Enter Name for current histogram. =>");
					reportName = in.nextLine();
					break;
					
				case "C":
					System.out.println("Enter comment for current histogram. =>");
					comment = in.nextLine();
					break;
				
				case "D":
					System.out.println(displayData());
					break;
				
				case "S":
					System.out.println("Enter the name of the file you wish to write to, ex 'file.txt' \n=> ");
					String newFileName = in.nextLine();
					WriteData(data, newFileName);
					System.out.println("Data written.");
					break;
			
				case "R":
					System.out.println("Enter the name of the file you wish to write to, ex 'file.txt' \n=> ");
					String file2 = in.nextLine();
					WriteGram(genReport(), file2);
					System.out.println("Histogram report written.");
					break;
					
				case "?":
					System.out.println("E - Enter raw data manually \n" + 
							"F - File input for raw data \n" + 
							"G - Generate data randomly with dice \n" + 
							"H - Create Histogram based on data and comment \n" + 
							"N - enter a name for current Histogram \n" + 
							"C - Enter a comment for current Histogram \n" + 
							"D - Display current Data \n" + 
							"S - Save histogram data in file \n" + 
							"R - Save histogram report in file \n" + 
							"Q - Quit program \n" + 
							"? - Display help menu");
					break;
				case "Q":
					System.out.println("Bye.");
					run = false; 
				default:
					System.out.println("Command Unknown.");
					break;
					
			}			
		}
		in.close();
	}
	
	public ArrayList<Integer> enterData()
	{
		//Scanner numIn = new Scanner(System.in);
		ArrayList<Integer> nums = new ArrayList<Integer>();
		int count = 0;
		System.out.println("Enter '-99' to exit.");
		int t = 0;
		while(t != -99)
		{
			System.out.println("Enter data "+count+" => ");
			t = Integer.parseInt(in.nextLine());
			if(t != -99)
			{
				nums.add(t);
				count++;
			}			
		}
		//numIn.close();
		return nums;
	}
	
	public ArrayList<Integer> genRandomData(ArrayList<Integer> values, int rollAmount)
	{
		ArrayList<Die> dice = new ArrayList<Die>();
		for(int n : values)
		{
			dice.add(new Die(n)); 
		}
		
		ArrayList<Integer> temp = new ArrayList<Integer>();		
		int tempSum = 0;
		for(int k  = 0; k < rollAmount; k++)
		{
			for(Die d : dice)
			{		
				tempSum+=d.Roll();
			}			
			temp.add(tempSum);
			tempSum = 0;
		}
		return temp;
	}
	
	public String displayData()
	{
		String str = "Data: ";
		for(int n : data)
		{
			str+=n+" ";
		}
		return str;
	}
	
	public String genReport()
	{
		hGram = new Histogram(data, comment);
		return hGram.generateHistogramVert();
	}
	
	public ArrayList<Integer> fileData(String file)
	{
		String str = "";
		try
		{
			str = String.join("\n",Files.readAllLines(Paths.get(file)));	         
		}
		catch(IOException e)
		{
			System.out.println(e);
		}    
		ArrayList<Integer> returnData = new ArrayList<Integer>();
	    List<String> rawData = Arrays.asList(str.split(","));
	      
	    for(String s : rawData)
	    {
	    	returnData.add(Integer.valueOf(s));
	    }
	    
	    return returnData;		
	}
	
	public void WriteData(ArrayList<Integer> passedData, String file)
	{
		try
		{
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			for(Integer n : passedData)
			{
				writer.print(n+",");
			}
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println(e);
	    }
	}
	
	public void WriteGram(String str, String file)
	{
		try
		{
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.println(str);
			writer.close();
		}		
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}
