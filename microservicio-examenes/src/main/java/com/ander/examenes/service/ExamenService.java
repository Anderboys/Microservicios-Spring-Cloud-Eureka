package com.ander.examenes.service;

import java.util.List;

import com.ander.commons.examenes.models.entity.Asignatura;
import com.ander.commons.examenes.models.entity.Examen;
import com.ander.commons.microservicio.service.Generic_Service;

public interface ExamenService extends Generic_Service<Examen> {
	//39.-4
	public List<Examen> findByNombre(String term);
	
	//41-3
	public Iterable<Asignatura>findAllAsignaturas();
}
