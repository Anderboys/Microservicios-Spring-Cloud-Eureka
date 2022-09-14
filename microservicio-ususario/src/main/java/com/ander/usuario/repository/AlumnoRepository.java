package com.ander.usuario.repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ander.commons.alumnos.models.entity.Alumno;


//public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
//46. Añadiendo paginación  -> extends PagingAndSortingRepository
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {
	
	// no es consulta sql nativa
	// consulta de hibernate O jpaQueryLanguage = hql o jpaql
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	public List<Alumno> finByNombreOrApellido(String term);
		
	

}
