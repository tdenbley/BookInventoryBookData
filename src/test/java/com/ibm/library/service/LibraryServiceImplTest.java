package com.ibm.library.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import com.ibm.library.endpoint.BookInventoryEndpoint;
import com.ibm.library.model.BookData;


class LibraryServiceImplTest {
	
	@Mock
	private BookInventoryEndpoint bookInventoryEndpoint;
	
	@InjectMocks
	private LibraryServiceImpl libraryService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Test LibraryService getBook with valid isbn")
	@Test
	void testGetBookExistingIsbn() {

		// Set the parameter values and mock the mothods
		String isbn = "1234";
		BookData bookDataMocked = new BookData("FICTION", isbn, "someBook","someAuthor");
		when(bookInventoryEndpoint.getBook(isbn)).thenReturn(bookDataMocked);

		// Call the method being tested, save the results
		BookData bookData = libraryService.getBook(isbn);
		
		// check the results are valid
		assertNotNull(bookData, "bookData should not be null");
		assertEquals(bookData, bookDataMocked, "bookData should be the same as: " + bookDataMocked);
		
		verify(bookInventoryEndpoint).getBook(isbn);
	}
	
	@Test
	void testGetBooks() {
		fail("Not yet implemented");
	}

}
