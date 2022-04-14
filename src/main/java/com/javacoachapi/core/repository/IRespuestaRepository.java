package com.javacoachapi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacoachapi.core.models.entities.Respuesta;

public interface IRespuestaRepository extends JpaRepository<Respuesta, Long>{
	
}
