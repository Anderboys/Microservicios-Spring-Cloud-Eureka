package com.ander.microservicio.respuestas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ander.microservicio.respuestas.model.entity.Respuesta;
import com.ander.microservicio.respuestas.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService {

	// 55 -> 2. Añadiendo componentes service y controller
	@Autowired
	private RespuestaRepository repository;
	
	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
	
		return repository.saveAll(respuestas);
	}

	//57. ->2  Añadiendo métodos en service y controlador para las respuestas del alumno
	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

}
