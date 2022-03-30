package com.namish.javacoachapi.core.models.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.namish.javacoachapi.core.models.dto.create.NivelDTO;
import com.namish.javacoachapi.core.models.entities.Nivel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NivelDTOConverter {
	
	private final ModelMapper modelMapper;
	
	public NivelDTO convertirEntityADTO(Nivel nivel) {
		return modelMapper.map(nivel, NivelDTO.class);
	}

	public Nivel convertirDTOAEntity(NivelDTO nivelDto) {
		Nivel nivel = new Nivel();
		nivel.setNombre(nivelDto.getNombre());
		return nivel;
	}
	
}
