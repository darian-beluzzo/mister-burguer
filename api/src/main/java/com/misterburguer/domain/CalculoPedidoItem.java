package com.misterburguer.domain;

import com.misterburguer.infra.util.BigDecimalUtil;

import java.math.BigDecimal;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public class CalculoPedidoItem {

    private String nome;

    private String quantidadeDesconto;

    private BigDecimal valorDesconto;

    public CalculoPedidoItem(final String pNome, final BigDecimal pValorDesconto, final String pQuantidadeDesconto) {
	nome = pNome;
	valorDesconto = BigDecimalUtil.setDefaultScale(pValorDesconto);
	quantidadeDesconto = pQuantidadeDesconto;
    }

    public CalculoPedidoItem() {

    }

    public String getNome() {
	return nome;
    }

    public String getQuantidadeDesconto() {
	return quantidadeDesconto;
    }

    public BigDecimal getValorDesconto() {
	return valorDesconto;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setQuantidadeDesconto(final String pQuantidadeDesconto) {
	quantidadeDesconto = pQuantidadeDesconto;
    }

    public void setValorDesconto(final BigDecimal pValorDesconto) {
	valorDesconto = BigDecimalUtil.setDefaultScale(pValorDesconto);
    }
}
