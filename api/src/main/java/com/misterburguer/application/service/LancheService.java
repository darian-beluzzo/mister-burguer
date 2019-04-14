package com.misterburguer.application.service;

import com.misterburguer.domain.CalculoLanche;
import com.misterburguer.domain.Lanche;

import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public interface LancheService extends IBaseService<Lanche, Long> {

    CalculoLanche calcularLanche(final Map<Long, Integer> pQuantidadeIngredientes);

}
