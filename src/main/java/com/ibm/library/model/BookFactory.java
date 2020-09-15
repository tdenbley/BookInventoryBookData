package com.ibm.library.model;

public class BookFactory {
	public static Book CreateBook(String bookType, String bookIsbn, String bookTitle, String bookAuthor) {
		
		Book book = null;
		
		if (bookType.equalsIgnoreCase("FICTION")) { 
			book = new BookFiction(bookIsbn, bookTitle, bookAuthor); 
		} else if (bookType.equalsIgnoreCase("NONFICTION")) { 
			book = new BookNonFiction(bookIsbn, bookTitle, bookAuthor); 
		} 
		return book;
	}
}
