package com.dropout.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.dropout.dto.CategoryDto;
import com.dropout.dto.CategoryResponse;
import com.dropout.entity.Category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dropout.repository.CategoryRepository;
import com.dropout.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public boolean saveCategory(CategoryDto categoryDto) {
		
		Category savedCategory=modelMapper.map(categoryDto, Category.class);
		savedCategory.setIsDeleted(false);
		savedCategory.setCreatedBy(1);
		savedCategory.setCreatedOn(new Date());
		Category saveCategory = categoryRepository.save(savedCategory);
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> listCategory= categoryRepository.findAll();
		List<CategoryDto> listCategoryDto = listCategory.stream()
				.map(cat->modelMapper.map(cat, CategoryDto.class)).toList();
		return listCategoryDto;
	}
	
	@Override
	public List<CategoryResponse> getAllActiveCategory() {
		List<Category> listCategory= categoryRepository.findByIsActiveTrue();
		List<CategoryResponse> listCategoryResponse= listCategory.stream().map(cat->modelMapper.map(cat, CategoryResponse.class)).toList();
		return listCategoryResponse;
	}
}
