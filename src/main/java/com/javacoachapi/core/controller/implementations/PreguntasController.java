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

import com.javacoachapi.core.controller.IPreguntasController;
import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.create.PreguntaCrearDTO;
import com.javacoachapi.core.services.IPreguntaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pregunta")
@Tag(name = "Preguntas", description = "Preguntas sobre los conceptos almacenados")
public class PreguntasController implements IPreguntasController {
	private static final Logger LOGGER=LoggerFactory.getLogger(PreguntasController.class);
	
	@Autowired
	IPreguntaService preguntaServ;
	
	@Override
	@GetMapping("/todos")
	@Operation(summary = "Devuelve todas las preguntas", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención de las preguntas exitosa", 
					content = @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = PreguntaDTO.class)))),
			@ApiResponse(responseCode = "404", description = "Obtención de las preguntas fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerPreguntas() {
		LOGGER.info("Acceso al endpoint \"/pregunta/todos\"");
		return ResponseEntity.ok().body(preguntaServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Devuelve la pregunta especificada", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención de la pregunta exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  PreguntaDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención de la pregunta fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerPregunta(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/pregunta/{}\"", id);
		return ResponseEntity.ok().body(preguntaServ.traerUno(id));
	}

	@Override
	@PostMapping("/crear")
	@Operation(summary = "Crea una nueva pregunta", responses = {
			@ApiResponse(responseCode = "201", description = "Creación de la pregunta exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  PreguntaDTO.class))),
			@ApiResponse(responseCode = "400", description = "Creación de la pregunta fallida", 
					content = @Content)	})
	public ResponseEntity<?> crearPregunta(@Valid @RequestBody PreguntaCrearDTO preguntaNueva) {
		LOGGER.info("Acceso al endpoint \"/pregunta/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(preguntaServ.crear(preguntaNueva));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Elimina la pregunta indicada", responses = {
			@ApiResponse(responseCode = "204", description = "Eliminación de la pregunta exitosa", 
					content = @Content),
			@ApiResponse(responseCode = "400", description = "Eliminación de la pregunta fallida", 
					content = @Content)	})
	public ResponseEntity<?> eliminarPregunta(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/pregunta/eliminar/{}\"", id);
		preguntaServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualiza la pregunta indicada", responses = {
			@ApiResponse(responseCode = "200", description = "Actualización de la pregunta exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  PreguntaDTO.class))),
			@ApiResponse(responseCode = "400", description = "Actualización de la pregunta fallida", 
					content = @Content)	})
	public ResponseEntity<?> actualizarPregunta(@Valid @RequestBody PreguntaCrearDTO preguntaActualizada, @PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/pregunta/actualizar/{}\"", id);
		return ResponseEntity.ok().body(preguntaServ.actualizar(preguntaActualizada, id));
	}

}
