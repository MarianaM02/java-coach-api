package com.javacoachapi.core.exceptions;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
		super("No encontrado");
	}
	
	public DataNotFoundException(Long id) {
		super("No encontrado. ID: " + id);
	}

}
