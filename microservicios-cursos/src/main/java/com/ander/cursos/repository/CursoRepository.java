package com.ander.cursos.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.ander.cursos.models.entity.Curso;

//public interface CursoRepository extends CrudRepository<Curso, Long> {
//46. Añadiendo paginación  -> extends PagingAndSortingRepository
public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {

	// 31. usando join para obtener el curso asignado al alumno
	@Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
	public Curso findCursoByAlumnId(Long id);

}
