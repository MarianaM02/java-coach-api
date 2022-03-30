package com.namish.javacoachapi.core.controller.implementations;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
	public NivelDTO traerNivel(@PathParam(value = "id") Long id) {
		return nivelServ.traerNivel(id);
	}
	
	@Override
	@GetMapping("/todos")
	public List<NivelDTO> traerTodosLosNiveles() {
		return nivelServ.traerTodosLosNiveles();
	}

	@Override
	@PostMapping("/crear")
	public NivelDTO crearNivel(@RequestBody NivelDTO nivelNuevo) {
		return nivelServ.crearNivel(nivelNuevo);
	}

	@Override
	@PutMapping("/actualizar")
	public NivelDTO actualizarNivel(@RequestBody NivelDTO nivelActualizado) {
		return nivelServ.actualizarNivel(nivelActualizado);
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public void eliminarNivel(@PathParam(value = "id")Long id) {
		nivelServ.eliminarNivel(id);
	}


}
