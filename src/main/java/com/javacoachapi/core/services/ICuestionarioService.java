package com.javacoachapi.core.services;

import java.util.List;

import com.javacoachapi.core.models.dto.catalogo.CuestionarioDTO;
import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;

public interface ICuestionarioService {

	List<PreguntaDTO> traerPreguntasPorConcepto(Long conceptoId);
	
	List<PreguntaDTO> traerPreguntasPorCapitulo(Long capituloId);

	List<RespuestaDTO> traerRespuestasPorPregunta(Long preguntaId);
	
	Boolean validarRespuesta(Long idRespuesta, Long idPregunta);
	
	CuestionarioDTO crearCuestionarioConcepto(Long idConcepto);
	
	CuestionarioDTO crearCuestionarioCapitulo(Long idCapitulo);
	
	
}
