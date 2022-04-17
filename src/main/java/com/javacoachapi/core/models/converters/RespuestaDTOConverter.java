package com.javacoachapi.core.models.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;
import com.javacoachapi.core.models.entities.Respuesta;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RespuestaDTOConverter {

	private final ModelMapper modelMapper;
	
	public RespuestaDTO convertirEntityADTO(Respuesta respuesta) {
		return modelMapper.map(respuesta, RespuestaDTO.class);
	}
	
	public Respuesta convertirDTOAEntity(RespuestaCrearDTO respuestaDTO) {
		Respuesta respuesta = new Respuesta();
		respuesta.setId(respuestaDTO.getId());
		respuesta.setRespuesta(respuestaDTO.getRespuesta());
		respuesta.setValida(respuestaDTO.getValida());
		return respuesta;
	}
	
}
