package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

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

	@Autowired
	IRespuestaRepository respuestaRepo;
	@Autowired
	RespuestaDTOConverter respuestaDtoConverter;

	@Override
	public RespuestaDTO traerUno(Long id) {
		return respuestaDtoConverter
				.convertirEntityADTO(respuestaRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id)));
	}

	@Override
	public List<RespuestaDTO> traerTodos() {
		List<Respuesta> respuestas = respuestaRepo.findAll();
		List<RespuestaDTO> respuestasDto = respuestas.stream().map(respuestaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return respuestasDto;
	}

	@Override
	public RespuestaDTO crear(RespuestaCrearDTO respuestaNueva) {
		Respuesta respuesta = respuestaDtoConverter.convertirDTOAEntity(respuestaNueva);
		RespuestaDTO respuestaDto = respuestaDtoConverter.convertirEntityADTO(respuestaRepo.save(respuesta));
		return respuestaDto;

	}

	@Override
	public boolean eliminar(Long id) {
		if (respuestaRepo.existsById(id)) {
			respuestaRepo.deleteById(id);
			return true;
		}
		throw new DataNotFoundException(id);
	}

	@Override
	public RespuestaDTO actualizar(RespuestaCrearDTO respuestaActualizada, Long id) {
		Respuesta respuesta = respuestaRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		respuesta.setRespuesta(respuestaActualizada.getRespuesta());
		respuesta.setValida(respuestaActualizada.getValida());
		respuestaRepo.save(respuesta);
		return respuestaDtoConverter.convertirEntityADTO(respuesta);

	}

}
