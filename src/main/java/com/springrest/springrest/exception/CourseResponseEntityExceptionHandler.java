package com.springrest.springrest.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CourseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ExecptionResponse execptionResponse = new ExecptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(execptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CourseNotFoundException.class)
	public final ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException ex, WebRequest request) {
		ExecptionResponse execptionResponse = new ExecptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(execptionResponse, HttpStatus.NOT_FOUND);
	}

}
