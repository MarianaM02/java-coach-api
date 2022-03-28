package com.namish.javacoachapi.core.controller.implementations;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namish.javacoachapi.core.controller.IUtilidadesController;

@RestController
public class UtilidadesController implements IUtilidadesController{

	@Override
	@GetMapping("/enviar/concepto")
	public ResponseEntity<Boolean> enviarConceptoAleatorioMail(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/generar/reporte")
	public ResponseEntity<Boolean> generarReporte() {
		// TODO Auto-generated method stub
		return null;
	}

}
