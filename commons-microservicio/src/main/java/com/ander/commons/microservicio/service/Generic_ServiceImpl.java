package com.ander.commons.microservicio.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

//public class Generic_ServiceImpl< E, R extends CrudRepository<E,Long> > implements Generic_Service<E> {
//46. Añadiendo paginación  -> extends PagingAndSortingRepository
public class Generic_ServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements Generic_Service<E> {

	@Autowired
	protected R repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	@Transactional
	public E save(E alumno) {
		// TODO Auto-generated method stub
		return repository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteByID(Long id) {
		repository.deleteById(id);
	}

	//46. -> 2 Añadiendo paginación
	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findAll(pageable);
	}

}
