package com.javacoachapi.core.models.dto.catalogo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CatalogoConceptoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String contenido;
	List<PreguntaDTO> preguntas;
	
	// TODO implementar Ejemplos
	List<String> ejemplos = new ArrayList<String>();

}
