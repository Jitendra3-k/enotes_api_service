package com.dropout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dropout.dto.CategoryDto;
import com.dropout.dto.CategoryResponse;
import com.dropout.service.CategoryService;
import com.dropout.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;


	@PostMapping("/save-category")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {
		Boolean isSaved = categoryService.saveCategory(categoryDto);
		if (isSaved) {
			return CommonUtil.createBuildResponseMessage("SAVED Successfully", HttpStatus.CREATED);
		//	return new ResponseEntity<>("SAVED", HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorResponseMessage("NOT SAVED", HttpStatus.INTERNAL_SERVER_ERROR);
	//		return new ResponseEntity<>("NOT SAVED", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/allCategory")
	public ResponseEntity<?> getAllCategory() {

		List<CategoryDto> allCategory = categoryService.getAllCategory();
		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return CommonUtil.createBuildResponse(allCategory, HttpStatus.OK);
		//	return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}

	}

	@GetMapping("/activeCategory")
	public ResponseEntity<?> getAllActiveCategory() {

		List<CategoryResponse> allActiveCategory = categoryService.getAllActiveCategory();
		if (CollectionUtils.isEmpty(allActiveCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return CommonUtil.createBuildResponse(allActiveCategory, HttpStatus.OK);
		//	return new ResponseEntity<>(allActiveCategory, HttpStatus.OK);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Integer id) throws Exception {

		CategoryResponse categoryResponse = categoryService.getById(id);
		if (ObjectUtils.isEmpty(categoryResponse)) {
			return CommonUtil.createErrorResponseMessage("Category with "+id+" not found", HttpStatus.NOT_FOUND);
		//	return new ResponseEntity<>("Category with "+id+" not found",HttpStatus.NOT_FOUND);
		}
		return CommonUtil.createBuildResponse(categoryResponse, HttpStatus.OK);
	//	return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteByCategoryId(@PathVariable Integer id) {

		Boolean isDeleted = categoryService.deleteCategoryById(id);
		if (isDeleted) {
			return CommonUtil.createBuildResponseMessage("Category with "+id+" deleted", HttpStatus.OK);
	//		return new ResponseEntity<>("Category with "+id+" deleted",HttpStatus.OK);
		} else {
			return CommonUtil.createErrorResponseMessage("Category no deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		//	return new ResponseEntity<>("Category no deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
