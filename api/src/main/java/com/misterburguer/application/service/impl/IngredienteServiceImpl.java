package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.BaseService;
import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Service
public class IngredienteServiceImpl extends BaseService<Ingrediente, Long> implements IngredienteService {

    public IngredienteServiceImpl(final Class<Ingrediente> clazz, final JpaRepository<Ingrediente, Long> repository) {
	super(clazz, repository);
    }
}
