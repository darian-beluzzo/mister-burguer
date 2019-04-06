package com.misterburguer.application.service;

import com.misterburguer.infra.dto.PedidoDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public interface PedidoService {

    BigDecimal calcularPedido(Map<Long, Integer> pIngredientes);
}
