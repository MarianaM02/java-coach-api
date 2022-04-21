package com.javacoachapi.core.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter @RequiredArgsConstructor @ToString
public class ValidationError {
	private final String fieldName;
	private final String message;
}
