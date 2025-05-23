package com.dropout.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import com.dropout.dto.CategoryDto;
import com.dropout.exceptions.ValidationException;

@Configuration
public class Validation {

	
	public void validateCategory(CategoryDto categoryDto) {

		Map<String, Object> error = new LinkedHashMap<>();

		if (ObjectUtils.isEmpty(categoryDto)) {
			throw new IllegalArgumentException("Category v=can'r be empty or null");
		} else {
			if (ObjectUtils.isEmpty(categoryDto.getName())) {
				error.put("name", "Name can't be null or empty");
			} else {
				if (categoryDto.getName().length() < 10) {
					error.put("name", "Name length shouln't be less than 10 ");
				}
				if (categoryDto.getName().length() > 100) {
					error.put("name", "Name length shouln't be greater than 100 ");
				}

			}

			if (!ObjectUtils.isEmpty(categoryDto.getDescription())) {
				if (categoryDto.getDescription().length() < 10) {
					error.put("Description ", "Description length shouln't be less than 10 ");
				}
				if (categoryDto.getDescription().length() > 100) {
					error.put("Description", "Description length shouln't be greater than 100 ");
				}
			}

			if (ObjectUtils.isEmpty(categoryDto.getIsActive())) {
				error.put("isActive", "isActive Can't be null or empty");
			}

		}
		
		if(!error.isEmpty()) {
			throw new ValidationException("Validation failed !",error);
		}
		

	}

}
