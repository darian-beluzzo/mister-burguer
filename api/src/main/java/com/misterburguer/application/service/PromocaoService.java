package com.misterburguer.application.service;

import com.misterburguer.domain.Ingrediente;

import java.util.List;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public interface PromocaoService {

    void calcularDescontoPromocao(List<Ingrediente> pIngredientes, Map<Long, Integer> pQuantidadeIngredientes);
}
