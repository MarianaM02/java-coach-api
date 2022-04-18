package com.javacoachapi.core.controller.implementations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacoachapi.core.controller.IUtilidadesController;
import com.javacoachapi.core.services.ICapituloService;
import com.javacoachapi.core.services.IUtilidadesService;

@RestController
public class UtilidadesController implements IUtilidadesController{
	
	@Autowired
	IUtilidadesService utilServ;
	@Autowired
	ICapituloService capituloServ;

	/**
	 * 
	 */
	@Override
	@GetMapping("/enviar/concepto")
	public ResponseEntity<?> enviarConceptoAleatorioMail(String mail) {
		// TODO Enviar concepto aleatorio por mail
		return null;
	}
	
	@Override
	@GetMapping("/generar/reporte")
	public ResponseEntity<?> generarReporte() throws IOException{
		// TODO Generar reporte en PDF
		utilServ.crearPDF("Algo");
		return ResponseEntity.ok().build();
	}

	@GetMapping("/catalogo")
	public ResponseEntity<?> traerCatalogoCompleto() {
		return ResponseEntity.ok().body(utilServ.traerCatalogoDTO());
	}
	
}
