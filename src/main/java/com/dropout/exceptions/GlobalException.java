package com.dropout.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dropout.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalException {
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		log.info("GlobalException :: handleException");
		return CommonUtil.createErrorResponseMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e){
		log.info("GlobalException :: handleResourceNotFoundException");
		return CommonUtil.createErrorResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e){
		log.info("GlobalException :: handleNullPointerException");
		return CommonUtil.createErrorResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException e) {
	    log.info("GlobalException :: ValidationException");
	    return CommonUtil.createErrorResponse(e.getErrors(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
	    log.info("GlobalException :: HttpMessageNotReadableException");
	    return CommonUtil.createErrorResponseMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistsDataException.class)
	public ResponseEntity<?> handleExistsDataException(ExistsDataException e) {
	    log.info("GlobalException :: ExistsDataException");
	    return CommonUtil.createErrorResponseMessage(e.getMessage(),HttpStatus.CONFLICT);
	}
 

}
