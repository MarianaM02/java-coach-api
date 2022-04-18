package com.javacoachapi.core.models.dto.catalogo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CatalogoCapituloDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer numero;
	private String nombre;
	private String nivelNombre;
	private List<CatalogoConceptoDTO> conceptos;
}
