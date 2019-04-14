package com.misterburguer.domain.builder;

import com.misterburguer.domain.CalculoPromocao;

import java.math.BigDecimal;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 13/04/19
 */
public class CalculoPedidoItemBuilder {

    private CalculoPromocao calculoPedidoItem;

    CalculoPedidoItemBuilder() {
	calculoPedidoItem = new CalculoPromocao();
    }

    public static CalculoPedidoItemBuilder newCalculoPedidoItem() {
	return new CalculoPedidoItemBuilder();
    }

    public CalculoPromocao build() {
	return calculoPedidoItem;
    }

    public CalculoPedidoItemBuilder comNome(final String pNome) {
	calculoPedidoItem.setNome(pNome);
	return this;
    }

    public CalculoPedidoItemBuilder comPercentualDesconto(final BigDecimal pQuantidadeDesconto) {
	calculoPedidoItem.setPercentualDesconto(pQuantidadeDesconto);
	return this;
    }

    public CalculoPedidoItemBuilder comQuantidadeDesconto(final Integer pQuantidadeDesconto) {
	calculoPedidoItem.setQuantidadeDesconto(pQuantidadeDesconto);
	return this;
    }

    public CalculoPedidoItemBuilder comValorIngrediente(final BigDecimal pValorIngrediente) {
	calculoPedidoItem.setValorIngrediente(pValorIngrediente);
	return this;
    }
}
