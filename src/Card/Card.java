package Card;

import java.util.*;

public class Card {
	
	private static final List<Character> Rank = Arrays.asList('2','3','4','5','6','7','8','9','T','J','Q','K','A');
	private static final List<Character> Suit = Arrays.asList('C','D','H','S');
	
	private char cardRank;
	private char cardSuit;
	
	public Card(char rank, char suit){
		if(isValidRank(rank)){
			cardRank = rank;
		}
		else {
			throw new IllegalArgumentException("Invalid rank:" + rank);
		}
		
		if(isValidSuit(suit)){
			cardSuit = suit;
		}
		else {
			throw new IllegalArgumentException("Invalid suit:" + suit);
		}
	}
	
	public char getRank(){
		return cardRank;
	}
	
	public char getSuit(){
		return cardSuit;
	}
	
	private boolean isValidRank(char rank){
		return 	Rank.contains(rank);
	}
	
	private boolean isValidSuit(char suit){
		return 	Suit.contains(suit);
	}
	
	
	public int getRankValue(){
		return Rank.indexOf(cardRank) + 2 ;
	}
	
	public int getSuitValue(){
		return Suit.indexOf(cardSuit) + 1 ;
	}
	
}
