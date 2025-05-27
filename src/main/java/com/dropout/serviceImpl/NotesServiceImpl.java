package com.dropout.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dropout.dto.NotesDto;
import com.dropout.dto.NotesDto.CategoryDto;
import com.dropout.dto.NotesDto.FileDetailsDto;
import com.dropout.entity.Category;
import com.dropout.entity.FileDetails;
import com.dropout.entity.Notes;
import com.dropout.exceptions.ResourceNotFoundException;
import com.dropout.repository.CategoryRepository;
import com.dropout.repository.FileDetailsRepository;
import com.dropout.repository.NotesRepository;
import com.dropout.service.NotesService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotesServiceImpl implements NotesService {

	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Value("${file.upload.path}")
	private String path;

	@Autowired
	private FileDetailsRepository fileDetailsRepository;

	@Override
	public boolean saveNotes(String notes, MultipartFile file) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		NotesDto notesDto = objectMapper.readValue(notes, NotesDto.class);
		checkCategoryExits(notesDto.getCategory());

		Notes notesMap = modelMapper.map(notesDto, Notes.class);
		FileDetails fileDetails = saveFileDetails(file);

		if (!ObjectUtils.isEmpty(fileDetails)) {
			notesMap.setFileDetails(fileDetails);
		} else {
			notesMap.setFileDetails(null);
		}

		Notes saveNotes = notesRepository.save(notesMap);

		if (ObjectUtils.isEmpty(saveNotes)) {
			return false;
		}

		return true;
	}

	private FileDetails saveFileDetails(MultipartFile file) throws IOException {

		if (!ObjectUtils.isEmpty(file) && !file.isEmpty()) {

			String originalFileName = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalFileName);
			List<String> allowedExtension = Arrays.asList("pdf", "png", "xlsx", "docx");
			if (!allowedExtension.contains(extension)) {
				throw new IllegalArgumentException("Only pdf,png,xlsx and docx is allowed to be uploaded");
			}

			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdir();
			}
			String uploadPath = path.concat(getUploadFileName(originalFileName));

			// upload file
			long copy = Files.copy(file.getInputStream(), Paths.get(uploadPath));
			if (copy != 0) {
				FileDetails fileDetails = new FileDetails();
				fileDetails.setOriginalFileName(originalFileName);
				fileDetails.setDisplayFileName(getDisplayName(originalFileName));
				fileDetails.setUploadFileName(getUploadFileName(originalFileName));
				fileDetails.setFileSize(file.getSize());
				fileDetails.setPath(uploadPath);
				FileDetails saveFile = fileDetailsRepository.save(fileDetails);

				return saveFile;
			}
		}
		return null;
	}

	private String getDisplayName(String originalFileName) {
		String extension = FilenameUtils.getExtension(originalFileName);
		String displayName = FilenameUtils.removeExtension(originalFileName);
		if (displayName.length() >= 8) {
			displayName = displayName.substring(0, 8);
		}
		displayName = displayName + "." + extension;
		return displayName;
	}

	private String getUploadFileName(String originalFileName) {
		String extension = FilenameUtils.getExtension(originalFileName);
		String randomUUID = UUID.randomUUID().toString();
		String uplaodFileName = originalFileName.concat(randomUUID) + "." + extension;
		return uplaodFileName;
	}

	private void checkCategoryExits(CategoryDto categoryDto) throws Exception {
		Category byId = categoryRepository.findById(categoryDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Category With given id not found"));

	}

	@Override
	public List<NotesDto> getAllNotes() {
		List<Notes> allNotes = notesRepository.findAll();
		return allNotes.stream().map(notes -> modelMapper.map(notes, NotesDto.class)).toList();

	}

}
