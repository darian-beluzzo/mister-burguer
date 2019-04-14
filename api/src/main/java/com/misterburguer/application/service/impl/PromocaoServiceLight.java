package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.IPromocaoServiceRegistry;
import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.CalculoPromocao;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.builder.CalculoPedidoItemBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 12/04/19
 */
@Service
public class PromocaoServiceLight implements PromocaoService {

    @Autowired
    public PromocaoServiceLight(final IPromocaoServiceRegistry pRegistry) {
	pRegistry.addService(this);
    }

    @Override
    public int compareTo(final Object o) {
	return 1;
    }

    @Override
    public CalculoPromocao verificarPromocaoECalcular(final Map<Ingrediente, Integer> pQuantidadeIngredientes) {

	Optional<Ingrediente> alfaceOptional = pQuantidadeIngredientes.keySet().stream().filter(i -> "Alface".equals(i.getNome()))
			.findFirst();
	Optional<Ingrediente> baconOptional = pQuantidadeIngredientes.keySet().stream().filter(i -> "Bacon".equals(i.getNome()))
			.findFirst();

	if (alfaceOptional.isPresent() && !baconOptional.isPresent()) {

	    BigDecimal desconto = BigDecimal.TEN;

	    return CalculoPedidoItemBuilder.newCalculoPedidoItem()
			    .comNome("Light")
			    .comPercentualDesconto(desconto).build();
	}

	return null;

    }
}
