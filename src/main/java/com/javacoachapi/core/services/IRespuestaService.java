package com.javacoachapi.core.services;

import java.util.List;

import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.dto.create.RespuestaCrearDTO;

public interface IRespuestaService {

	RespuestaDTO traerUno(Long id);

	List<RespuestaDTO> traerTodos();

	RespuestaDTO crear(RespuestaCrearDTO respuestaNueva);

	boolean eliminar(Long id);

	RespuestaDTO actualizar(RespuestaCrearDTO respuestaActualizada, Long id);

}
