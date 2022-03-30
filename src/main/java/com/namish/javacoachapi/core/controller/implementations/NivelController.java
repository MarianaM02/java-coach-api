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

import com.namish.javacoachapi.core.controller.INivelController;
import com.namish.javacoachapi.core.models.dto.create.NivelDTO;
import com.namish.javacoachapi.core.services.INivelService;

@RestController
@RequestMapping("/nivel")
public class NivelController implements INivelController {
	
	@Autowired
	INivelService nivelServ;

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<NivelDTO> traerNivel(@PathParam(value = "id") Long id) {
		return new ResponseEntity<NivelDTO>(nivelServ.traerNivel(id), HttpStatus.FOUND);
	}
	
	@Override
	@GetMapping("/todos")
	public ResponseEntity<List<NivelDTO>> traerTodosLosNiveles() {
		return new ResponseEntity<List<NivelDTO>>(nivelServ.traerTodosLosNiveles(), HttpStatus.OK);
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<NivelDTO> crearNivel(@RequestBody NivelDTO nivelNuevo) {
		return new ResponseEntity<NivelDTO>(nivelServ.crearNivel(nivelNuevo), HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/actualizar")
	public ResponseEntity<NivelDTO> actualizarNivel(@RequestBody NivelDTO nivelActualizado) {
		return new ResponseEntity<NivelDTO>(nivelServ.actualizarNivel(nivelActualizado), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarNivel(@PathParam(value = "id")Long id) {
		nivelServ.eliminarNivel(id);
		return new ResponseEntity<String>("Nivel Eliminado", HttpStatus.NO_CONTENT);
	}


}
