package com.javacoachapi.core.models.dto.create;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RespuestaCrearDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotBlank(message = "Campo respuesta no puede ser nulo")
	private String respuesta;
	@NotNull(message = "Campo valida no puede ser nulo")
	private Boolean valida;
	

}
