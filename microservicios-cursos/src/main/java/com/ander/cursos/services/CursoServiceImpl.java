package com.ander.cursos.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ander.commons.microservicio.service.Generic_ServiceImpl;
import com.ander.cursos.models.entity.Curso;
import com.ander.cursos.repository.CursoRepository;

@Service
public class CursoServiceImpl extends Generic_ServiceImpl<Curso, CursoRepository> implements CursoService {

	//31-2
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnId(Long id) {
		return repository.findCursoByAlumnId(id);
	}


}
 