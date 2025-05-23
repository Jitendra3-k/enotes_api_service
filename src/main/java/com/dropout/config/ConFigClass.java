package com.dropout.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dropout.dto.CategoryDto;
import com.dropout.util.Validation;

@Configuration
public class ConFigClass {
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
	@Bean
	public CategoryDto categoryDto() {
		return new CategoryDto();
	}
	
	@Bean
	public Validation getValidationBean() {
	    return new Validation();
	}

}
