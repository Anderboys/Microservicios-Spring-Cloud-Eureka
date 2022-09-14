package com.ander.service;

import java.util.Optional;

import com.ander.model.entity.Alumno;

public interface AlumnoService {
	
	public Iterable<Alumno> findAll();
	// Optional -> java 8
	public Optional<Alumno> findById(Long id);
	public Alumno save(Alumno alumno);
	public void deleteByID(Long id);
	

}
