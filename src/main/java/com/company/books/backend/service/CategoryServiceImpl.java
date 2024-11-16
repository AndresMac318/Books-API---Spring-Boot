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

import com.company.books.backend.model.Category;
import com.company.books.backend.model.dao.ICategoryDao;
import com.company.books.backend.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoriaService {
	
	// variable log que permitira escribir msj de log en nuestra app
	private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	//uso de autowired para inyectar la clase "CategoriaDAO" al servicio, facilita creacion de objetos y creacion de dependencias
	@Autowired
	private ICategoryDao categoryDao;

	//transactional indica que el metodo es transaccional, ira a la bd y hara una transaccion
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> getCategories() {
		
		log.info("Start method getCategories()");
		
		// se crea el objeto de respuesta, lo que retornara el metodo
		// response tiene acceso a la metadata "CatResRest extiende de ResponseRest"
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			
			List<Category> categoria = (List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategoria(categoria); // se pasan las categorias consultadas a la DB a la lista de categorias de CategoriaRsponse
			response.setMetadata("Response ok", "200", "Response successfully!"); // se establece la metadata gracias a la herencia en la clase CatRespRest de ResponseRest
			
		} catch(Exception e) {
			response.setMetadata("Response non ok", "-1", "Error when querying Category");
			log.error("Error when querying Category: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> getCategoryByID(Long id) {
		log.info("Start method getCategoryByID()");
		
		CategoryResponseRest response = new CategoryResponseRest(); 
		List<Category> list = new ArrayList<>();
				
		try {
			Optional<Category> categoria = categoryDao.findById(id);
			
			if(categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoryResponse().setCategoria(list);
			} else {
				log.error("Error when querying Category");
				response.setMetadata("Response non ok", "-1", "Category not found");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error when querying Category");
			response.setMetadata("Response not ok", "-1", "Error when querying Category");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Response ok", "200", "Response successfully!");
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // devuelve 200
		
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> create(Category categoria) {
		log.info("Start method create()");
		CategoryResponseRest response = new CategoryResponseRest(); 
		List<Category> list = new ArrayList<>(); 
		
		try {
			
			// validar campos
			if (categoria.getName() == null || categoria.getName().isEmpty() || categoria.getName().length() < 3) {
		        response.setMetadata("Response not ok", "-1", "The 'name' field is Required");
		        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
		    }

		    if (categoria.getDescription() == null || categoria.getDescription().isEmpty() || categoria.getDescription().length() < 3) {
		        response.setMetadata("Response not ok", "-1", "The 'description' field is Required");
		        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
		    }
			
			Category savedCategory = categoryDao.save(categoria);
			
			if(savedCategory != null) {
				list.add(savedCategory);
				response.getCategoryResponse().setCategoria(list);
			} else {
				log.error("Error when creating Category");
				response.setMetadata("Response not ok", "-1", "Category not created");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			log.error("Error creating category");
			response.setMetadata("Response not ok", "-1", "Error creating Category");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Response ok", "200", "Category created!");
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
		log.info("Start method update category()");
		CategoryResponseRest response = new CategoryResponseRest(); 
		List<Category> list = new ArrayList<>();
		
		try {
			
			//se consulta por id la categoria a actualizar
			Optional<Category> searchedCategory = categoryDao.findById(id);
			
			if (searchedCategory.isPresent()) {
				//se setean los valores de la categoria consultada por los enviados en el body
				searchedCategory.get().setName(category.getName());
				searchedCategory.get().setDescription(category.getDescription());
				
				Category updatedCategory = categoryDao.save(searchedCategory.get()); //actualizando
				
				if(updatedCategory != null) {
					response.setMetadata("Response ok", "200", "Category updated!");
					list.add(updatedCategory);
					response.getCategoryResponse().setCategoria(list);
				} else {
					log.error("Error updating category");
					response.setMetadata("Response not ok", "-1", "Category not updated");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("Error updating category");
				response.setMetadata("Response not ok", "-1", "Category not updated");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error updating category", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Response not ok", "-1", "Category not updated");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> delete(Long id) {
		log.info("Start method delete()");
		CategoryResponseRest response = new CategoryResponseRest(); 
		
		try {
			//delete record
			categoryDao.deleteById(id);
			response.setMetadata("Response ok", "200", "Category deleted!");
			
		} catch (Exception e) {
			log.error("Error deleting category", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Response not ok", "-1", "Category not deleted :(");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
