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

import com.javacoachapi.core.controller.INivelController;
import com.javacoachapi.core.models.dto.create.NivelDTO;
import com.javacoachapi.core.services.INivelService;

@RestController
@RequestMapping("/nivel")
public class NivelController implements INivelController {
	
	@Autowired
	INivelService nivelServ;

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerNivel(@PathVariable Long id) {
		return ResponseEntity.ok().body(nivelServ.traerNivel(id));
	}
	
	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerTodosLosNiveles() {
		return ResponseEntity.ok().body(nivelServ.traerTodosLosNiveles());
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearNivel(@RequestBody NivelDTO nivelNuevo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(nivelServ.crearNivel(nivelNuevo));
	}

	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarNivel(@RequestBody NivelDTO nivelActualizado, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok().body(nivelServ.actualizarNivel(nivelActualizado, id));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarNivel(@PathVariable Long id) {
		nivelServ.eliminarNivel(id);
		return ResponseEntity.noContent().build();
	}


}
