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

@RestController
@RequestMapping("/nivel")
public class NivelController implements INivelController {
	private static final Logger LOGGER=LoggerFactory.getLogger(NivelController.class);
	
	@Autowired
	INivelService nivelServ;

	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerTodosLosNiveles() {
		LOGGER.info("Acceso al endpoint \"/nivel/todos\"");
		return ResponseEntity.ok().body(nivelServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerNivel(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/nivel/{}\"", id);
		return ResponseEntity.ok().body(nivelServ.traerUno(id));
	}
	
	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearNivel(@Valid @RequestBody NivelDTO nivelNuevo) {
		LOGGER.info("Acceso al endpoint \"/nivel/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(nivelServ.crear(nivelNuevo));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarNivel(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/nivel/eliminar/{}\"", id);
		nivelServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarNivel(@Valid @RequestBody NivelDTO nivelActualizado, @PathVariable Long id) throws Exception {
		LOGGER.info("Acceso al endpoint \"/nivel/actualizar/{}\"", id);
		return ResponseEntity.ok().body(nivelServ.actualizar(nivelActualizado, id));
	}


}
