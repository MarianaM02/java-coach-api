package com.namish.javacoachapi.core.models.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CapituloDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private List<ConceptoDTO> conceptos;

}
