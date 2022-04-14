package com.javacoachapi.core.models.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;
import com.javacoachapi.core.models.entities.Concepto;
import com.javacoachapi.core.repository.ICapituloRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConceptoDTOConverter {
	
	private final ModelMapper modelMapper;
	@Autowired
	ICapituloRepository capituloRepo;
	
	public ConceptoDTO convertirEntityADTO(Concepto concepto) {
		return modelMapper.map(concepto, ConceptoDTO.class);
	}
	
	public Concepto convertirDTOAEntity(ConceptoCrearDTO conceptoDTO) {
		Concepto concepto = new Concepto();
		concepto.setNombre(conceptoDTO.getNombre());
		concepto.setContenido(conceptoDTO.getContenido());
		concepto.setCapitulo(capituloRepo.getById(conceptoDTO.getCapituloId()));
		return concepto;
	}
	
}
