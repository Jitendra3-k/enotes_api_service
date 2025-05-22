package com.dropout.service;

import com.dropout.dto.CategoryDto;
import com.dropout.dto.CategoryResponse;
import com.dropout.entity.Category;

import java.util.List;


public interface CategoryService {
	
	boolean saveCategory(CategoryDto category);
	List<CategoryDto> getAllCategory();
	List<CategoryResponse> getAllActiveCategory();

}
