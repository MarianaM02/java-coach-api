package com.namish.javacoachapi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namish.javacoachapi.core.models.entities.Pregunta;

public interface IPreguntaRepository extends JpaRepository<Pregunta, Long>{

}
