package com.company.books.backend.response;

import java.util.List;

import com.company.books.backend.model.Category;

public class CategoryResponse {

	private List<Category> categoria;

	public List<Category> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Category> categoria) {
		this.categoria = categoria;
	}
	
}
