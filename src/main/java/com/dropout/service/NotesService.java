package com.dropout.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dropout.dto.NotesDto;


public interface NotesService {
	
	boolean saveNotes(String notes,MultipartFile file) throws Exception;
	List<NotesDto> getAllNotes();

}
