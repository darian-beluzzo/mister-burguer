package com.misterburguer.application.service;

import com.misterburguer.domain.CalculoPedido;

import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public interface PedidoService {

    CalculoPedido calcularPedido(Map<Long, Integer> pIngredientes);
}
