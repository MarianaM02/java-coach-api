package com.javacoachapi.core.controller;

import org.springframework.http.ResponseEntity;

public interface ICuestionarioController {

	/**
	 * Recibe un concepto y devuelve las preguntas y respuestas posibles
	 * relacionadas a este concepto.
	 * 
	 * @param id concepto
	 * @return cuestionario con todas las preguntas
	 */
	ResponseEntity<?> traerCuestionarioPorConcepto(Long idConcepto);

	/**
	 * Valida si el id_respuesta enviado es valido para el id_pregunta
	 * correspondiente.
	 * 
	 * @param idRespuesta
	 * @param idPregunta
	 * @return
	 */
	ResponseEntity<?> validarRespuesta(Long idRespuesta, Long idPregunta);

}
