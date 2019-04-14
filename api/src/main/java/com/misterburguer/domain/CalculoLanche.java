package com.misterburguer.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public class CalculoLanche {

    private List<CalculoPromocao> itens = new ArrayList<>();

    private BigDecimal valorTotal;

    public CalculoLanche() {
    }

    public CalculoLanche(final BigDecimal pValorTotal) {
	valorTotal = pValorTotal;
    }

    public void addItemECalcularDesconto(final CalculoPromocao pCalculoPromocaoItem) {
	if (pCalculoPromocaoItem == null) {
	    return;
	}
	itens.add(pCalculoPromocaoItem);

	calcularEAplicarDesconto(pCalculoPromocaoItem);
    }

    public List<CalculoPromocao> getItens() {
	return itens;
    }

    public BigDecimal getValorTotal() {
	return valorTotal;
    }

    public void setItens(final List<CalculoPromocao> pItens) {
	itens = pItens;
    }

    public void setValorTotal(final BigDecimal pValorTotal) {
	valorTotal = pValorTotal;
    }

    private void calcularEAplicarDesconto(final CalculoPromocao pCalculoPromocaoItem) {
	BigDecimal valorDesconto;

	if (pCalculoPromocaoItem.isDescontoPercentual()) {
	    valorDesconto = valorTotal.multiply(pCalculoPromocaoItem.getPercentualDesconto())
			    .divide(new BigDecimal(100), RoundingMode.HALF_UP);
	} else {
	    BigDecimal qtdDesconto = new BigDecimal(pCalculoPromocaoItem.getQuantidadeDesconto());
	    valorDesconto = pCalculoPromocaoItem.getValorIngrediente().multiply(qtdDesconto);
	}

	pCalculoPromocaoItem.setValorDesconto(valorDesconto);

	if (valorDesconto != null) {
	    valorTotal = valorTotal.subtract(valorDesconto);
	}
    }
}
