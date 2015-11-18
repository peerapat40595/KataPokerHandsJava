package Main;

import java.util.*;
import PokerHand.*;
import Card.*;

public class main {
	public static void main(String[] args) {
	
		PokerHand Player1Hand;
		PokerHand Player2Hand;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Black: ");
		String blackInput = scan.nextLine();
		Player1Hand = main.convertInputToPokerHand(blackInput.trim());
		
		System.out.print("White: ");
		String whiteInput = scan.nextLine();
		Player2Hand = main.convertInputToPokerHand(whiteInput.trim());
		
		if(Player1Hand.isWinRank(Player2Hand)){
			System.out.println("Black wins. - with " + Player1Hand.getHandRank());
		}
		else if(Player2Hand.isWinRank(Player1Hand)){
			System.out.println("White wins. - with " + Player2Hand.getHandRank());
		} 
		//Same Hand Rank Find Win Card Rank
		else if(Player1Hand.WinCardRank(Player2Hand) > 0){
			System.out.println("Black wins. - with " + Player1Hand.getHandRank() + ": " + convertToString(Player1Hand.WinCardRank(Player2Hand)));
		}
		else if(Player2Hand.WinCardRank(Player1Hand) > 0){
			System.out.println("White wins. - with " + Player2Hand.getHandRank() + ": " + convertToString(Player2Hand.WinCardRank(Player1Hand)));
		}
		else {
			System.out.println("Tie.");
		}
		
	}
	
	public static PokerHand convertInputToPokerHand(String input){
		Card[] cards = new Card[5];
		int j=0;
		String[] parts = input.split(" ");
		if(parts.length != 5){
			throw new IllegalArgumentException("Invalid Input");
		}
		for(int i = 0 ; i < parts.length ; i++)
		{
			if(parts[i].length()==2)
			{
				cards[j] = new Card(parts[i].charAt(0),parts[i].charAt(1));
				j++;
			}
		}
		return new PokerHand(cards);
	}
	
	public static String convertToString(int input){
		if(input < 10){
			return ""+input;
		}
		if(input == 10){
			return "10";
		}
		if(input == 11){
			return "Jack";
		}
		if(input == 12){
			return "Queen";
		}
		if(input == 13){
			return "King";
		}
		if(input == 14){
			return "Ace";
		}
		return "";
	}
	
}
