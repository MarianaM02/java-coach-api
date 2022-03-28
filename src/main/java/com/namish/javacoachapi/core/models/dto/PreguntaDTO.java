package com.namish.javacoachapi.core.models.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PreguntaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String pregunta;
	private List<RespuestaDTO> respuestas;
	
	
}
