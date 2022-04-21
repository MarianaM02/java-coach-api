package com.javacoachapi.core.models.dto.create;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PreguntaCrearDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotBlank(message = "Campo pregunta no puede ser nulo ni estar vacío")
	private String pregunta;
	@NotNull(message = "Campo conceptoId no puede ser nulo")
	private Long conceptoId;
	@NotEmpty(message = "Campo respuestas no puede ser nulo ni estar vacío")
	private List<RespuestaCrearDTO> respuestas;
	
}
