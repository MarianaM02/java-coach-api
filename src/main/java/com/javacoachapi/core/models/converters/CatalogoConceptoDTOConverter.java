package com.javacoachapi.core.models.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javacoachapi.core.models.dto.catalogo.CatalogoConceptoDTO;
import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.services.ICuestionarioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CatalogoConceptoDTOConverter {

	private final ModelMapper modelMapper;
	@Autowired
	ICuestionarioService cuestionarioServ;
	
	public CatalogoConceptoDTO convertir(ConceptoDTO conceptoDto) {
		CatalogoConceptoDTO catConceptoDto = modelMapper.map(conceptoDto, CatalogoConceptoDTO.class);
		catConceptoDto.setPreguntas(cuestionarioServ.traerPreguntasPorConcepto(conceptoDto.getId()));
		return catConceptoDto;
	}
	
}
