package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoachapi.core.models.converters.CapituloDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.repository.INivelRepository;
import com.javacoachapi.core.services.ICapituloService;

@Service
@Transactional
public class CapituloService implements ICapituloService {

	@Autowired
	ICapituloRepository capituloRepo;
	@Autowired
	CapituloDTOConverter capituloDtoConverter;
	@Autowired
	INivelRepository nivelRepo;

	@Override
	public CapituloDTO traerUno(Long capituloId) {
		// TODO orElseThrows() Exeption no encontrado
		Capitulo capitulo = capituloRepo.findById(capituloId).orElse(null);
		return capituloDtoConverter.convertirEntityADTO(capitulo);
	}

	@Override
	public List<CapituloDTO> traerTodos() {
		List<CapituloDTO> capitulosDto = capituloRepo.findAll().stream().map(capituloDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return capitulosDto;
	}

	@Override
	public CapituloDTO crear(CapituloCrearDTO capituloNuevo) {
		// TODO orElseThrows() Exeption no creado
		Capitulo capitulo = capituloDtoConverter.convertirDTOAEntity(capituloNuevo);
		return capituloDtoConverter.convertirEntityADTO(capituloRepo.save(capitulo));
	}

	@Override
	public boolean eliminar(Long id) {
		// TODO orElseThrows() Exeption no encontrado
		if (capituloRepo.existsById(id)) {
			capituloRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public CapituloDTO actualizar(CapituloCrearDTO capituloActualizado, Long id) {
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

}
