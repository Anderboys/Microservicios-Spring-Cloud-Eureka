package com.ander.usuario.service;

import java.util.List;

import com.ander.commons.alumnos.models.entity.Alumno;
import com.ander.commons.microservicio.service.Generic_Service;

public interface AlumnoService extends Generic_Service<Alumno>{
	
	public List<Alumno> finByNombreOrApellido(String term);

}
