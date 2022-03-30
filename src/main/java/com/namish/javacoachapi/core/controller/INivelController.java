package com.namish.javacoachapi.core.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.namish.javacoachapi.core.models.dto.create.NivelDTO;

public interface INivelController {
	
	ResponseEntity<NivelDTO> traerNivel(Long id);
	
	ResponseEntity<List<NivelDTO>> traerTodosLosNiveles();

	ResponseEntity<NivelDTO> crearNivel(NivelDTO nivelNuevo);
	
	ResponseEntity<NivelDTO> actualizarNivel(NivelDTO nivelNuevo);
	
	ResponseEntity<?> eliminarNivel(Long id);
	
}
