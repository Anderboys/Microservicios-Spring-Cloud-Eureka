package com.ander.microservicio.respuestas.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ander.microservicio.respuestas.model.entity.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {

	//56. Escribiendo consulta JPQL con join para las respuestas del alumno por examen
	// Son CONSULTAS A NIVEL DE OBJETOS - de clase entity(@Entity) ,  y NO DE TABLAS
	// select r 'seria un alias from clase entity'
	// r.alumno -> es a atributos
	// fetch: en una sola consulta -> trae todo los Objetos relacionados: alumno & respúesta
	@Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen (Long alumnoId,Long examenId);
}
