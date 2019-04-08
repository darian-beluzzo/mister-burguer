package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.BaseService;
import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	Map<Long, Integer> mapIngredianteQuantidade = ingredientes.stream().filter(Objects::nonNull)
			.collect(Collectors.toMap(Ingrediente::getId, v -> 1));

	return sumarizarValorIngredientes(mapIngredianteQuantidade);
    }

    @Override
    public BigDecimal sumarizarValorIngredientes(final Map<Long, Integer> pQuantidadeIngredientes) {

	if (pQuantidadeIngredientes == null || pQuantidadeIngredientes.isEmpty()) {
	    return BigDecimal.ZERO;
	}

	List<Ingrediente> ingredientes = findAllById(pQuantidadeIngredientes.keySet());

	Function<Ingrediente, BigDecimal> funcaoSomaIngredientes = i -> {
	    Integer qtdPedida = pQuantidadeIngredientes.get(i.getId());
	    if (qtdPedida == null || qtdPedida <= 0) {
		return BigDecimal.ZERO;
	    } else {
		return i.getValor().multiply(new BigDecimal(qtdPedida));
	    }
	};

	Optional<BigDecimal> valorTotal = ingredientes.stream().map(funcaoSomaIngredientes).reduce(BigDecimal::add);
	return valorTotal.orElse(BigDecimal.ZERO);
    }
}
