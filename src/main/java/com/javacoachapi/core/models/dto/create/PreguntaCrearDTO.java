package com.javacoachapi.core.models.dto.create;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PreguntaCrearDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String pregunta;
	private Long conceptoId;
	private List<RespuestaCrearDTO> respuestas;
	
}