package com.ibm.library.model;

import com.ibm.library.exception.BadValue;

public class BookNonFiction extends Book {
	
	public BookNonFiction() {
		super();
	}
	
	public BookNonFiction(String isbn, String title, String author) {
		super(isbn, title, author);
	}
	
	@Override
	public double calculateLateFee(int numDaysLate) throws BadValue {
		double lateFee = 0.0;
		
		if (numDaysLate < 0) {
			throw new BadValue("numDaysLate is negative");
		}
		
		lateFee = numDaysLate * 1.5 * .8;

		return lateFee;
	}
}
