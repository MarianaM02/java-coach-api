package com.javacoachapi.core.models.dto.mail;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormMailRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "Campo nombre no puede ser nulo ni estar vacío")
	private String nombre;
	@NotBlank(message = "Campo email no puede ser nulo ni estar vacío")
	@Email(message = "El email debe ser válido")
	private String mail;
	
}
