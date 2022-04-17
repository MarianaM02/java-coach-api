package com.javacoachapi.core.services;

import java.util.List;

import com.javacoachapi.core.models.dto.create.NivelDTO;

public interface INivelService {
	
	NivelDTO traerUno(Long id);
	
	List<NivelDTO> traerTodos();
	
	NivelDTO crear(NivelDTO nivelNuevo);
	
	boolean eliminar(Long id);
	
	NivelDTO actualizar(NivelDTO nivelActualizado, Long id) throws Exception;

}
