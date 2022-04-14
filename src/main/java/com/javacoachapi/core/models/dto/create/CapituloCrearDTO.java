package com.javacoachapi.core.models.dto.create;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CapituloCrearDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer numero;
	private String nombre;
	private Long nivelId;
	
}
