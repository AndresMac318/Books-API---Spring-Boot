package com.company.books.backend.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.ICategoriaService;

public class CategoryRestControllerTest {
	
	@InjectMocks
	CategoryRestController categoryController;
	
	@Mock
	private ICategoriaService service;

	// se iniciaran los mocks antes de cada ejecucion de 1 @Test
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void createTest() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Category category = new Category(Long.valueOf(1), "Clasicos", "Libros clasicos de literatura moderna");
		
		when(service.create(any(Category.class))).thenReturn(new ResponseEntity<CategoryResponseRest>(HttpStatus.OK));
		
		ResponseEntity<CategoryResponseRest> response = categoryController.create(category);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
}
