package com.javacoachapi.core.services;

import java.util.List;

import com.javacoachapi.core.models.dto.create.NivelDTO;

public interface INivelService {
	
	NivelDTO traerNivel(Long id);
	
	List<NivelDTO> traerTodosLosNiveles();
	
	NivelDTO crearNivel(NivelDTO nivelNuevo);
	
	boolean eliminarNivel(Long id);
	
	NivelDTO actualizarNivel(NivelDTO nivelActualizado, Long id) throws Exception;

}
