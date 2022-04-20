package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(PreguntaService.class);

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
		LOGGER.info("Buscando pregunta {}...", id);
		Pregunta pregunta = preguntaRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		LOGGER.info("Pregunta encontrada.", id);
		return preguntaDtoConverter.convertirEntityADTO(pregunta);
	}

	@Override
	public List<PreguntaDTO> traerTodos() {
		LOGGER.info("Buscando todas las preguntas...");
		List<Pregunta> preguntas = preguntaRepo.findAll();
		List<PreguntaDTO> preguntasDto = preguntas.stream().map(preguntaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		LOGGER.info(preguntasDto.isEmpty() ? "No hay preguntas." : "Preguntas encontradas.");
		return preguntasDto;
	}

	@Override
	public PreguntaDTO crear(PreguntaCrearDTO preguntaNueva) {
		LOGGER.info("Creando nueva pregunta...");
		Pregunta pregunta = preguntaDtoConverter.convertirDTOAEntity(preguntaNueva);
		LOGGER.info("Pregunta \"{}\" creada.", pregunta.getPregunta());
		return preguntaDtoConverter.convertirEntityADTO(preguntaRepo.save(pregunta));
	}

	@Override
	public boolean eliminar(Long id) {
		LOGGER.info("Eliminando pregunta {}...", id);
		if (preguntaRepo.existsById(id)) {
			preguntaRepo.deleteById(id);
			LOGGER.info("Pregunta eliminada.");
			return true;
		}
		throw new DataNotFoundException(id);
	}

	@Override
	public PreguntaDTO actualizar(PreguntaCrearDTO preguntaActualizada, Long id) {
		LOGGER.info("Actualizando pregunta {}...", id);
		Pregunta pregunta = preguntaRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		pregunta.setPregunta(preguntaActualizada.getPregunta());
		pregunta.setConcepto(conceptoRepo.findById(preguntaActualizada.getConceptoId()).get());
		pregunta.getRespuestas().clear();
		pregunta.getRespuestas().addAll(preguntaActualizada.getRespuestas().stream()
				.map(respuestaDtoConverter::convertirDTOAEntity).collect(Collectors.toList()));
		
		PreguntaDTO preguntaDto = preguntaDtoConverter.convertirEntityADTO(preguntaRepo.save(pregunta));
		LOGGER.info("Pregunta actualizada.");
		return preguntaDto;

	}

	@Override
	public boolean borrarPorConcepto(Long id) {
		preguntaRepo.deleteAll(preguntaRepo.findByConceptoId(id));
		return true;
	}

}
