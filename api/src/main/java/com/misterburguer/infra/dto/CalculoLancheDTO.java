package com.misterburguer.infra.dto;

import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public class CalculoLancheDTO {

    private List<CalculoLancheItemDTO> itens;

    private String valorTotal;

    public List<CalculoLancheItemDTO> getItens() {
	return itens;
    }

    public String getValorTotal() {
	return valorTotal;
    }

    public void setItens(final List<CalculoLancheItemDTO> pItens) {
	itens = pItens;
    }

    public void setValorTotal(final String pValorTotal) {
	valorTotal = pValorTotal;
    }
}
