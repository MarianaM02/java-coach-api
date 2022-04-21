package com.javacoachapi.core.controller.implementations;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacoachapi.core.controller.IRespuestasController;
import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;
import com.javacoachapi.core.services.IRespuestaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/respuesta")
@Tag(name = "Respuestas", description = "Respuestas de las preguntas")
public class RespuestasController implements IRespuestasController {
	private static final Logger LOGGER=LoggerFactory.getLogger(RespuestasController.class);
			
	@Autowired
	IRespuestaService respuestaServ;

	@Override
	@GetMapping("/todos")
	@Operation(summary = "Devuelve todas las respuestas", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención de las respuestas exitosa", 
					content = @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = RespuestaDTO.class)))),
			@ApiResponse(responseCode = "404", description = "Obtención de las respuestas fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerRespuestas() {
		LOGGER.info("Acceso al endpoint \"/respuesta/todos\"");
		return ResponseEntity.ok().body(respuestaServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Devuelve la respuesta especificada", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención de la respuesta exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  RespuestaDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención de la respuesta fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerRespuesta(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/respuesta/{}\"", id);
		return ResponseEntity.ok().body(respuestaServ.traerUno(id));
	}

	@Override
	@PostMapping("/crear")
	@Operation(summary = "Crea una nueva respuesta", responses = {
			@ApiResponse(responseCode = "201", description = "Creación de la respuesta exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  RespuestaDTO.class))),
			@ApiResponse(responseCode = "400", description = "Creación de la respuesta fallida", 
					content = @Content)	})
	public ResponseEntity<?> crearRespuesta(@Valid @RequestBody RespuestaCrearDTO respuestaNueva) {
		LOGGER.info("Acceso al endpoint \"/respuesta/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestaServ.crear(respuestaNueva));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Elimina la respuesta indicada", responses = {
			@ApiResponse(responseCode = "204", description = "Eliminación de la respuesta exitosa", 
					content = @Content),
			@ApiResponse(responseCode = "400", description = "Eliminación de la respuesta fallida", 
					content = @Content)	})
	public ResponseEntity<?> eliminarRespuesta(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/respuesta/eliminar/{}\"", id);
		respuestaServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualiza la respuesta indicada", responses = {
			@ApiResponse(responseCode = "200", description = "Actualización de la respuesta exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  RespuestaDTO.class))),
			@ApiResponse(responseCode = "400", description = "Actualización de la respuesta fallida", 
					content = @Content)	})
	public ResponseEntity<?> actualizarRespuesta(@Valid @RequestBody RespuestaCrearDTO respuestaActualizada, @PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/respuesta/actualizar/{}\"", id);
		return ResponseEntity.ok().body(respuestaServ.actualizar(respuestaActualizada, id));
	}

}
