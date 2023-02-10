package com.example.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionController {
	
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> exception(CustomerNotFoundException exception) {
		return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerAlreadyPresentException.class)
	public ResponseEntity<Object> exception(CustomerAlreadyPresentException exception) {
		return new ResponseEntity<>("Customer with provided email id already exists", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = OrderCannotBePlacedException.class)
	public ResponseEntity<Object> exception(OrderCannotBePlacedException exception) {
		return new ResponseEntity<>("cannot place this order", HttpStatus.BAD_REQUEST);
	}
	
	
	
}
