package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.IPromocaoServiceRegistry;
import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.CalculoPromocao;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.domain.builder.CalculoPedidoItemBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 12/04/19
 */
@Service
public class PromocaoServiceMaisCarne implements PromocaoService {

    @Autowired
    public PromocaoServiceMaisCarne(final IPromocaoServiceRegistry pRegistry) {
	pRegistry.addService(this);
    }

    @Override
    public int compareTo(final Object o) {
	return -1;
    }

    @Override
    public CalculoPromocao verificarPromocaoECalcular(final Map<Ingrediente, Integer> pQuantidadeIngredientes) {

	Optional<Ingrediente> carneOptional = pQuantidadeIngredientes.keySet().stream()
			.filter(i -> "Hamburger de carne".equals(i.getNome()))
			.findFirst();

	if (carneOptional.isPresent()) {

	    Ingrediente ingrediente = carneOptional.get();

	    Integer qtdPedida = pQuantidadeIngredientes.get(ingrediente);

	    if (qtdPedida >= 3) {
		// Calcula a quantidade a pagar de acordo com a quantidade e desconto.
		int qtdDescontar = qtdPedida / 3;

		return CalculoPedidoItemBuilder.newCalculoPedidoItem()
				.comNome("Muita carne")
				.comQuantidadeDesconto(qtdDescontar)
				.comValorIngrediente(ingrediente.getValor()).build();
	    }
	}
	return null;
    }
}
