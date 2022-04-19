package com.javacoachapi.core.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;

import com.javacoachapi.core.models.dto.mail.FormMailRequest;

public interface IUtilidadesController {

	/**
	 * Envia un consejo aleatorio (concepto) via correo a un destinatario.
	 * 
	 * @param mail
	 * @return
	 * @throws IOException 
	 */
	ResponseEntity<?> enviarConceptoAleatorioMail(FormMailRequest form) throws IOException;

	/**
	 * Sirve para generar un PDF con las cantidades de conceptos, preguntas,
	 * respuestas y ejemplos relacionados.
	 * 
	 * @return
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws DocumentException 
	 */
	ResponseEntity<?> generarReporte() throws IOException;

	/**
	 * @throws IOException 
	 * 
	 */


}
