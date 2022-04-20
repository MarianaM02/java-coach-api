package com.javacoachapi.core.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER=LoggerFactory.getLogger(CuestionarioService.class);
			
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
		LOGGER.info("Buscando preguntas del concepto {}...", conceptoId);
		List<Pregunta> preguntas = preguntaRepo.findByConceptoId(conceptoId);
		List<PreguntaDTO> preguntasDto = preguntas.stream()
				.map(preguntaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		LOGGER.info("Preguntas del concepto encontradas.");
		return preguntasDto;
	}

	@Override
	public List<RespuestaDTO> traerRespuestasPorPregunta(Long preguntaId) {
		LOGGER.info("Buscando respuestas de la pregunta {}...", preguntaId);
		List<Respuesta> respuestas = preguntaRepo.findById(preguntaId)
				.orElseThrow(() -> new DataNotFoundException(preguntaId)).getRespuestas();
		List<RespuestaDTO> respuestasDto = respuestas.stream()
				.map(respuestaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		LOGGER.info("Respuestas de la preguntas encontradas.");
		return respuestasDto;

	}

	@Override
	public List<PreguntaDTO> traerPreguntasPorCapitulo(Long capituloId) {
		LOGGER.info("Buscando preguntas del capitulo {}...", capituloId);
		List<Concepto> conceptos = conceptoRepo.findByCapitulo(
				capituloRepo.findById(capituloId).orElseThrow(() -> new DataNotFoundException(capituloId)));
		List<PreguntaDTO> preguntas = new ArrayList<PreguntaDTO>();
		conceptos.stream().map(c -> this.traerPreguntasPorConcepto(c.getId())).collect(Collectors.toList())
				.forEach(p -> preguntas.addAll(p));
		LOGGER.info("Preguntas del capítulo encontradas.");
		return preguntas;
	}

	@Override
	public Boolean validarRespuesta(Long idRespuesta, Long idPregunta) {
		LOGGER.info("Validando Respuesta {} de pregunta {}...", idRespuesta, idPregunta);
		List<Respuesta> listaRespuestas = preguntaRepo.findById(idPregunta)
				.orElseThrow(() -> new DataNotFoundException(idPregunta)).getRespuestas();
		Respuesta respuesta = respuestaRepo.findById(idRespuesta)
				.orElseThrow(() -> new DataNotFoundException(idRespuesta));
		if (listaRespuestas.contains(respuesta)) {
			boolean esValida = respuesta.getValida();
			LOGGER.info(esValida ? "Respuesta Correcta" : "Respuesta Incorrecta");
			return esValida;
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La respuesta no corresponde a la pregunta");
	}

	@Override
	@Transactional
	public CuestionarioDTO crearCuestionarioConcepto(Long idConcepto) {
		LOGGER.info("Creando cuestionario del concepto {}...", idConcepto);
		CuestionarioDTO cuestionario = new CuestionarioDTO();
		cuestionario.setId(idConcepto);
		cuestionario.setNombre(conceptoRepo.findById(idConcepto)
				.orElseThrow(() -> new DataNotFoundException(idConcepto))
				.getNombre());
		cuestionario.setPreguntas(this.traerPreguntasPorConcepto(idConcepto));
		LOGGER.info("Cuestionario creado.", idConcepto);
		return cuestionario;
	}

	@Override
	@Transactional
	public CuestionarioDTO crearCuestionarioCapitulo(Long idCapitulo) {
		LOGGER.info("Creando cuestionario del capítulo {}...", idCapitulo);
		CuestionarioDTO cuestionario = new CuestionarioDTO();
		cuestionario.setId(idCapitulo);
		cuestionario.setNombre(capituloRepo.findById(idCapitulo)
				.orElseThrow(()-> new DataNotFoundException(idCapitulo))
				.getNombre());
		cuestionario.setPreguntas(this.traerPreguntasPorCapitulo(idCapitulo));
		LOGGER.info("Cuestionario creado.", idCapitulo);
		return cuestionario;
	}

}