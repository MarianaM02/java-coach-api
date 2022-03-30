package com.namish.javacoachapi.core.models.converters;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.namish.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.namish.javacoachapi.core.models.dto.create.PreguntaCrearDTO;
import com.namish.javacoachapi.core.models.entities.Pregunta;
import com.namish.javacoachapi.core.repository.IConceptoRepository;
import com.namish.javacoachapi.core.repository.IRespuestaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PreguntaDTOConverter {

	private final ModelMapper modelMapper;
	@Autowired
	IRespuestaRepository respuestaRepo;
	@Autowired
	IConceptoRepository conceptoRepo;
	@Autowired
	RespuestaDTOConverter respConverter;

	public PreguntaDTO convertirEntityADTO(Pregunta pregunta) {
		PreguntaDTO preguntaDTO = modelMapper.map(pregunta, PreguntaDTO.class);
		preguntaDTO.setRespuestas(
				pregunta.getRespuestas()
				.stream()
				.map(respConverter::convertirEntityADTO)
				.collect(Collectors.toList()));
		return preguntaDTO;
	}

	public Pregunta convertirDTOAEntity(PreguntaCrearDTO preguntaDTO) {
		Pregunta pregunta = new Pregunta();
		pregunta.setPregunta(preguntaDTO.getPregunta());
		pregunta.setConcepto(conceptoRepo.getById(preguntaDTO.getConceptoId()));
		pregunta.setRespuestas(respuestaRepo.saveAll(
				preguntaDTO.getRespuestas()
				.stream()
				.map(respConverter::convertirDTOAEntity)
				.collect(Collectors.toList())));
		return pregunta;
	}

}
