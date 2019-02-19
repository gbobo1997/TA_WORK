package katie_stover;
/*******************************************************************************
*                             Yahtzee Hand                                       *
*                                                                              *
* PROGRAMMER:       Katie Stover                                               *
* CLASS:            CS102                                                      *
* ASSIGNMENT:       Assignment assig-4                                         *
* INSTRUCTOR:       Dean Zeller                                                *
* SUBMISSION DATE:  Feb. 22, 2019                                              *
*                                                                              *
* DESCRIPTION:                                                                 *
* Create a die object to run tests. We will use in Assignment 4 as well.       *
*                                                                              *
* COPYRIGHT:                                                                   *
* This program is (c) 2019 Katie Stover and Dean Zeller. This is original work,*
* without use of outside sources.                                              *
*******************************************************************************/
import java.util.*;
public class Yahtzeehand
{
  ArrayList <Die> dice;
  private int dice_sides;
  
  public Yahtzeehand(int dNum, int sideNum)
  {
	  dice=new ArrayList<Die>();
	  this.dice_sides = sideNum;
	  for (int i=0; i<dNum; i++)
	  {
		  dice.add(new Die(sideNum));
	  }
	  
  }
  
  public Yahtzeehand(int dNum)
  {
	  this(dNum, 6);
  }
  
  public Yahtzeehand()
  {
	  this(5,6);
  }

  //methods
  public void rollDice()
  {
	  for (Die d: this.dice)
	  d.roll();
  }

  public String toString()
  {
	  String s=" ";
	  for (Die d: this.dice)
	  {
		  s+=d.getCurrentValue()+" ";
	  }
	  return s;
  }

  public void setDice (int d1,int d2, int d3, int d4, int d5)
  {
	  this.dice.get(0).cheat(d1);
	  this.dice.get(1).cheat(d2);
	  this.dice.get(2).cheat(d3);
	  this.dice.get(3).cheat(d4);
	  this.dice.get(4).cheat(d5);
  }
  
  public int countDice (int k)
  {
	  int count=0;
	  for (Die d : this.dice)
	  {
		  if (d.getCurrentValue()==k)
		  {
			  count++;
		  }
			  
	  }
	  return count;
  }
    
  // Scoring section
  
  public int faceValue(int n)
  {
	  	  return (countDice(n)*n);
  }
  
  
  public int threeKindValue()
  {
	  for(int i=0; i < this.dice_sides; i++)
	  {
		  if(countDice(i) > 2)
		  {
			  return i*countDice(i);
		  }
	  }
	  return 0;
  }
  
  public int fourKindValue()
  {
	  for(int i=0; i < this.dice_sides; i++)
	  {
		  if(countDice(i) > 3)
		  {
			  return i*countDice(i);
		  }
	  }
	  return 0;
  }
  
  public int yhatzeeValue()
  {
	  for(int i=0; i < this.dice_sides; i++)
	  {
		  if(countDice(i) > 4)
		  {
			  return i*countDice(i);
		  }
	  }
	  return 0;
  }
  
  public int fullHouseValue()
  {
	  boolean three = false;
	  boolean two =  false;
	  for(int i=0; i < this.dice_sides; i++)
	  {
		  if(countDice(i) == 3)
		  {
			  three = true;
		  }
		  if(countDice(i) == 2)
		  {
			  two = true;
		  }
	  }
	  if(three && two)
	  {
		  return 25;
	  }
	  else
	  {
		  return 0;
	  }	  
  }
  
  public void sortDice()
  {
	  Collections.sort(dice, (d1, d2) -> Integer.valueOf(d1.getCurrentValue()).compareTo(d2.getCurrentValue())); 
  }
  
  public int smallStraightValue()
  {
	  sortDice();
	  String values = "";
	  for(Die d: this.dice) 
	  {
		  values += d.getCurrentValue();
	  }
	  if(values.contains("1234")||values.contains("2345")||values.contains("3456"))
	  {
		  return 30;
	  }
	  else
	  {
		  return 0;
	  }
  }
  
  public int largeStraightValue()
  {
	  sortDice();
	  String values = "";
	  for(Die d: this.dice) 
	  {
		  values += d.getCurrentValue();
	  }
	  if(values.contains("12345")||values.contains("23456"))
	  {
		  return 40;
	  }
	  else
	  {
		  return 0;
	  }
  }
  
  public int chance()
  {
	  int sum = 0;
	  for(Die d: this.dice) 
	  {
		  sum+= d.getCurrentValue();
	  }
	  return sum;
  }
  
  public void printHeaderManual(int n)
  {
	  System.out.println("Yahtzee Hand Report \nCreating "+n+" manual YahtzeeHand examples");
	  System.out.println("        Dice      1s   2s   3s   4s   5s   6s   3k   4k   FH   Sm   Lg   Yt   Ch");
  }
  
