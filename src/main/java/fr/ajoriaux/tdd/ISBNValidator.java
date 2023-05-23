package fr.ajoriaux.tdd;

public class ISBNValidator {

	public boolean validateISBN(String isbn) {
		if (isbn.length() != 10) throw new NumberFormatException("An ISBN should have 10 numbers.");
		return false;
	}
}
