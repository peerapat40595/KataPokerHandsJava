package PokerHand;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Card.*;

public class TestPokerHand {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testValidHand() {
		Card[] cards = {
			new Card('2','S'),
			new Card('A','C'),
			new Card('T','D'),
			new Card('T','S'),
			new Card('K','H')	
		};
		new PokerHand(cards);
	}
	
	@Test
	public void testInValidHand() {
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage(containsString("Invalid Hand"));
		Card[] cards = {
			new Card('2','S'),
			new Card('A','C'),
			new Card('T','D'),
			new Card('T','S')
		};
		new PokerHand(cards);
	}
	
	@Test
	public void testValidHandAndSortByRank(){
		
		Card[] cards = {
			new Card('A', 'C'),
			new Card('T', 'S'),
			new Card('2', 'H'),
			new Card('6', 'D'),
			new Card('8', 'C')
		};
		PokerHand firstHand = new PokerHand(cards);
		cards = firstHand.getPokerHandCards();
		
		Card[] solutionCards = {
			new Card('2', 'H'),
			new Card('6', 'D'),
			new Card('8', 'C'),
			new Card('T', 'S'),
			new Card('A', 'C')
		};
		
		for(int i = 0 ; i < cards.length ; i++)
		{
			assertEquals(cards[i].getRank(), solutionCards[i].getRank());
			assertEquals(cards[i].getSuit(), solutionCards[i].getSuit());
		}
	}
	
	@Test
	public void testValidHandStraightFlushAndWinCardRank(){
		
		Card[] cards = {
				new Card('A', 'S'),
				new Card('T', 'S'),
				new Card('K', 'S'),
				new Card('J', 'S'),
				new Card('Q', 'S')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "Straight flush");
		assertEquals(firstHand.getHandRankValue(), 1);
		
		Card[] cards2 = {
				new Card('A', 'S'),
				new Card('3', 'S'),
				new Card('2', 'S'),
				new Card('4', 'S'),
				new Card('5', 'S')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "Straight flush");
		assertEquals(secondHand.getHandRankValue(), 1);
		
		assertEquals(firstHand.WinCardRank(secondHand), 14);//A
	}
	
	@Test
	public void testValidHandAndFourOfAKind(){
		
		Card[] cards = {
				new Card('A', 'S'),
				new Card('A', 'C'),
				new Card('A', 'H'),
				new Card('J', 'S'),
				new Card('A', 'D')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "Four of a kind");
		assertEquals(firstHand.getHandRankValue(), 2);
		
		Card[] cards2 = {
				new Card('2', 'D'),
				new Card('3', 'C'),
				new Card('2', 'C'),
				new Card('2', 'S'),
				new Card('2', 'H')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "Four of a kind");
		assertEquals(secondHand.getHandRankValue(), 2);
		
		assertEquals(firstHand.WinCardRank(secondHand), 14);//A
	}
	
	@Test
	public void testValidHandAndFullHouse(){
		
		Card[] cards = {
				new Card('T', 'S'),
				new Card('T', 'C'),
				new Card('J', 'H'),
				new Card('J', 'S'),
				new Card('T', 'D')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "Full House");
		assertEquals(firstHand.getHandRankValue(), 3);
		
		Card[] cards2 = {
				new Card('5', 'D'),
				new Card('3', 'C'),
				new Card('3', 'S'),
				new Card('5', 'S'),
				new Card('5', 'H')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "Full House");
		assertEquals(secondHand.getHandRankValue(), 3);
		
		assertEquals(firstHand.WinCardRank(secondHand), 10);//T
	}
	
	@Test
	public void testValidHandAndFlush(){
		
		Card[] cards = {
				new Card('A', 'H'),
				new Card('8', 'H'),
				new Card('J', 'H'),
				new Card('2', 'H'),
				new Card('5', 'H')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "Flush");
		assertEquals(firstHand.getHandRankValue(), 4);
		
		Card[] cards2 = {
				new Card('T', 'C'),
				new Card('9', 'C'),
				new Card('Q', 'C'),
				new Card('5', 'C'),
				new Card('6', 'C')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "Flush");
		assertEquals(secondHand.getHandRankValue(), 4);
		
		assertEquals(firstHand.WinCardRank(secondHand), 14);//A
	}
	
