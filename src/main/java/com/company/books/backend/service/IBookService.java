package com.company.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.BookResponseRest;

public interface IBookService {
	
	public ResponseEntity<BookResponseRest> getBooks();
	
	public ResponseEntity<BookResponseRest> getBookByID(Long id);
	
	public ResponseEntity<BookResponseRest> create(Book book);
	
	public ResponseEntity<BookResponseRest> update(Book book, Long id);
	
	public ResponseEntity<BookResponseRest> delete(Long id);

}
