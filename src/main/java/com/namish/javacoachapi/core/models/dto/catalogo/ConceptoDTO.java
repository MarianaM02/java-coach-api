package com.namish.javacoachapi.core.models.dto.catalogo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConceptoDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String contenido;

}
