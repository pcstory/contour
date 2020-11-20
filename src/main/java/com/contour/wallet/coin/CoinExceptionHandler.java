package com.contour.wallet.coin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//@Order(HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CoinExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InsufficientfundException.class)
	protected ResponseEntity<Status> handleEntityNotFound(InsufficientfundException ex) {
		Status dto = new Status();
		dto.setValue(ex.getMessage());
		return new ResponseEntity<Status>(dto, HttpStatus.NOT_ACCEPTABLE);
	}
}
