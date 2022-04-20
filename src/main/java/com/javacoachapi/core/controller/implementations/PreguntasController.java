package com.javacoachapi.core.controller.implementations;

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
import com.javacoachapi.core.models.dto.create.PreguntaCrearDTO;
import com.javacoachapi.core.services.IPreguntaService;

@RestController
@RequestMapping("/pregunta")
public class PreguntasController implements IPreguntasController {
	private static final Logger LOGGER=LoggerFactory.getLogger(PreguntasController.class);
	
	@Autowired
	IPreguntaService preguntaServ;
	
	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerPreguntas() {
		LOGGER.info("Acceso al endpoint \"/pregunta/todos\"");
		return ResponseEntity.ok().body(preguntaServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerPregunta(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/pregunta/{}\"", id);
		return ResponseEntity.ok().body(preguntaServ.traerUno(id));
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearPregunta(@RequestBody PreguntaCrearDTO preguntaNueva) {
		LOGGER.info("Acceso al endpoint \"/pregunta/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(preguntaServ.crear(preguntaNueva));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarPregunta(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/pregunta/eliminar/{}\"", id);
		preguntaServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarPregunta(@RequestBody PreguntaCrearDTO preguntaActualizada, @PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/pregunta/actualizar/{}\"", id);
		return ResponseEntity.ok().body(preguntaServ.actualizar(preguntaActualizada, id));
	}

}
