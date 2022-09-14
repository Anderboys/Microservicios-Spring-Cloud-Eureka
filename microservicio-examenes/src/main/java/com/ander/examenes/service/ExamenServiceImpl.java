package com.ander.examenes.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ander.commons.examenes.models.entity.Asignatura;
import com.ander.commons.examenes.models.entity.Examen;
import com.ander.commons.microservicio.service.Generic_ServiceImpl;
import com.ander.examenes.repository.AsignaturaRepository;
import com.ander.examenes.repository.ExamenRepository;

@Service
public class ExamenServiceImpl extends Generic_ServiceImpl<Examen, ExamenRepository> implements ExamenService{
	
	//41 -4.1 inyectar
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	//39.-5 inmplementar metodos
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {		
		return repository.findByNombre(term);
	}

	
	//41 -4.2
	@Override
	@Transactional(readOnly = true)
	public Iterable<Asignatura> findAllAsignaturas() {		
		return asignaturaRepository.findAll();
	}
	
}
