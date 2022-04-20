package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoachapi.core.exceptions.DataNotFoundException;
import com.javacoachapi.core.models.converters.ConceptoDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;
import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.models.entities.Concepto;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.repository.IConceptoRepository;
import com.javacoachapi.core.services.IConceptoService;
import com.javacoachapi.core.services.IPreguntaService;

@Service
@Transactional
public class ConceptoService implements IConceptoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConceptoService.class);

	@Autowired
	IConceptoRepository conceptoRepo;
	@Autowired
	ConceptoDTOConverter conceptoDTOConverter;
	@Autowired
	ICapituloRepository capituloRepo;
	@Autowired
	IPreguntaService preguntaServ;

	@Override
	public ConceptoDTO traerUno(Long id) {
		LOGGER.info("Buscando concepto {}...", id);
		Concepto concepto = conceptoRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		LOGGER.info("Concepto encontrado.", id);
		return conceptoDTOConverter.convertirEntityADTO(concepto);
	}

	@Override
	public List<ConceptoDTO> traerTodos() {
		LOGGER.info("Buscando todos los conceptos...");
		List<ConceptoDTO> conceptosDto = conceptoRepo.findAll().stream().map(conceptoDTOConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		LOGGER.info(conceptosDto.isEmpty() ? "No hay conceptos." : "Conceptos encontrados.");
		return conceptosDto;
	}

	@Override
	public ConceptoDTO crear(ConceptoCrearDTO conceptoNuevo) {
		LOGGER.info("Creando nuevo concepto...");
		Concepto concepto = conceptoDTOConverter.convertirDTOAEntity(conceptoNuevo);
		LOGGER.info("Concepto \"{}\" creado.", concepto.getNombre());
		return conceptoDTOConverter.convertirEntityADTO(conceptoRepo.save(concepto));
	}

	@Override
	public boolean eliminar(Long id) {
		LOGGER.info("Eliminando concepto {}...", id);
		if (conceptoRepo.existsById(id)) {
			preguntaServ.borrarPorConcepto(id);
			conceptoRepo.deleteById(id);
			LOGGER.info("Concepto eliminado.");
			return true;
		}
		throw new DataNotFoundException(id);
	}

	@Override
	public ConceptoDTO actualizar(ConceptoCrearDTO conceptoActualizado, Long id) {
		LOGGER.info("Actualizando concepto {}...", id);
		Concepto concepto = conceptoRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		concepto.setNombre(conceptoActualizado.getNombre());
		concepto.setContenido(conceptoActualizado.getContenido());
		concepto.setCapitulo(capituloRepo.findById(conceptoActualizado.getCapituloId()).get());
		conceptoRepo.save(concepto);
		LOGGER.info("Concepto actualizado.");
		return conceptoDTOConverter.convertirEntityADTO(concepto);
	}

	@Override
	public List<ConceptoDTO> traerConceptosPorCapitulo(Long capituloId) {
		LOGGER.info("Buscando conceptos del capitulo {}...", capituloId);
		Capitulo capitulo = capituloRepo.findById(capituloId).orElseThrow(() -> new DataNotFoundException(capituloId));
		List<ConceptoDTO> conceptosDto = conceptoRepo.findByCapitulo(capitulo).stream()
				.map(conceptoDTOConverter::convertirEntityADTO).collect(Collectors.toList());
		return conceptosDto;
	}

	@Override
	public ConceptoDTO traerConceptoAleatorio() {
		LOGGER.info("Buscando concepto aleatorio...");
		int max = (int) conceptoRepo.count();
		Long idAleatorio = (long) (Math.random() * max + 1);
		ConceptoDTO concepto = this.traerUno(idAleatorio);
		return concepto;
	}

	@Override
	public boolean borrarPorCapitulo(Long id) {
		LOGGER.info("Eliminando conceptos del Capítulo {}...", id);
		List<Concepto> conceptos = conceptoRepo
				.findByCapitulo(capituloRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id)));
		conceptos.forEach(c->preguntaServ.borrarPorConcepto(c.getId()));
		conceptoRepo.deleteAll(conceptos);
		return true;
	}

}
