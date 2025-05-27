package com.dropout.serviceImpl;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dropout.dto.NotesDto;
import com.dropout.entity.Category;
import com.dropout.entity.Notes;
import com.dropout.exceptions.ResourceNotFoundException;
import com.dropout.repository.CategoryRepository;
import com.dropout.repository.NotesRepository;
import com.dropout.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService {
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean saveNotes(NotesDto notesDto) throws Exception {
		
		checkCategoryExits(notesDto.getCategory());
		
		Notes saveNotes = notesRepository.save(modelMapper.map(notesDto, Notes.class));
		if(ObjectUtils.isEmpty(saveNotes)) {
			return false;
		}
		return true;
	}

	private void checkCategoryExits(com.dropout.dto.NotesDto.CategoryDto categoryDto) throws Exception {
		Category byId = categoryRepository.findById(categoryDto.getId())
				.orElseThrow(()->new ResourceNotFoundException("Category With given id not found"));
		
	}

	@Override
	public List<NotesDto> getAllNotes() {
		List<Notes> allNotes = notesRepository.findAll();
		 List<NotesDto> allNotesDto = allNotes.stream()
				.map(notes->modelMapper.map(notes, NotesDto.class)).toList();
		 return allNotesDto;
		
	}

	

}
