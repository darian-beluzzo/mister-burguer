package com.misterburguer.application.service;

import com.misterburguer.domain.CalculoLanche;
import com.misterburguer.domain.Ingrediente;

import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 12/04/19
 */
public interface IPromocaoServiceRegistry {

    void addService(PromocaoService pService);

    void executarServicosPromocoes(final CalculoLanche pCalculoPromocao,
                    Map<Ingrediente, Integer> pQuantidadeIngredientes);

    void removeService(PromocaoService pService);
}
