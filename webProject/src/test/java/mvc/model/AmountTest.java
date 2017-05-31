package mvc.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AmountTest {


	@Test
	public void whenPassOneDigitAsFractionPart_thenDigitIsTensofCents() {
		//Arrange Act Assert
		Amount amount = new Amount("2", "1");
		assertEquals(210, amount.getValue());
	}
	
	@Test
	public void whenPassTwoDigitsAsFractionPart_thenDigitIsCents() {
		//Arrange Act Assert
		Amount amount = new Amount("2", "11");
		assertEquals(211, amount.getValue());
		
	}
	
	@Test
	public void whenPassZeroAndADigitAsFractionPart_thenDigitIsCents() {
		//Arrange Act Assert
		Amount amount = new Amount("2", "01");
		assertEquals(201, amount.getValue());

	}

}
