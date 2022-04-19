package com.javacoachapi.core.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.javacoachapi.core.exceptions.DataNotFoundException;
import com.javacoachapi.core.models.converters.PreguntaDTOConverter;
import com.javacoachapi.core.models.converters.RespuestaDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.CuestionarioDTO;
import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.entities.Concepto;
import com.javacoachapi.core.models.entities.Pregunta;
import com.javacoachapi.core.models.entities.Respuesta;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.repository.IConceptoRepository;
import com.javacoachapi.core.repository.IPreguntaRepository;
import com.javacoachapi.core.repository.IRespuestaRepository;
import com.javacoachapi.core.services.ICuestionarioService;

@Service
public class CuestionarioService implements ICuestionarioService {
	@Autowired
	IPreguntaRepository preguntaRepo;
	@Autowired
	PreguntaDTOConverter preguntaDtoConverter;
	@Autowired
	IRespuestaRepository respuestaRepo;
	@Autowired
	RespuestaDTOConverter respuestaDtoConverter;
	@Autowired
	IConceptoRepository conceptoRepo;
	@Autowired
	ICapituloRepository capituloRepo;

	@Override
	public List<PreguntaDTO> traerPreguntasPorConcepto(Long conceptoId) {
		List<Pregunta> preguntas = preguntaRepo.findByConceptoId(conceptoId);
		List<PreguntaDTO> preguntasDto = preguntas.stream()
				.map(preguntaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return preguntasDto;
	}

	@Override
	public List<RespuestaDTO> traerRespuestasPorPregunta(Long preguntaId) {

		List<Respuesta> respuestas = preguntaRepo.findById(preguntaId)
				.orElseThrow(() -> new DataNotFoundException(preguntaId)).getRespuestas();
		List<RespuestaDTO> respuestasDto = respuestas.stream()
				.map(respuestaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return respuestasDto;

	}

	@Override
	public List<PreguntaDTO> traerPreguntasPorCapitulo(Long capituloId) {
		List<Concepto> conceptos = conceptoRepo.findByCapitulo(
				capituloRepo.findById(capituloId).orElseThrow(() -> new DataNotFoundException(capituloId)));
		List<PreguntaDTO> preguntas = new ArrayList<PreguntaDTO>();
		conceptos.stream().map(c -> this.traerPreguntasPorConcepto(c.getId())).collect(Collectors.toList())
				.forEach(p -> preguntas.addAll(p));
		return preguntas;
	}

	@Override
	public Boolean validarRespuesta(Long idRespuesta, Long idPregunta) {
		List<Respuesta> listaRespuestas = preguntaRepo.findById(idPregunta)
				.orElseThrow(() -> new DataNotFoundException(idPregunta)).getRespuestas();
		Respuesta respuesta = respuestaRepo.findById(idRespuesta)
				.orElseThrow(() -> new DataNotFoundException(idRespuesta));
		if (listaRespuestas.contains(respuesta)) {
			return respuesta.getValida();
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La respuesta no corresponde a la pregunta");
	}

	@Override
	@Transactional
	public CuestionarioDTO crearCuestionarioConcepto(Long idConcepto) {
		CuestionarioDTO cuestionario = new CuestionarioDTO();
		cuestionario.setId(idConcepto);
		cuestionario.setNombre(conceptoRepo.findById(idConcepto)
				.orElseThrow(() -> new DataNotFoundException(idConcepto))
				.getNombre());
		cuestionario.setPreguntas(this.traerPreguntasPorConcepto(idConcepto));
		return cuestionario;
	}

	@Override
	@Transactional
	public CuestionarioDTO crearCuestionarioCapitulo(Long idCapitulo) {
		CuestionarioDTO cuestionario = new CuestionarioDTO();
		cuestionario.setId(idCapitulo);
		cuestionario.setNombre(capituloRepo.findById(idCapitulo)
				.orElseThrow(()-> new DataNotFoundException(idCapitulo))
				.getNombre());
		cuestionario.setPreguntas(this.traerPreguntasPorCapitulo(idCapitulo));
		return cuestionario;
	}

}