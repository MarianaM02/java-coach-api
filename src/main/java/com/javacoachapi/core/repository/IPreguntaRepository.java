package com.javacoachapi.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacoachapi.core.models.entities.Pregunta;

public interface IPreguntaRepository extends JpaRepository<Pregunta, Long>{

	List<Pregunta> findByConceptoId(Long id);
	
}
