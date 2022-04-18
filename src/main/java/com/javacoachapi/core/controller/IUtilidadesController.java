package com.javacoachapi.core.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;

public interface IUtilidadesController {

	/**
	 * Envia un consejo aleatorio (concepto) via correo a un destinatario.
	 * 
	 * @param mail
	 * @return
	 */
	ResponseEntity<?> enviarConceptoAleatorioMail(String mail);

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

}
