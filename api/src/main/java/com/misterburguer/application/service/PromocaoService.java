package com.misterburguer.application.service;

import com.misterburguer.domain.Promocao;

import java.util.List;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public interface PromocaoService {

    List<Promocao> calcularDescontoPromocao(Map<Long, Integer> pQuantidadeIngredientes);
}
