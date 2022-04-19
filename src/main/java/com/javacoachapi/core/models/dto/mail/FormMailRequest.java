package com.javacoachapi.core.models.dto.mail;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormMailRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String mail;
	
}
