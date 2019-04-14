package com.misterburguer.application.service;

import com.misterburguer.domain.CalculoPromocao;
import com.misterburguer.domain.Ingrediente;

import java.util.Map;

/**
 * Serviço responsável por verifica o enquadramento das promoções e calcular o desconto.
 * Implementa Comparable pois o cálculo do desconto percentual deve ser aplicado primeiro.
 *
 * @author darian.beluzzo
 * @version 1.0
 * @since 12/04/19
 */
public interface PromocaoService extends Comparable {

    CalculoPromocao verificarPromocaoECalcular(final Map<Ingrediente, Integer> pQuantidadeIngredientes);
}
