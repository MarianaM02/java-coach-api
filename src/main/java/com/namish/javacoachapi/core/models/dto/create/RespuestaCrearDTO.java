package com.namish.javacoachapi.core.models.dto.create;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RespuestaCrearDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Long id;
	private String respuesta;
	private Boolean valida;
	

}
