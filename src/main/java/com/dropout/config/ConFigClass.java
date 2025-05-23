package com.dropout.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.dropout.dto.CategoryDto;
import com.dropout.util.Validation;

@Configuration
@EnableJpaAuditing
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
	
	@Bean
	public AuditConfigurationClass auditConfigurationClass() {
		return new AuditConfigurationClass();
	}

}
