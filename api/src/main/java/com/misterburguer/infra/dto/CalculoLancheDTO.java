package com.misterburguer.infra.dto;

import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public class CalculoLancheDTO {

    private List<CalculoPedidoItemDTO> itens;

    private String valorTotal;

    public List<CalculoPedidoItemDTO> getItens() {
	return itens;
    }

    public String getValorTotal() {
	return valorTotal;
    }

    public void setItens(final List<CalculoPedidoItemDTO> pItens) {
	itens = pItens;
    }

    public void setValorTotal(final String pValorTotal) {
	valorTotal = pValorTotal;
    }
}
