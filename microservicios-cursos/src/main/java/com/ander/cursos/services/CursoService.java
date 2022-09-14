package com.ander.cursos.services;

import com.ander.commons.microservicio.service.Generic_Service;
import com.ander.cursos.models.entity.Curso;

public interface CursoService extends Generic_Service<Curso> {
   //31-1
	public Curso findCursoByAlumnId(Long id);
}
