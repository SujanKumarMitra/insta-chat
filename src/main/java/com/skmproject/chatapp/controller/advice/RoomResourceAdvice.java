package com.skmproject.chatapp.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.skmproject.chatapp.exception.RoomAlreadyExistsException;
import com.skmproject.chatapp.exception.RoomNotFoundException;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
@RestControllerAdvice
public class RoomResourceAdvice {

	@ExceptionHandler
	public ResponseEntity<Object> roomExists(RoomAlreadyExistsException exception) {
		return getResponseEntity(exception, HttpStatus.CONFLICT);
		
	}
	@ExceptionHandler
	public ResponseEntity<Object> roomNotFound(RoomNotFoundException exception) {
		return getResponseEntity(exception, HttpStatus.NOT_FOUND);
		
	}
	
	private ResponseEntity<Object> getResponseEntity(Exception exception, HttpStatus status) {
		Map<String, Object> errorAttributes = new HashMap<>();
		errorAttributes.put("code",status.value());
		errorAttributes.put("error", status.getReasonPhrase());
		errorAttributes.put("message",exception.getMessage());
		return ResponseEntity
				.status(status)
				.body(errorAttributes);
	}
}
