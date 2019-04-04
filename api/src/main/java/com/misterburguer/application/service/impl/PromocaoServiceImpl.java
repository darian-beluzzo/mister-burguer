package com.misterburguer.application.service.impl;

import com.misterburguer.application.service.PromocaoService;
import com.misterburguer.infra.dto.PedidoDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 03/04/19
 */
@Service
public class PromocaoServiceImpl implements PromocaoService {

    @Override
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
