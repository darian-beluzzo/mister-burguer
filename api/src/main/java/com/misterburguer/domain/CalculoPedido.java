package com.misterburguer.domain;

import com.misterburguer.infra.util.BigDecimalUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public class CalculoPedido {

    private List<CalculoPedidoItem> itens;

    private BigDecimal valorTotal;

    public CalculoPedido(final BigDecimal pValorTotal, final List<CalculoPedidoItem> pItens) {
	valorTotal = BigDecimalUtil.setDefaultScale(pValorTotal);
	itens = pItens;
    }

    public List<CalculoPedidoItem> getItens() {
	return itens;
    }

    public BigDecimal getValorTotal() {
	return valorTotal;
    }

    public void setItens(final List<CalculoPedidoItem> pItens) {
	itens = pItens;
    }

    public void setValorTotal(final BigDecimal pValorTotal) {
	valorTotal = pValorTotal;
    }
}
