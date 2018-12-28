package Yahtzee_Assignment_3_4;

import java.util.Arrays;
import java.util.Scanner;

public class YahtzeeSoloGame 
{
	private YahtzeeScoreCard scoreCard;
	private YahtzeeScoreCard computerCard;
	
	public YahtzeeSoloGame()
	{
		this.scoreCard = new YahtzeeScoreCard(new YahtzeeHand());
		this.computerCard = new YahtzeeScoreCard(new YahtzeeHand());
	}
	
	public void gameStart()
	{
		System.out.println("Welcome to a Yhatzee Game. You can end/restart the game at any time."
				+"\n You get 3-total rolls, the best score after all 3-rolls is your score, the computer"
				+"\n will play with you and at the end of your rolls your scores will be compared!");
		boolean exit = false;
		Scanner in = new Scanner(System.in);
		String choice = "";
		int rolls = 0;
		int bestUserScore = 0;
		int bestComputerScore = 0;
		String winner = "";
		while(!exit)
		{
			System.out.println("Please enter a command, to see a list of commands enter '?'");
			choice = in.nextLine().toLowerCase();
			switch(choice)
			{
			case "?":
				// HAL gets to cheat every time you do something
				computerTurn();
				System.out.println("Commands: \n Re-roll die/dice - > Dice"
						+"\n End/Restart Game -> End Game \n See Scores -> Score");
				break;
			
			case "dice":
				if(rolls < 3)
				{
					System.out.println("Enter a 1 if you wish to re-roll a die or a 0 if you do not for each of the 5 die. "
							+"\n Example: 10101. This rolls the first, third, and fifth die.");
					String rerolls = in.nextLine();
					// Clever thing for dice re-rolls
					boolean[] choices = new boolean[6];
					Arrays.fill(choices, Boolean.FALSE);
					int i = 0;
					for(char c : rerolls.toCharArray())
					{
						if(c == '1') {choices[i] = true;}
						i++;
					}
					scoreCard.rollDice(choices[0], choices[1], choices[2], choices[3], choices[4], false);				
					rolls++;
				}
				else
				{
					System.out.println("You have already used all of your rolls.");
				}
				// HAL gets to cheat every time you do something
				computerTurn();
				break;
			
			case "score":
				// HAL gets to cheat every time you do something
				computerTurn();
				scoreCard.displayScoresheet();
				break;
			
			case "end game":
				// HAL gets to cheat every time you do something
				computerTurn();
				System.out.println("Goodbye. ");
				exit = true;
			}
			
			if(scoreCard.getTotal() > bestUserScore) {bestUserScore = scoreCard.getTotal();}
			if(computerCard.getTotal() > bestComputerScore) {bestComputerScore = computerCard.getTotal();}
		}
		// HAL gets to cheat every time you do something
		computerTurn();
		if(bestUserScore > bestComputerScore) {winner = "You have won!";}
		else {winner = "HAL has won! Don't feel bad, you're only human...";}
		System.out.println(winner);
		System.out.println("Your score: "+bestUserScore
						   +"\n HAL's score: "+bestComputerScore);
		
		in.close();
		
	}
	
	private void computerTurn()
	{
		// Re-roll all the dice, not smart but I'm lazy
		computerCard.rollDice(true, true, true, true, true, true);
		computerCard.scoreTotal();
		// This could be better and I could write something that reads the values of the dice
	}
}