  public void manualTenTest()
  {
	  setDice(2,1,3,6,1);	
	  System.out.println(reportLine(1));
	  setDice(6,2,5,3,4);	
	  System.out.println(reportLine(2));
	  
  }
  
  public String reportLine(int line)
  {
	  String header=line+"."+" "+toString();
	  String output = String.format("%s    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d", header, 
			  faceValue(1), 
			  faceValue(2), 
			  faceValue(3), 
			  faceValue(4), 
			  faceValue(5), 
			  faceValue(6), 
			  threeKindValue(),
			  fourKindValue(),
			  fullHouseValue(),
			  smallStraightValue(),
			  largeStraightValue(),
			  yhatzeeValue(),
			  chance()
			  );  
	  
	  return output;
  }
  
  public void testMethod(int numTests)
  {
	  double oneSum = 0;
	  int oneHit = 0;
	  
	  int twoSum = 0;
	  int twoHit = 0;
	  
	  int threeSum = 0;
	  int threeHit = 0;
	  
	  int fourSum = 0;
	  int fourHit = 0;
	  
	  int fiveSum = 0;
	  int fiveHit = 0;
	  
	  int sixSum = 0;
	  int sixHit = 0;
	  
	  int threeKSum = 0;
	  int threeKHit= 0;
	  
	  int fourKSum = 0;
	  int fourKHit = 0;
	  
	  int fullHouseSum = 0;
	  int fullHouseHit = 0;
	  
	  int smallStSum = 0;
	  int smallStHit = 0;
	  
	  int lrgStSum = 0;
	  int lrgStHit = 0;
	  
	  int yhatSum = 0;
	  int yhatHit = 0;
	  
	  int chSum = 0;
	  int chHit = numTests;
	  
	  
	  
	  printHeaderManual(numTests);
	  for(int i = 0; i < numTests; i++)
	 {
		 rollDice();
		 
		 oneSum += faceValue(1);
		 oneHit += hit(faceValue(1));
		 
		 twoSum += faceValue(2);
		 twoHit += hit(faceValue(2));
		 
		 threeSum += faceValue(3);
		 threeHit += hit(faceValue(3));
		 
		 fourSum += faceValue(4);
		 fourHit += hit(faceValue(4));
		 
		 fiveSum += faceValue(5);
		 fiveHit += hit(faceValue(5));
		 
		 sixSum += faceValue(6);
		 sixHit += hit(faceValue(6));
		 
		 threeKSum += threeKindValue();
		 threeKHit += hit(threeKindValue());
		 
		 fourKSum += fourKindValue();
		 fourKHit += hit(fourKindValue());
		 
		 fullHouseSum += fullHouseValue();
		 fullHouseHit += hit(fullHouseValue());
		 
		 smallStSum += smallStraightValue();
		 smallStHit += hit(smallStraightValue());
		 
		 lrgStSum += largeStraightValue();
		 lrgStHit += hit(largeStraightValue());
		 
		 yhatSum += yhatzeeValue();
		 yhatHit += hit(yhatzeeValue());
		 
		 chSum += chance(); 
		 
		 
		 System.out.println(reportLine(i));		 
	 }
	  System.out.println("        Dice      1s   2s   3s   4s   5s   6s   3k   4k   FH   Sm   Lg   Yt   Ch");
	  String header = "% non-zero   ";
	  String output = String.format("%s    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d", header, 
			  oneHit/numTests, 
			  twoHit/numTests, 
			  threeHit/numTests, 
			  fourHit/numTests, 
			  fiveHit/numTests, 
			  sixHit/numTests, 
			  threeKHit/numTests,
			  fourKHit/numTests,
			  fullHouseHit/numTests,
			  smallStHit/numTests,
			  lrgStHit/numTests,
			  yhatHit/numTests,
			  100
			  ); 
	  System.out.println(output);
	  header = "Average Score ";
	  String averages = String.format("%s    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d    %d", header, 
			  oneSum/numTests, 
			  twoSum/numTests, 
			  threeSum/numTests, 
			  fourSum/numTests, 
			  fiveSum/numTests, 
			  sixSum/numTests, 
			  threeKSum/numTests,
			  fourKSum/numTests,
			  fullHouseSum/numTests,
			  smallStSum/numTests,
			  lrgStSum/numTests,
			  yhatSum/numTests,
			  chSum/numTests
			  ); 
	  System.out.println(averages);
  }
  
  public int hit(int num)
  {
	  if (num != 0)
	  {
		  return 1;
	  }
	  else
	  {
		  return 0;
	  }
  }
  
  
  
  
  
  
}

     
