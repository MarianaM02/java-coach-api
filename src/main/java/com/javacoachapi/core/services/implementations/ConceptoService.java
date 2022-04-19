package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

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
		Concepto concepto = conceptoRepo.findById(id)
				.orElseThrow(() -> new DataNotFoundException(id));
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
		Concepto concepto = conceptoDTOConverter.convertirDTOAEntity(conceptoNuevo);
		return conceptoDTOConverter.convertirEntityADTO(conceptoRepo.save(concepto));
	}

	@Override
	public boolean eliminar(Long id) {
		if (conceptoRepo.existsById(id)) {
			conceptoRepo.deleteById(id);
			return true;			
		}
		throw new DataNotFoundException(id);
	}

	@Override
	public ConceptoDTO actualizar(ConceptoCrearDTO conceptoActualizado, Long id) {
			Concepto concepto = conceptoRepo.findById(id)
					.orElseThrow(() -> new DataNotFoundException(id));
			concepto.setNombre(conceptoActualizado.getNombre());
			concepto.setContenido(conceptoActualizado.getContenido());
			concepto.setCapitulo(capituloRepo.findById(conceptoActualizado.getCapituloId()).get());
			conceptoRepo.save(concepto);
			return conceptoDTOConverter.convertirEntityADTO(concepto);
	}

	@Override
	public List<ConceptoDTO> traerConceptosPorCapitulo(Long capituloId) {
		Capitulo capitulo = capituloRepo.findById(capituloId)
				.orElseThrow(() -> new DataNotFoundException(capituloId));
		List<ConceptoDTO> conceptosDto = conceptoRepo.findByCapitulo(capitulo)
				.stream()
				.map(conceptoDTOConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return conceptosDto;
	}
	
	@Override
	public ConceptoDTO traerConceptoAleatorio() {
		int max = (int) conceptoRepo.count();
		Long idAleatorio = (long) (Math.random()*max+1);
		ConceptoDTO concepto = this.traerUno(idAleatorio);
		return concepto;
	}
	

}
