package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.BaseService;
import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Service
public class IngredienteServiceImpl extends BaseService<Ingrediente, Long> implements IngredienteService {

    public IngredienteServiceImpl(final IngredienteRepository repository) {
	super(Ingrediente.class, repository);
    }
}
