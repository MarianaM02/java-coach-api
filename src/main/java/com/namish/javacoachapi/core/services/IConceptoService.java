package com.namish.javacoachapi.core.services;

import java.util.List;

import com.namish.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.namish.javacoachapi.core.models.dto.create.ConceptoCrearDTO;

public interface IConceptoService {

	ConceptoDTO traerConcepto(Long id);
	
	List<ConceptoDTO> traerTodosLosConceptos();
	
	ConceptoDTO crearConcepto(ConceptoCrearDTO conceptoNuevo);
	
	void eliminarConcepto(Long id);
	
	ConceptoDTO actualizarConcepto(ConceptoDTO conceptoActualizado);

	List<ConceptoDTO> traerConceptosPorCapitulo(Long capituloId);
	
}
