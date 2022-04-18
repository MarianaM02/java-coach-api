package com.javacoachapi.core.services;

import java.io.IOException;

import com.javacoachapi.core.models.dto.catalogo.CatalogoDTO;

public interface IUtilidadesService {

	void crearPDF(Object data) throws IOException;

	void mandarMail(Object data);

	CatalogoDTO traerCatalogoDTO();
	
}
