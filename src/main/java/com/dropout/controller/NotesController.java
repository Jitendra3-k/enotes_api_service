package com.dropout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dropout.dto.NotesDto;
import com.dropout.service.NotesService;
import com.dropout.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
	
	@Autowired
	private NotesService notesService;
	
	@PostMapping("/save-notes")
	public ResponseEntity<?> saveNote(@RequestBody NotesDto notesDto) throws Exception{
		boolean saveNotes = notesService.saveNotes(notesDto);
		if(saveNotes) {
			return CommonUtil.createBuildResponseMessage("Notes Saved Successfully", HttpStatus.CREATED);
		}
		return CommonUtil.createBuildResponseMessage("Notes Not Saved Successfully", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/all-notes")
	public ResponseEntity<?> getAllNotes(){
		List<NotesDto> allNotes = notesService.getAllNotes();
		if(CollectionUtils.isEmpty(allNotes)) {
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(allNotes, HttpStatus.OK);
	}

}
