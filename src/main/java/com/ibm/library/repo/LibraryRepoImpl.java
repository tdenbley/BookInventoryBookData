package com.ibm.library.repo;

import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import com.ibm.library.model.Book;
import com.ibm.library.model.BookFactory;
import com.ibm.library.model.BookFiction;
import com.ibm.library.model.BookNonFiction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class LibraryRepoImpl implements LibraryRepo {

    @Override
    public Collection<Book> getBooks() {
    	
    	ArrayList<Book> books = null;
    	
    	Book book = null;
    	
    	String fileName = "C:\\data\\books.txt";
    	File file = new File(fileName);

    	String line;
		String[] bookDataFields = null;
		String bookType = null;
		String bookIsbn = null;
		String bookTitle = null;
		String bookAuthor = null;
		
    	FileInputStream fis = null;
    	InputStreamReader isr = null;
    	BufferedReader br = null;
    	
		try {
			
	    	fis = new FileInputStream(file);
	    	isr = new InputStreamReader(fis);
	    	br = new BufferedReader(isr);
	    	
	    	while((line = br.readLine()) != null){
	    		
	    		bookDataFields = line.split("\\|");
	    		
	    		bookType = bookDataFields[0];
	    		bookIsbn = bookDataFields[1];
	    		bookTitle = bookDataFields[2];
	    		bookAuthor = bookDataFields[3];
	    	        
	    		book = null;
	    		
	    		book = BookFactory.CreateBook(bookType, bookIsbn, bookTitle, bookAuthor);
	    		
	        	if (book != null) {
	        		if (books == null) {
	        			books = new ArrayList<Book>();
	        		}
	        		books.add(book);
	        	}
	    	}
		} catch (IOException e) {
			// Add processing for errors
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// Add processing for errors
				}
			}
		}

    	return books;
    }
}
