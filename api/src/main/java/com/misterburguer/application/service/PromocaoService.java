package com.misterburguer.application.service;

import com.misterburguer.infra.dto.PedidoDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public interface PromocaoService {

    BigDecimal enquadrarPromocao(List<PedidoDTO> pIngredientes);
}
