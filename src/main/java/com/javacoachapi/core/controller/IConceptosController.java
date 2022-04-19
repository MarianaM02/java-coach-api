package com.javacoachapi.core.controller;


import org.springframework.http.ResponseEntity;

import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;

public interface IConceptosController {

	/**
	 * Devuelve una lista de conceptos
	 * 
	 * @return lista de conceptos
	 */
	ResponseEntity<?> traerConceptos();

	/**
	 * Devuelve un concepto
	 * 
	 * @param id del concepto a mostrar
	 * @return concepto
	 */
	ResponseEntity<?> traerConcepto(Long id);

	/**
	 * Almacena un concepto nuevo
	 * 
	 * @param concepto nuevo
	 * @return concepto creado
	 */
	ResponseEntity<?> crearConcepto(ConceptoCrearDTO conceptoNuevo);

	/**
	 * Elimina un concepto
	 * 
	 * @param id del concepto a eliminar
	 * @return
	 */
	ResponseEntity<?> eliminarConcepto(Long id);

	/**
	 * Actualiza un concepto
	 * 
	 * @param concepto actualizado
	 * @return concepto actualizado
	 */
	ResponseEntity<?> actualizarConcepto(ConceptoCrearDTO conceptoActualizado, Long id);

	ResponseEntity<?> traerAleatorio();
}
