package com.javacoachapi.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;

@Service
public interface ICapituloService {

	CapituloDTO traerCapitulo(Long capituloId);
	
	List<CapituloDTO> traerTodosLosCapitulos();
	
	CapituloDTO crearCapitulo(CapituloCrearDTO capituloNuevo);
	
	boolean eliminarCapitulo(Long id);
	
	CapituloDTO actualizarCapitulo(CapituloCrearDTO capituloActualizado, Long id);

	List<PreguntaDTO> traerPreguntasPorCapitulo(Long capituloId);
	
}
