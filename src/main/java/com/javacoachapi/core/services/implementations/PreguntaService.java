package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoachapi.core.exceptions.DataNotFoundException;
import com.javacoachapi.core.models.converters.PreguntaDTOConverter;
import com.javacoachapi.core.models.converters.RespuestaDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.create.PreguntaCrearDTO;
import com.javacoachapi.core.models.entities.Pregunta;
import com.javacoachapi.core.repository.IConceptoRepository;
import com.javacoachapi.core.repository.IPreguntaRepository;
import com.javacoachapi.core.services.IPreguntaService;

@Service
@Transactional
public class PreguntaService implements IPreguntaService {

	@Autowired
	IPreguntaRepository preguntaRepo;
	@Autowired
	PreguntaDTOConverter preguntaDtoConverter;
	@Autowired
	RespuestaDTOConverter respuestaDtoConverter;
	@Autowired
	IConceptoRepository conceptoRepo;

	@Override
	public PreguntaDTO traerUno(Long id) {
		return preguntaDtoConverter
				.convertirEntityADTO(preguntaRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id)));
	}

	@Override
	public List<PreguntaDTO> traerTodos() {
		List<Pregunta> preguntas = preguntaRepo.findAll();
		List<PreguntaDTO> preguntasDto = preguntas.stream().map(preguntaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return preguntasDto;
	}

	@Override
	public PreguntaDTO crear(PreguntaCrearDTO preguntaNueva) {
		Pregunta pregunta = preguntaDtoConverter.convertirDTOAEntity(preguntaNueva);
		return preguntaDtoConverter.convertirEntityADTO(preguntaRepo.save(pregunta));
	}

	@Override
	public boolean eliminar(Long id) {
		if (preguntaRepo.existsById(id)) {
			preguntaRepo.deleteById(id);
			return true;
		}
		throw new DataNotFoundException(id);
	}

	@Override
	public PreguntaDTO actualizar(PreguntaCrearDTO preguntaActualizada, Long id) {
		Pregunta pregunta = preguntaRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		pregunta.setPregunta(preguntaActualizada.getPregunta());
		pregunta.setConcepto(conceptoRepo.findById(preguntaActualizada.getConceptoId()).get());
		pregunta.getRespuestas().clear();
		pregunta.getRespuestas().addAll(preguntaActualizada.getRespuestas().stream()
				.map(respuestaDtoConverter::convertirDTOAEntity).collect(Collectors.toList()));
		PreguntaDTO preguntaDto = preguntaDtoConverter.convertirEntityADTO(preguntaRepo.save(pregunta));

		return preguntaDto;

	}

}
