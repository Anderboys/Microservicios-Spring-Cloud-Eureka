package com.ander.commons.microservico.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

import com.ander.commons.microservicio.service.Generic_Service;

public class Generic_Controller<E, S extends Generic_Service<E>> {

	@Autowired
	protected S service;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(service.findAll());
	}

	//46. -> 3 Añadiendo paginación
	@GetMapping("/pagina")
	public ResponseEntity<?> listar(Pageable pageable) {
		return ResponseEntity.ok().body(service.findAll(pageable));
	}	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {
		Optional<E> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}

	// 44. -> 1 Añadiendo reglas de validaciones en los entity -> @Validated -> activar
	// validacion @Valid -> javax.validation pom.xml
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody E entinity, BindingResult result) {
		
		//44 -2 (validar) metodo protected, tanto para Editar como para Crear POST y PUT
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		E entityDb = service.save(entinity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		service.deleteByID(id);
		return ResponseEntity.noContent().build(); // 204 http status
	}

	
	// 44 -> 2
	protected ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

}
