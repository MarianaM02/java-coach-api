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

import com.namish.javacoachapi.core.controller.ICapitulosController;
import com.namish.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.namish.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.namish.javacoachapi.core.services.ICapituloService;

@RestController
@RequestMapping("/capitulo")
public class CapitulosController implements ICapitulosController{
	
	@Autowired
	ICapituloService capituloServ;

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<CapituloDTO> traerCapitulo(@PathParam(value = "id") Long id) {
		return new ResponseEntity<CapituloDTO>(capituloServ.traerCapitulo(id), HttpStatus.OK);
	}

	@Override
	@GetMapping("/todos")
	public ResponseEntity<List<CapituloDTO>> traerTodosLosCapitulos() {
		return new ResponseEntity<List<CapituloDTO>>(capituloServ.traerTodosLosCapitulos(), HttpStatus.OK);
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<CapituloDTO> crearCapitulo(@RequestBody CapituloCrearDTO capituloNuevo) {
		return new ResponseEntity<CapituloDTO>(capituloServ.crearCapitulo(capituloNuevo), HttpStatus.CREATED);
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCapitulo(@PathParam(value = "id") Long id) {
		return new ResponseEntity<String>("Capitulo id " + id + " eliminado", HttpStatus.NO_CONTENT);
	}
	
	@Override
	@PutMapping("/actualizar")
	public ResponseEntity<CapituloDTO> actualizarCapitulo(@RequestBody CapituloCrearDTO capituloActualizado) {
		// TODO Auto-generated method stub
		return null;
	}

}
