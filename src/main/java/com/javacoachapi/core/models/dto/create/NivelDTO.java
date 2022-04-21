package com.javacoachapi.core.models.dto.create;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NivelDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotBlank(message = "Campo nombre no puede ser nulo ni estar vacío")
	private String nombre;
	
}
