package com.dropout.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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

		Category savedCategory = modelMapper.map(categoryDto, Category.class);
		if (ObjectUtils.isEmpty(savedCategory.getId())) {
			savedCategory.setIsDeleted(false);
			savedCategory.setCreatedBy(1);
			savedCategory.setCreatedOn(new Date());
		} else {
			updateCategory(savedCategory);
		}

		Category saveCategory = categoryRepository.save(savedCategory);
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
	}

	private void updateCategory(Category savedCategory) {
		Optional<Category> findById = categoryRepository.findById(savedCategory.getId());
		if (findById.isPresent()) {
			Category category = findById.get();
			savedCategory.setCreatedBy(category.getCreatedBy());
			savedCategory.setCreatedOn(category.getCreatedOn());
			savedCategory.setIsDeleted(category.getIsDeleted());
			savedCategory.setUpdatedBy(1);
			savedCategory.setUpdatedOn(new Date());
		}

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> listCategory = categoryRepository.findByIsDeletedFalse();
		List<CategoryDto> listCategoryDto = listCategory.stream().map(cat -> modelMapper.map(cat, CategoryDto.class))
				.toList();
		return listCategoryDto;
	}

	@Override
	public List<CategoryResponse> getAllActiveCategory() {
		List<Category> listCategory = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
		List<CategoryResponse> listCategoryResponse = listCategory.stream()
				.map(cat -> modelMapper.map(cat, CategoryResponse.class)).toList();
		return listCategoryResponse;
	}

	@Override
	public CategoryResponse getById(Integer id) {
		Optional<Category> category = categoryRepository.findByIdAndIsDeletedFalse(id);
		if (category.isPresent()) {
			Category category2 = category.get();
			return modelMapper.map(category2, CategoryResponse.class);
		}

		return null;
	}

	@Override
	public Boolean deleteCategoryById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			Category category2 = category.get();
			category2.setIsDeleted(true);
			categoryRepository.save(category2);
			return true;
		}
		return false;
	}

}
