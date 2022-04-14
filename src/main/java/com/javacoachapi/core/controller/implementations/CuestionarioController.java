package com.javacoachapi.core.controller.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacoachapi.core.controller.ICuestionarioController;
import com.javacoachapi.core.services.IPreguntaService;

@RestController
@RequestMapping("/cuestionario")
public class CuestionarioController implements ICuestionarioController{

	@Autowired
	IPreguntaService preguntaServ;
	
	@Override
	@GetMapping("/{idConcepto}")
	public ResponseEntity<?> traerCuestionarioPorConcepto(Long idConcepto) {
		return ResponseEntity.ok().body(preguntaServ.crearCuestionario(idConcepto));
	}

	@Override
	@GetMapping("/validar/{idRespuesta}/{idPregunta}")
	public ResponseEntity<?> validarRespuesta(@PathVariable Long idRespuesta, @PathVariable Long idPregunta) {
		return ResponseEntity.ok().body(preguntaServ.validarRespuesta(idRespuesta, idPregunta));
	}


}
