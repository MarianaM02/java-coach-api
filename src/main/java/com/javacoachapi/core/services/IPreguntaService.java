package com.javacoachapi.core.services;

import java.util.List;

import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.create.PreguntaCrearDTO;

public interface IPreguntaService {

	PreguntaDTO traerUno(Long id);
	
	List<PreguntaDTO> traerTodos();
	
	PreguntaDTO crear(PreguntaCrearDTO preguntaNueva);
	
	boolean eliminar(Long id);
	
	PreguntaDTO actualizar(PreguntaCrearDTO preguntaActualizada, Long id);

	boolean borrarPorConcepto(Long id);

}
