package com.javacoachapi.core.services;

import java.util.List;

import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;

public interface IConceptoService {

	ConceptoDTO traerConcepto(Long id);
	
	List<ConceptoDTO> traerTodosLosConceptos();
	
	ConceptoDTO crearConcepto(ConceptoCrearDTO conceptoNuevo);
	
	boolean eliminarConcepto(Long id);
	
	ConceptoDTO actualizarConcepto(ConceptoCrearDTO conceptoActualizado, Long id);

	List<ConceptoDTO> traerConceptosPorCapitulo(Long capituloId);
	
}
