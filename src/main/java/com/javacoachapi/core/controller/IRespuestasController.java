package com.javacoachapi.core.controller;

import org.springframework.http.ResponseEntity;

import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;

public interface IRespuestasController {

	ResponseEntity<?> traerRespuestas();

	ResponseEntity<?> traerRespuesta(Long id);

	ResponseEntity<?> crearRespuesta(RespuestaCrearDTO RespuestaNueva);

	ResponseEntity<?> eliminarRespuesta(Long id);

	ResponseEntity<?> actualizarRespuesta(RespuestaCrearDTO respuestaActualizada, Long id);

}
