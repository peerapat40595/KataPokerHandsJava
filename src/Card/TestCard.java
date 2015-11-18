package Card;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestCard {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testValidCard() {
		new Card('2','S');
		new Card('A','C');
		new Card('T','D');
		new Card('K','H');
	}
	
	@Test
	public void testInvalidRank() {
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage(containsString("Invalid rank"));
		new Card('1','C');
	}
	
	@Test
	public void testInvalidSuit() {
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage(containsString("Invalid suit"));
		new Card('T','A');
	}

	
	@Test
	public void testGetRank(){
		Card firstCard = new Card('T','H');
		Card secondCard = new Card('2','H');
		Card thirdCard = new Card('A','D');
		
		assertEquals(firstCard.getRankValue(), 10);
		assertEquals(secondCard.getRankValue(), 2);
		assertEquals(thirdCard.getRankValue(), 14);
		
		assertEquals(firstCard.getRank(), 'T');
		assertEquals(secondCard.getRank(), '2');
		assertEquals(thirdCard.getRank(), 'A');
	}
	
	@Test
	public void testGetSuit(){
		Card firstCard = new Card('A','C');
		Card secondCard = new Card('2','D');
		Card thirdCard = new Card('T','H');
		Card forthCard = new Card('A','S');
		
		assertEquals(firstCard.getSuitValue(), 1);
		assertEquals(secondCard.getSuitValue(), 2);
		assertEquals(thirdCard.getSuitValue(), 3);
		assertEquals(forthCard.getSuitValue(), 4);
		
		assertEquals(firstCard.getSuit(), 'C');
		assertEquals(secondCard.getSuit(), 'D');
		assertEquals(thirdCard.getSuit(), 'H');
		assertEquals(forthCard.getSuit(), 'S');
	}
	

}
