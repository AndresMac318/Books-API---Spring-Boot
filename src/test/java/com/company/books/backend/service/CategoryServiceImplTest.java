package com.company.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Category;
import com.company.books.backend.model.dao.ICategoryDao;
import com.company.books.backend.response.CategoryResponseRest;

public class CategoryServiceImplTest {
	
	// para indicar que utilice el Mock, que inyecte todos los mocks de la categoriadao
	@InjectMocks
	CategoryServiceImpl service;
	
	@Mock
	ICategoryDao categoryDao;
	
	List<Category> list = new ArrayList<Category>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.chargeCategories();
	}
	
	@Test
	public void getCategoriesTest() {
		// when: cuando se use el metodo findAll se retornara una lista de datos enduro
		when(categoryDao.findAll()).thenReturn(list);
		
		ResponseEntity<CategoryResponseRest> response = service.getCategories();
		
		assertEquals(4, response.getBody().getCategoryResponse().getCategoria().size());
		
		// verifica que categoryDao llamo 1 vez a findAll()
		verify(categoryDao, times(1)).findAll();
	}
	
	public void chargeCategories() {
		Category catOne = new Category(Long.valueOf(1), "Abarrotes", "Distintos abarrotes");
		Category catTwo = new Category(Long.valueOf(1), "Lacteos", "Variedad de lacteos");
		Category catThree = new Category(Long.valueOf(1), "Bebidas", "Bebidas sin azucar");
		Category catFour = new Category(Long.valueOf(1), "Carnes blancas", "Distintas carnes blancas");
		
		list.add(catOne);
		list.add(catTwo);
		list.add(catThree);
		list.add(catFour);
	}

}
