package com.ander.usuario.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ander.commons.alumnos.models.entity.Alumno;
import com.ander.commons.microservicio.service.Generic_ServiceImpl;
import com.ander.usuario.repository.AlumnoRepository;

@Service
// PARA REUTILIZAR TODO EL METODO CRUD -> CommonServiceImpl
public class AlumnoServiceImpl extends Generic_ServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	 // consulta  -> readOnly
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> finByNombreOrApellido(String term) {
		return repository.finByNombreOrApellido(term);
	}
	
	


}
