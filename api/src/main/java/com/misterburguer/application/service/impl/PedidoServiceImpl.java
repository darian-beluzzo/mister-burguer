package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.IngredienteService;
import com.misterburguer.application.service.PedidoService;
import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.domain.Ingrediente;
import com.misterburguer.infra.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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
    private PromocaoService promocaoService;

    @Override public BigDecimal calcularPedido(final Map<Long, Integer> pIngredientes) {
	List<Ingrediente> ingredientes = ingredienteService.findAllById(pIngredientes.keySet());

	BigDecimal valorIngredientes = ingredienteService.sumarizarValorIngredientes(ingredientes);
	promocaoService.calcularDescontoPromocao(ingredientes, pIngredientes);

	return null;
    }

    public BigDecimal enquadrarPromocao(final List<PedidoDTO> pIngredientes) {
	boolean temAlface = false;
	boolean temBacon = false;

	//	for (IngredienteDTO ingrediente : pIngredientes) {
	//	    temAlface &= "Alface".equals(ingrediente.getNome());
	//	    temBacon &= "Bacon".equals(ingrediente.getNome());
	//	}
	//	Map<String, List<IngredienteDTO>> ingredientesPorNome = pIngredientes.stream()
	//			.collect(Collectors.groupingBy(IngredienteDTO::getNome));

	return null;
    }
}
