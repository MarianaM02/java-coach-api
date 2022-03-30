package com.namish.javacoachapi.core.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.namish.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.namish.javacoachapi.core.models.dto.create.CapituloCrearDTO;

public interface ICapitulosController {

	ResponseEntity<CapituloDTO> traerCapitulo(Long id);

	ResponseEntity<List<CapituloDTO>> traerTodosLosCapitulos();

	ResponseEntity<CapituloDTO> crearCapitulo(CapituloCrearDTO capituloNuevo);
	
	ResponseEntity<CapituloDTO> actualizarCapitulo(CapituloCrearDTO capituloActualizado);
	
	ResponseEntity<?> eliminarCapitulo(Long id);

}
