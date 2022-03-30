package com.namish.javacoachapi.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.namish.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.namish.javacoachapi.core.models.dto.create.PreguntaCrearDTO;

@Service
public interface IPreguntaService {

	PreguntaDTO traerPregunta(Long id);
	
	List<PreguntaDTO> traerTodasLasPreguntas();
	
	PreguntaDTO crearPregunta(PreguntaCrearDTO preguntaNueva);
	
	void eliminarPregunta(Long id);
	
	PreguntaDTO actualizarPregunta(PreguntaCrearDTO preguntaActualizada);
	
	List<PreguntaDTO> traerPreguntasPorConcepto(Long conceptoId);
	
	List<PreguntaDTO> traerPreguntasPorCapitulo(Long capituloId);
	
}
