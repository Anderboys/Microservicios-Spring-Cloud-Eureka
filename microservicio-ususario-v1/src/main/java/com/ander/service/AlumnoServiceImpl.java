package com.ander.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ander.model.entity.Alumno;
import com.ander.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	
	@Autowired
	private AlumnoRepository repo_alumno;
	

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		// TODO Auto-generated method stub
		return repo_alumno.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		// TODO Auto-generated method stub
		return repo_alumno.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		// TODO Auto-generated method stub
		return repo_alumno.save(alumno);
	}

	@Override
	@Transactional
	public void deleteByID(Long id) {
		repo_alumno.deleteById(id);	
	}

}