	@Test
	public void testValidHandAndStraight(){
		
		Card[] cards = {
				new Card('A', 'S'),
				new Card('2', 'C'),
				new Card('3', 'H'),
				new Card('5', 'S'),
				new Card('4', 'D')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "Straight");
		assertEquals(firstHand.getHandRankValue(), 5);
		
		Card[] cards2 = {
				new Card('8', 'D'),
				new Card('Q', 'C'),
				new Card('9', 'C'),
				new Card('T', 'S'),
				new Card('J', 'H')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "Straight");
		assertEquals(secondHand.getHandRankValue(), 5);
		
		assertEquals(firstHand.WinCardRank(secondHand), -12);//Q
	}
	
	@Test
	public void testValidHandThreeOfAKind(){
		
		Card[] cards = {
				new Card('J', 'S'),
				new Card('J', 'C'),
				new Card('3', 'H'),
				new Card('5', 'S'),
				new Card('J', 'D')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "Three of a kind");
		assertEquals(firstHand.getHandRankValue(), 6);
		
		Card[] cards2 = {
				new Card('8', 'D'),
				new Card('8', 'C'),
				new Card('9', 'C'),
				new Card('T', 'S'),
				new Card('8', 'H')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "Three of a kind");
		assertEquals(secondHand.getHandRankValue(), 6);
		
		assertEquals(firstHand.WinCardRank(secondHand), 11);//J
	}
	
	@Test
	public void testValidHandAndTwoPairs(){
		
		Card[] cards = {
				new Card('A', 'C'),
				new Card('A', 'H'),
				new Card('J', 'H'),
				new Card('2', 'H'),
				new Card('2', 'D')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "Two Pairs");
		assertEquals(firstHand.getHandRankValue(), 7);
		
		Card[] cards2 = {
				new Card('T', 'C'),
				new Card('9', 'S'),
				new Card('A', 'S'),
				new Card('A', 'D'),
				new Card('9', 'S')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "Two Pairs");
		assertEquals(secondHand.getHandRankValue(), 7);
		
		assertEquals(firstHand.WinCardRank(secondHand), -9);//9 (Ace same pair)
	}
	
	@Test
	public void testValidHandAndPair(){
		
		Card[] cards = {
				new Card('K', 'C'),
				new Card('4', 'H'),
				new Card('4', 'D'),
				new Card('Q', 'H'),
				new Card('2', 'D')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "Pair");
		assertEquals(firstHand.getHandRankValue(), 8);
		
		Card[] cards2 = {
				new Card('K', 'C'),
				new Card('8', 'S'),
				new Card('Q', 'C'),
				new Card('Q', 'D'),
				new Card('7', 'S')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "Pair");
		assertEquals(secondHand.getHandRankValue(), 8);
		
		assertEquals(firstHand.WinCardRank(secondHand), -12);//Q
	}
	
	@Test
	public void testValidHandAndHighCard(){
		
		Card[] cards = {
				new Card('2', 'H'),
				new Card('3', 'D'),
				new Card('5', 'S'),
				new Card('9', 'C'),
				new Card('K', 'D')
		};
		PokerHand firstHand = new PokerHand(cards);
		assertEquals(firstHand.getHandRank(), "High Card");
		assertEquals(firstHand.getHandRankValue(), 9);
		
		Card[] cards2 = {
				new Card('2', 'C'),
				new Card('3', 'H'),
				new Card('4', 'S'),
				new Card('8', 'C'),
				new Card('A', 'H')
		};
		PokerHand secondHand = new PokerHand(cards2);
		assertEquals(secondHand.getHandRank(), "High Card");
		assertEquals(secondHand.getHandRankValue(), 9);
		
		assertEquals(firstHand.WinCardRank(secondHand), -14);//A
	}
}
