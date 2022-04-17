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
	public RespuestaDTO traerUno(Long id) {
		// TODO orElseThrows() Exception no encontrado
		return respuestaDtoConverter.convertirEntityADTO(respuestaRepo.findById(id).orElse(null));
	}

	@Override
	public List<RespuestaDTO> traerTodos() {
		List<Respuesta> respuestas = respuestaRepo.findAll();
		List<RespuestaDTO> respuestasDto = respuestas.stream()
				.map(respuestaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return respuestasDto;
	}

	@Override
	public RespuestaDTO crear(RespuestaCrearDTO respuestaNueva) {
		// TODO orElseThrows() Exception no creado
		Respuesta respuesta = respuestaDtoConverter.convertirDTOAEntity(respuestaNueva);
		return respuestaDtoConverter.convertirEntityADTO(respuestaRepo.save(respuesta));
	}

	@Override
	public boolean eliminar(Long id) {
		// TODO orElseThrows() Exeption no encontrado
		if (respuestaRepo.existsById(id)) {
			respuestaRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public RespuestaDTO actualizar(RespuestaCrearDTO respuestaActualizada, Long id) {
		// TODO orElseThrows() Exeption no encontrado
		if (respuestaRepo.existsById(id)) {
			Respuesta respuesta = respuestaRepo.findById(id).get();
			respuesta.setRespuesta(respuestaActualizada.getRespuesta());
			respuesta.setValida(respuestaActualizada.getValida());
			respuestaRepo.save(respuesta);
			return respuestaDtoConverter.convertirEntityADTO(respuesta);
		}
		return null;
	}

}
