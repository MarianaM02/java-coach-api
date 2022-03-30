package com.namish.javacoachapi.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.namish.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.namish.javacoachapi.core.models.dto.create.CapituloCrearDTO;

@Service
public interface ICapituloService {

	CapituloDTO traerCapitulo(Long capituloId);
	
	List<CapituloDTO> traerTodosLosCapitulos();
	
	CapituloDTO crearCapitulo(CapituloCrearDTO capituloNuevo);
	
	void eliminarCapitulo(Long id);
	
	CapituloDTO actualizarCapitulo(CapituloCrearDTO capituloActualizado);
	

}
