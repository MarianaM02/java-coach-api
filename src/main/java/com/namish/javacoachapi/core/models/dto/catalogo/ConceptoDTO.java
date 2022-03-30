package com.namish.javacoachapi.core.models.dto.catalogo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ConceptoDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String contenido;

}
