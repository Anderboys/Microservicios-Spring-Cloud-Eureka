package com.ander.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ander.model.entity.Alumno;
import com.ander.service.AlumnoService;

@RestController
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty()) {
		return	ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno  ){
		Alumno alumnodb = service.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnodb);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id){
		// Buscamos en la base de datos por el ID
		Optional<Alumno> o = service.findById(id);
		// validamos q exista
		if(o.isEmpty()) {
		return	ResponseEntity.notFound().build(); // notFound() 404
		}
		// Si existe lo obtenemos
		Alumno alumnodb = o.get();
		// cambiamos los datos con 'set' -  que vienen del -> @RequestBody Alumno
		alumnodb.setNombre(alumno.getNombre());
		alumnodb.setApellido(alumno.getApellido());
		alumno.setEmail(alumno.getEmail());
		// y guardamos los cambios
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnodb)); // CREATED 201
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminar( @PathVariable Long id){
		service.deleteByID(id);
		return ResponseEntity.noContent().build(); //204 http status
	}
	
	
	

}
