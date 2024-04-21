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

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.ICategoriaService;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {
	
	// variable log que permitira escribir msj de log en nuestra app
	private static final Logger log = LoggerFactory.getLogger(CategoryRestController.class);
	
	@Autowired
	private ICategoriaService service;
	
	// con ResponseEntity nos permite devolver un statuscode http
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> consultaCat() {
		
		log.info("Paso por el controller consultaCat()");
		
		ResponseEntity<CategoryResponseRest> response = service.getCategories();
		
		return response;
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> getCategoryByID(@PathVariable("id") Long id){
		ResponseEntity<CategoryResponseRest> response = service.getCategoryByID(id);
		return response;
	}
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> create(@RequestBody Category request){
		ResponseEntity<CategoryResponseRest> response = service.create(request);
		return response;
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> update(@RequestBody Category request, @PathVariable("id") Long id){
		ResponseEntity<CategoryResponseRest> response = service.update(request, id);
		return response;
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> delete(@PathVariable("id") Long id){
		ResponseEntity<CategoryResponseRest> response = service.delete(id);
		return response;
	}
	
}
