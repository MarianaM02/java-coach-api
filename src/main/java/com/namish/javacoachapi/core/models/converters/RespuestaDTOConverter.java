package com.namish.javacoachapi.core.models.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.namish.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.namish.javacoachapi.core.models.dto.create.RespuestaCrearDTO;
import com.namish.javacoachapi.core.models.entities.Respuesta;

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
		respuesta.setRespuesta(respuestaDTO.getRespuesta());
		respuesta.setValida(respuestaDTO.getValida());
		return respuesta;
	}
	
}
