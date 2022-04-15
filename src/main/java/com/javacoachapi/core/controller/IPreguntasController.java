package com.javacoachapi.core.controller;

import org.springframework.http.ResponseEntity;

import com.javacoachapi.core.models.dto.create.PreguntaCrearDTO;

public interface IPreguntasController {

	ResponseEntity<?> traerPreguntas();
	
	ResponseEntity<?> traerPregunta(Long id);
	
	ResponseEntity<?> crearPregunta(PreguntaCrearDTO preguntaNueva);
	
	ResponseEntity<?> eliminarPregunta(Long id);
	
	ResponseEntity<?> actualizarPregunta(PreguntaCrearDTO preguntaActualizada, Long id);
	
}
