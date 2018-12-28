package Yahtzee_Assignment_3_4;

public class YahtzeeScoreCard 
{
	// Too many private int attributes
	private int onesScore = -1;
	private int twosScore = -1;
	private int threesScore = -1;
	private int foursScore = -1;
	private int fivesScore = -1;
	private int sixesScore = -1;
	private int bonusScore = -1;
	private int threeKindScore = -1;
	private int fourKindScore = -1;
	private int fullHouseScore = -1;
	private int smallStraightScore = -1;
	private int largeStraightScore = -1;
	private int yahtzeeScore = -1;
	private int chanceScore = -1;
	private int totalScore = -1;
	
	private YahtzeeHand hand;
	
	public YahtzeeScoreCard(YahtzeeHand hand_)
	{
		this.hand = hand_;
	}
	
	public void rollDice(boolean d1, boolean d2, boolean d3, boolean d4, boolean d5, boolean AI)
	{
		if(d1) hand.rollDie(0);
		if(d2) hand.rollDie(1);
		if(d3) hand.rollDie(2);
		if(d4) hand.rollDie(3);
		if(d5) hand.rollDie(4);
		if(!AI) {
		hand.printDice();
		}
	}
	
	public void scoreOnes() {this.onesScore = hand.faceValue(1);}
	
	public void scoreTwos() {this.twosScore = hand.faceValue(2);}
	
	public void scoreThrees() {this.threesScore = hand.faceValue(3);}
	
	public void scoreFours() {this.foursScore = hand.faceValue(4);}
	
	public void scoreFives() {this.fivesScore = hand.faceValue(5);}
	
	public void scoreSixes() {this.sixesScore = hand.faceValue(6);}
	
	public void bonusScore()
	{
		if(this.onesScore+this.twosScore+this.threesScore+this.foursScore+this.fivesScore+this.sixesScore > 62) {this.bonusScore = 35;}
		else {this.bonusScore = 0;}
	}
	
	public void scoreThreeKind() {this.threeKindScore = hand.threeKindValue();}
	
	public void scoreFourKind() {this.fourKindScore = hand.fourKindValue();}
	
	public void scoreFullHouse() {this.fullHouseScore = hand.fullHouseValue();}
	
	public void scoreSmallStraight() {this.smallStraightScore = hand.smallStraightValue();}
	
	public void scoreLargeStraight() {this.largeStraightScore = hand.largeStraightValue();}
	
	public void scoreYahtzee() {this.yahtzeeScore = hand.yahtzeeValue();}
	
	public void scoreChance() {this.chanceScore = hand.chanceValue();}
	
	// This is added in addition to what the assignment states
	public int getTotal() {return this.totalScore;}
	
	public void scoreTotal() 
	{
		// Call every got dang method here
		scoreOnes();
		scoreTwos();
		scoreThrees();
		scoreFours();
		scoreFives();
		scoreSixes();
		bonusScore();
		scoreThreeKind();
		scoreFourKind();
		scoreFullHouse();
		scoreSmallStraight();
		scoreLargeStraight();
		scoreYahtzee();
		scoreChance();
		
		// Oh jeez
		this.totalScore = this.onesScore + this.twosScore 
				+ this.threesScore + this.foursScore 
				+ this.fivesScore + this.sixesScore 
				+ this.bonusScore + this.threeKindScore 
				+ this.fourKindScore + this.fullHouseScore 
				+ this.smallStraightScore + this.largeStraightScore 
				+ this.yahtzeeScore + this.chanceScore;
	}
	
	public void displayScoresheet()
	{
		// Calculate Score before printing
		scoreTotal();
		// This is my neatly formatted table ie. no string formatting but they're all ints so this is fine
		System.out.println("+--------------------------------------------------+\n"
						  +"| Ones Score:     "+this.onesScore+  "-------------------------------|\n"
						  +"| Twos Score:     "+this.twosScore+  "-------------------------------|\n"
						  +"| Threes Score:   "+this.threesScore+"-------------------------------|\n"
						  +"| Fours Score:    "+this.foursScore+ "-------------------------------|\n"
						  +"| Fives Score:    "+this.fivesScore+ "-------------------------------|\n"
						  +"| Sixes Score:    "+this.sixesScore+ "-------------------------------|\n"
						  +"| Bonus Score:    "+this.bonusScore+ "-------------------------------|\n"
						  +"+--------------------------------------------------+\n"
						  +"| 3 of a Kind:    "+this.threeKindScore+"-------------------------------|\n"
						  +"| 4 of a Kind:    "+this.fourKindScore+"-------------------------------|\n"
						  +"| Full House:     "+this.fullHouseScore+"-------------------------------|\n"
						  +"| Small Straight: "+this.smallStraightScore+"-------------------------------|\n"
						  +"| Large Straight: "+this.largeStraightScore+"-------------------------------|\n"
						  +"| Yahtzee Score:  "+this.yahtzeeScore+"-------------------------------|\n"
						  +"| Total Score:    "+this.totalScore+"-------------------------------|\n"
						  +"+--------------------------------------------------|\n");
	}
	
}
