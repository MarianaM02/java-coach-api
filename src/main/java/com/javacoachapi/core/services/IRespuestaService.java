package com.javacoachapi.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;

@Service
public interface IRespuestaService {

RespuestaDTO traerRespuesta(Long id);
	
	List<RespuestaDTO> traerTodasLasRespuestas();
	
	RespuestaDTO crearRespuesta(RespuestaCrearDTO respuestaNueva);
	
	boolean eliminarRespuesta(Long id);
	
	RespuestaDTO actualizarRespuesta(RespuestaCrearDTO respuestaActualizada, Long id);
	
}
