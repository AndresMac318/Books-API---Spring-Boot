package com.company.books.backend.response;

public class BookResponseRest extends ResponseRest {
	
	private BookResponse bookResponse = new BookResponse();
	
	public BookResponse getBookResponse() {
		return bookResponse;
	}
	
	public void setBookResponse(BookResponse bookResponse) {
		this.bookResponse = bookResponse;
	}

}
