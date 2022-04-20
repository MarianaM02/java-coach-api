package com.javacoachapi.core.services;

import java.util.List;

import com.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;

public interface ICapituloService {

	CapituloDTO traerUno(Long capituloId);
	
	List<CapituloDTO> traerTodos();
	
	CapituloDTO crear(CapituloCrearDTO capituloNuevo);
	
	boolean eliminar(Long id);
	
	CapituloDTO actualizar(CapituloCrearDTO capituloActualizado, Long id);

	boolean borrarPorNivel(Long id);

}
