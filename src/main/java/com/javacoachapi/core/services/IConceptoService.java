package com.javacoachapi.core.services;

import java.util.List;

import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;

public interface IConceptoService {

	ConceptoDTO traerUno(Long id);
	
	List<ConceptoDTO> traerTodos();
	
	ConceptoDTO crear(ConceptoCrearDTO conceptoNuevo);
	
	boolean eliminar(Long id);
	
	ConceptoDTO actualizar(ConceptoCrearDTO conceptoActualizado, Long id);

	List<ConceptoDTO> traerConceptosPorCapitulo(Long capituloId);

	ConceptoDTO traerConceptoAleatorio();

	boolean borrarPorCapitulo(Long id);
	
}
