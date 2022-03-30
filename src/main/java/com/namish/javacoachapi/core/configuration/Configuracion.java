package com.namish.javacoachapi.core.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
