package com.ander.usuario.controller;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ander.commons.alumnos.models.entity.Alumno;
import com.ander.commons.microservico.controller.Generic_Controller;
import com.ander.usuario.service.AlumnoService;

@RestController
public class AlumnoController extends Generic_Controller<Alumno, AlumnoService> {
	
	// 51. Añadiendo método handler del controlador para ver la imagen del alumno
	@GetMapping("/upload/img/{id}")
	public ResponseEntity<?> verAlumno (@PathVariable Long id){
		// obtener alumno y validar q no sea vacio
		Optional<Alumno> o = service.findById(id);
		// validamos q exista
		if (o.isEmpty() || o.get().getFoto() == null ) {
			return ResponseEntity.notFound().build(); // notFound() 404
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		return ResponseEntity.ok()
			   .contentType(MediaType.IMAGE_JPEG)
			   .body(imagen);
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {

		// (validar) metodo protected, tanto para Editar como para Crear POST y PUT
		if (result.hasErrors()) {
			return this.validar(result);
		}

		// Buscamos en la base de datos por el ID
		Optional<Alumno> o = service.findById(id);
		// validamos q exista
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build(); // notFound() 404
		}
		// Si existe lo obtenemos
		Alumno alumnodb = o.get();
		// cambiamos los datos con 'set' - que vienen del -> @RequestBody Alumno
		alumnodb.setNombre(alumno.getNombre());
		alumnodb.setApellido(alumno.getApellido());
		alumnodb.setEmail(alumno.getEmail());
		// y guardamos los cambios
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnodb)); // CREATED 201
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term) {
		return ResponseEntity.ok(service.finByNombreOrApellido(term));
	}

	
	// 49. Añadiendo métodos handlers con soporte multipartfile para post y put
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConfoto(@Valid Alumno alumno, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {
		if (!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.crear(alumno, result);
	}

	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid  Alumno alumno, BindingResult result,
			@PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException {

		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Alumno> o = service.findById(id);

		if (o.isEmpty()) {
			return ResponseEntity.notFound().build(); // notFound() 404
		}
		Alumno alumnodb = o.get();
		alumnodb.setNombre(alumno.getNombre());
		alumnodb.setApellido(alumno.getApellido());
		alumnodb.setEmail(alumno.getEmail());

		if (!archivo.isEmpty()) {
			alumnodb.setFoto(archivo.getBytes());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnodb)); // CREATED 201
	}

}
