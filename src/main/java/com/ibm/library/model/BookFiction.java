package com.ibm.library.model;

import com.ibm.library.exception.BadValue;

public class BookFiction extends Book {
	
	public BookFiction() {
		super();
	}
	
	public BookFiction(String isbn, String title, String author) {
		super(isbn, title, author);
	}
	
	@Override
	public double calculateLateFee(int numDaysLate) throws BadValue {
		double lateFee = 0.0;
		
		if (numDaysLate < 0) {
			throw new BadValue("numDaysLate is negative");
		}
		
		lateFee = ( numDaysLate / 2 ) * 0.75;

		return lateFee;
	}
}
