package com.company.books.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.BookResponseRest;
import com.company.books.backend.service.IBookService;

@RestController
@RequestMapping("/v1")
public class BookRestController {
	
	private static final Logger log = LoggerFactory.getLogger(BookRestController.class); 

	@Autowired
	private IBookService service;
	
	@GetMapping("/books")
	public ResponseEntity<BookResponseRest> getBooks(){
		log.info("Start method getBooks");
		ResponseEntity<BookResponseRest> response = service.getBooks();
		return response;
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<BookResponseRest> getBookByID(@PathVariable("id") Long id){
		ResponseEntity<BookResponseRest> response = service.getBookByID(id);
		return response;
	}
	
	@PostMapping("/books")
	public ResponseEntity<BookResponseRest> create(@RequestBody Book request){
		ResponseEntity<BookResponseRest> response = service.create(request);
		return response;
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<BookResponseRest> update(@RequestBody Book request, @PathVariable("id") Long id){
		ResponseEntity<BookResponseRest> response = service.update(request, id);
		return response;
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<BookResponseRest> delete(@PathVariable("id") Long id){
		ResponseEntity<BookResponseRest> response = service.delete(id);
		return response;
	}
}
