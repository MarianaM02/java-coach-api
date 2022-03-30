package com.namish.javacoachapi.core.models.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.namish.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.namish.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.namish.javacoachapi.core.models.entities.Capitulo;
import com.namish.javacoachapi.core.repository.INivelRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CapituloDTOConverter {

	private final ModelMapper modelMapper;
	@Autowired
	INivelRepository nivelRepo;
	
	public CapituloDTO convertirEntityADTO(Capitulo capitulo) {
		return modelMapper.map(capitulo, CapituloDTO.class);
	}
	
	public Capitulo convertirDTOAEntity(CapituloCrearDTO capituloDto) {
		Capitulo capitulo = new Capitulo();
		capitulo.setNumero(capituloDto.getNumero());
		capitulo.setNombre(capituloDto.getNombre());
		capitulo.setNivel(nivelRepo.findById(capituloDto.getNivelId()).get());
		return capitulo;
	}
	
}
