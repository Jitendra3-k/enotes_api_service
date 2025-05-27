package com.dropout.dto;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto {
	
	private String title;
	private String description;
	private CategoryDto category;
	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updatedOn;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CategoryDto {
		private Integer id;
		private String name;
		
	}

}
