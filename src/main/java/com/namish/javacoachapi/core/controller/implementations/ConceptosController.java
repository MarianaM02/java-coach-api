package com.namish.javacoachapi.core.controller.implementations;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namish.javacoachapi.core.controller.IConceptosController;
import com.namish.javacoachapi.core.models.dto.ConceptoDTO;

@RestController
@RequestMapping("/concepto")
public class ConceptosController implements IConceptosController{

	@Override
	@GetMapping("/todos")
	public ResponseEntity<List<ConceptoDTO>> traerConceptos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ConceptoDTO> traerConcepto(@PathParam(value = "id") Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<ConceptoDTO> crearConcepto(@RequestBody ConceptoDTO conceptoNuevo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarConcepto(@PathParam(value = "id") Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PutMapping("/actualizar")
	public ResponseEntity<ConceptoDTO> actualizarConcepto(@RequestBody ConceptoDTO conceptoActualizado) {
		// TODO Auto-generated method stub
		return null;
	}

}
