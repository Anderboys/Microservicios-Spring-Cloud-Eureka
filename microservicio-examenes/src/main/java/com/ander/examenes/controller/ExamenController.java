package com.ander.examenes.controller;
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

import com.ander.commons.examenes.models.entity.Examen;
import com.ander.commons.microservico.controller.Generic_Controller;
import com.ander.examenes.service.ExamenService;

@RestController
public class ExamenController extends Generic_Controller<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar (@Valid @RequestBody Examen examen,BindingResult result, @PathVariable Long id){
		
		// (validar) metodo protected, tanto para Editar como para Crear POST y PUT
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		
		Optional<Examen> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build(); // 404 no found
		}
		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());		
		
//		List<Pregunta> eliminadas = new ArrayList<>();
//		examenDb.getPreguntas().forEach(pdb ->{
//			if(!examen.getPreguntas().contains(pdb)) {
//				eliminadas.add(pdb);
//			}
//		});
		// optimizar api stream de java 8		
		examenDb.getPreguntas()
		.stream() // stream de java 8
		.filter(pdb -> !examen.getPreguntas().contains(pdb))
		.forEach(examenDb::removePregunta);
			
//		eliminadas.forEach(p -> {
//			examenDb.removePregunta(p);
//		});		
		// eliminadas.forEach(examenDb::removePregunta);		
		examenDb.setPreguntas(examen.getPreguntas());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	//39 -6  buscar nombre like
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	//41 -> #.5   Como es Solo listar se implementa aqui, porq se podria crear otra clase con el nombre AsignaturasController.java
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
	
}
