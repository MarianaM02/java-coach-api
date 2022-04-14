package com.javacoachapi.core.models.dto.catalogo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RespuestaDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Long id;
	private String respuesta;

}
