package com.mb;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InitialTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitialTemplateApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()

				.setPropertyCondition(Conditions.isNotNull());
		return mapper;
	}

}
