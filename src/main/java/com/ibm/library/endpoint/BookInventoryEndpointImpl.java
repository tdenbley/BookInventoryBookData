package com.ibm.library.endpoint;

import java.util.Collection;
import java.util.Arrays;
import com.ibm.library.model.BookData;
import com.ibm.library.service.LibraryServiceImpl;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class BookInventoryEndpointImpl implements BookInventoryEndpoint {
	private final Logger logger = LoggerFactory.getLogger(BookInventoryEndpointImpl.class);
	// Notice that we're not using @Autowired
	// Spring creates a bean for RestTemplate in one of its jars ... we're not going
	// to use it.
	// We could also create a bean for RestTemplate by using a class w/
	// @Configuration & method w/ @Bean
	private RestTemplate restTemplate = new RestTemplate();

	@Value("${bookinventory.endpoint}")
	private String bookInventoryEndpoint;

	@Override
	@HystrixCommand(fallbackMethod = "getBook_fallBack", commandKey = "endpointGetBook", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "40000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75") })

	public BookData getBook(String isbn) {

		logger.info("Entered BookInventoryEndpointImpl.getBook() isbn=[" + isbn + "]");
		
		logger.debug("Getting book with isbn: " + isbn);
		String bookInventoryRESTRequestURL = "http://" + bookInventoryEndpoint + "/bookinventory/book/" + isbn;
		BookData book = this.restTemplate.getForObject(bookInventoryRESTRequestURL, BookData.class);

		logger.info("Leaving BookInventoryEndpointImpl.getBook() isbn=[" + isbn + "]");

		return book;
	}

	@Override
	public Collection<BookData> getBooks() {
		
		logger.info("Entered BookInventoryEndpointImpl.getBooks()");
		
		Collection<BookData> books = null;

		String bookInventoryRESTRequestURL = "http://" + bookInventoryEndpoint + "/bookinventory/books";

		BookData[] bookDataArray = this.restTemplate.getForObject(bookInventoryRESTRequestURL, BookData[].class);
		if ((bookDataArray != null) && (bookDataArray.length != 0)) {
			books = Arrays.asList(bookDataArray);
		}
		logger.info("Leaving BookInventoryEndpointImpl.getBooks()");
		return books;
	}

	public BookData getBook_fallBack(String isbn) {
		logger.warn("!!!!!!!!!! IN FALLBACK.  getBook_fallBack isbn=" + isbn);
		BookData book = new BookData("FICTION", "CACHED BOOK DATA", "Some Book", "Some Author");
		logger.warn("!!!!!!!!!! EXITING FALLBACK Method.  getBook_fallBack isbn=" + isbn);
		return book;

	}

}
