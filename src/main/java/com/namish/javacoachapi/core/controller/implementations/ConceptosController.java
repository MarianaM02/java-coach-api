package com.namish.javacoachapi.core.controller.implementations;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namish.javacoachapi.core.controller.IConceptosController;
import com.namish.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.namish.javacoachapi.core.services.IConceptoService;

@RestController
@RequestMapping("/concepto")
public class ConceptosController implements IConceptosController{

	@Autowired
	IConceptoService conceptoServ;
	
	@Override
	@GetMapping("/todos")
	public ResponseEntity<List<ConceptoDTO>> traerConceptos() {
		return new ResponseEntity<List<ConceptoDTO>>(conceptoServ.traerTodosLosConceptos(), HttpStatus.OK);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ConceptoDTO> traerConcepto(@PathParam(value = "id") Long id) {
		return new ResponseEntity<ConceptoDTO>(conceptoServ.traerConcepto(id), HttpStatus.OK);
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<ConceptoDTO> crearConcepto(@RequestBody ConceptoDTO conceptoNuevo) {
		return null;
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminarConcepto(@PathParam(value = "id") Long id) {
		return new ResponseEntity<String>("Concepto Borrado", HttpStatus.NO_CONTENT);
	}

	@Override
	@PutMapping("/actualizar")
	public ResponseEntity<ConceptoDTO> actualizarConcepto(@RequestBody ConceptoDTO conceptoActualizado) {
		return null;
	}

}
