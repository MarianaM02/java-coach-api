package com.javacoachapi.core.controller.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacoachapi.core.controller.ICuestionarioController;
import com.javacoachapi.core.services.ICuestionarioService;

@RestController
@RequestMapping("/cuestionario")
public class CuestionarioController implements ICuestionarioController{

	@Autowired
	ICuestionarioService cuetionarioServ;
	
	@Override
	@GetMapping("/concepto/{idConcepto}")
	public ResponseEntity<?> traerCuestionarioPorConcepto(@PathVariable Long idConcepto) {
		return ResponseEntity.ok().body(cuetionarioServ.crearCuestionarioConcepto(idConcepto));
	}
	
	@Override
	@GetMapping("/capitulo/{idCapitulo}")
	public ResponseEntity<?> traerCuestionarioPorCapitulo(@PathVariable Long idCapitulo) {
		return ResponseEntity.ok().body(cuetionarioServ.crearCuestionarioCapitulo(idCapitulo));
	}

	@Override
	@GetMapping("/validar/{idRespuesta}/{idPregunta}")
	public ResponseEntity<?> validarRespuesta(@PathVariable Long idRespuesta, @PathVariable Long idPregunta) {
		return ResponseEntity.ok().body(cuetionarioServ.validarRespuesta(idRespuesta, idPregunta));
	}

}
