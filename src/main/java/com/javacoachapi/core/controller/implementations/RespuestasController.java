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
import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;
import com.javacoachapi.core.services.IRespuestaService;

@RestController
@RequestMapping("/respuesta")
public class RespuestasController implements IRespuestasController {
	private static final Logger LOGGER=LoggerFactory.getLogger(RespuestasController.class);
			
	@Autowired
	IRespuestaService respuestaServ;

	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerRespuestas() {
		LOGGER.info("Acceso al endpoint \"/respuesta/todos\"");
		return ResponseEntity.ok().body(respuestaServ.traerTodos());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerRespuesta(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/respuesta/{}\"", id);
		return ResponseEntity.ok().body(respuestaServ.traerUno(id));
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearRespuesta(@Valid @RequestBody RespuestaCrearDTO respuestaNueva) {
		LOGGER.info("Acceso al endpoint \"/respuesta/crear\"");
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestaServ.crear(respuestaNueva));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarRespuesta(@PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/respuesta/eliminar/{}\"", id);
		respuestaServ.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarRespuesta(@Valid @RequestBody RespuestaCrearDTO respuestaActualizada, @PathVariable Long id) {
		LOGGER.info("Acceso al endpoint \"/respuesta/actualizar/{}\"", id);
		return ResponseEntity.ok().body(respuestaServ.actualizar(respuestaActualizada, id));
	}

}
