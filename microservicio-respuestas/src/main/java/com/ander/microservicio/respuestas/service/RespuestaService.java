package com.ander.microservicio.respuestas.service;

import com.ander.microservicio.respuestas.model.entity.Respuesta;

public interface RespuestaService {

	//55 -> 1. Añadiendo componentes service y controller
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	
	//57. -> 1 Añadiendo métodos en service y controlador para las respuestas del alumno
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen (Long alumnoId,Long examenId);
	
}
