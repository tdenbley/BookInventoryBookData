package com.ibm.library.repo;

import java.util.Collection;
import com.ibm.library.model.Book;

public interface LibraryRepo {
	
	public Collection<Book> getBooks();
	//public Collection<Book> getBooks2();
}
