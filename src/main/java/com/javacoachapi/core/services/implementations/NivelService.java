package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoachapi.core.exceptions.DataNotFoundException;
import com.javacoachapi.core.models.converters.NivelDTOConverter;
import com.javacoachapi.core.models.dto.create.NivelDTO;
import com.javacoachapi.core.models.entities.Nivel;
import com.javacoachapi.core.repository.INivelRepository;
import com.javacoachapi.core.services.ICapituloService;
import com.javacoachapi.core.services.INivelService;

@Service
@Transactional
public class NivelService implements INivelService {
	private static final Logger LOGGER=LoggerFactory.getLogger(NivelService.class);

	@Autowired
	INivelRepository nivelRepo;
	@Autowired
	NivelDTOConverter nivelDtoConverter;
	@Autowired
	ICapituloService capituloServ;

	@Override
	public NivelDTO traerUno(Long id) {
		LOGGER.info("Buscando nivel {}...", id);
		Nivel nivel = nivelRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		LOGGER.info("Nivel encontrado.", id);
		return nivelDtoConverter.convertirEntityADTO(nivel);
	}

	@Override
	public List<NivelDTO> traerTodos() {
		LOGGER.info("Buscando todos los niveles...");
		List<Nivel> niveles = nivelRepo.findAll();
		List<NivelDTO> nivelesDto = niveles.stream().map(nivelDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		LOGGER.info(nivelesDto.isEmpty() ? "No hay niveles." : "Niveles encontrados.");
		return nivelesDto;
	}

	@Override
	public NivelDTO crear(NivelDTO nivelNuevo) {
		LOGGER.info("Creando nuevo nivel...");
		Nivel nivel = nivelDtoConverter.convertirDTOAEntity(nivelNuevo);
		LOGGER.info("Nivel \"{}\" creado.", nivel.getNombre());
		NivelDTO nivelDto = nivelDtoConverter.convertirEntityADTO(nivelRepo.save(nivel));
		return nivelDto;
	}

	@Override
	public boolean eliminar(Long id) {
		LOGGER.info("Eliminando nivel {}...", id);
		if (nivelRepo.existsById(id)) {
			capituloServ.borrarPorNivel(id);
			nivelRepo.deleteById(id);
			LOGGER.info("Nivel eliminado.");
			return true;
		}
		throw new DataNotFoundException(id);

	}

	@Override
	public NivelDTO actualizar(NivelDTO nivelActualizado, Long id) throws Exception {
		LOGGER.info("Actualizando nivel {}...", id);
		Nivel nivel = nivelRepo.findById(id).orElseThrow(() -> new DataNotFoundException(id));
		nivel.setNombre(nivelActualizado.getNombre());
		nivelRepo.save(nivel);
		LOGGER.info("Nivel actualizado.");
		return nivelDtoConverter.convertirEntityADTO(nivel);

	}

}
