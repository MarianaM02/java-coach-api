package com.javacoachapi.core.models.dto.catalogo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CuestionarioDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idConcepto;
	private String nombreConcepto;
	private List<PreguntaDTO> preguntas;

}
