package com.ander.cursos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ander.commons.alumnos.models.entity.Alumno;
import com.ander.commons.examenes.models.entity.Examen;
import com.ander.commons.microservico.controller.Generic_Controller;
import com.ander.cursos.models.entity.Curso;
import com.ander.cursos.services.CursoService;

@RestController
public class CursoController extends Generic_Controller<Curso, CursoService>{

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso,BindingResult result, @PathVariable Long id){
		
		// (validar) metodo protected, tanto para Editar como para Crear POST y PUT
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
		dbCurso.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	// como es una lista -> List,  en el postam el formato json se envia con corchetes ejm: [{"id":"1"} ]
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
	   // List<Alumno> alumnos 
		alumnos.forEach(a -> {
			dbCurso.addAlumno(a);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
		dbCurso.removeAlumno(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	// 31-3
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarAlumnoID(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnId(id);
		return ResponseEntity.ok(curso);
	}
	
	
	//38. -> 5 Añadiendo relación entre cursos y exámenes metodos -> asignar-examenes && eliminar-examen
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
	   // List<Alumno> alumnos 
		examenes.forEach(e -> {
			dbCurso.addExamenes(e);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Curso> o = this.service.findById(id);
		if(!o.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
		dbCurso.removeExamene(examen);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	

	
}
