package com.namish.javacoachapi.core.models.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CuestionarioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idConcepto;
	private String nombreConcepto;
	private List<PreguntaDTO> preguntas;

}
