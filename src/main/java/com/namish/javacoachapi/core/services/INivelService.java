package com.namish.javacoachapi.core.services;

import java.util.List;

import com.namish.javacoachapi.core.models.dto.create.NivelDTO;

public interface INivelService {
	
	NivelDTO traerNivel(Long id);
	
	List<NivelDTO> traerTodosLosNiveles();
	
	NivelDTO crearNivel(NivelDTO nivelNuevo);
	
	void eliminarNivel(Long id);
	
	NivelDTO actualizarNivel(NivelDTO nivelActualizado);

}
