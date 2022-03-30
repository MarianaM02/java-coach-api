package com.namish.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namish.javacoachapi.core.models.converters.NivelDTOConverter;
import com.namish.javacoachapi.core.models.dto.create.NivelDTO;
import com.namish.javacoachapi.core.models.entities.Nivel;
import com.namish.javacoachapi.core.repository.INivelRepository;
import com.namish.javacoachapi.core.services.INivelService;

@Service
public class NivelService implements INivelService {
	
	@Autowired
	INivelRepository nivelRepo;
	@Autowired
	NivelDTOConverter nivelDtoConverter;
	
	@Override
	public NivelDTO traerNivel(Long id) {
		return nivelDtoConverter.convertirEntityADTO(nivelRepo.findById(id).get());
	}

	@Override
	public List<NivelDTO> traerTodosLosNiveles() {
		List<Nivel> niveles = nivelRepo.findAll();
		List<NivelDTO> nivelesDTO = niveles.stream()
				.map(nivelDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return nivelesDTO;
	}

	@Override
	public NivelDTO crearNivel(NivelDTO nivelNuevo) {
		Nivel nivel = nivelDtoConverter.convertirDTOAEntity(nivelNuevo);
		return nivelDtoConverter.convertirEntityADTO(nivelRepo.save(nivel));
	}

	@Override
	public void eliminarNivel(Long id) {
		nivelRepo.deleteById(id);
		
	}

	@Override
	public NivelDTO actualizarNivel(NivelDTO nivelActualizado) {
		// TODO Auto-generated method stub
		return null;
	}

}
