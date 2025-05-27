package com.dropout.service;

import java.util.List;



import com.dropout.dto.NotesDto;


public interface NotesService {
	
	boolean saveNotes(NotesDto notesDto) throws Exception;
	List<NotesDto> getAllNotes();

}
