package com.meru.orders.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	public ResponseEntity<CustomErrorResponse> handleOrderNotFoundError(OrderNotFoundException e){
		CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<CustomErrorResponse> handleAlreadyPaidException(OrderAlreadyPaidException e){
		CustomErrorResponse error = new CustomErrorResponse("ALREADY_PAID_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
