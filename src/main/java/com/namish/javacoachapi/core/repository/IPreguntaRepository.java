package com.namish.javacoachapi.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namish.javacoachapi.core.models.entities.Concepto;
import com.namish.javacoachapi.core.models.entities.Pregunta;

public interface IPreguntaRepository extends JpaRepository<Pregunta, Long>{

	List<Pregunta> findByConcepto(Concepto concepto);
	
}
