package fr.ajoriaux.tdd;

public class ISBNValidator {

	public boolean validateISBN(String isbn) {
		if (isbn.length() != 10) throw new NumberFormatException("An ISBN should have 10 numbers.");
		
		int total = 0;
		
		for (int i = 0; i< 10; i++) {
			if (!Character.isDigit(isbn.charAt(i))) {
				if (i == 9 && isbn.charAt(i) == 'X'){
					total += 10;
					break;
				}else {
					throw new NumberFormatException("An ISBN should only contains digits.");
				}
			}
			total += Character.getNumericValue(isbn.charAt(i)) * (10-i);
		}
		
		return (total % 11 == 0);
	}
}
