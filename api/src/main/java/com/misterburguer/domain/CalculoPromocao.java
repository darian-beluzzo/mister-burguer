package com.misterburguer.domain;

import java.math.BigDecimal;

/**
 * @author darian.beluzzo
 * @version 1.0
 * @since 30/03/19
 */
public class CalculoPromocao {

    private String nome;

    private BigDecimal percentualDesconto;

    private Integer quantidadeDesconto;

    private BigDecimal valorDesconto;

    private BigDecimal valorIngrediente;

    public CalculoPromocao() {
    }

    public String getNome() {
	return nome;
    }

    public BigDecimal getPercentualDesconto() {
	return percentualDesconto;
    }

    public Integer getQuantidadeDesconto() {
	return quantidadeDesconto;
    }

    public BigDecimal getValorDesconto() {
	return valorDesconto;
    }

    public BigDecimal getValorIngrediente() {
	return valorIngrediente;
    }

    public boolean isDescontoPercentual() {
	return percentualDesconto != null;
    }

    public void setNome(final String pNome) {
	nome = pNome;
    }

    public void setPercentualDesconto(final BigDecimal pPercentualDesconto) {
	if (quantidadeDesconto != null) {
	    throw new IllegalStateException("Não é possível aplicar dois tipos de desconto para o mesmo item.");
	}
	percentualDesconto = pPercentualDesconto;
    }

    public void setQuantidadeDesconto(final Integer pQuantidadeDesconto) {
	if (percentualDesconto != null) {
	    throw new IllegalStateException("Não é possível aplicar dois tipos de desconto para o mesmo item.");
	}
	quantidadeDesconto = pQuantidadeDesconto;
    }

    public void setValorDesconto(final BigDecimal pValorDesconto) {
	valorDesconto = pValorDesconto;
    }

    public void setValorIngrediente(final BigDecimal pValorIngrediente) {
	valorIngrediente = pValorIngrediente;
    }
}
