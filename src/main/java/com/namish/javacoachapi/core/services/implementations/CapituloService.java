package com.namish.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namish.javacoachapi.core.models.converters.CapituloDTOConverter;
import com.namish.javacoachapi.core.models.dto.catalogo.CapituloDTO;
import com.namish.javacoachapi.core.models.dto.create.CapituloCrearDTO;
import com.namish.javacoachapi.core.models.entities.Capitulo;
import com.namish.javacoachapi.core.repository.ICapituloRepository;
import com.namish.javacoachapi.core.services.ICapituloService;

@Service
public class CapituloService implements ICapituloService{

	@Autowired
	ICapituloRepository capituloRepo;
	@Autowired
	CapituloDTOConverter capituloDtoConverter;

	@Override
	public CapituloDTO traerCapitulo(Long capituloId) {
		Capitulo capitulo = capituloRepo.findById(capituloId).get();
		return capituloDtoConverter.convertirEntityADTO(capitulo);
	}

	@Override
	public List<CapituloDTO> traerTodosLosCapitulos() {
		List<CapituloDTO> capitulosDto = capituloRepo.findAll()
				.stream()
				.map(capituloDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return capitulosDto;
	}

	@Override
	public CapituloDTO crearCapitulo(CapituloCrearDTO capituloNuevo) {
		Capitulo capitulo = capituloDtoConverter.convertirDTOAEntity(capituloNuevo);
		return capituloDtoConverter.convertirEntityADTO(capituloRepo.save(capitulo));
	}

	@Override
	public void eliminarCapitulo(Long id) {
		capituloRepo.deleteById(id);
	}

	@Override
	public CapituloDTO actualizarCapitulo(CapituloCrearDTO capituloActualizado) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
