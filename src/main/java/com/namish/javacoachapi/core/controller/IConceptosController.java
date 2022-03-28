package com.namish.javacoachapi.core.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.namish.javacoachapi.core.models.dto.ConceptoDTO;

public interface IConceptosController {

	/**
	 * Devuelve una lista de conceptos
	 * 
	 * @return lista de conceptos
	 */
	ResponseEntity<List<ConceptoDTO>> traerConceptos();

	/**
	 * Devuelve un concepto
	 * 
	 * @param id del concepto a mostrar
	 * @return concepto
	 */
	ResponseEntity<ConceptoDTO> traerConcepto(Long id);

	/**
	 * Almacena un concepto nuevo
	 * 
	 * @param concepto nuevo
	 * @return concepto creado
	 */
	ResponseEntity<ConceptoDTO> crearConcepto(ConceptoDTO conceptoNuevo);

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
	ResponseEntity<ConceptoDTO> actualizarConcepto(ConceptoDTO conceptoActualizado);
}
