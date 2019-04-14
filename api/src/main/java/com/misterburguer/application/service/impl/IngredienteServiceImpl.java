package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.BaseService;
import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.repository.IngredienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    public BigDecimal sumarizarValorIngredientes(final List<Ingrediente> pIngredientes) {

	if (CollectionUtils.isEmpty(pIngredientes)) {
	    return BigDecimal.ZERO;
	}

	Map<Ingrediente, Integer> mapIngredianteQuantidade = pIngredientes.stream().filter(Objects::nonNull)
			.collect(Collectors.toMap(key -> key, value -> 1));

	return sumarizarValorIngredientes(mapIngredianteQuantidade);
    }

    @Override
    public BigDecimal sumarizarValorIngredientes(final Map<Ingrediente, Integer> pQuantidadeIngredientes) {

	if (CollectionUtils.isEmpty(pQuantidadeIngredientes)) {
	    return BigDecimal.ZERO;
	}

	Function<Ingrediente, BigDecimal> funcaoSomaIngredientes = i -> {
	    Integer qtdPedida = pQuantidadeIngredientes.get(i);
	    if (qtdPedida == null || qtdPedida <= 0 || i.getValor() == null) {
		return BigDecimal.ZERO;
	    } else {
		return i.getValor().multiply(new BigDecimal(qtdPedida));
	    }
	};

	Optional<BigDecimal> valorTotal = pQuantidadeIngredientes.keySet().stream()
			.filter(Objects::nonNull)
			.map(funcaoSomaIngredientes)
			.reduce(BigDecimal::add);
	return valorTotal.orElse(BigDecimal.ZERO);
    }

    public Map<Ingrediente, Integer> transformarMapDeIdsParaMapIngredientes(Map<Long, Integer> pQuantidadeIngredientes) {

	List<Ingrediente> ingredientes = repository.findAllById(pQuantidadeIngredientes.keySet());

	return ingredientes.stream().filter(Objects::nonNull)
			.collect(Collectors.toMap(key -> key, key -> pQuantidadeIngredientes.get(key.getId())));
    }
}
