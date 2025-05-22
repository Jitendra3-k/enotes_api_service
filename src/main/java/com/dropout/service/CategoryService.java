package com.dropout.service;

import com.dropout.entity.Category;

import java.util.List;


public interface CategoryService {
	
	boolean saveCategory(Category category);
	List<Category> getAllCategory();

}
