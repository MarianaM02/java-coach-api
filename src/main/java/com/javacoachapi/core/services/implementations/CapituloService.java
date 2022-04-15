package com.javacoachapi.core.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacoachapi.core.models.converters.CapituloDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.javacoachapi.core.models.dto.catalogo.PreguntaDTO;
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.models.entities.Concepto;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.repository.IConceptoRepository;
import com.javacoachapi.core.repository.INivelRepository;
import com.javacoachapi.core.services.ICapituloService;
import com.javacoachapi.core.services.IPreguntaService;

@Service
public class CapituloService implements ICapituloService {

	@Autowired
	ICapituloRepository capituloRepo;
	@Autowired
	IConceptoRepository conceptoRepo;
	@Autowired
	INivelRepository nivelRepo;
	@Autowired
	CapituloDTOConverter capituloDtoConverter;
	@Autowired
	IPreguntaService preguntaService;

	@Override
	public CapituloDTO traerCapitulo(Long capituloId) {
		// TODO orElseThrows() Exeption no encontrado
		Capitulo capitulo = capituloRepo.findById(capituloId).orElse(null);
		return capituloDtoConverter.convertirEntityADTO(capitulo);
	}

	@Override
	public List<CapituloDTO> traerTodosLosCapitulos() {
		List<CapituloDTO> capitulosDto = capituloRepo.findAll().stream().map(capituloDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return capitulosDto;
	}

	@Override
	public CapituloDTO crearCapitulo(CapituloCrearDTO capituloNuevo) {
		// TODO orElseThrows() Exeption no creado
		Capitulo capitulo = capituloDtoConverter.convertirDTOAEntity(capituloNuevo);
		return capituloDtoConverter.convertirEntityADTO(capituloRepo.save(capitulo));
	}

	@Override
	public boolean eliminarCapitulo(Long id) {
		// TODO orElseThrows() Exeption no encontrado
		if (capituloRepo.existsById(id)) {
			capituloRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public CapituloDTO actualizarCapitulo(CapituloCrearDTO capituloActualizado, Long id) {
		// TODO orElseThrows() Exeption no encontrado
		if (capituloRepo.existsById(id)) {
			Capitulo capitulo = capituloRepo.findById(id).get();
			capitulo.setNumero(capituloActualizado.getNumero());
			capitulo.setNombre(capituloActualizado.getNombre());
			capitulo.setNivel(nivelRepo.getById(capituloActualizado.getNivelId()));
			capituloRepo.save(capitulo);
			return capituloDtoConverter.convertirEntityADTO(capitulo);
		}
		return null;
	}

	@Override
	public List<PreguntaDTO> traerPreguntasPorCapitulo(Long capituloId) {
		// TODO exceptions
		List<Concepto> conceptos = conceptoRepo.findByCapitulo(capituloRepo.findById(capituloId).orElse(null));
		List<PreguntaDTO> preguntas = new ArrayList<PreguntaDTO>();
		conceptos.stream()
			.map(c -> preguntaService.traerPreguntasPorConcepto(c.getId()))
			.collect(Collectors.toList())
			.forEach(p -> preguntas.addAll(p));
		return preguntas;
	}

}
