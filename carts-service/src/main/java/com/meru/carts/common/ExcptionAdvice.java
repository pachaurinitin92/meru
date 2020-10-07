package com.meru.carts.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcptionAdvice {

	public ResponseEntity<CustomeErrorResponse> handleNotFoundException(NotFoundException e){
		CustomeErrorResponse error = new CustomeErrorResponse("NOT_FOUND_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<CustomeErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
}
