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
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.javacoachapi.core.services.ICapituloService;

@RestController
@RequestMapping("/capitulo")
public class CapitulosController implements ICapitulosController{
	private static final Logger LOGGER=LoggerFactory.getLogger(CapitulosController.class);
	
	@Autowired
	ICapituloService capituloServ;
	
	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerTodosLosCapitulos() {
		LOGGER.info("Acceso al endpoint \"/capitulo/todos\"");
		return ResponseEntity.ok().body(capituloServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerCapitulo(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/capitulo/{}\"", id);
		return ResponseEntity.ok().body(capituloServ.traerUno(id));
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearCapitulo(@Valid @RequestBody CapituloCrearDTO capituloNuevo) {
		LOGGER.info("Acceso al endpoint \"/capitulo/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(capituloServ.crear(capituloNuevo));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCapitulo(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/capitulo/eliminar/{}\"", id);
		capituloServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarCapitulo(@Valid @RequestBody CapituloCrearDTO capituloActualizado, @PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/capitulo/actualizar/{}\"", id);
		return ResponseEntity.ok().body(capituloServ.actualizar(capituloActualizado, id));
	}

}
