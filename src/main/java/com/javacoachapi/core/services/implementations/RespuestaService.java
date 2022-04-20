package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacoachapi.core.exceptions.DataNotFoundException;
import com.javacoachapi.core.models.converters.RespuestaDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;
import com.javacoachapi.core.models.entities.Respuesta;
import com.javacoachapi.core.repository.IRespuestaRepository;
import com.javacoachapi.core.services.IRespuestaService;

@Service
public class RespuestaService implements IRespuestaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RespuestaService.class);

	@Autowired
	IRespuestaRepository respuestaRepo;
	@Autowired
	RespuestaDTOConverter respuestaDtoConverter;

	@Override
	public RespuestaDTO traerUno(Long id) {
		LOGGER.info("Buscando respuesta {}...", id);
		Respuesta respuesta = respuestaRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		LOGGER.info("Respuesta encontrada.");
		return respuestaDtoConverter.convertirEntityADTO(respuesta);
	}

	@Override
	public List<RespuestaDTO> traerTodos() {
		LOGGER.info("Buscando todas las respuestas...");
		List<Respuesta> respuestas = respuestaRepo.findAll();
		List<RespuestaDTO> respuestasDto = respuestas.stream().map(respuestaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		LOGGER.info(respuestasDto.isEmpty() ? "No hay respuestas." : "Respuestas encontradas.");
		return respuestasDto;
	}

	@Override
	public RespuestaDTO crear(RespuestaCrearDTO respuestaNueva) {
		LOGGER.info("Creando nueva respuesta...");
		Respuesta respuesta = respuestaDtoConverter.convertirDTOAEntity(respuestaNueva);
		LOGGER.info("Respuesta \"{}\" creada.", respuesta.getRespuesta());
		RespuestaDTO respuestaDto = respuestaDtoConverter.convertirEntityADTO(respuestaRepo.save(respuesta));
		return respuestaDto;

	}

	@Override
	public boolean eliminar(Long id) {
		LOGGER.info("Eliminando respuesta {}...", id);
		if (respuestaRepo.existsById(id)) {
			respuestaRepo.deleteById(id);
			LOGGER.info("Respuesta eliminada.");
			return true;
		}
		throw new DataNotFoundException(id);
	}

	@Override
	public RespuestaDTO actualizar(RespuestaCrearDTO respuestaActualizada, Long id) {
		LOGGER.info("Actualizando respuesta {}...", id);
		Respuesta respuesta = respuestaRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		respuesta.setRespuesta(respuestaActualizada.getRespuesta());
		respuesta.setValida(respuestaActualizada.getValida());
		respuestaRepo.save(respuesta);
		LOGGER.info("Respuesta actualizada.");
		return respuestaDtoConverter.convertirEntityADTO(respuesta);

	}

}
