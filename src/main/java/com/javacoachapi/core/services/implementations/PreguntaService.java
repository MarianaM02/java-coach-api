package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacoachapi.core.models.converters.PreguntaDTOConverter;
import com.javacoachapi.core.models.converters.RespuestaDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.CuestionarioDTO;
import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.catalogo.RespuestaDTO;
import com.javacoachapi.core.models.dto.create.PreguntaCrearDTO;
import com.javacoachapi.core.models.entities.Pregunta;
import com.javacoachapi.core.models.entities.Respuesta;
import com.javacoachapi.core.repository.IPreguntaRepository;
import com.javacoachapi.core.repository.IRespuestaRepository;
import com.javacoachapi.core.services.IConceptoService;
import com.javacoachapi.core.services.IPreguntaService;

@Service
public class PreguntaService implements IPreguntaService{

	@Autowired
	IPreguntaRepository preguntaRepo;
	@Autowired
	PreguntaDTOConverter preguntaDtoConverter;
	@Autowired
	IRespuestaRepository respuestaRepo;
	@Autowired
	RespuestaDTOConverter respuestaDtoConverter;
	@Autowired
	IConceptoService conceptoServ;
	
	@Override
	public PreguntaDTO traerPregunta(Long id) {
		// TODO Exception No encontrada
		return preguntaDtoConverter.convertirEntityADTO(
				preguntaRepo.findById(id).orElse(null));
	}

	@Override
	public List<PreguntaDTO> traerTodasLasPreguntas() {
		List<Pregunta> preguntas = preguntaRepo.findAll();
		List<PreguntaDTO> preguntasDto = preguntas.stream()
				.map(preguntaDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return preguntasDto;
	}

	@Override
	public PreguntaDTO crearPregunta(PreguntaCrearDTO preguntaNueva) {
		// TODO Exception No Creada
		Pregunta pregunta = preguntaDtoConverter.convertirDTOAEntity(preguntaNueva);
		return preguntaDtoConverter.convertirEntityADTO(preguntaRepo.save(pregunta));
	}

	@Override
	public boolean eliminarPregunta(Long id) {
		// TODO Exception No encontrada
		if (preguntaRepo.existsById(id)) {
			preguntaRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public PreguntaDTO actualizarPregunta(PreguntaCrearDTO preguntaActualizada, Long id) {
		if (preguntaRepo.existsById(id)) {
			return preguntaDtoConverter.convertirEntityADTO(
					preguntaRepo.save(
							preguntaDtoConverter.convertirDTOAEntity(preguntaActualizada)));
		}
		// TODO Exception No encontrada
		return null;
	}

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
		if (preguntaRepo.existsById(preguntaId)) {
			List<Respuesta> respuestas = preguntaRepo.findById(
					preguntaId).get().getRespuestas();
			List<RespuestaDTO> respuestasDto = respuestas.stream()
					.map(respuestaDtoConverter::convertirEntityADTO)
					.collect(Collectors.toList());
			return respuestasDto;
		}
		// TODO Exception No encontrada
		return null;
	}

	@Override
	public Boolean validarRespuesta(Long idRespuesta, Long idPregunta) {
		List<Respuesta> listaRespuestas = preguntaRepo.findById(idPregunta).orElse(null).getRespuestas();
		Respuesta respuesta = respuestaRepo.findById(idRespuesta).orElse(null);
		if (listaRespuestas.contains(respuesta)) {
			return respuesta.getValida();
		}
		// TODO respuesta no corresponde a esa pregunta
		return false;
	}

	@Override
	public CuestionarioDTO crearCuestionario(Long idConcepto) {
		CuestionarioDTO cuestionario = new CuestionarioDTO();
		cuestionario.setIdConcepto(idConcepto);
		cuestionario.setNombreConcepto(
				conceptoServ.traerConcepto(idConcepto).getNombre());
		cuestionario.setPreguntas(this.traerPreguntasPorConcepto(idConcepto));
		return cuestionario;
	}

	

}
