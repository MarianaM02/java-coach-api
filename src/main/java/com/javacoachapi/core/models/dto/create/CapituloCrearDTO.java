package com.javacoachapi.core.models.dto.create;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CapituloCrearDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotNull(message = "Campo numero no puede ser nulo")
	private Integer numero;
	@NotBlank(message = "Campo nombre no puede ser nulo ni estar vacío")
	private String nombre;
	@NotNull(message = "Campo nivelId no puede ser nulo")
	private Long nivelId;
	
}
