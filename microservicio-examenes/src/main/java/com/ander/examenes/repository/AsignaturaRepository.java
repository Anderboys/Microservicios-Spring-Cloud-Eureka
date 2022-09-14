package com.ander.examenes.repository;

import org.springframework.data.repository.CrudRepository;

import com.ander.commons.examenes.models.entity.Asignatura;

//41-2  crear new repository  : AsignaturaRepository + CrudRepository
public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {

}
