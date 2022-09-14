package com.ander.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.ander.commons.alumnos.models.entity"})
public class MicroservicioUsusarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioUsusarioApplication.class, args);
	}

}
