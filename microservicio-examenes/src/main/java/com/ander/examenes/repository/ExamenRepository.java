package com.ander.examenes.repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ander.commons.examenes.models.entity.Examen;

//public interface ExamenRepository extends CrudRepository<Examen, Long> {
//46. Añadiendo paginación  -> extends PagingAndSortingRepository
public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long> {

	// 39.-3 Buscar exámenes usando operador like
	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String term);

}
