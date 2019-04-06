package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.BaseService;
import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
@Service
public class IngredienteServiceImpl extends BaseService<Ingrediente, Long> implements IngredienteService {

    private IngredienteRepository repository;

    public IngredienteServiceImpl(final IngredienteRepository repository) {
	super(Ingrediente.class, repository);
	this.repository = repository;
    }

    @Override
    public List<Ingrediente> findAllById(final Set<Long> ingredientes) {
	return repository.findAllById(ingredientes);
    }

    public BigDecimal sumarizarValorIngredientes(final List<Ingrediente> ingredientes) {
	if (ingredientes == null || ingredientes.size() == 0) {
	    return BigDecimal.ZERO;
	}
	return ingredientes.stream().map(Ingrediente::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
