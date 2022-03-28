package com.namish.javacoachapi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namish.javacoachapi.core.models.entities.Ejemplo;

public interface IEjemploRepository extends JpaRepository<Ejemplo, Long> {

}
