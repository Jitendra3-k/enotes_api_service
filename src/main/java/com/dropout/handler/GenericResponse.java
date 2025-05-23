package com.dropout.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {
	
	private HttpStatus httpStatusCode;
	private String status;
	private Object data;
	private String message;
	
	public ResponseEntity<?> create(){
		Map<String, Object> errors=new LinkedHashMap<>();
		errors.put("Status Code", httpStatusCode);
		errors.put("Status ", status);
		if(!ObjectUtils.isEmpty(data)) {
			errors.put("data ", data);
		}
		errors.put("message ", message);
		
		return new ResponseEntity<>(errors,httpStatusCode);
	}

}
