package Main;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Card.Card;
import PokerHand.PokerHand;

public class TestMain {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testValidInput(){
		main.convertInputToPokerHand("2H 3S AH AC 5D");
	}
	
	@Test
	public void testInvalidInput() {
		exception.expect(IllegalArgumentException.class);
	    main.convertInputToPokerHand("2H 3S AH AC 5D 4D");
	}
	
	@Test
	public void testInvalidInput2() {
		exception.expect(IllegalArgumentException.class);
	    main.convertInputToPokerHand("2H 3S AH AC");
	}
	
	@Test
	public void testInvalidInput3() {
		exception.expect(IllegalArgumentException.class);
	    main.convertInputToPokerHand("2DD 3S AH AC 5D");
	}
	

}
