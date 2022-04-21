package com.javacoachapi.core.models.dto.create;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConceptoCrearDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "Campo nombre no puede ser nulo ni estar vacío")
	private String nombre;
	@NotBlank(message = "Campo contenido no puede ser nulo ni estar vacío")
	private String contenido;
	@NotNull(message = "Campo capituloId no puede ser nulo")
	private Long capituloId;

}
