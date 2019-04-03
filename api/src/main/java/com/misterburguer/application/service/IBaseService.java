package com.misterburguer.application.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 02/04/19
 */
public interface IBaseService<T, ID extends Serializable> {

    T create(T pEntity);

    void deleteById(ID pId);

    List<T> findAll();

    Optional<T> findById(ID id);

    T update(T pEntity);

}
