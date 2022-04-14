package com.javacoachapi.core.controller;

import org.springframework.http.ResponseEntity;

import com.javacoachapi.core.models.dto.create.NivelDTO;

public interface INivelController {
	
	ResponseEntity<?> traerNivel(Long id);
	
	ResponseEntity<?> traerTodosLosNiveles();

	ResponseEntity<?> crearNivel(NivelDTO nivelNuevo);
	
	ResponseEntity<?> actualizarNivel(NivelDTO nivelActualizado, Long id);
	
	ResponseEntity<?> eliminarNivel(Long id);
	
}
