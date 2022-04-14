package com.javacoachapi.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacoachapi.core.models.entities.Concepto;
import com.javacoachapi.core.models.entities.Ejemplo;

public interface IEjemploRepository extends JpaRepository<Ejemplo, Long> {

	List<Ejemplo> findByConcepto(Concepto concepto);
	
}
