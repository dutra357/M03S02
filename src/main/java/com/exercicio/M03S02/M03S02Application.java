package com.exercicio.M03S02;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "API - SENAI M03S02"))
@EnableSwagger2
public class M03S02Application {

	public static void main(String[] args) {
		SpringApplication.run(M03S02Application.class, args);
	}

}
