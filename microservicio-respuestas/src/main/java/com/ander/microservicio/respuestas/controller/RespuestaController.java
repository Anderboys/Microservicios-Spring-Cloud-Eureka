package com.ander.microservicio.respuestas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ander.microservicio.respuestas.model.entity.Respuesta;
import com.ander.microservicio.respuestas.service.RespuestaService;

@RestController
public class RespuestaController {
	
	//55 -> 3. Añadiendo componentes service y controller
	@Autowired
	private RespuestaService service;
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas){		
		Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);		
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);
	}
	
	//57. ->3 Añadiendo métodos en service y controlador para las respuestas del alumno
	// deben ir igual el argumento @PathVariable alumnoId =  {alumnoId}
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")	
	public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen(@PathVariable Long  alumnoId ,@PathVariable Long examenId){
		Iterable<Respuesta>respuestas = service.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return ResponseEntity.ok(respuestas);
	}

}
