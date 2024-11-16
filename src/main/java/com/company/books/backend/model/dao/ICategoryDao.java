package com.company.books.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {

}
