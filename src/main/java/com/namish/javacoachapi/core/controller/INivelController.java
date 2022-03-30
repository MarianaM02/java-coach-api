package com.namish.javacoachapi.core.controller;

import java.util.List;

import com.namish.javacoachapi.core.models.dto.create.NivelDTO;

public interface INivelController {
	
	NivelDTO traerNivel(Long id);
	
	List<NivelDTO> traerTodosLosNiveles();

	NivelDTO crearNivel(NivelDTO nivelNuevo);
	
	NivelDTO actualizarNivel(NivelDTO nivelNuevo);
	
	void eliminarNivel(Long id);
	
}
