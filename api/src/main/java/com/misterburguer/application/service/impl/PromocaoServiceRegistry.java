package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.IPromocaoServiceRegistry;
import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.CalculoLanche;
import com.misterburguer.domain.CalculoPromocao;
import com.misterburguer.domain.Ingrediente;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 12/04/19
 */
@Component
public class PromocaoServiceRegistry implements IPromocaoServiceRegistry {

    /**
     * Serviços registrados.
     */
    private SortedSet<PromocaoService> services = new TreeSet<>();

    /**
     *
     */
    public void addService(final PromocaoService pService) {
	services.add(pService);
    }

    /**
     *
     */
    public void removeService(final PromocaoService pService) {
	if (services.contains(pService)) {
	    services.remove(pService);
	}
    }

    @Override
    public void executarServicosPromocoes(final CalculoLanche pCalculoPromocao,
		    final Map<Ingrediente, Integer> pQuantidadeIngredientes) {

	if (!CollectionUtils.isEmpty(services)) {

	    services.forEach(listener -> {

		final CalculoPromocao item = listener.verificarPromocaoECalcular(pQuantidadeIngredientes);
		if (item != null) {
		    pCalculoPromocao.addItemECalcularDesconto(item);
		}

	    });
	}
    }
}
