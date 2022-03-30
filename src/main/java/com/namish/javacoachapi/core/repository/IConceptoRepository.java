package com.namish.javacoachapi.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namish.javacoachapi.core.models.entities.Capitulo;
import com.namish.javacoachapi.core.models.entities.Concepto;

public interface IConceptoRepository extends JpaRepository<Concepto, Long>{
	
	List<Concepto> findByCapitulo(Capitulo capitulo);

}
