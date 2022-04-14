package com.javacoachapi.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javacoachapi.core.models.dto.catalogo.CuestionarioDTO;
import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.dto.create.PreguntaCrearDTO;

@Service
public interface IPreguntaService {

	PreguntaDTO traerPregunta(Long id);
	
	List<PreguntaDTO> traerTodasLasPreguntas();
	
	PreguntaDTO crearPregunta(PreguntaCrearDTO preguntaNueva);
	
	boolean eliminarPregunta(Long id);
	
	PreguntaDTO actualizarPregunta(PreguntaCrearDTO preguntaActualizada, Long id);
	
	List<PreguntaDTO> traerPreguntasPorConcepto(Long conceptoId);

	List<RespuestaDTO> traerRespuestasPorPregunta(Long preguntaId);
	
	Boolean validarRespuesta(Long idRespuesta, Long idPregunta);
	
	CuestionarioDTO crearCuestionario(Long idConcepto);
	
}
