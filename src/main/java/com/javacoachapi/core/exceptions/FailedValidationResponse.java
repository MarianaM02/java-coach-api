package com.javacoachapi.core.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class FailedValidationResponse {
	private List<ValidationError> errors = new ArrayList<ValidationError>();

	public FailedValidationResponse(MethodArgumentNotValidException ex) {
		super();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			this.errors.add(
					new ValidationError(
							error.getField(), 
							error.getDefaultMessage()));
		}
	}

}
