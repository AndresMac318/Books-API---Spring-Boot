package com.company.books.backend.response;

import java.util.List;

import com.company.books.backend.model.Book;

public class BookResponse {
	
	private List<Book> book;
	
	public List<Book> getBook(){
		return book;
	}
	
	public void setBook(List<Book> book) {
		this.book = book;
	}

}
