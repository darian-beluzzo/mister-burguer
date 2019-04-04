package com.misterburguer.controller;

import com.misterburguer.application.service.IBaseService;
import com.misterburguer.domain.shared.IObjetoComId;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe abstrata com as implementações padrão de um resource CRUD.
 *
 * @param <T>  o tipo da entidade
 * @param <ID> o tipo do ID da entidade
 * @param <D> o tipo do DTO
 */
abstract class BaseResource<T extends IObjetoComId, ID, D extends IObjetoComId> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseResource.class);

    protected Class<D> dtoClass;

    protected Class<T> entityClass;

    @Autowired
    protected ModelMapper modelMapper;

    protected IBaseService<T, ID> service;

    BaseResource(IBaseService<T, ID> service, Class<T> entityClass, Class<D> dtoClass) {
	this.service = service;
	this.dtoClass = dtoClass;
	this.entityClass = entityClass;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable ID id) {
	LOGGER.info("Request to delete {}: {}", entityClass.getName(), id);
	service.deleteById(id);
	return ResponseEntity.ok().build();
    }

    protected D convertToDTO(T entity) {
	return modelMapper.map(entity, dtoClass);
    }

    protected T convertToEntity(D dto) {
	return modelMapper.map(dto, entityClass);
    }

    @PostMapping
    ResponseEntity<D> create(@Valid @RequestBody D dto, HttpServletRequest request) throws URISyntaxException {
	LOGGER.info("Request to create {}: {}", entityClass.getName(), dto);
	T entity;
	try {
	    entity = convertToEntity(dto);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(dto);
	}

	T result = service.create(entity);
	D resultDTO = convertToDTO(result);
	return ResponseEntity.created(new URI(request.getRequestURI() + resultDTO.getId()))
			.body(resultDTO);
    }

    @GetMapping
    Collection<D> findAll() {
	LOGGER.info("Request to findAll {}.", entityClass.getName());
	List<T> entities = service.findAll();
	return entities.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
    }

    @GetMapping("{id}")
    ResponseEntity<?> get(@PathVariable ID id) {
	LOGGER.info("Request to findById {}: {}.", entityClass.getName(), id);
	Optional<T> entity = service.findById(id);
	if (entity.isPresent()) {
	    return ResponseEntity.ok().body(convertToDTO(entity.get()));
	}
	return new ResponseEntity<D>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    ResponseEntity<D> update(@Valid @RequestBody D dto, HttpServletRequest request) throws URISyntaxException {
	LOGGER.info("Request to update {}: {}", entityClass.getName(), dto);
	T entity;
	try {
	    entity = convertToEntity(dto);
	} catch (RuntimeException e) {
	    return ResponseEntity.badRequest().body(dto);
	}

	T result = service.update(entity);
	D resultDTO = convertToDTO(result);
	return ResponseEntity.created(new URI(request.getRequestURI() + resultDTO.getId()))
			.body(resultDTO);
    }
}
