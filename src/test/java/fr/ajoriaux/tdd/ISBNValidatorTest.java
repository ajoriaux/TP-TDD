package fr.ajoriaux.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISBNValidatorTest {
	private ISBNValidator validator;
	
	@BeforeEach
	public void init() {
		validator = new ISBNValidator();
	}
	
	@Test
	public void invalidLengthThrowException() {
		assertThrows(NumberFormatException.class, () -> validator.validateISBN("222647272"));
		assertThrows(NumberFormatException.class, () -> validator.validateISBN("22264727278"));
	}
	
	@Test
	public void shouldHaveOnlyDigits() {
		assertThrows(NumberFormatException.class, () -> validator.validateISBN("271449A323"));
	}
	
	@Test
	public void checkValidISBN() {
		assertTrue(validator.validateISBN("2714493238"));
	}
	
	@Test
	public void lastCharactercanBeX() {
		assertTrue(validator.validateISBN("222647272X"));
	}
}