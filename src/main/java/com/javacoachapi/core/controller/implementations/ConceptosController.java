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

import com.javacoachapi.core.controller.IConceptosController;
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;
import com.javacoachapi.core.services.IConceptoService;

@RestController
@RequestMapping("/concepto")
public class ConceptosController implements IConceptosController{

	@Autowired
	IConceptoService conceptoServ;
	
	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerConceptos() {
		return ResponseEntity.ok().body(conceptoServ.traerTodosLosConceptos());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerConcepto(@PathVariable Long id) {
		return ResponseEntity.ok().body(conceptoServ.traerConcepto(id));
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearConcepto(@RequestBody ConceptoCrearDTO conceptoNuevo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(conceptoServ.crearConcepto(conceptoNuevo));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarConcepto(@PathVariable Long id) {
		conceptoServ.eliminarConcepto(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarConcepto(@RequestBody ConceptoCrearDTO conceptoActualizado, @PathVariable Long id) {
		return ResponseEntity.ok().body(conceptoServ.actualizarConcepto(conceptoActualizado, id));
	}

}
