package com.javacoachapi.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacoachapi.core.models.entities.Capitulo;
import com.javacoachapi.core.models.entities.Nivel;

public interface ICapituloRepository extends JpaRepository<Capitulo, Long>{
	
	List<Capitulo> findByNivel(Nivel nivel);

}
