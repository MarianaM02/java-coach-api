package com.javacoachapi.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.models.entities.Concepto;

public interface IConceptoRepository extends JpaRepository<Concepto, Long>{
	
	List<Concepto> findByCapitulo(Capitulo capitulo);
	
}
