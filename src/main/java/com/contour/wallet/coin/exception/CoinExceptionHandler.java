package com.contour.wallet.coin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CoinExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BizException.class)
	protected ResponseEntity<StatusDto> handleEntityNotFound(BizException ex) {
		StatusDto dto = new StatusDto();
		dto.setStatus(ex.getMessage());
		return new ResponseEntity<StatusDto>(dto, HttpStatus.NOT_ACCEPTABLE);
	}
}
