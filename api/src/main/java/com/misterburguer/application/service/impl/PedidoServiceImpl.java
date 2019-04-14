package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.IPromocaoServiceRegistry;
import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.application.service.PedidoService;
import com.misterburguer.domain.CalculoLanche;
import com.misterburguer.domain.Ingrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 03/04/19
 */
@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private IPromocaoServiceRegistry promocaoServiceRegistry;

    @Override
    @Transactional
    public CalculoLanche calcularPedido(final Map<Long, Integer> pQuantidadeIngredientes) {

	final Map<Ingrediente, Integer> mapIngredienteQuantidade = ingredienteService
			.transformarMapDeIdsParaMapIngredientes(pQuantidadeIngredientes);

	BigDecimal valorTotal = ingredienteService.sumarizarValorIngredientes(mapIngredienteQuantidade);

	CalculoLanche calculoPedido = new CalculoLanche(valorTotal);
	promocaoServiceRegistry.executarServicosPromocoes(calculoPedido, mapIngredienteQuantidade);

	return calculoPedido;
    }
}
