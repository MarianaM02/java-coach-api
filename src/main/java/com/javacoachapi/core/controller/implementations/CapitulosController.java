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

import com.javacoachapi.core.controller.ICapitulosController;
import com.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.javacoachapi.core.services.ICapituloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/capitulo")
@Tag(name = "Capitulos", description = "Capitulos de OCA Oracle Certified Associate Java SE 8 Programmer I Study Guide Exam")
public class CapitulosController implements ICapitulosController{
	private static final Logger LOGGER=LoggerFactory.getLogger(CapitulosController.class);
	
	@Autowired
	ICapituloService capituloServ;
	
	@Override
	@GetMapping("/todos")
	@Operation(summary = "Devuelve todos los capítulos", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención de los capítulos exitosa", 
					content = @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = CapituloDTO.class)))),
			@ApiResponse(responseCode = "404", description = "Obtención de los capítulos fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerTodosLosCapitulos() {
		LOGGER.info("Acceso al endpoint \"/capitulo/todos\"");
		return ResponseEntity.ok().body(capituloServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Devuelve el capítulo especificado", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención del capítulo exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CapituloDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención del capítulo fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerCapitulo(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/capitulo/{}\"", id);
		return ResponseEntity.ok().body(capituloServ.traerUno(id));
	}

	@Override
	@PostMapping("/crear")
	@Operation(summary = "Crea un nuevo capítulo", responses = {
			@ApiResponse(responseCode = "201", description = "Creación del capítulo exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CapituloDTO.class))),
			@ApiResponse(responseCode = "400", description = "Creación del capítulo fallida", 
					content = @Content)	})
	public ResponseEntity<?> crearCapitulo(@Valid @RequestBody CapituloCrearDTO capituloNuevo) {
		LOGGER.info("Acceso al endpoint \"/capitulo/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(capituloServ.crear(capituloNuevo));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Elimina el capítulo indicado", responses = {
			@ApiResponse(responseCode = "204", description = "Eliminación del capítulo exitosa", 
					content = @Content),
			@ApiResponse(responseCode = "400", description = "Eliminación del capítulo fallida", 
					content = @Content)	})
	public ResponseEntity<?> eliminarCapitulo(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/capitulo/eliminar/{}\"", id);
		capituloServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualiza el capítulo indicado", responses = {
			@ApiResponse(responseCode = "200", description = "Actualización del capítulo exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CapituloDTO.class))),
			@ApiResponse(responseCode = "400", description = "Actualización del capítulo fallida", 
					content = @Content)	})
	public ResponseEntity<?> actualizarCapitulo(@Valid @RequestBody CapituloCrearDTO capituloActualizado, @PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/capitulo/actualizar/{}\"", id);
		return ResponseEntity.ok().body(capituloServ.actualizar(capituloActualizado, id));
	}

}
