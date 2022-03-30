package com.namish.javacoachapi.core.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namish.javacoachapi.core.models.converters.ConceptoDTOConverter;
import com.namish.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.namish.javacoachapi.core.models.dto.create.ConceptoCrearDTO;
import com.namish.javacoachapi.core.models.entities.Concepto;
import com.namish.javacoachapi.core.repository.IConceptoRepository;
import com.namish.javacoachapi.core.services.IConceptoService;

@Service
public class ConceptoService implements IConceptoService {

	@Autowired
	IConceptoRepository conceptoRepo;
	@Autowired
	ConceptoDTOConverter conceptoDTOConverter;

	@Override
	public ConceptoDTO traerConcepto(Long id) {
		Concepto concepto = conceptoRepo.findById(id).get();
		return conceptoDTOConverter.convertirEntityADTO(concepto);
	}

	@Override
	public List<ConceptoDTO> traerTodosLosConceptos() {
		List<Concepto> conceptos = conceptoRepo.findAll();
		List<ConceptoDTO> conceptosDto = conceptos.stream()
				.map(conceptoDTOConverter::convertirEntityADTO)
				.collect(Collectors.toList());
		return conceptosDto;
	}

	@Override
	public ConceptoDTO crearConcepto(ConceptoCrearDTO conceptoNuevo) {
		Concepto concepto = conceptoDTOConverter.convertirDTOAEntity(conceptoNuevo);
		return conceptoDTOConverter.convertirEntityADTO(conceptoRepo.save(concepto));
	}

	@Override
	public void eliminarConcepto(Long id) {
		conceptoRepo.deleteById(id);
	}

	@Override
	public ConceptoDTO actualizarConcepto(ConceptoDTO conceptoActualizado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConceptoDTO> traerConceptosPorCapitulo(Long capituloId) {
		// TODO Auto-generated method stub
		return null;
	}

}
