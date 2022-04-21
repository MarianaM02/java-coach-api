package com.javacoachapi.core.controller.implementations;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javacoachapi.core.controller.IUtilidadesController;
import com.javacoachapi.core.models.dto.mail.FormMailRequest;
import com.javacoachapi.core.services.ICapituloService;
import com.javacoachapi.core.services.IUtilidadesService;

@RestController
public class UtilidadesController implements IUtilidadesController{
	private static final Logger LOGGER=LoggerFactory.getLogger(UtilidadesController.class);
	
	@Autowired
	IUtilidadesService utilServ;
	@Autowired
	ICapituloService capituloServ;

	/**
	 * @throws IOException 
	 * 
	 */
	@Override
	@GetMapping("/enviar/concepto")
	public ResponseEntity<?> enviarConceptoAleatorioMail(@Valid @RequestBody FormMailRequest form) throws IOException {
		LOGGER.info("Acceso al endpoint \"/enviar/concepto\"");
		utilServ.mandarMailConJavaMailSender(form);
		return ResponseEntity.accepted().build();
	}
	
	@Override
	@GetMapping("/generar/reporte")
	public ResponseEntity<?> generarReporte() throws IOException{
		LOGGER.info("Acceso al endpoint \"/generar/reporte\"");
		utilServ.crearPDF("Algo");
		return ResponseEntity.ok().build();
	}

	@GetMapping("/catalogo")
	public ResponseEntity<?> traerCatalogoCompleto() {
		LOGGER.info("Acceso al endpoint \"/catalogo\"");
		return ResponseEntity.ok().body(utilServ.traerCatalogoDTO());
	}
	
}
