package fr.ajoriaux.tdd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ISBNValidatorTest {
	@Test
	public void invalidLengthThrowException() {
		ISBNValidator validator = new ISBNValidator();
		assertThrows(NumberFormatException.class, () -> validator.validateISBN("222647272"));
		assertThrows(NumberFormatException.class, () -> validator.validateISBN("22264727278"));
	}
}