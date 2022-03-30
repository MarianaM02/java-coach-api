package com.namish.javacoachapi.core.controller;

import org.springframework.http.ResponseEntity;

import com.namish.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.namish.javacoachapi.core.models.dto.catalogo.CuestionarioDTO;

public interface ICuestionarioController {

	/**
	 * Recibe un concepto y devuelve las preguntas y respuestas posibles
	 * relacionadas a este concepto.
	 * 
	 * @param concepto
	 * @return cuestionario con todas las preguntas
	 */
	ResponseEntity<CuestionarioDTO> traerCuestionarioPorConcepto(ConceptoDTO concepto);

	/**
	 * Valida si el id_respuesta enviado es valido para el id_pregunta
	 * correspondiente.
	 * 
	 * @param idRespuesta
	 * @param idPregunta
	 * @return
	 */
	ResponseEntity<Boolean> validarRespuesta(Long idRespuesta, Long idPregunta);

}
