package com.dropout.dto;

import java.util.Date;

import com.dropout.entity.FileDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto {
	private Integer id;
	private String title;
	private String description;
	private CategoryDto category;
	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updatedOn;
	
	private FileDetailsDto fileDetails;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FileDetailsDto {
		private Integer id;
		private String originalFileName;
		private String displayFileName;
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CategoryDto {
		private Integer id;
		private String name;
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FileDetails{
		private Integer id;
		private String uploadFileName;
		private String originalFileName;
		private String displayFileName;
		
	}



}
