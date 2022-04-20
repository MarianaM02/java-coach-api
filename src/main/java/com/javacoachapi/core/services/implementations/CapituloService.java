package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoachapi.core.exceptions.DataNotFoundException;
import com.javacoachapi.core.models.converters.CapituloDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.repository.INivelRepository;
import com.javacoachapi.core.services.ICapituloService;
import com.javacoachapi.core.services.IConceptoService;

@Service
@Transactional
public class CapituloService implements ICapituloService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CapituloService.class);

	@Autowired
	ICapituloRepository capituloRepo;
	@Autowired
	CapituloDTOConverter capituloDtoConverter;
	@Autowired
	INivelRepository nivelRepo;
	@Autowired
	IConceptoService conceptoServ;

	@Override
	public CapituloDTO traerUno(Long capituloId) {
		LOGGER.info("Buscando capítulo {}...", capituloId);
		Capitulo capitulo = capituloRepo.findById(capituloId).orElseThrow(() -> new DataNotFoundException(capituloId));
		LOGGER.info("Capítulo encontrado.", capituloId);
		return capituloDtoConverter.convertirEntityADTO(capitulo);
	}

	@Override
	public List<CapituloDTO> traerTodos() {
		LOGGER.info("Buscando todos los capítulos...");
		List<CapituloDTO> capitulosDto = capituloRepo.findAll().stream().map(capituloDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		LOGGER.info(capitulosDto.isEmpty() ? "No hay capítulos." : "Capítulos encontrados.");
		return capitulosDto;
	}

	@Override
	public CapituloDTO crear(CapituloCrearDTO capituloNuevo) {
		LOGGER.info("Creando nuevo capítulo...");
		Capitulo capitulo = capituloDtoConverter.convertirDTOAEntity(capituloNuevo);
		LOGGER.info("Capítulo \"{}\" creado.", capitulo.getNombre());
		return capituloDtoConverter.convertirEntityADTO(capituloRepo.save(capitulo));
	}

	@Override
	public boolean eliminar(Long id) {
		LOGGER.info("Eliminando capítulo {}...", id);
		if (capituloRepo.existsById(id)) {
			conceptoServ.borrarPorCapitulo(id);
			capituloRepo.deleteById(id);
			LOGGER.info("Capítulo eliminado.");
			return true;
		}
		throw new DataNotFoundException(id);
	}

	@Override
	public CapituloDTO actualizar(CapituloCrearDTO capituloActualizado, Long id) {
		LOGGER.info("Actualizando capítulo {}...", id);
		Capitulo capitulo = capituloRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		capitulo.setNumero(capituloActualizado.getNumero());
		capitulo.setNombre(capituloActualizado.getNombre());
		capitulo.setNivel(nivelRepo.getById(capituloActualizado.getNivelId()));
		capituloRepo.save(capitulo);
		LOGGER.info("Capítulo actualizado.");
		return capituloDtoConverter.convertirEntityADTO(capitulo);

	}

	@Override
	public boolean borrarPorNivel(Long id) {
		LOGGER.info("Eliminando capítulos del Nivel {}...", id);
		List<Capitulo> capitulos = capituloRepo.findByNivel(
				nivelRepo.findById(id).orElseThrow(()->new DataNotFoundException()));
		capitulos.forEach(c->conceptoServ.borrarPorCapitulo(c.getId()));
		capituloRepo.deleteAll(capitulos);
		return true;
	}

}
