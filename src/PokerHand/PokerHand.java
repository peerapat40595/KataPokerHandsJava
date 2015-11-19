package PokerHand;
import java.util.*;

import Card.*;

public class PokerHand {
	private Card[] playerHandCards;
	private List<Integer> OneCardRank = new ArrayList<Integer>();
	private List<Integer> TwoCardRank = new ArrayList<Integer>();
	private List<Integer> ThreeCardRank = new ArrayList<Integer>();
	private List<Integer> FourCardRank = new ArrayList<Integer>();
	private int handRankValue;
	
	public PokerHand(Card[] cards){
		
		if(!isValidHand(cards)){
			throw new IllegalArgumentException("Invalid Hand");
		}
		playerHandCards = cards;
			
		sortByRank();// Sort Card By Rank
		setCardCount();// Set Card List
		setHandRankValue();// Set Hand Rank
		
	}
	
	public Card[] getPokerHandCards(){
		return playerHandCards;
	}
	
	private boolean isValidHand(Card[] cards){
		if(cards.length != 5){
			return false;
		}
		for(int i=cards.length - 1 ; i >= 1 ; i--){
			if(cards[i] == null){
				return false;
			}
		}
		return true;
	}
	
	/* 
	 only 5 playerHandCards use bubble sort
	 */
	private void sortByRank(){
		Card temp;
		int j=0;
		boolean swapped=true;
		
		while(swapped){
			swapped = false;
			j++;
			for(int i = 0 ; i < playerHandCards.length - j ; i++ ){
				if(playerHandCards[i].getRankValue() > playerHandCards[i+1].getRankValue()){
					temp = playerHandCards[i];
					playerHandCards[i] = playerHandCards[i+1];
					playerHandCards[i+1] = temp;
					swapped = true;
				}
			}
		}
	}
	
	private void setCardCount(){
		int count = 0;
		int rankTemp = 0;
		
		for(int i = playerHandCards.length - 1; i >= 0 ; i--){
			if(playerHandCards[i].getRankValue() != rankTemp ){
				rankTemp = playerHandCards[i].getRankValue();
				count = 0;
			}
			count++;
			if( count == 1 ){
				OneCardRank.add(rankTemp);
			}
			else if( count == 2){
				OneCardRank.remove(new Integer(rankTemp));
				TwoCardRank.add(rankTemp);
			}
			else if( count == 3){
				TwoCardRank.remove(new Integer(rankTemp));
				ThreeCardRank.add(rankTemp);
			}
			else if( count == 4){
				ThreeCardRank.remove(new Integer(rankTemp));
				FourCardRank.add(rankTemp);
			}
		}
		
	}

	private void setHandRankValue(){
		if(isStraightFlush(playerHandCards)){
			handRankValue = 1;
		}
		else if(isFourOfAKind(playerHandCards)){
			handRankValue = 2;
		}
		else if(isFullHouse(playerHandCards)){
			handRankValue = 3;
		}
		else if(isFlush(playerHandCards)){
			handRankValue = 4;
		}
		else if(isStraight(playerHandCards)){
			handRankValue = 5;
		}
		else if(isThreeOfAKind(playerHandCards)){
			handRankValue = 6;
		}
		else if(isTwoPairs(playerHandCards)){
			handRankValue = 7;
		}
		else if(isPairs(playerHandCards)){
			handRankValue =  8;
		}
		else if(isHighCard(playerHandCards)){
			handRankValue = 9;
		}
	}
	
	public int getHandRankValue(){
		return handRankValue;
	}
	
	public String getHandRank(){
		if(handRankValue == 1){
			return "Straight flush";
		}
		if(handRankValue == 2){
			return "Four of a kind";
		}
		if(handRankValue == 3){
			return "Full House";
		}
		if(handRankValue == 4){
			return "Flush";
		}
		if(handRankValue == 5){
			return "Straight";
		}
		if(handRankValue == 6){
			return "Three of a kind";
		}
		if(handRankValue == 7){
			return "Two Pairs";
		}
		if(handRankValue == 8){
			return "Pair";
		}
		if(handRankValue == 9){
			return "High Card";
		}
		return "";//impossible
	}
	
