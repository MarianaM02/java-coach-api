package com.javacoachapi.core.services;

import java.io.IOException;

import com.javacoachapi.core.models.dto.catalogo.CatalogoDTO;
import com.javacoachapi.core.models.dto.mail.FormMailRequest;

public interface IUtilidadesService {

	void crearPDF(String mail) throws IOException;
	
	void mandarMailConJavaMailSender(FormMailRequest form) throws IOException;

	CatalogoDTO traerCatalogoDTO();
	
}
