package com.namish.javacoachapi.core.controller.implementations;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namish.javacoachapi.core.controller.ICuestionarioController;
import com.namish.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.namish.javacoachapi.core.models.dto.catalogo.CuestionarioDTO;

@RestController
@RequestMapping("/cuestionario")
public class CuestionarioController implements ICuestionarioController{

	@Override
	@GetMapping("/concepto")
	public ResponseEntity<CuestionarioDTO> traerCuestionarioPorConcepto(ConceptoDTO concepto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/validar/{idRespuesta}/{idPregunta}")
	public ResponseEntity<Boolean> validarRespuesta(@PathParam(value = "idRespuesta") Long idRespuesta, @PathParam(value = "idPregunta") Long idPregunta) {
		// TODO Auto-generated method stub
		return null;
	}


}
