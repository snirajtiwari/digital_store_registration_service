package org.digitalstore.registration.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RegistrationException
{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex)
	{
		String errorMessage = null;
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		for (ObjectError error : errors)
		{
			errorMessage = error.getDefaultMessage();
		}
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

	}

	/*@ExceptionHandler(ValidationException.class)
	public ResponseEntity<String> handleValidationExceptions(ValidationException ex)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}
*/}
