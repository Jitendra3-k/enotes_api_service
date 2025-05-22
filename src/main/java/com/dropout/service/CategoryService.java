package com.dropout.service;

import com.dropout.dto.CategoryDto;
import com.dropout.dto.CategoryResponse;


import java.util.List;


public interface CategoryService {
	
	boolean saveCategory(CategoryDto category);
	List<CategoryDto> getAllCategory();
	List<CategoryResponse> getAllActiveCategory();
	CategoryResponse getById(Integer id);
	Boolean deleteCategoryById(Integer id);

}
