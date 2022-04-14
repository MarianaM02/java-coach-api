package com.javacoachapi.core.controller;


import org.springframework.http.ResponseEntity;

import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;

public interface ICapitulosController {

	ResponseEntity<?> traerCapitulo(Long id);

	ResponseEntity<?> traerTodosLosCapitulos();

	ResponseEntity<?> crearCapitulo(CapituloCrearDTO capituloNuevo);
	
	ResponseEntity<?> actualizarCapitulo(CapituloCrearDTO capituloActualizado, Long id);
	
	ResponseEntity<?> eliminarCapitulo(Long id);

}
