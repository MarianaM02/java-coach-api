package com.javacoachapi.core.models.converters;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javacoachapi.core.models.dto.catalogo.CatalogoCapituloDTO;
import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.repository.INivelRepository;
import com.javacoachapi.core.services.IConceptoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CatalogoCapituloDTOConverter {

	private final ModelMapper modelMapper;
	@Autowired
	INivelRepository nivelRepo;
	@Autowired
	IConceptoService conceptoServ;
	@Autowired
	CatalogoConceptoDTOConverter conceptoConverter;
	
	public CatalogoCapituloDTO convertir(Capitulo capitulo) {
		CatalogoCapituloDTO capituloDto = modelMapper.map(capitulo, CatalogoCapituloDTO.class);
		capituloDto.setConceptos(
				conceptoServ.traerConceptosPorCapitulo(capitulo.getId())
				.stream()
				.map(conceptoConverter::convertir)
				.collect(Collectors.toList()));
		return capituloDto;
	}
	
}
