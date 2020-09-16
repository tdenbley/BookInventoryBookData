package com.ibm.library.endpoint;

import java.util.Collection;

import java.util.Arrays;

import com.ibm.library.model.BookData;

import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;

@Component
public class BookInventoryEndpointImpl implements BookInventoryEndpoint {
	
	// Notice that we're not using @Autowired
	// Spring creates a bean for RestTemplate in one of its jars ... we're not going to use it.
	// We could also create a bean for RestTemplate by using a class w/ @Configuration & method w/ @Bean
	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${bookinventory.endpoint}")
	private String bookInventoryEndpoint;
	
	@Override
	public BookData getBook(String isbn) {

		String bookInventoryRESTRequestURL = "http://" + bookInventoryEndpoint + "/bookinventory/book/" + isbn;
		BookData book = this.restTemplate.getForObject(bookInventoryRESTRequestURL, BookData.class);
		return book;
	}
	
	@Override
	public Collection<BookData> getBooks() {
		
		Collection<BookData> books = null;
		
		String bookInventoryRESTRequestURL = "http://" + bookInventoryEndpoint + "/bookinventory/books";

		BookData[] bookDataArray = this.restTemplate.getForObject(bookInventoryRESTRequestURL, BookData[].class);
		if ((bookDataArray != null) && (bookDataArray.length != 0)) { 
			books = Arrays.asList(bookDataArray); 
		}
		return books;
	}
}
