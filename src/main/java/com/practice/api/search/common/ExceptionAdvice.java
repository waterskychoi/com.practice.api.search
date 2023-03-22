package com.practice.api.search.common;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.practice.api.search.enums.ExceptionCode;
import com.practice.api.search.model.resp.ExceptionResp;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;


	@ExceptionHandler(value = {Throwable.class})
	public ResponseEntity<Object> handleException(Exception exception, HttpHeaders headers) {
		//TODO : Logging
		String message = messageSource.getMessage(ExceptionCode.MESSAGE_NOT_READABLE.getCode(), null, null, null);
		return new ResponseEntity<>(new ExceptionResp(ExceptionCode.INTERNAL_SERVER_ERROR.getCode(), message), headers, HttpStatusCode.valueOf(500));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		FieldError fieldError = ex.getBindingResult()
				.getFieldErrors()
				.stream().findFirst().get();

		String message = messageSource.getMessage(fieldError.getDefaultMessage(), null, null, request.getLocale());
		return new ResponseEntity<>(new ExceptionResp(ExceptionCode.INVALID_REQUEST.getCode(), message), headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
//		var cause = (InvalidFormatException)ex.getCause();
//		cause.getPath().get(0).getFieldName();
		String message = messageSource.getMessage(ExceptionCode.MESSAGE_NOT_READABLE.getCode(), null, null, request.getLocale());
		return new ResponseEntity<>(new ExceptionResp(ExceptionCode.MESSAGE_NOT_READABLE.getCode(), message), headers, status);
	}
}