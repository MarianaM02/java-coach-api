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

import com.javacoachapi.core.controller.ICapitulosController;
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.javacoachapi.core.services.ICapituloService;

@RestController
@RequestMapping("/capitulo")
public class CapitulosController implements ICapitulosController{
	
	@Autowired
	ICapituloService capituloServ;

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerCapitulo(@PathVariable Long id) {
		return ResponseEntity.ok().body(capituloServ.traerCapitulo(id));
	}

	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerTodosLosCapitulos() {
		return ResponseEntity.ok().body(capituloServ.traerTodosLosCapitulos());
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearCapitulo(@RequestBody CapituloCrearDTO capituloNuevo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(capituloServ.crearCapitulo(capituloNuevo));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCapitulo(@PathVariable Long id) {
		capituloServ.eliminarCapitulo(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarCapitulo(@RequestBody CapituloCrearDTO capituloActualizado, @PathVariable Long id) {
		return ResponseEntity.ok().body(capituloServ.actualizarCapitulo(capituloActualizado, id));
	}

}
