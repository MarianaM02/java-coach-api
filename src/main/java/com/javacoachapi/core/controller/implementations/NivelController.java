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

import com.javacoachapi.core.controller.INivelController;
import com.javacoachapi.core.models.dto.create.NivelDTO;
import com.javacoachapi.core.services.INivelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/nivel")
@Tag(name = "Niveles", description = "Niveles de dificultad de los capítulos")
public class NivelController implements INivelController {
	private static final Logger LOGGER=LoggerFactory.getLogger(NivelController.class);
	
	@Autowired
	INivelService nivelServ;

	@Override
	@GetMapping("/todos")
	@Operation(summary = "Devuelve todos los niveles", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención de los niveles exitosa", 
					content = @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = NivelDTO.class)))),
			@ApiResponse(responseCode = "404", description = "Obtención de los niveles fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerTodosLosNiveles() {
		LOGGER.info("Acceso al endpoint \"/nivel/todos\"");
		return ResponseEntity.ok().body(nivelServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Devuelve el nivel especificado", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención del nivel exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  NivelDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención del nivel fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerNivel(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/nivel/{}\"", id);
		return ResponseEntity.ok().body(nivelServ.traerUno(id));
	}
	
	@Override
	@PostMapping("/crear")
	@Operation(summary = "Crea un nuevo nivel", responses = {
			@ApiResponse(responseCode = "201", description = "Creación del nivel exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  NivelDTO.class))),
			@ApiResponse(responseCode = "400", description = "Creación del nivel fallida", 
					content = @Content)	})
	public ResponseEntity<?> crearNivel(@Valid @RequestBody NivelDTO nivelNuevo) {
		LOGGER.info("Acceso al endpoint \"/nivel/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(nivelServ.crear(nivelNuevo));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Elimina el nivel indicado", responses = {
			@ApiResponse(responseCode = "204", description = "Eliminación del nivel exitosa", 
					content = @Content),
			@ApiResponse(responseCode = "400", description = "Eliminación del nivel fallida", 
					content = @Content)	})
	public ResponseEntity<?> eliminarNivel(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/nivel/eliminar/{}\"", id);
		nivelServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualiza el nivel indicado", responses = {
			@ApiResponse(responseCode = "200", description = "Actualización del nivel exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  NivelDTO.class))),
			@ApiResponse(responseCode = "400", description = "Actualización del nivel fallida", 
					content = @Content)	})
	public ResponseEntity<?> actualizarNivel(@Valid @RequestBody NivelDTO nivelActualizado, @PathVariable Long id) throws Exception {
		LOGGER.info("Acceso al endpoint \"/nivel/actualizar/{}\"", id);
		return ResponseEntity.ok().body(nivelServ.actualizar(nivelActualizado, id));
	}


}
