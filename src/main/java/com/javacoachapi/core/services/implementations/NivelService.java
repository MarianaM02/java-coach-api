package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacoachapi.core.models.converters.NivelDTOConverter;
import com.javacoachapi.core.models.dto.create.NivelDTO;
import com.javacoachapi.core.models.entities.Nivel;
import com.javacoachapi.core.repository.INivelRepository;
import com.javacoachapi.core.services.INivelService;

@Service
public class NivelService implements INivelService {

	@Autowired
	INivelRepository nivelRepo;
	@Autowired
	NivelDTOConverter nivelDtoConverter;

	@Override
	public NivelDTO traerNivel(Long id) {
		return nivelDtoConverter.convertirEntityADTO(nivelRepo.findById(id).orElse(null));
	}

	@Override
	public List<NivelDTO> traerTodosLosNiveles() {
		List<Nivel> niveles = nivelRepo.findAll();
		List<NivelDTO> nivelesDTO = niveles.stream().map(nivelDtoConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return nivelesDTO;
	}

	@Override
	public NivelDTO crearNivel(NivelDTO nivelNuevo) {
		Nivel nivel = nivelDtoConverter.convertirDTOAEntity(nivelNuevo);
		return nivelDtoConverter.convertirEntityADTO(nivelRepo.save(nivel));
	}

	@Override
	public boolean eliminarNivel(Long id) {
		if (nivelRepo.existsById(id)) {
			nivelRepo.deleteById(id);
			return true;
		}
		return false;

	}

	@Override
	public NivelDTO actualizarNivel(NivelDTO nivelActualizado, Long id) throws Exception {
		// TODO orElseThrows() Exeption no encontrado
		if (nivelRepo.existsById(id)) {
			Nivel nivel = nivelRepo.findById(id).get();
			nivel.setNombre(nivelActualizado.getNombre());
			nivelRepo.save(nivel);
			return nivelDtoConverter.convertirEntityADTO(nivel);
		}
		return null;

	}

}
