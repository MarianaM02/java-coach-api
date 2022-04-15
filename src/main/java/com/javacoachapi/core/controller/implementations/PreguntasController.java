package com.javacoachapi.core.controller.implementations;

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

	@Autowired
	IPreguntaService preguntaServ;
	
	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerPreguntas() {
		return ResponseEntity.ok().body(preguntaServ.traerTodasLasPreguntas());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerPregunta(@PathVariable Long id) {
		return ResponseEntity.ok().body(preguntaServ.traerPregunta(id));
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearPregunta(@RequestBody PreguntaCrearDTO preguntaNueva) {
		return ResponseEntity.status(HttpStatus.CREATED).body(preguntaServ.crearPregunta(preguntaNueva));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarPregunta(@PathVariable Long id) {
		preguntaServ.eliminarPregunta(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarPregunta(@RequestBody PreguntaCrearDTO preguntaActualizada, @PathVariable Long id) {
		return ResponseEntity.ok().body(preguntaServ.actualizarPregunta(preguntaActualizada, id));
	}

}
