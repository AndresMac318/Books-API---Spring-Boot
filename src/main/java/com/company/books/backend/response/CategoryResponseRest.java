package com.company.books.backend.response;

public class CategoryResponseRest extends ResponseRest {
	
	// *instancia de clase CategoryResponse "dicha clase devuelve una lista de categorias"
	// *esta clase "catRespRest" devolverá un objeto de tipo categoriaResponse que a su 
	// vez devuelve una lista
	private CategoryResponse categoryResponse = new CategoryResponse();

	public CategoryResponse getCategoryResponse() {
		return categoryResponse;
	}

	public void setCategoryResponse(CategoryResponse categoriaResponse) {
		this.categoryResponse = categoriaResponse;
	}

}
