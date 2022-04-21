package com.javacoachapi.core.controller.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacoachapi.core.controller.ICuestionarioController;
import com.javacoachapi.core.models.dto.catalogo.CuestionarioDTO;
import com.javacoachapi.core.services.ICuestionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cuestionario")
@Tag(name = "Cuestionarios", description = "Cuestionarios para estudiar Java")
public class CuestionarioController implements ICuestionarioController{
	private static final Logger LOGGER=LoggerFactory.getLogger(CuestionarioController.class);

	@Autowired
	ICuestionarioService cuetionarioServ;
	
	@Override
	@GetMapping("/concepto/{idConcepto}")
	@Operation(summary = "Devuelve todas las preguntas del concepto indicado", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención del cuestionario exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CuestionarioDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención del cuestionario fallida", 
					content = @Content)
	})
	public ResponseEntity<?> traerCuestionarioPorConcepto(@PathVariable Long idConcepto) {
		LOGGER.info("Acceso al endpoint \"/cuestionario/concepto/{}\"", idConcepto);
		return ResponseEntity.ok().body(cuetionarioServ.crearCuestionarioConcepto(idConcepto));
	}
	
	@Override
	@GetMapping("/capitulo/{idCapitulo}")
	@Operation(summary = "Devuelve todas las preguntas del capítulo indicado", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención del cuestionario exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CuestionarioDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención del cuestionario fallida", 
					content = @Content)
	})
	public ResponseEntity<?> traerCuestionarioPorCapitulo(@PathVariable Long idCapitulo) {
		LOGGER.info("Acceso al endpoint \"/cuestionario/capitulo/{}\"", idCapitulo);
		return ResponseEntity.ok().body(cuetionarioServ.crearCuestionarioCapitulo(idCapitulo));
	}

	@Override
	@GetMapping("/validar/{idRespuesta}/{idPregunta}")
	@Operation(summary = "Devuelve si la respuesta a una pregunta es correcta", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención de la validación exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  Boolean.class))),
			@ApiResponse(responseCode = "404", description = "Obtención de la validación fallida", 
					content = @Content)
	})
	public ResponseEntity<?> validarRespuesta(@PathVariable Long idRespuesta, @PathVariable Long idPregunta) {
		LOGGER.info("Acceso al endpoint \"/cuestionario/validar/{}/{}\"", idRespuesta, idPregunta);
		return ResponseEntity.ok().body(cuetionarioServ.validarRespuesta(idRespuesta, idPregunta));
	}

}
