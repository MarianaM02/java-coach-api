package com.javacoachapi.core.controller.implementations;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacoachapi.core.controller.IUtilidadesController;

@RestController
public class UtilidadesController implements IUtilidadesController{

	@Override
	@GetMapping("/enviar/concepto")
	public ResponseEntity<?> enviarConceptoAleatorioMail(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/generar/reporte")
	public ResponseEntity<?> generarReporte() {
		// TODO Auto-generated method stub
		return null;
	}

}
