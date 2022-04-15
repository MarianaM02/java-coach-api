package com.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacoachapi.core.models.converters.ConceptoDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.models.dto.create.ConceptoCrearDTO;
import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.models.entities.Concepto;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.repository.IConceptoRepository;
import com.javacoachapi.core.services.IConceptoService;

@Service
public class ConceptoService implements IConceptoService {

	@Autowired
	IConceptoRepository conceptoRepo;
	@Autowired
	ICapituloRepository capituloRepo;
	@Autowired
	ConceptoDTOConverter conceptoDTOConverter;

	@Override
	public ConceptoDTO traerConcepto(Long id) {
		// TODO orElseThrows() Exeption no encontrado
		Concepto concepto = conceptoRepo.findById(id).orElse(null);
		return conceptoDTOConverter.convertirEntityADTO(concepto);
	}

	@Override
	public List<ConceptoDTO> traerTodosLosConceptos() {
		List<ConceptoDTO> conceptosDto = conceptoRepo.findAll()
				.stream()
				.map(conceptoDTOConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return conceptosDto;
	}

	@Override
	public ConceptoDTO crearConcepto(ConceptoCrearDTO conceptoNuevo) {
		// TODO orElseThrows() Exeption no creado
		Concepto concepto = conceptoDTOConverter.convertirDTOAEntity(conceptoNuevo);
		return conceptoDTOConverter.convertirEntityADTO(conceptoRepo.save(concepto));
	}

	@Override
	public boolean eliminarConcepto(Long id) {
		// TODO orElseThrows() Exeption no encontrado
		if (conceptoRepo.existsById(id)) {
			conceptoRepo.deleteById(id);
			return true;			
		}
		return false;
	}


	@Override
	public List<ConceptoDTO> traerConceptosPorCapitulo(Long capituloId) {
		// TODO orElseThrows() Exeption no encontrado
		Capitulo capitulo = capituloRepo.findById(capituloId).get();
		List<ConceptoDTO> conceptosDto = conceptoRepo.findByCapitulo(capitulo)
				.stream()
				.map(conceptoDTOConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return conceptosDto;
	}

	@Override
	public ConceptoDTO actualizarConcepto(ConceptoCrearDTO conceptoActualizado, Long id) {
		// TODO orElseThrows() Exeption no encontrado
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

}
