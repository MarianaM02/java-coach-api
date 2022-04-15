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

import com.javacoachapi.core.controller.IRespuestasController;
import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;
import com.javacoachapi.core.services.IRespuestaService;

@RestController
@RequestMapping("/respuesta")
public class RespuestasController implements IRespuestasController {

	@Autowired
	IRespuestaService respuestaServ;

	@Override
	@GetMapping("/todos")
	public ResponseEntity<?> traerRespuestas() {
		return ResponseEntity.ok().body(respuestaServ.traerTodasLasRespuestas());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> traerRespuesta(@PathVariable Long id) {
		return ResponseEntity.ok().body(respuestaServ.traerRespuesta(id));
	}

	@Override
	@PostMapping("/crear")
	public ResponseEntity<?> crearRespuesta(@RequestBody RespuestaCrearDTO respuestaNueva) {
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestaServ.crearRespuesta(respuestaNueva));
	}

	@Override
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarRespuesta(@PathVariable Long id) {
		respuestaServ.eliminarRespuesta(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarRespuesta(@RequestBody RespuestaCrearDTO respuestaActualizada, @PathVariable Long id) {
		return ResponseEntity.ok().body(respuestaServ.actualizarRespuesta(respuestaActualizada, id));
	}

}
