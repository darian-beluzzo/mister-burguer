package com.misterburguer.application.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public abstract class BaseService<T, ID> implements IBaseService<T, ID> {

    protected Class<T> clazz;

    protected JpaRepository<T, ID> repository;

    public BaseService(Class<T> clazz, JpaRepository<T, ID> repository) {
	this.clazz = clazz;
	this.repository = repository;
    }

    public T create(T pEntity) {
	return repository.save(pEntity);
    }

    public void deleteById(ID pId) {
	repository.deleteById(pId);
    }

    public List<T> findAll() {
	return repository.findAll();
    }

    public Optional<T> findById(ID id) {
	return repository.findById(id);
    }

    public T update(T pEntity) {
	return repository.save(pEntity);
    }
}
