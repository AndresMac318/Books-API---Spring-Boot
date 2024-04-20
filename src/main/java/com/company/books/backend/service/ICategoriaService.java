package com.company.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;

public interface ICategoriaService {
	
	//metodo de tipo ResponseEntity que resuelve CategoryResponseRest objeto de respuesta(metadata y lista) 
	public ResponseEntity<CategoryResponseRest> getCategories();
	
	public ResponseEntity<CategoryResponseRest> getCategoryByID(Long id);
	
	public ResponseEntity<CategoryResponseRest> create(Category categoria);
	
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	
	public ResponseEntity<CategoryResponseRest> delete(Long id);
}
