package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacoachapi.core.models.converters.ConceptoDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;
import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.models.entities.Concepto;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.repository.IConceptoRepository;
import com.javacoachapi.core.services.IConceptoService;

@Service
@Transactional
public class ConceptoService implements IConceptoService {

	@Autowired
	IConceptoRepository conceptoRepo;
	@Autowired
	ConceptoDTOConverter conceptoDTOConverter;
	@Autowired
	ICapituloRepository capituloRepo;

	@Override
	public ConceptoDTO traerUno(Long id) {
		// TODO orElseThrows() Exception no encontrado
		Concepto concepto = conceptoRepo.findById(id).orElse(null);
		return conceptoDTOConverter.convertirEntityADTO(concepto);
	}

	@Override
	public List<ConceptoDTO> traerTodos() {
		List<ConceptoDTO> conceptosDto = conceptoRepo.findAll()
				.stream()
				.map(conceptoDTOConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return conceptosDto;
	}

	@Override
	public ConceptoDTO crear(ConceptoCrearDTO conceptoNuevo) {
		// TODO orElseThrows() Exception no creado
		Concepto concepto = conceptoDTOConverter.convertirDTOAEntity(conceptoNuevo);
		return conceptoDTOConverter.convertirEntityADTO(conceptoRepo.save(concepto));
	}

	@Override
	public boolean eliminar(Long id) {
		// TODO orElseThrows() Exception no encontrado
		if (conceptoRepo.existsById(id)) {
			conceptoRepo.deleteById(id);
			return true;			
		}
		return false;
	}

	@Override
	public ConceptoDTO actualizar(ConceptoCrearDTO conceptoActualizado, Long id) {
		// TODO orElseThrows() Exception no encontrado
		if (conceptoRepo.existsById(id)) {
			Concepto concepto = conceptoRepo.findById(id).get();
			concepto.setNombre(conceptoActualizado.getNombre());
			concepto.setContenido(conceptoActualizado.getContenido());
			concepto.setCapitulo(capituloRepo.findById(conceptoActualizado.getCapituloId()).get());
			conceptoRepo.save(concepto);
			return conceptoDTOConverter.convertirEntityADTO(concepto);
		}
		return null;
	}

	@Override
	public List<ConceptoDTO> traerConceptosPorCapitulo(Long capituloId) {
		// TODO orElseThrows() Exception no encontrado
		Capitulo capitulo = capituloRepo.findById(capituloId).get();
		List<ConceptoDTO> conceptosDto = conceptoRepo.findByCapitulo(capitulo)
				.stream()
				.map(conceptoDTOConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return conceptosDto;
	}

}
