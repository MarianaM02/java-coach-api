package com.javacoachapi.core.controller.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER=LoggerFactory.getLogger(CuestionarioController.class);

	@Autowired
	ICuestionarioService cuetionarioServ;
	
	@Override
	@GetMapping("/concepto/{idConcepto}")
	public ResponseEntity<?> traerCuestionarioPorConcepto(@PathVariable Long idConcepto) {
		LOGGER.info("Acceso al endpoint \"/cuestionario/concepto/{}\"", idConcepto);
		return ResponseEntity.ok().body(cuetionarioServ.crearCuestionarioConcepto(idConcepto));
	}
	
	@Override
	@GetMapping("/capitulo/{idCapitulo}")
	public ResponseEntity<?> traerCuestionarioPorCapitulo(@PathVariable Long idCapitulo) {
		LOGGER.info("Acceso al endpoint \"/cuestionario/capitulo/{}\"", idCapitulo);
		return ResponseEntity.ok().body(cuetionarioServ.crearCuestionarioCapitulo(idCapitulo));
	}

	@Override
	@GetMapping("/validar/{idRespuesta}/{idPregunta}")
	public ResponseEntity<?> validarRespuesta(@PathVariable Long idRespuesta, @PathVariable Long idPregunta) {
		LOGGER.info("Acceso al endpoint \"/cuestionario/validar/{}/{}\"", idRespuesta, idPregunta);
		return ResponseEntity.ok().body(cuetionarioServ.validarRespuesta(idRespuesta, idPregunta));
	}

}
