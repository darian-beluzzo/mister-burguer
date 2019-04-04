package com.misterburguer.application.service;

import com.misterburguer.domain.Ingrediente;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public interface IngredienteService extends IBaseService<Ingrediente, Long> {

    BigDecimal sumarizarValorIngredientes(List<Ingrediente> ingredientes);

    BigDecimal sumarizarValorIngredientesPorId(List<Long> idsIngredientes);
}
