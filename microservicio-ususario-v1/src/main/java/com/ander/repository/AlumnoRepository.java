package com.ander.repository;

import org.springframework.data.repository.CrudRepository;

import com.ander.model.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
