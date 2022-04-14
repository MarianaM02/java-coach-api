package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacoachapi.core.models.converters.RespuestaDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;
import com.javacoachapi.core.models.entities.Respuesta;
import com.javacoachapi.core.repository.IRespuestaRepository;
import com.javacoachapi.core.services.IRespuestaService;

@Service
public class RespuestaService implements IRespuestaService {

	@Autowired
	IRespuestaRepository respuestaRepo;
	@Autowired
	RespuestaDTOConverter respuestaDtoConverter;

	@Override
	public RespuestaDTO traerRespuesta(Long id) {
		return respuestaDtoConverter.convertirEntityADTO(respuestaRepo.findById(id).orElse(null));
	}

	@Override
	public List<RespuestaDTO> traerTodasLasRespuestas() {
		List<Respuesta> respuestas = respuestaRepo.findAll();
		List<RespuestaDTO> respuestasDto = respuestas.stream().map(respuestaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return respuestasDto;
	}

	@Override
	public RespuestaDTO crearRespuesta(RespuestaCrearDTO respuestaNueva) {
		Respuesta respuesta = respuestaDtoConverter.convertirDTOAEntity(respuestaNueva);
		return respuestaDtoConverter.convertirEntityADTO(respuestaRepo.save(respuesta));
	}

	@Override
	public boolean eliminarRespuesta(Long id) {
		if (respuestaRepo.existsById(id)) {
			respuestaRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public RespuestaDTO actualizarRespuesta(RespuestaCrearDTO respuestaActualizada, Long id) {
		if (respuestaRepo.existsById(id)) {
			return respuestaDtoConverter.convertirEntityADTO(
					respuestaRepo.save(respuestaDtoConverter.convertirDTOAEntity(respuestaActualizada)));
		}
		return null;
	}

}
