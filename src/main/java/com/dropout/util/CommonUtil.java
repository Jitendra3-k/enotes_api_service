package com.dropout.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dropout.handler.GenericResponse;

public class CommonUtil {
	
	public static ResponseEntity<?> createBuildResponse(Object data,HttpStatus httpStatus){
		GenericResponse genericResponse=GenericResponse.builder()
				.httpStatusCode(httpStatus)
				.status("Success")
				.message("Success")
				.data(data)
				.build();
		return genericResponse.create();
	}
	
	public static ResponseEntity<?> createBuildResponseMessage(String message,HttpStatus httpStatus){
		GenericResponse genericResponse=GenericResponse.builder()
				.httpStatusCode(httpStatus)
				.status("Success")
				.message(message)
				.build();
		return genericResponse.create();
	}
	
	public static ResponseEntity<?> createErrorResponse(Object data,HttpStatus httpStatus){
		GenericResponse genericResponse=GenericResponse.builder()
				.httpStatusCode(httpStatus)
				.status("Failed")
				.data(data)
				.message("Failed")
				.build();
		return genericResponse.create();
	}
	
	public static ResponseEntity<?> createErrorResponseMessage(String message,HttpStatus httpStatus){
		GenericResponse genericResponse=GenericResponse.builder()
				.httpStatusCode(httpStatus)
				.status("Failed")
				.message(message)
				.build();
		return genericResponse.create();
	}
	

}
