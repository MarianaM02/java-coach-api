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

import com.javacoachapi.core.controller.IConceptosController;
import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;
import com.javacoachapi.core.services.IConceptoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/concepto")
@Tag(name = "Conceptos", description = "Conceptos de Java")
public class ConceptosController implements IConceptosController{
	private static final Logger LOGGER=LoggerFactory.getLogger(ConceptosController.class);

	@Autowired
	IConceptoService conceptoServ;
	
	@Override
	@GetMapping("/todos")
	@Operation(summary = "Devuelve todos los conceptos", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención de los conceptos exitosa", 
					content = @Content(mediaType = "application/json", 
					array = @ArraySchema( schema = @Schema(implementation = ConceptoDTO.class)))),
			@ApiResponse(responseCode = "404", description = "Obtención de los conceptos fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerConceptos() {
		LOGGER.info("Acceso al endpoint \"/concepto/todos\"");
		return ResponseEntity.ok().body(conceptoServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Devuelve el concepto especificado", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención del concepto exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ConceptoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención del concepto fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerConcepto(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/concepto/{}\"", id);
		return ResponseEntity.ok().body(conceptoServ.traerUno(id));
	}

	@Override
	@PostMapping("/crear")
	@Operation(summary = "Crea un nuevo concepto", responses = {
			@ApiResponse(responseCode = "201", description = "Creación del concepto exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ConceptoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Creación del concepto fallida", 
					content = @Content)	})
	public ResponseEntity<?> crearConcepto(@Valid @RequestBody ConceptoCrearDTO conceptoNuevo) {
		LOGGER.info("Acceso al endpoint \"/concepto/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(conceptoServ.crear(conceptoNuevo));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	@Operation(summary = "Elimina el concepto indicado", responses = {
			@ApiResponse(responseCode = "204", description = "Eliminación del concepto exitosa", 
					content = @Content),
			@ApiResponse(responseCode = "400", description = "Eliminación del concepto fallida", 
					content = @Content)	})
	public ResponseEntity<?> eliminarConcepto(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/concepto/eliminar/{}\"", id);
		conceptoServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	@Operation(summary = "Actualiza el concepto indicado", responses = {
			@ApiResponse(responseCode = "200", description = "Actualización del concepto exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ConceptoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Actualización del concepto fallida", 
					content = @Content)	})
	public ResponseEntity<?> actualizarConcepto(@Valid @RequestBody ConceptoCrearDTO conceptoActualizado, @PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/concepto/actualizar/{}\"", id);
		return ResponseEntity.ok().body(conceptoServ.actualizar(conceptoActualizado, id));
	}
	
	@Override
	@GetMapping("/aleatorio")
	@Operation(summary = "Devuelve un concepto aleatorio", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención del concepto aleatorio exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ConceptoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención del concepto aleatorio fallida", 
					content = @Content)	})
	public ResponseEntity<?> traerAleatorio() {
		LOGGER.info("Acceso al endpoint \"/concepto/aleatorio\"");
		return ResponseEntity.ok().body(conceptoServ.traerConceptoAleatorio());
	}

}
