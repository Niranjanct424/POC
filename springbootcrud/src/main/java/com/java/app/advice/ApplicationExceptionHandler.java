package com.java.app.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.java.app.exception.UserNotFoundExeption;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errMap = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errMap.put(error.getField(), error.getDefaultMessage());
		});
		return errMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundExeption.class)
	public Map<String, String> handleBusinessExceptioin(UserNotFoundExeption ex) {
		Map<String, String> errMap = new HashMap<String, String>();
		errMap.put("errorMessage", ex.getMessage());
		return errMap;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handle(ConstraintViolationException exception) {
		Map<String, String> errMap = new HashMap<String, String>();
		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		StringBuilder builder = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			builder.append(violation.getMessage()+" , ");
		}
		errMap.put("errorMessage", builder.toString());
		return errMap;
	}

}
