package com.company.books.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Book;
import com.company.books.backend.model.dao.IBookDao;
import com.company.books.backend.response.BookResponseRest;

@Service
public class BookServiceImpl implements IBookService {

	public static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private IBookDao bookDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<BookResponseRest> getBooks() {
		BookResponseRest response = new BookResponseRest();
		try {
			List<Book> book = (List<Book>) bookDao.findAll();
			response.getBookResponse().setBook(book);
			response.setMetadata("Response ok", "200", "Response successfully!");
		} catch (Exception e) {
			response.setMetadata("Response non ok", "-1", "Error when querying Book");
			log.error("Error when querying Book: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<BookResponseRest> getBookByID(Long id) {
		log.info("Start method getBookByID()");
		
		BookResponseRest response = new BookResponseRest(); 
		List<Book> list = new ArrayList<>();
				
		try {
			Optional<Book> book = bookDao.findById(id);
			
			if(book.isPresent()) {
				list.add(book.get());
				response.getBookResponse().setBook(list);
			} else {
				log.error("Error when querying Book");
				response.setMetadata("Response non ok", "-1", "Book not found");
				return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error when querying Book");
			response.setMetadata("Response not ok", "-1", "Error when querying Book");
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Response ok", "200", "Response successfully!");
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<BookResponseRest> create(Book book) {
		log.info("Start method create Book()");
		BookResponseRest response = new BookResponseRest(); 
		List<Book> list = new ArrayList<>(); 
		
		try {
			
			// validation
			if (book.getName() == null || book.getName().isEmpty() || book.getName().length() < 3) {
		        response.setMetadata("Response not ok", "-1", "The 'name' field is Required");
		        return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
		    }

		    if (book.getDescription() == null || book.getDescription().isEmpty() || book.getDescription().length() < 3) {
		        response.setMetadata("Response not ok", "-1", "The 'description' field is Required");
		        return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
		    }
			
			Book savedBook = bookDao.save(book);
			
			if(savedBook != null) {
				list.add(savedBook);
				response.getBookResponse().setBook(list);
			} else {
				log.error("Error when creating Book");
				response.setMetadata("Response not ok", "-1", "Book not created");
				return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			log.error("Error creating category");
			response.setMetadata("Response not ok", "-1", "Error creating Book");
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Response ok", "200", "Book created!");
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<BookResponseRest> update(Book book, Long id) {
		log.info("Start method update Book()");
		BookResponseRest response = new BookResponseRest(); 
		List<Book> list = new ArrayList<>();
		
		try {
			
			// get category to update
			Optional<Book> searchedBook = bookDao.findById(id);
			
			if (searchedBook.isPresent()) {
				// setting search book data x data in body
				searchedBook.get().setName(book.getName());
				searchedBook.get().setDescription(book.getDescription());
				
				Book updatedBook = bookDao.save(searchedBook.get()); // updating
				
				if(updatedBook != null) {
					response.setMetadata("Response ok", "200", "Book updated!");
					list.add(updatedBook);
					response.getBookResponse().setBook(list);
				} else {
					log.error("Error updating book");
					response.setMetadata("Response not ok", "-1", "Book not updated");
					return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("Error updating book");
				response.setMetadata("Response not ok", "-1", "Book not updated");
				return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error updating book", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Response not ok", "-1", "Book not updated");
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<BookResponseRest> delete(Long id) {
		log.info("Start method delete book()");
		BookResponseRest response = new BookResponseRest(); 
		
		try {
			//delete record
			bookDao.deleteById(id);
			response.setMetadata("Response ok", "200", "Book deleted!");
			
		} catch (Exception e) {
			log.error("Error deleting book", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Response not ok", "-1", "Book not deleted :(");
			return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
	}

}