	private boolean isStraightFlush(Card[] cards){
		return isFlush(cards)&&isStraight(cards);
	}
	
	private boolean isFourOfAKind(Card[] cards){
		if( FourCardRank.size() == 1 ){
			return true;
		}
		return false;
	}
	
	private boolean isFullHouse(Card[] cards){
		if(ThreeCardRank.size() == 1 && TwoCardRank.size() == 1){
			return true;
		}
		return false;
	}
	
	private boolean isFlush(Card[] cards){
		
		for (int i = 1 ; i < cards.length - 1 ; i ++){
			if (cards[i].getSuitValue() != cards[i+1].getSuitValue()){
				return false;
			}
		}
		return true;
	}
	
	private boolean isStraight(Card[] cards){
		
		for( int i = 0 ; i < cards.length - 1 ; i++ ){
			if(cards[i].getRankValue() + 1 != cards[i+1].getRankValue()){
				// check 2 3 4 5 A 
				if(i == cards.length - 2 && cards[i].getRank()=='5'&&cards[i+1].getRank()=='A'){
					//Swap In List to 5 4 3 2 A
					OneCardRank.remove(new Integer(14));
					OneCardRank.add(14);
					return true;
				}
				else{
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isThreeOfAKind(Card[] cards){
		if(ThreeCardRank.size() == 1 && OneCardRank.size() == 2){
			return true;
		}
		return false;
	}
	
	private boolean isTwoPairs(Card[] cards){
		if(TwoCardRank.size() == 2 && OneCardRank.size() == 1){
			return true;
		}
		return false;
	}
	
	private boolean isPairs(Card[] cards){
		if(TwoCardRank.size() == 1 && OneCardRank.size() == 3){
			return true;
		}
		return false;
	}
	
	private boolean isHighCard(Card[] cards){
		if(OneCardRank.size() == 5){
			return true;
		}
		return false;
	}
	
	public boolean isWinRank(PokerHand opponentHand){
		if(handRankValue < opponentHand.handRankValue){
			return true;
		}
		if(handRankValue > opponentHand.handRankValue){
			return false;
		}
		return false;
	}

	public int WinCardRank(PokerHand opponentHand){
		if(WinCardRankList(FourCardRank, opponentHand.FourCardRank)!=0){
			return WinCardRankList(FourCardRank, opponentHand.FourCardRank);
		}
		if(WinCardRankList(ThreeCardRank, opponentHand.ThreeCardRank)!=0){
			return WinCardRankList(ThreeCardRank, opponentHand.ThreeCardRank);
		}
		if(WinCardRankList(TwoCardRank, opponentHand.TwoCardRank)!=0){
			return WinCardRankList(TwoCardRank, opponentHand.TwoCardRank);
		}
		if(WinCardRankList(OneCardRank, opponentHand.OneCardRank)!=0){
			return WinCardRankList(OneCardRank, opponentHand.OneCardRank);
		}
		return 0;//Tie
	}
	
	public String WinCardRankCount(PokerHand opponentHand){
		if(WinCardRankList(FourCardRank, opponentHand.FourCardRank)!=0){
			return "Four cards of";
		}
		if(WinCardRankList(ThreeCardRank, opponentHand.ThreeCardRank)!=0){
			return "Three cards of";
		}
		if(WinCardRankList(TwoCardRank, opponentHand.TwoCardRank)!=0){
			return "Two cards of";
		}
		if(WinCardRankList(OneCardRank, opponentHand.OneCardRank)!=0){
			return "High";
		}
		return "";//Tie
	}
	
	private int WinCardRankList(List<Integer> CardRank1,List<Integer> CardRank2){
		if(CardRank1.size() > CardRank2.size()){
			return +CardRank1.get(0);
		}
		if(CardRank1.size() < CardRank2.size()){
			return -CardRank2.get(0);
		} 
		for(int i = 0 ; i < CardRank1.size() ; i++){
			if(CardRank1.get(i) > CardRank2.get(i)){
				return +CardRank1.get(i);
			}
			else if(CardRank1.get(i) < CardRank2.get(i)){
				return -CardRank2.get(i);
			}
		}
		return 0;//Tie
	}
	
}
