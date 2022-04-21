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
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;
import com.javacoachapi.core.services.IConceptoService;

@RestController
@RequestMapping("/concepto")
public class ConceptosController implements IConceptosController{
	private static final Logger LOGGER=LoggerFactory.getLogger(ConceptosController.class);

	@Autowired
	IConceptoService conceptoServ;
	
	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerConceptos() {
		LOGGER.info("Acceso al endpoint \"/concepto/todos\"");
		return ResponseEntity.ok().body(conceptoServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerConcepto(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/concepto/{}\"", id);
		return ResponseEntity.ok().body(conceptoServ.traerUno(id));
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearConcepto(@Valid @RequestBody ConceptoCrearDTO conceptoNuevo) {
		LOGGER.info("Acceso al endpoint \"/concepto/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(conceptoServ.crear(conceptoNuevo));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarConcepto(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/concepto/eliminar/{}\"", id);
		conceptoServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarConcepto(@Valid @RequestBody ConceptoCrearDTO conceptoActualizado, @PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/concepto/actualizar/{}\"", id);
		return ResponseEntity.ok().body(conceptoServ.actualizar(conceptoActualizado, id));
	}
	
	@Override
	@GetMapping("/aleatorio")
	public ResponseEntity<?> traerAleatorio() {
		LOGGER.info("Acceso al endpoint \"/concepto/aleatorio\"");
		return ResponseEntity.ok().body(conceptoServ.traerConceptoAleatorio());
	}

}
