package com.javacoachapi.core.models.converters;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.repository.INivelRepository;
import com.javacoachapi.core.services.IConceptoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CapituloDTOConverter {

	private final ModelMapper modelMapper;
	@Autowired
	INivelRepository nivelRepo;
	@Autowired
	IConceptoService conceptoServ;
	@Autowired
	ConceptoDTOConverter conceptoDtoConverter;
	
	public CapituloDTO convertirEntityADTO(Capitulo capitulo) {
		CapituloDTO capituloDto = modelMapper.map(capitulo, CapituloDTO.class);
		capituloDto.setConceptos(
				conceptoServ.traerConceptosPorCapitulo(capitulo.getId()));
		return capituloDto;
	}
	
	public Capitulo convertirDTOAEntity(CapituloCrearDTO capituloDto) {
		Capitulo capitulo = new Capitulo();
		capitulo.setNumero(capituloDto.getNumero());
		capitulo.setNombre(capituloDto.getNombre());
		capitulo.setNivel(nivelRepo.getById(capituloDto.getNivelId()));
		return capitulo;
	}
	
}
