package com.javacoachapi.core.models.dto.create;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConceptoCrearDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String contenido;
	private Long capituloId;

}
