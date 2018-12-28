// Written by Robert Carver, (c) October 2018
//package Yahtzee_Assignment_3_4;

public class YhatzeeHandTester {

	public static void main(String[] args) 
	{
		YahtzeeHand test = new YahtzeeHand();
		test.rollDice();
		test.printDice();
		
		System.out.println(test.chanceValue());
		
		test.dieSort();
		test.printDice();
		
		test.report(4);
	}

}
