package com.ander.commons.microservicio.service;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Generic_Service<E> {
	
	public Iterable<E> findAll();
	
	//46. -> 1 Añadiendo paginación
	// para que sea paginable
	public Page<E> findAll(Pageable pageable);
	
	// Optional -> java 8
	public Optional<E> findById(Long id);
	public E save(E entity);
	public void deleteByID(Long id);
	

}
