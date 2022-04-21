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
import com.javacoachapi.core.models.dto.catalogo.CatalogoDTO;
import com.javacoachapi.core.models.dto.mail.FormMailRequest;
import com.javacoachapi.core.services.ICapituloService;
import com.javacoachapi.core.services.IUtilidadesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Utilidades", description = "Métodos de utilidades de la API")
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
	@Operation(summary = "Envía un concepto aleatorio por mail", responses = {
			@ApiResponse(responseCode = "202", description = "Mail enviado exitosamente", 
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Fallo al enviar el mail", 
					content = @Content)
	})
	public ResponseEntity<?> enviarConceptoAleatorioMail(@Valid @RequestBody FormMailRequest form) throws IOException {
		LOGGER.info("Acceso al endpoint \"/enviar/concepto\"");
		utilServ.mandarMailConJavaMailSender(form);
		return ResponseEntity.accepted().build();
	}
	
	@Override
	@GetMapping("/generar/reporte")
	@Operation(summary = "Genera un reporte en PDF indicando las cantidades de conceptos, preguntas, respuestas y ejemplos disponibles actualmente.", responses = {
			@ApiResponse(responseCode = "200", description = "Reporte creado exitosamente", 
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Fallo en la creación del reporte", 
					content = @Content)
	})
	public ResponseEntity<?> generarReporte() throws IOException{
		LOGGER.info("Acceso al endpoint \"/generar/reporte\"");
		utilServ.crearPDF();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/catalogo")
	@Operation(summary = "Devuelve todo el contenido del libro disponible actualmente.", responses = {
			@ApiResponse(responseCode = "200", description = "Obtención del catálogo exitosa", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CatalogoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Obtención del catálogo fallida", 
					content = @Content)
	})
	public ResponseEntity<?> traerCatalogoCompleto() {
		LOGGER.info("Acceso al endpoint \"/catalogo\"");
		return ResponseEntity.ok().body(utilServ.traerCatalogoDTO());
	}
	
}
