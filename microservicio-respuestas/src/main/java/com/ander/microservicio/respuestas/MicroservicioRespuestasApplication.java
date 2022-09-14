package com.ander.microservicio.respuestas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//53. Configurando entity scan : Con esto tenemos relacionado tanto del propio propyecto y de commons.alumnos && commons.examenes
// para que el JPA sepa donde se encuentra las clases Entity  -> @Entity
@EntityScan({"com.ander.microservicio.respuestas.model.entity" ,
	"com.ander.commons.alumnos.models.entity",
	"com.ander.commons.examenes.models.entity"
   })
@EnableEurekaClient
@SpringBootApplication
public class MicroservicioRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRespuestasApplication.class, args);
	}

}
